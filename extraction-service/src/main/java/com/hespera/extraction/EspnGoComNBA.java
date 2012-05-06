package com.hespera.extraction;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.collect.Lists;
import com.hespera.extraction.geo.BasketballGeo;
import com.hespera.extraction.model.Event;

public class EspnGoComNBA extends Scraper {

	private static final String WEBSITE = "http://espn.go.com/nba/schedule";

	private static final Pattern tablePattern = Pattern.compile("<table cellpadding=\"3\" cellspacing=\"1\" class=\"tablehead\"><tr class=\"stathead\">(.+?)</table>");

	private static final Pattern entryPattern = Pattern.compile("<td><a href=\"http://espn.go.com/nba/team/(.+?)\">[a-zA-z ]+</a> at <a href=\"http://espn.go.com/nba/team/(.+?)\">[a-zA-z ]+</a>(.+?)<a href=\"http://espn.go.com/nba/(.+?)\" >(.+?)</a></td><td align=\"right\">(<a href=\"(.+?)\">)?[0-9:AMPTBD ]+(</a>)?</td>");
	private static final Pattern datePattern = Pattern.compile("<td colspan=\"10\">[a-zA-z]+, [a-zA-z]+ \\d{1,2}</td>");
	private static final Pattern titlePattern = Pattern.compile("<a href=\"(.+?)\">[a-zA-Z\\. ]+</a> at <a href=\"(.+?)\">[a-zA-Z\\. ]+</a>");
	private static final Pattern timePattern = Pattern.compile("\\d{1,2}:\\d{2} [AP]M");
	
	public static void main(String[] args) {
		try {
			List<Event> events = new ArrayList<Event>();
			
			URI uri = new URI(WEBSITE);
			String content = read(uri);
	
	    	Matcher tableMatcher = tablePattern.matcher(content);
	    	while(tableMatcher.find()) {
	    		String table = content.substring(tableMatcher.start(), tableMatcher.end());

	    		// Extract date from table header
	    		String date = null;
	    		Matcher dateMatcher = datePattern.matcher(table);
	      		if(dateMatcher.find()) {
	      			date = clean(table.substring(dateMatcher.start(), dateMatcher.end()));
	      			date = date.substring(date.indexOf(", ") + 2);
	      			date = date + " " + new GregorianCalendar().get(GregorianCalendar.YEAR);
	    		}
	    		
	      		// Extract entries from table
	    		Matcher entryMatcher = entryPattern.matcher(table);
	    		while(entryMatcher.find()) {
	    			String entry = table.substring(entryMatcher.start(), entryMatcher.end());

	    			// Extract title from entry
	    			String title = null;
	    			Matcher titleMatcher = titlePattern.matcher(entry);
		      		if(titleMatcher.find()) {
		      			title = clean(entry.substring(titleMatcher.start(), titleMatcher.end()));
                        title = title.replace("Tickets| Venue&nbsp;", "");
	        		}
		      		
		      		// Extract time from entry
	    			String time = null;
	    			Matcher timeMatcher = timePattern.matcher(entry);
		      		if(timeMatcher.find()) {
		      			time = entry.substring(timeMatcher.start(), timeMatcher.end());
	        		}
		      		
		      		try {
		    			// Determine location based on enum mappings
		        		BasketballGeo basketballGeo = BasketballMapper.valueOf(title.substring(title.indexOf("at ") + 3).toUpperCase().replace(" ", "_").replace(".", "")).team;
		        		Date dateTime = new SimpleDateFormat("MMM d yyyy h:mm a z").parse(date + " " + time + " EDT");
		        		
		        		Event event = new Event(
		        			UUID.randomUUID(),
		        			title,
		        			dateTime,
		        			dateTime,
		        			basketballGeo.longitude,
		        			basketballGeo.latitude,
		        			Lists.newArrayList("sports", "basketball")
						);
		        		events.add(event);
		      		} catch (ParseException e) {
		      			//Can't parse time
		      		}
	        	}
	    	}
		
	        //Loader.loadAll(events);
			for(Event event : events) {
				System.out.println(event);
			}
			System.out.println("NBA Games: " + events.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
