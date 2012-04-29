package com.hespera.extraction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.collect.Lists;
import com.hespera.extraction.geo.BaseballGeo;
import com.hespera.extraction.model.Event;

public class EspnGoComMLB {
	
	private static final String WEBSITE = "http://espn.go.com/mlb/schedule";
	
	private static final Pattern tablePattern = Pattern.compile("<table class=\"tablehead\" cellpadding=\"3\" cellspacing=\"1\"><tr class=\"stathead\">(.+?)</table>");
	private static final Pattern entryPattern = Pattern.compile("<td>(<a href=\"/mlb/preview\\?gameId=\\d+\">)?[a-zA-Z\\. ]+(</a>)?</td><td align=\"right\">[a-zA-Z0-9 :]+</td>");
	private static final Pattern datePattern = Pattern.compile("<a name=\"\\d{8}\">");
	private static final Pattern titlePattern = Pattern.compile("<td>(<a href=\"/mlb/preview\\?gameId=\\d+\">)?([a-zA-Z\\. ]+?)at([a-zA-Z\\. ]+?)(</a>)?</td>");
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
	      			date = table.substring(dateMatcher.start(), dateMatcher.end()).replaceAll("\\D", "");
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
	        		}
		      		
		      		// Extract time from entry
	    			String time = null;
	    			Matcher timeMatcher = timePattern.matcher(entry);
		      		if(timeMatcher.find()) {
		      			time = entry.substring(timeMatcher.start(), timeMatcher.end());
	        		}
		      		
	    			// Determine location based on enum mappings
		      		BaseballGeo baseballGeo = BaseballMapper.valueOf(title.substring(title.indexOf("at ") + 3).toUpperCase().replace(" ", "_").replace(".", "")).team;
	        		Date dateTime = new SimpleDateFormat("yyyyMMdd h:mm a z").parse(date + " " + time + " EDT");
	        		
	        		Event event = new Event(
	        			UUID.randomUUID(),
	        			title,
	        			dateTime,
	        			dateTime,
	        			baseballGeo.longitude,
	        			baseballGeo.latitude,
	        			Lists.newArrayList("sports", "baseball")
					);
	        		events.add(event);
	        	}
	    	}
		
	        Loader.loadAll(events);
			for(Event event : events) {
				System.out.println(event);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static String clean(String in) {
		return in.replaceAll("<[^>]*>", "");
	}
	
	private static String read(URI uri) throws Exception {
		StringBuilder builder = new StringBuilder();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(uri.toURL().openStream()));
			
			String inputLine;
	        while((inputLine = reader.readLine()) != null) {
	        	builder.append(inputLine);
	        }
		} finally {
			if(reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return builder.toString();
	}

}
