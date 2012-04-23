package com.hespera.scraper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hespera.scraper.model.Event;

public class EspnGoCom {
	public static void main(String[] args) throws Exception {
		List<Event> events = new ArrayList<Event>();
		
		URI uri = new URI("http://espn.go.com/mlb/schedule");
		
		Pattern tablePattern = Pattern.compile("<table class=\"tablehead\" cellpadding=\"3\" cellspacing=\"1\"><tr class=\"stathead\">(.+?)</table>");
		Pattern entryPattern = Pattern.compile("<td>(<a href=\"/mlb/preview\\?gameId=\\d+\">)?[a-zA-Z0-9 ]+(</a>)?</td><td align=\"right\">[a-zA-Z0-9 :]+</td>");
		Pattern datePattern = Pattern.compile("<a name=\"\\d{8}\">");
		
		BufferedReader in = new BufferedReader(new InputStreamReader(uri.toURL().openStream()));
		String inputLine;
        while((inputLine = in.readLine()) != null) {
        	
        	Matcher tableMatcher = tablePattern.matcher(inputLine);
        	while(tableMatcher.find()) {
        		String table = inputLine.substring(tableMatcher.start(), tableMatcher.end()); 
        		
        		Date date = null;
        		Matcher dateMatcher = datePattern.matcher(table);
	      		if(dateMatcher.find()) {
	      			date = new SimpleDateFormat("yyyyMMdd").parse(table.substring(dateMatcher.start(), dateMatcher.end()).replaceAll("\\D", ""));
        		}
        		
        		Matcher entryMatcher = entryPattern.matcher(table);
        		while(entryMatcher.find()) {
        			
        			String[] values = table.substring(entryMatcher.start(), entryMatcher.end()).split("</td>");
            		
            		String title = clean(values[0]);
            		String time = clean(values[1]);
            		Ballpark ballpark = TeamMapper.valueOf(title.substring(title.indexOf("at ") + 3).toUpperCase().replace(" ", "_")).team();
            		
            		Event event = new Event(
            			UUID.randomUUID(),
            			title,
            			date,
            			date,
            			ballpark.longitude(),
            			ballpark.latitude()		
    				);
            		events.add(event);
            	}
        	}
        }
        in.close();
        Loader.loadAll(events);
	}
	
	private static String clean(String in) {
		return in.replaceAll("<[^>]*>", "");
	}

}
