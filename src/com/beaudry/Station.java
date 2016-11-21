package com.beaudry;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Station {
	private String stationName;
	private Map<String, Set<String>> stops;
	
	public Station() {
		this.stops = new HashMap<String, Set<String>>();
	}
	
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public Map<String, Set<String>> getTubeLines() {
		return stops;
	}
	public void setTubeLines(Map<String, Set<String>> stops) {
		this.stops = stops;
	} 
	
	public void addStop(String stopName, String tubeLine){
		Set<String> tubeLines = this.stops.get(stopName);
		if(tubeLines == null){
			tubeLines = new HashSet<String>();
		}
		tubeLines.add(tubeLine);
		this.stops.put(stopName, tubeLines);
	}
	
	public Set<String> getTubelineList(String stop){
		Set<String> result = this.stops.get(stop);
		if(result == null){
			return new HashSet<String>();
		}
		return result;
	}
	
	@Override
	public String toString() {
		return this.stationName;
	}
}
