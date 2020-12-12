package com.projeto_es;

import static org.junit.Assert.*;

import org.junit.Test;

public class FeatureEnvyThresholdsTest {
	int ATFD_metric = 4;
	double LAA_metric = 0.428571;
	
	public boolean isFeatureEnvy(int ATFD, double LAA, int ATFD_metric, double LAA_metric) {
		return ((ATFD>ATFD_metric && LAA<LAA_metric));
			
	}

	@Test
	public void testIsFeatureEnvy() {
		int ATFD = 5;
		double LAA = 0.39;
		assert(isFeatureEnvy(ATFD, LAA, ATFD_metric, LAA_metric));
		
	}

}
