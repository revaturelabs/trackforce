package com.revature.dao;

import java.io.IOException;

public interface DatabaseDAO {
	public String deleteAll();

	public String populate() throws IOException;
	public String populateSF() throws IOException;
}
