package com.example.replicantrecords.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AlbumsRepo extends CrudRepository<Albums, Long>
{
	// Contracts 
    List<Albums> findByTitleAndReleaseYear(String title, int releaseYear);
    @Query("SELECT a FROM Albums a WHERE a.releaseYear BETWEEN ?1 AND ?2")
    List<Albums> findReleaseYearBetween(int low, int high);
	Albums save(WriteAlbumDTO album);
	
}
