package abd.usama.knit;

public class CityAndDistance {
	public final static double AVERAGE_RADIUS_OF_EARTH_KM = 6371;
	
	double lattitude[] = { 20.593684, 34.0483704, 34.0836708, 34.1525864, 32.2396325, 31.6339793, 31.1048145,
			30.7333148, 29.9456906, 28.7040592, 32.2425758, 32.5387385, 29.3803039, 27.1766701, 26.9124336,
			26.4498954, 26.9157487, 26.2389469, 24.8887435, 24.585445, 25.4357869, 24.8318452, 23.4873393, 24.7913957, 27.0360066, 27.3389356, 25.5787726, 26.577531,
			24.8170111, 25.3176452, 26.8466937, 22.572646, 23.1793013, 19.8761653, 19.8920686, 22.4674463,
			17.385044, 17.6868159, 17.383309, 23.5609405, 14.1669963, 13.1623166, 12.9715987, 12.2958104,
			10.3280265, 11.6233774, 9.9252007, 10.7869994, 13.801001, 12.626927, 13.0826802, 13.6287557, 10.7905,
			10.2381136, 8.4003984, 8.0883064, 29.6433615, 32.219042, 30.9676214, 11.4064138, 11.3530022, 30.4599099,
			29.5892407, 15.2832187, 15.3860329, 9.2876254 };

	double langnitude[] = { 78.96288000000004, 74.3804791, 74.79728249999994, 77.57705349999992, 77.18871450000006,
			74.87226420000002, 77.17340330000002, 76.7794179, 78.1642478, 77.10249019999992, 76.32127809999997,
			75.97099779999996, 79.46356579999997, 78.00807450000002, 75.78727090000007, 74.63991629999998,
			70.90834429999995, 73.02430939999999, 74.62692160000006, 73.71247900000003, 77.66506600000002,
			79.91985490000002, 77.74182380000002, 85.0002336, 88.26267510000002, 88.60650350000003,
			91.89325350000001, 93.17112199999997, 93.93684389999999, 82.97391440000001, 80.94616599999995,
			88.36389499999996, 75.78490970000007, 75.3433139, 86.091184, 78.43458540000006, 78.486671,
			83.21848150000005, 78.4010528, 72.75109210000005, 77.80914959999996, 75.86791949999997,
			77.59456269999998, 76.63938050000002, 72.7846336, 92.72648279999999, 78.11977539999998,
			79.13782739999999, 78.92553199999998, 80.19271090000007, 80.27071840000008, 79.41917949999993, 78.7047,
			77.48918219999996, 76.9787076, 77.5384507, 79.43218249999995, 76.32340369999997, 77.19164969999997,
			76.6932438, 76.7959095, 78.0663978, 79.64666599999998, 73.98619099999996, 73.84403980000002,
			79.31292910000002 };
	
	String cities[] = { "Laddakh", "Gulmarg", "Srinagar", "Leh", "Manali", "Amritsar", "Shimla ", "Chandigarh",
			"Haridwar", "Delhi", "Macleoud Ganj", "Dalhauzi", "Nanital", "Agra", "Jaipur", "Ajmer", "Jasalmer",
			"Jaudhpur", "Chittaurgarrh", "Uday pur", "Shivpuri", "Khajuraho", "Sanchi", "Gaya", "Darzling",
			"Gangtauk", "Shillong", "Kajiranga", "Imfal", "Varansi", "Lucknow", "Kolkata", "Ujjain", "Aurangabad",
			"Kaunark", "Panchmani", "Hyderabad", "Vizag", "Golkunda", "Vizapur",
			"Puttaparthi", "Belur", "Banglore", "Maisur", "Lakshdweep", "Port blair", "Madhurai", "Thanjaboor",
			"Pondicherri", "Mamalapuram", "Chennai", "Tiruchirappalli", "Trichy", "Codai kanal", "Kovalam", "Kanyakumari",
			"Ranikhet", "Dharamshala", "Chail", "Ooty", "Kunnoor", "Musoori", "Almorha", "Margao", "Vaskodagama",
			"Rameshvaram" };

	public int calculateDistanceInKilometer(double userLat, double userLng, double venueLat, double venueLng) {

		double latDistance = Math.toRadians(userLat - venueLat);
		double lngDistance = Math.toRadians(userLng - venueLng);

		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(userLat))
				* Math.cos(Math.toRadians(venueLat)) * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);

		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		return (int) (Math.round(AVERAGE_RADIUS_OF_EARTH_KM * c));
	}

	public String[] getNearBycities(int distance, String city) {

		int i;
		int n = 0;
		int m = 0;
		int nearby[] = new int[cities.length];
		String nearestCities[] = new String[nearby.length];
		
	
		int DistanceMatrix[][] = new int[cities.length][cities.length];
		for (i = 0; i < cities.length; i++) {
			if (city.equalsIgnoreCase(cities[i])) {
				//System.out.println("lang"+langnitude[i]+"lat"+lattitude[i]);
				break;
			}
		}
		// calculating distance
		for (int x = 0; x <DistanceMatrix.length ; x++) {
			for (int y = 0; y < DistanceMatrix.length; y++) {
				DistanceMatrix[x][y] = calculateDistanceInKilometer(lattitude[x], langnitude[x], lattitude[y],
						langnitude[y]);
			}
		}
		// Initializing the nearby array
		for (int j = 0; j < nearby.length; j++) {
			nearby[j] = -1;
		}
		// inserting nearby cities into nearby array
		for (int j = 0; j < DistanceMatrix.length; j++) {
			if (DistanceMatrix[i][j] == 0)
				continue;
			if (DistanceMatrix[i][j] <= distance) {
				nearby[n] = j;
				n++;
			}
		}
		// finding those cities in Array cities
		for (int l = 0; l < nearby.length; l++) {
			if (nearby[l] == -1)
				continue;
			nearestCities[m++] = cities[nearby[l]];
		}
		return nearestCities;

	}
	public int getDistance(String source_city,String Dest_city){
		int i =0;
		while(!source_city.equalsIgnoreCase(cities[i]))
			i++;
		System.out.println("source city:"+cities[i]);
		int j = 0 ; 
		while (!Dest_city.equalsIgnoreCase(cities[j]))
			j++;
		System.out.println("destination city:"+cities[j]);
		
		
		return calculateDistanceInKilometer(lattitude[i], langnitude[i], lattitude[j], langnitude[j]);
	}

}