package com.beaudry;

import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.assertEquals;

public class LondonTubeTest {

	LondonTube londonTube = new LondonTube();

	@Test
	public void test() {
		for(int i = 1; i <= 31; i++){
			System.out.println(i);
			londonTube.getStationsFromEastHam(i);
			System.out.println();
		}
		
	}
}