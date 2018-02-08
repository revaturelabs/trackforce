package com.revature.services;

import java.io.IOException;
import java.util.Collection;

public interface Delegate {
	void execute() throws IOException;

	<T> Collection<T> read(String...args) throws IOException;

}
