package com.hespera.scraper;

import com.hespera.scraper.geo.BasketballGeo;

public enum BasketballMapper {

	CHARLOTTE (BasketballGeo.CHARLOTTE_BOBCATS),
	DALLAS (BasketballGeo.DALLAS_MAVERICKS),
	DETROIT (BasketballGeo.DETROIT_PISTONS ),
	INDIANA (BasketballGeo.INDIANA_PACERS),
	MILWAUKEE (BasketballGeo.MILWAUKEE_BUCKS),
	MEMPHIS (BasketballGeo.MEMPHIS_GRIZZLIES),
	NEW_JERSEY (BasketballGeo.NEW_JERSEY_NETS),
	PHILADELPHIA (BasketballGeo.PHILADELPHIA_76ERS),
	PORTLAND (BasketballGeo.PORTLAND_TRAIL_BLAZERS),
	SAN_ANTONIO (BasketballGeo.SAN_ANTONIO_SPURS),
	TORONTO (BasketballGeo.TORONTO_RAPTORS),
	WASHINGTON (BasketballGeo.WASHINGTON_WIZARDS);
		
	private final BasketballGeo team;
	
	private BasketballMapper(BasketballGeo team) {
		this.team = team;
	}
	
	public BasketballGeo team() { return team; };
}
