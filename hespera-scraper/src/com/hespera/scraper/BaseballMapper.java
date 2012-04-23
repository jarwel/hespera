package com.hespera.scraper;

import com.hespera.scraper.geo.BaseballGeo;

public enum BaseballMapper {
	
	ARIZONA (BaseballGeo.ARIZONA_DIAMONDBACKS),
	ATLANTA (BaseballGeo.ATLANTA_BRAVES),
	BALTIMORE (BaseballGeo.BALTIMORE_ORIOLES),
	BOSTON (BaseballGeo.BOSTON_RED_SOX),
	CHICAGO_CUBS (BaseballGeo.CHICAGO_CUBS),
	CHICAGO_WHITE_SOX (BaseballGeo.CHICAGO_WHITE_SOX),
	CINCINNATI (BaseballGeo.CINCINNATI_REDS),
	CLEVELAND (BaseballGeo.CLEVELAND_INDIANS),
	COLORADO (BaseballGeo.COLORADO_ROCKIES),
	DETROIT (BaseballGeo.DETROIT_TIGERS),
	HOUSTON (BaseballGeo.HOUSTON_ASTROS),
	KANSAS_CITY (BaseballGeo.KANSAS_CITY_ROYALS),
	LA_ANGELS (BaseballGeo.LOS_ANGELES_ANGELS),
	LA_DODGERS (BaseballGeo.LOS_ANGELES_DODGERS),
	MIAMI (BaseballGeo.MIAMI_MARLINS),
	MILWAUKEE  (BaseballGeo.MILWAUKEE_BREWERS),
	MINNESOTA (BaseballGeo.MINNESOTA_TWINS),
	NY_METS (BaseballGeo.NEW_YORK_METS),
	NY_YANKEES (BaseballGeo.NEW_YORK_YANKEES),
	OAKLAND (BaseballGeo.OAKLAND_ATHLETICS),
	PHILADELPHIA (BaseballGeo.PHILADELPHIA_PHILLIES),
	PITTSBURGH (BaseballGeo.PITTSBURGH_PIRATES),
	TAMPA_BAY (BaseballGeo.TAMPA_BAY_RAYS),
	TEXAS (BaseballGeo.TEXAS_RANGERS),
	TORONTO (BaseballGeo.TORONTO_BLUE_JAYS),
	SAN_DIEGO (BaseballGeo.SAN_DIEGO_PADRES),
	SAN_FRANCISCO (BaseballGeo.SAN_FRANCISCO_GIANTS),
	SEATTLE (BaseballGeo.SEATTLE_MARINERS),
	ST_LOUIS (BaseballGeo.ST_LOUIS_CARDINALS),
	WASHINGTON (BaseballGeo.WASHINGTON_NATIONALS);
	
	private final BaseballGeo team;
	
	private BaseballMapper(BaseballGeo team) {
		this.team = team;
	}
	
	public BaseballGeo team() { return team; };
	
}
