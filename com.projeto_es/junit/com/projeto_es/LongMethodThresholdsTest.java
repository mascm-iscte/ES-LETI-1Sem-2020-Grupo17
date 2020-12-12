package com.projeto_es;

import static org.junit.Assert.*;

import org.junit.Test;

public class LongMethodThresholdsTest {
	int LOC_metric =15;
	int CYCLO_metric = 20;  
	
	public boolean isLongMethod (int LOC, int CYCLO, int LOC_metric, int CYCLO_metric) {
		return (LOC>LOC_metric && CYCLO>CYCLO_metric);
	}	
	
	
	@Test
	public void testIsLongMethod() {
		int LOC = 20;
		int CYCLO = 30;
		assert(isLongMethod(LOC, CYCLO, LOC_metric, CYCLO_metric)== true);
	}

}
