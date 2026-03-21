package com.example.replicantrecords.domain;

import lombok.Data;

@Data
public class ArtistDTO {
    private Long id;
    private String artistName;
    private int yearFormed;
    
    // Constructor for DTO (Getter and Setters)
    public ArtistDTO(Artist artist) {
    	
    	this.id = artist.getID();
    	this.artistName = artist.getArtistName();
    	this.yearFormed = artist.getYearFormed();
    
    	
    	
    }
}
