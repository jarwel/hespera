package com.hespera.extraction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

abstract class Scraper {
	
	protected static String read(URL url) throws Exception {
		StringBuilder builder = new StringBuilder();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			
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
	
	protected static String clean(String in) {
		return in.replaceAll("<[^>]*>", "");
	}
	
}
