package com.projeto_es;

public class FeatureEnvyThresholds extends Thresholds{
		int ATFD_metric;
		double LAA_metric;
		
		FeatureEnvyThresholds(int ATFD_metric, double LAA_metric){
			this.ATFD_metric=ATFD_metric;
			this.LAA_metric=LAA_metric;
		}

		@Override
		public boolean isFeatureEnvy(int ATFD, double LAA, int ATFD_metric, double LAA_metric) {
			return ((ATFD>ATFD_metric && LAA<LAA_metric));
				
		}
}
