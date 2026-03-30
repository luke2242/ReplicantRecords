package com.example.replicantrecords.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.replicantrecords.domain.Artist;
import com.example.replicantrecords.domain.ArtistRepository;

@SpringBootTest
@Transactional
public class ArtistRepoTest {
	
    @Autowired
	private ArtistRepository artistRepo;
	
	@Test
	@DisplayName("Check if artist saves to repository correctly")
	void saveArtistTest() {
		Artist artist = new Artist("Daft Punk", 1999);
		artistRepo.save(artist);
		
		// Checks that the artist was added
		assertTrue(artistRepo.existsById(artist.getID()));
		
		// Checks if the album exists, and returns true of false
		boolean exists = artistRepo.existsById(artist.getID());
		assertThat(exists).isTrue();
	}
	
	@Test
	@DisplayName("Checks if artist updates correctly")
	void updateArtistTest() {
		Artist artist = new Artist("Daft Punk", 1999);
		artistRepo.save(artist);
		
		// Implements updated names
		artist.setArtistName("Kraft");
		artistRepo.save(artist);
		
		// Checks if the updated artist exists
        Artist updatedArtist = artistRepo.findById(artist.getID()).orElse(null);
        
        assertNotNull(updatedArtist);
		
        // Checks that the name was updated correctly
        assertEquals("Kraft", updatedArtist.getArtistName());
	}
	
	@Test
	@DisplayName("Checks if our artist list works correctly form repo")
	void getListOfAllArtistsTest() {
		
		// Current size of the repo
		long currentSize = artistRepo.count();
		
		Artist artist = new Artist("Draft Punk", 1099);
		artistRepo.save(artist);
		
		// Checks that the artist was added
		assertTrue(artistRepo.existsById(artist.getID()));
	
		
		Iterable<Artist> artistList = artistRepo.findAll();
		
		assertNotNull(artistList);
		
		// Makes sure the artist was added
		assertThat(artistList).hasSize((int) currentSize + 1);
		
	}
}