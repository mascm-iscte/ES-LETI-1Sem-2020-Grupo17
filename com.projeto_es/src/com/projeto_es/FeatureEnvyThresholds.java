package com.projeto_es;

public class FeatureEnvyThresholds extends Thresholds{
		int ATFD_metric;
		double LAA_metric;
		int NOFA_metric;
		
		FeatureEnvyThresholds(int ATFD_metric, double LAA_metric, int NOFA_metric){
			this.ATFD_metric=ATFD_metric;
			this.LAA_metric=LAA_metric;
			this.NOFA_metric= NOFA_metric;
		}

		@Override
		public boolean isFeatureEnvy(int ATFD, double LAA, int NOFA, int ATFD_metric, double LAA_metric, int NOFA_metric) {
			return ((ATFD>ATFD_metric && LAA<LAA_metric) || (ATFD>ATFD_metric && LAA>LAA_metric && NOFA>NOFA_metric));
				
		}
}
