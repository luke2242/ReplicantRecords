package com.example.replicantrecords.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Artist {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String artistName;
    private int yearFormed;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    private List<Albums> albums = new ArrayList<>();

    public Artist() {}

    public Artist(String artistName, int yearFormed) {
        this.artistName = artistName;
        this.yearFormed = yearFormed;
    }
    
    public Long getID() {
    	return this.id;
    }
	
	public String getArtistName() {
		return artistName;
	}
	
	public int getYearFormed() {
		return yearFormed;
	}
	
	public List<Albums> getAlbums(){
		return albums;
	}
	
	public void setArtistName(String name) {
		this.artistName = name;
	}
	

	public void setYearFormed(int yearFormed) {
		this.yearFormed = yearFormed;
	}

}
