package com.hespera.extraction;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.collect.Lists;
import com.hespera.extraction.geo.ConcertGeo;
import com.hespera.extraction.model.Event;

public class EventfulCom extends Scraper {

	private static final Pattern entryPattern = Pattern.compile("<td class=\"event-info\">(.+?)</td>");
	private static final Pattern titlePattern = Pattern.compile("<a href=\"http://eventful.com/(.+?)\" class=\"summary url\" data-ga-label=\"Browse Result Title Link\" title=\"(.+?)\">(.+?)</a>");
	private static final Pattern datePattern = Pattern.compile("<span class=\"value-title\" title=\"\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\"></span>");
	private static final Pattern locationPattern = Pattern.compile("<p class=\"place location\">(.+?)</p>");
	
	private static List<Event> events = new ArrayList<Event>();
	
	public static void main(String[] args) throws Exception {
	
		URL[] urls = {
			new URL("http://eventful.com/sanfrancisco/events/categories/music/this-week"),
			new URL("http://eventful.com/sanfrancisco/events/categories/music/this-week?page_number=2"),
			new URL("http://eventful.com/sanfrancisco/events/categories/music/this-week?page_number=3"),
			new URL("http://eventful.com/sanfrancisco/events/categories/music/this-week?page_number=4"),
			new URL("http://eventful.com/sanfrancisco/events/categories/music/this-week?page_number=5")
		};
		
		
		for(URL url : urls) {
			scrape(url);
		}
		
		Loader.loadAll(events);
		for(Event event : events) {
			System.out.println(event);
		}
		
	}
	
	public static void scrape(URL url) {
		try {
			String content = read(url);
			
			Matcher entryMatcher = entryPattern.matcher(content);
	    	while(entryMatcher.find()) {
	    		String entry = content.substring(entryMatcher.start(), entryMatcher.end()); 
	    		
	    		String title = null;
	    		Matcher titleMatcher = titlePattern.matcher(entry);
	    		if(titleMatcher.find()) {
	    			title =clean(entry.substring(titleMatcher.start(), titleMatcher.end()));
	    		}
	    		
	    		String date = null;
	    		Matcher dateMatcher = datePattern.matcher(entry);
	    		if(dateMatcher.find()) {
	    			date = entry.substring(dateMatcher.start(), dateMatcher.end());
	    			date = date.substring(date.indexOf("title=\"") + 7, date.indexOf("\"></span>"));
	    		}
	    		
	    		String location = null;
	    		Matcher locationMatcher = locationPattern.matcher(entry);
	    		if(locationMatcher.find()) {
	    			location = clean(entry.substring(locationMatcher.start(), locationMatcher.end()));
	    		}
	    		
	    		Date dateTime = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss").parse(date);
        		
	    		try {
	    			ConcertGeo concertGeo = ConcertGeo.valueOf(location.toUpperCase().replace("-", "").replaceAll(" +", "_"));
	    			Event event = new Event(
    					UUID.randomUUID(), 
    					title, dateTime, 
    					dateTime, 
    					concertGeo.longitude, 
    					concertGeo.latitude,
    					Lists.newArrayList("concert")
    				);
		    		events.add(event);
	    		}
	    		catch(IllegalArgumentException e) {
	    			e.printStackTrace();
	    		}
	    	}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
