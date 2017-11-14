/**
 * this class parses a GeoJSON data stream and creates instances of Earthquake based on the input data.
 * Coordinate object of the source data contains [lon, lat, depth]. X and Y coordinates of the 
 * earthquake are extracted from lon and lat respectively.
 * Member variables of the Earthquake class, namelydepth, magnitude, place, and time,
 * are populated using data in the "properties" object.
 * copyright: Behzad Vahedi and Sara Lafia - March 2016
 */
package Controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;

import Model.Earthquake;

public class GeoJSONParser {
	static ArrayList<Earthquake> quakes = new ArrayList<Earthquake>();
	// getter
	public static ArrayList<Earthquake> getQuakes() {
		return quakes;
	}

	public GeoJSONParser(String dataSourceLink) throws IOException{
		quakes.clear();
		URL dataURL = new URL(dataSourceLink);
		Scanner scanner = new Scanner(dataURL.openStream());
		String featureCollectionStream = new String();
		while (scanner.hasNext())
			featureCollectionStream += scanner.nextLine();
		scanner.close();

		JSONObject featureCollection = new JSONObject(featureCollectionStream);
		JSONArray features = featureCollection.getJSONArray("features"); 

		Iterator<Object> featuresIterator = features.iterator();

		double x,y = 0;		
		while(featuresIterator.hasNext()) {
			JSONObject earthquake = (JSONObject) featuresIterator.next();
			JSONObject earthquakeGeom = earthquake.getJSONObject("geometry");
			JSONArray coordinates = (JSONArray) earthquakeGeom.get("coordinates");
			JSONObject properties = earthquake.getJSONObject("properties");
			//JSONObject id = earthquake.getJSONObject("id");
//			JSONString id = (JSONString) earthquake.get("id");
			String id = (String) earthquake.get("id");
			if(coordinates.get(0) instanceof Integer)
			{
				x = (Integer)coordinates.get(0) * 1.0;
			}
			else
			{
				x = (double)coordinates.get(0);
			}
			if(coordinates.get(1) instanceof Integer)
			{
				y = (Integer)coordinates.get(1) * 1.0;
			}
			else
			{
				y = (double)coordinates.get(1);
			}
			quakes.add(new Earthquake(x, y));
			if (coordinates.get(2) instanceof Integer)
			{
				quakes.get(quakes.size()-1).setDepth(((Integer)coordinates.get(2) * 1.0));
			}
			else
			{
				quakes.get(quakes.size()-1).setDepth((double)coordinates.get(2));
			}
			
			if(properties.get("mag").toString() != "null")
			{
				quakes.get(quakes.size()-1).setMagnitude(Double.valueOf(properties.get("mag").toString()));
			}
			else
			{
				quakes.get(quakes.size()-1).setMagnitude(-10.0);
			}
			quakes.get(quakes.size()-1).setPlace((String)properties.get("place"));
			quakes.get(quakes.size()-1).setTime((long)properties.get("time"));
			quakes.get(quakes.size()-1).setId(id);
		}

	}
}