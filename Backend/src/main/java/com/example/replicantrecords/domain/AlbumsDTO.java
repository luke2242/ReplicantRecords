package com.example.replicantrecords.domain;

import lombok.Data;

@Data
public class AlbumsDTO {
    private Long id;
    private String title;
    private int releaseYear;
    private Long artistId;
    private String artistName;
    private String genre;
    
    // Constructor for DTO (Getter and Setters)
    public AlbumsDTO(Albums album) {
    	
        this.id = album.getID();
        this.title = album.getTitle();
        this.releaseYear = album.getReleaseYear();
        this.artistId = album.getArtist().getID();
        this.artistName = album.getArtist().getArtistName();
        this.genre = album.getGenre();
    	
    }
}
