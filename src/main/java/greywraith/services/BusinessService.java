package greywraith.services;

import greywraith.domain.rest.Sample;

public class BusinessService {
	
	public Sample getLocalBusiness(long longitute, long latitude) {
		Sample sample = new Sample();
		sample.setName("alex");
		return sample;
	}
}
