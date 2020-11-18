package com.projeto_es;

public class LongMethodThresholds extends Thresholds{
	
	int LOC_metric;
	int CYCLO_metric;
	
	LongMethodThresholds(int LOC_metric, int CYCLO_metric){
		this.LOC_metric = LOC_metric;
		this.CYCLO_metric = CYCLO_metric;
	}
	
	@Override
	public boolean isLongMethod (int LOC, int CYCLO, int LOC_metric, int CYCLO_metric) {
		return (LOC>LOC_metric && CYCLO>CYCLO_metric);
	}	
}
