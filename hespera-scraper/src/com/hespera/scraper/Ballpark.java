package com.hespera.scraper;

public enum Ballpark {
	
	BALTIMORE_ORIOLES (-76.6216993, 39.2832939),
	CHICAGO_CUBS (-84.5080251, 39.0978555),
	CINCINNATI_REDS (-84.5080251, 39.0978555),
	CLEVELAND_INDIANS (-81.685195, 41.496107),
	COLORADO_ROCKIES (-104.9932032, 39.7553551),
	DETROIT_TIGERS (-83.048487, 42.338753),
	HOUSTON_ASTROS (-95.357294, 29.756815),
	KANSAS_CITY_ROYALS (-94.4806194, 39.0510186),
	LOS_ANGELES_ANGELS (-117.883043, 33.80003),
	LOS_ANGELES_DODGERS (-118.2409737, 34.0732204),
	MILWAUKEE_BREWERS  (-87.9720433, 43.028758),
	MINESOTA_TWINS (-93.2763987, 44.981696),
	OAKLAND_ATHLETICS (-122.2015715, 37.7515541),
	TAMPA_BAY_RAYS (-82.459355, 27.94762),
	TEXAS_RANGERS (-101.9082093, 34.969932),
	TORONTO_BLUE_JAYS (-79.3902933, 43.6415711),
	SAN_DIEGO_PADRES (-117.1556175, 32.7056639),
	SAN_FRANCISCO_GIANTS (-122.3908715, 37.7781428),
	SEATTLE_MARINERS (-122.3343941, 47.5918403);
	
	private final double longitude;
	private final double latitude;
	
	private Ballpark(double longitude, double latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	public double longitude() { return longitude; };
	public double latitude() { return latitude; };
	
}