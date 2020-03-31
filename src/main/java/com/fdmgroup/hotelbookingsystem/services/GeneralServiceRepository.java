package com.fdmgroup.hotelbookingsystem.services;

public interface GeneralServiceRepository<E> {

	E findByUsernameAndPassword(String username, String password);

	
	
}
