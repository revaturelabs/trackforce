package com.revature.services;

public class ServiceLookup {
	public Service getService(String service) {
		
		switch (service.toUpperCase()) {
			case "ASSOCIATE":
				return new AssociateService();
			case "BATCH":
				return new BatchesService();
			case "CLIENT":
				return new ClientService();
			case "CURRICULUM":
				return new CurriculumService();
			case "MARKETING":
				return new MarketingStatusService();
			default:
				return null;
		}
	}
}
