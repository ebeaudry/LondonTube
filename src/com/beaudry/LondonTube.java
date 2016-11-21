package com.beaudry;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class LondonTube {
	
	private Map<String, Station> tubeMap;
	private Map<String, Integer> stopMap;
	
	public LondonTube(){
		setupTubeMap();
	}
	
	public void getStationsFromEastHam(int stops){
		Set<String> stations = new HashSet<String>();
		stations.add("East Ham");
		this.stopMap = new HashMap<String, Integer>();
		this.stopMap.put("East Ham", 0);
		for(int index = 1; index <= stops; index++){
			stations = getStops(stations, index, stops);	
		}
		Set<String> output = new TreeSet<String>();
		output.addAll(stations);
		for(String station : output){
			System.out.println(station);
		}
		
	}
	private Set<String> getStops(Set<String> stations, int currentIndex, int stopIndex){
		Set<String> childStations = new HashSet<String>();
		for(String stationName : stations){	
			Station station = getStation(stationName);
			Map<String, Set<String>> stops = station.getTubeLines();
			for(String stop : stops.keySet() ){
				if(currentIndex == stopIndex){
					if(!stopMap.containsKey(stop)){
						childStations.add(stop);
					}
				}
				else{
					if(!stopMap.containsKey(stop)){
						stopMap.put(stop, currentIndex);
					}
					childStations.add(stop);
				}
			}
		}
		return childStations;
	}
	private void setupTubeMap(){
		this.tubeMap = new HashMap<String, Station>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader("London tube lines.csv"));
			String tubeStop = "";
			String title = reader.readLine();
			while((tubeStop = reader.readLine()) != null){
				String[] stopData = tubeStop.split(",");
				
				String tubeLineName = stopData[0];
				String from = stopData[1];
				String to = stopData[2];
				
				Station toStation = this.getStation(to);
				toStation.addStop(from, tubeLineName);
				this.tubeMap.put(to, toStation);
				
				Station fromStation = this.getStation(from);
				fromStation.addStop(to, tubeLineName);
				this.tubeMap.put(from, fromStation);
				
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Station getStation(String stationName){
		Station station = this.tubeMap.get(stationName);
		if(station == null){
			station = new Station();
			station.setStationName(stationName);
		}
		return station;
	}
}
