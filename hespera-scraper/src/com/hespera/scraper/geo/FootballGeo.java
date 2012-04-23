package com.hespera.scraper.geo;

public enum FootballGeo {
	
	ARIZONA_CARDINALS (-111.952224, 33.336569),
	CINCINNATI_BENGALS (-84.511823, 39.106899),
	CLEVELAND_BROWNS (-81.6988255, 41.5050391),
	DALLAS_COWBOYS (-97.092909, 32.747904),
	GREENBAY_PACKERS (-88.0622376, 44.5012386),
	HOUSTON_TEXANS (-95.4107581, 29.6863042),
	NEW_ENGLAND_PATRIOTS (-71.2670041, 42.0902551),
	NEW_ORLEANS_SAINTS (-90.080563, 29.952211),
	NEW_YORK_GIANTS (-74.0736902, 40.8142092),
	OAKLAND_RAIDERS (-122.2015715, 37.7515541),
	PITTSBURGH_STEELERS (-80.015942, 40.446093),
	SAN_FRANCISCO_49ERS (-122.388814, 37.7147906);
	
	private final double longitude;
	private final double latitude;
	
	private FootballGeo(double longitude, double latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	public double longitude() { return longitude; };
	public double latitude() { return latitude; };
}
