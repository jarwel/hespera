package com.hespera.extraction.geo;

public enum BasketballGeo {

	CHARLOTTE_BOBCATS (-80.8393335, 35.2250606),
	DALLAS_MAVERICKS (-96.810159, 32.790442),
	DETROIT_PISTONS (-83.2437214, 42.696511),
	INDIANA_PACERS (-86.1604915, 39.7672589),
	MEMPHIS_GRIZZLIES (-90.051885, 35.136686),
	MILWAUKEE_BUCKS (-87.917166, 43.043728),
	NEW_JERSEY_NETS (-73.9761879, 40.683267),
	PHILADELPHIA_76ERS (-75.1713324, 39.9042715),
	PORTLAND_TRAIL_BLAZERS (-122.667285, 45.531608),
	SAN_ANTONIO_SPURS (94.8930737, 27.4707784),
	TORONTO_RAPTORS (-79.3790852, 43.6436759),
	WASHINGTON_WIZARDS (-77.0210577, 38.8975641);
	
	public final double longitude;
	public final double latitude;
	
	private BasketballGeo(double longitude, double latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
	}

}
