package com.hespera.extraction;

import com.hespera.extraction.geo.BasketballGeo;

public enum BasketballMapper {

	ATLANTA (BasketballGeo.ATLANTA_HAWKS),
    BOSTON (BasketballGeo.BOSTON_CELTICS),
	CHARLOTTE (BasketballGeo.CHARLOTTE_BOBCATS),
	CHICAGO (BasketballGeo.CHICAGO_BULLS),
	DALLAS (BasketballGeo.DALLAS_MAVERICKS),
    DENVER (BasketballGeo.DENVER_NUGGETS),
	DETROIT (BasketballGeo.DETROIT_PISTONS ),
	INDIANA (BasketballGeo.INDIANA_PACERS),
    LA_CLIPPERS (BasketballGeo.LOS_ANGELES_CLIPPERS),
    LA_LAKERS (BasketballGeo.LOS_ANGELES_LAKERS),
    MIAMI (BasketballGeo.MIAMI_HEAT),
	MILWAUKEE (BasketballGeo.MILWAUKEE_BUCKS),
	MEMPHIS (BasketballGeo.MEMPHIS_GRIZZLIES),
	NEW_JERSEY (BasketballGeo.NEW_JERSEY_NETS),
    NEW_YORK (BasketballGeo.NEW_YORK_KNICKS),
    OKLAHOMA_CITY (BasketballGeo.OKLAHOMA_CITY_THUNDER),
    ORLANDO (BasketballGeo.ORLANDO_MAGIC),
	PHILADELPHIA (BasketballGeo.PHILADELPHIA_76ERS),
	PORTLAND (BasketballGeo.PORTLAND_TRAIL_BLAZERS),
	SAN_ANTONIO (BasketballGeo.SAN_ANTONIO_SPURS),
	TORONTO (BasketballGeo.TORONTO_RAPTORS),
    UTAH (BasketballGeo.UTAH_JAZZ),
	WASHINGTON (BasketballGeo.WASHINGTON_WIZARDS);
		
	public final BasketballGeo team;
	
	private BasketballMapper(BasketballGeo team) {
		this.team = team;
	}
	
}
