package com.gop.util;

import java.util.ArrayList;
import java.util.List;

import org.geojson.LineString;
import org.geojson.LngLatAlt;

public class GeoJsonUtil {
	
	public static LineString drawLineString(LngLatAlt start, LngLatAlt end){
		LineString line = new LineString();
		List<LngLatAlt> points = new ArrayList<LngLatAlt>();
		points.add(start);
		points.add(end);
		line.setCoordinates(points);
		return line;
	}

}
