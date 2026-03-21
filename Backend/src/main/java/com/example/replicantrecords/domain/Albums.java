package com.example.replicantrecords.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Albums{
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    private Artist artist;

	private int releaseYear;

    public Albums() {}

    public Albums(String title, int releaseYear, Artist artist) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.artist = artist;
    }
	
	public Long getID() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public int getReleaseYear() {
		return releaseYear;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}
	
}
