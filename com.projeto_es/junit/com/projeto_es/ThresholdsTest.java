package com.projeto_es;

import static org.junit.Assert.*;

import org.junit.Test;

 

public class ThresholdsTest {
	int LOC_metric = 5;
	int CYCLO_metric = 10;
	int ATFD_metric = 5;
	int LAA_metric = 20;
	
	public void setLOC_metric(int LOC_metric) {
		this.LOC_metric = LOC_metric;
	}
	public void setCYCLO_metric(int CYCLO_metric) {
		this.CYCLO_metric = CYCLO_metric;
		}
	public void setATFD_metric(int ATFD_metric) {
		this.ATFD_metric = ATFD_metric;
	}
	public void setLAA_metric(int LAA_metric) {
		this.LAA_metric = LAA_metric;
	} 
	
	public boolean isLongMethod(int LOC, int CYCLO) {
		return (LOC>80 && CYCLO>10);
	}
	public boolean isFeatureEnvy(int ATFD, int LAA) {
		return ((ATFD>4 && LAA<0.428571) || (ATFD>4 && LAA>0.428571)); 
		
	}
	public int getLOC_metric() {
		return this.LOC_metric;
	}
	public int getCYCLO_metric() {
		return this.CYCLO_metric;
	}
	public int getATFD_metric() {
		return this.ATFD_metric;
	}
	public double getLAA_metric() {
		return this.LAA_metric;
	}
	
	@Test
	public void testIsLongMethod() {
		int LOC = 81;
		int CYCLO = 11;
		assert(isLongMethod(LOC, CYCLO)==true);
	}

	@Test
	public void testIsFeatureEnvy() {
		int ATFD = 5;
		int LAA = 324;
		assert(isFeatureEnvy(ATFD, LAA) == true);
	}

	
	@Test
	public void testSetLOC_metric() {
		int LOC = 10;
		setLOC_metric(LOC);
		assertNotNull(LOC);
	}
	
	@Test
	public void testSetCYCLO_metric() {
		int CYCLO = 5;
		setCYCLO_metric(CYCLO);
		assertNotNull(CYCLO);
	}
	
	
	@Test
	public void testSetATFD_metric() {
		int ATFD = 5;
		setATFD_metric(ATFD);
		assertNotNull(ATFD);
	}
	
	@Test
	public void testSetLAA_metric() {
		int LAA = 4;
		setLAA_metric(LAA);
		assertNotNull(LAA);
	}
	
	@Test
	public void testGetLOC_metric() {
		int LOC_exp = 5;
		assert(getLOC_metric() == LOC_exp);
	}

	
	@Test
	public void testGetCYCLO_metric() {
		int CYCLO_exp = 10;
		assert(getCYCLO_metric() == CYCLO_exp);
	}
	
	
	@Test
	public void testGetATFD_metric() {
		int ATFD_exp = 5;
		assert(getATFD_metric() == ATFD_exp);
	}
	
	
	@Test
	public void testGetLAA_metric() {
		int LAA_exp = 20;
		assert(getLAA_metric() == LAA_exp);
	}
}
