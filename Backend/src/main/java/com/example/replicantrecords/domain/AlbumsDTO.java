package com.example.replicantrecords.domain;

import lombok.Data;

@Data
public class AlbumsDTO {
    private Long id;
    private String title;
    private int releaseYear;
    
    // Constructor for DTO (Getter and Setters)
    public AlbumsDTO(Albums album) {
    	
    	this.id = album.getID();
    	this.title = album.getTitle();
    	this.releaseYear = album.getReleaseYear();
    	
    }
}
