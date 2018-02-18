package com.revature.services;

import java.io.IOException;
import java.util.Collection;

public interface Service {
	@Deprecated
	void execute() throws IOException;

	@Deprecated
	<T> Collection<T> read(String...args) throws IOException;

}
