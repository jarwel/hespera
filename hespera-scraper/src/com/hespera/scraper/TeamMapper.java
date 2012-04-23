package com.hespera.scraper;

public enum TeamMapper {
	
	ARIZONA (Ballpark.ARIZONA_DIAMONDBACKS),
	ATLANTA (Ballpark.ATLANTA_BRAVES),
	BALTIMORE (Ballpark.BALTIMORE_ORIOLES),
	CHICAGO_CUBS (Ballpark.CHICAGO_CUBS),
	CHICAGO_WHITE_SOX (Ballpark.CHICAGO_WHITE_SOX),
	CINCINNATI (Ballpark.CINCINNATI_REDS),
	CLEVELAND (Ballpark.CLEVELAND_INDIANS),
	COLORADO (Ballpark.COLORADO_ROCKIES),
	DETROIT (Ballpark.DETROIT_TIGERS),
	HOUSTON (Ballpark.HOUSTON_ASTROS),
	KANSAS_CITY (Ballpark.KANSAS_CITY_ROYALS),
	LA_ANGELS (Ballpark.LOS_ANGELES_ANGELS),
	LA_DODGERS (Ballpark.LOS_ANGELES_DODGERS),
	MIAMI (Ballpark.MIAMI_MARLINS),
	MILWAUKEE  (Ballpark.MILWAUKEE_BREWERS),
	MINNESOTA (Ballpark.MINNESOTA_TWINS),
	NY_METS (Ballpark.NEW_YORK_METS),
	NY_YANKEES (Ballpark.NEW_YORK_YANKEES),
	OAKLAND (Ballpark.OAKLAND_ATHLETICS),
	PHILADELPHIA (Ballpark.PHILADELPHIA_PHILLIES),
	PITTSBURGH (Ballpark.PITTSBURGH_PIRATES),
	TAMPA_BAY (Ballpark.TAMPA_BAY_RAYS),
	TEXAS (Ballpark.TEXAS_RANGERS),
	TORONTO (Ballpark.TORONTO_BLUE_JAYS),
	SAN_DIEGO (Ballpark.SAN_DIEGO_PADRES),
	SAN_FRANCISCO (Ballpark.SAN_FRANCISCO_GIANTS),
	SEATTLE (Ballpark.SEATTLE_MARINERS);
	
	private final Ballpark team;
	
	private TeamMapper(Ballpark team) {
		this.team = team;
	}
	
	public Ballpark team() { return team; };
	
}
