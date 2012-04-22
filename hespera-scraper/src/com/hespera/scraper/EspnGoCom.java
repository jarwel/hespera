package com.hespera.scraper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EspnGoCom {
	
	private static String SITE = "http://espn.go.com/mlb/schedule";

	public static void main(String[] args) throws Exception {
		URL url = new URL(SITE);
		
		Pattern pattern = Pattern.compile("<td>(<a href=\"/mlb/preview\\?gameId=\\d+\">)?[a-zA-Z0-9 ]+(</a>)?</td><td align=\"right\">[a-zA-Z0-9 :]+</td>");
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        
		String inputLine;
        while((inputLine = in.readLine()) != null) {
        	Matcher matcher = pattern.matcher(inputLine);
        	while(matcher.find()) {
        		String[] values = inputLine.substring(matcher.start(), matcher.end()).split("</td>");
        		
        		String title = clean(values[0]);
        		String time = clean(values[1]);
        		String location = title.substring(title.indexOf("at ") + 3).toUpperCase().replace(" ", "_");
        		TeamMapper mapper = TeamMapper.valueOf(location);
        		Ballpark bp = mapper.team();
        		System.out.println("Title: " + title + " Time: " + time + " Location: " + bp.longitude());
        	}
        }
        in.close();
	}
	
	private static String clean(String in) {
		return in.replaceAll("<[^>]*>", "");
	}

}
