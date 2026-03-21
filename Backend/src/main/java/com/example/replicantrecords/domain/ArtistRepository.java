package com.example.replicantrecords.domain;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ArtistRepository extends CrudRepository<Artist, Long> {
	
	// Contracts
    List<Artist> findByArtistNameAndYearFormed(String artistName, int yearFormed);
    
    @Query("SELECT a FROM Artist a WHERE a.yearFormed BETWEEN ?1 AND ?2")
    List<Artist> findArtistsYearBetween(int low, int high);

	Artist save(WriteArtistDTO dto);
    
    
    
}
