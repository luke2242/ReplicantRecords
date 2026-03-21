package com.example.replicantrecords.service;

public class ArtistNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	// Will throw a message to indicate artist was not found
	public ArtistNotFoundException(String message) {
        super(message);
    }

}
