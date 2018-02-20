package com.revature.services;

import java.io.IOException;
import java.util.Collection;

@Deprecated
public interface Service {
	void execute() throws IOException;

	<T> Collection<T> read(String...args) throws IOException;

}
