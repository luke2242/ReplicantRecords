package com.example.replicantrecords.service;

import java.util.List;
import java.util.Optional;

import com.example.replicantrecords.domain.Artist;
import com.example.replicantrecords.domain.WriteArtistDTO;

public interface ArtistService {
	
	// CRUD Functionalities
	Artist save(WriteArtistDTO dto);
	Optional<Artist> findByID(Long id);
	Artist update(Long id, WriteArtistDTO update);
	void deleteByID(Long id);
	Iterable<Artist> findAll();
	
	//  Queries
    List<Artist> findByArtistNameAndYearFormed(String artistName, int yearFormed);
    List<Artist> findArtistsYearBetween(int low, int high);
    
    
	
	
}
