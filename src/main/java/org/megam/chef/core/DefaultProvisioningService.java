package org.megam.chef.core;

import java.io.IOException;

import org.megam.chef.exception.IdentifierException;
import org.megam.chef.exception.ProvisionerException;

/**
 * This class isn't implemented. Use DefaultProvisioningServiceWithShell
 * 
 * @author rajthilak
 * 
 */
public class DefaultProvisioningService<T> extends
		AbstractProvisioningService<T> {

	/**
	 * 
	 * @throws ProvisionerException
	 */
	public DefaultProvisioningService() throws ProvisionerException {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.megam.chef.ProvisioningService#provision(java.lang.String)
	 */
	@Override
	/**
	 * @throws ProvisionerException
	 */
	public T provision(String jsonString) throws ProvisionerException, IOException, IdentifierException {
		throw new RuntimeException(
				"Not implemented yet. Use CHEF_WITH_SHELL in your factory instead.");
	}
}
