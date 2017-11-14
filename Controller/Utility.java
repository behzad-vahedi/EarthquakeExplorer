package Controller;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONObject;

import Model.Buffer;
import Model.OurPoint;

public class Utility {
	//constructor

	public static double distanceCalculator(OurPoint firstPoint, OurPoint secondPoint) {
		
		double lon1 = firstPoint.getX();
		double lat1 = firstPoint.getY();
		double lon2 = secondPoint.getX();
		double lat2 = secondPoint.getY();
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515* 1.609344;

		return (dist);
	}

	// This function converts decimal degrees to radians
	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	// This function converts radians to  decimal degrees
	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}

}


