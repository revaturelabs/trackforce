package com.revature.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/clients")
public class ClientResource {

	// likely should return associates from DB
	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<MyAssociate> getAllAssociatess() {
		List<MyAssociate> associates = getData();
		associates.add(new MyAssociate(2,true));
		associates.add(new MyAssociate(4,true));
		associates.add(new MyAssociate(4,true));
		associates.add(new MyAssociate(4,false));
		associates.add(new MyAssociate(2,false));
		associates.add(new MyAssociate(1,false));
		associates.add(new MyAssociate(2,false));
		return associates;
	}

	// likely should return associates from DB
	@GET
	@Path("{clientid}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<MyAssociate> getAssociatesForSpecificClient(@PathParam("clientid") int clientid) {
		List<MyAssociate> associates = getData();
		return associates;
	}
	
	private List<MyAssociate> getData(){
		List<MyAssociate> associates = new ArrayList<>();
		associates.add(new MyAssociate(1,true));
		associates.add(new MyAssociate(5,true));
		associates.add(new MyAssociate(3,true));
		associates.add(new MyAssociate(4,true));
		associates.add(new MyAssociate(2,true));
		associates.add(new MyAssociate(2,true));
		associates.add(new MyAssociate(4,true));
		associates.add(new MyAssociate(4,true));
		associates.add(new MyAssociate(2,true));
		associates.add(new MyAssociate(4,true));
		associates.add(new MyAssociate(4,true));
		associates.add(new MyAssociate(4,false));
		associates.add(new MyAssociate(2,false));
		associates.add(new MyAssociate(1,false));
		associates.add(new MyAssociate(2,false));
		associates.add(new MyAssociate(3,false));
		associates.add(new MyAssociate(5,false));
		associates.add(new MyAssociate(4,false));
		associates.add(new MyAssociate(3,false));
		associates.add(new MyAssociate(2,false));
		associates.add(new MyAssociate(4,false));
		associates.add(new MyAssociate(5,false));
		associates.add(new MyAssociate(3,false));
		associates.add(new MyAssociate(2,false));
		associates.add(new MyAssociate(4,false));
		associates.add(new MyAssociate(5,false));
		associates.add(new MyAssociate(3,false));
		return associates;
	}
	
	class MyAssociate{
		private int stage;
		private boolean isMapped;
		public MyAssociate(int stage, boolean isMapped) {
			super();
			this.stage = stage;
			this.isMapped = isMapped;
		}
		public int getStage() {
			return stage;
		}
		public boolean isMapped() {
			return isMapped;
		}
		public void setStage(int stage) {
			this.stage = stage;
		}
		public void setMapped(boolean isMapped) {
			this.isMapped = isMapped;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + (isMapped ? 1231 : 1237);
			result = prime * result + stage;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			MyAssociate other = (MyAssociate) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (isMapped != other.isMapped)
				return false;
			if (stage != other.stage)
				return false;
			return true;
		}
		private ClientResource getOuterType() {
			return ClientResource.this;
		}
		@Override
		public String toString() {
			return "MyAssociate [stage=" + stage + ", isMapped=" + isMapped + "]";
		}
		
	}
}
