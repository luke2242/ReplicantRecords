package com.example.replicantrecords.service;

public class AlbumNotFoundException extends RuntimeException  {

	private static final long serialVersionUID = 1L;

	// Will throw a message to indicate artist was not found
	public AlbumNotFoundException(String message) {
        super(message);
    }
}
