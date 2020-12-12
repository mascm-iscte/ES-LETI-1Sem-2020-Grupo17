package com.projeto_es;

public class LongMethodThresholds extends Thresholds{
	
	int LOC_metric;
	int CYCLO_metric;  
	
	LongMethodThresholds(int LOC_metric, int CYCLO_metric){
		this.LOC_metric = LOC_metric;
		this.CYCLO_metric = CYCLO_metric;
	}
	/**
	 * Method for classification of the method. Return "TRUE" if both are bigger than the correspondent default metric
	 * @param LOC Number of lines of code of the method
	 * @param CYCLO cyclomatic complexity of the method 
	 * @param LOC_metric Threshold metric for number of lines of code of the method
	 * @param CYCLO_metric Threshold metric for cyclomatic complexity of the method
	 * @return boolean flag, "TRUE" if given method is long
	 */
	@Override
	public boolean isLongMethod (int LOC, int CYCLO, int LOC_metric, int CYCLO_metric) {
		return (LOC>LOC_metric && CYCLO>CYCLO_metric);
	}	
}
