package com.example.replicantrecords.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.replicantrecords.domain.AlbumsRepo;
import com.example.replicantrecords.domain.Artist;
import com.example.replicantrecords.domain.ArtistRepository;
import com.example.replicantrecords.domain.Albums;

@SpringBootTest
public class AlbumRepoTest {
	
	@Autowired
	private AlbumsRepo albumRepo;
	@Autowired
	private ArtistRepository artistRepo;
	
	@Test
	@DisplayName("Check if album saves to repository correctly")
	void saveAlbumTest() {
		Artist artist = new Artist("Daft Punk", 1999);
		artistRepo.save(artist);
		
		// Checks that the artist was added
		assertTrue(artistRepo.existsById(artist.getID()));
		
		Albums album = new Albums("Hello World", 2000, "Electronic", artist);
		albumRepo.save(album);
		
		// Checks that the album was added
		assertTrue(albumRepo.existsById(album.getID()));
		
		// Checks if the album exists, and returns true of false
		boolean exists = albumRepo.existsById(album.getID());
		assertThat(exists).isTrue();
	}
	
	@Test
	@DisplayName("Checks if we can get the album by comparing id")
	void testGetAlbum() {
		
		Artist artist = new Artist("Daft Punk", 1999);
		artistRepo.save(artist);
		
		Albums album = albumRepo.save(new Albums("Hello World", 2000, "Electronic", artist));
		
		// Checks that the album was added
		assertTrue(albumRepo.existsById(album.getID()));
		
		Albums fetchAlbum = albumRepo.findById(album.getID()).orElse(null);
		
		// Ensures that the album isn't null
		assertNotNull(fetchAlbum);
		
		// Checks that both the albums id's are equal to each other
		assertEquals(album.getID(), fetchAlbum.getID());
	}
	
	@Test
	@DisplayName("Checks if we can delete the album by using id")
	void deleteAlbumByIDTest() {
		
		Artist artist = new Artist("Daft Punk", 1999);
		artistRepo.save(artist);
		
		Albums album = albumRepo.save(new Albums("Hello World", 2000, "Electronic", artist));
		
		// Checks that the album was added
		assertTrue(albumRepo.existsById(album.getID()));
		
		// Should delete the album
		albumRepo.deleteById(album.getID());
		
		// Attempts to locate the deleted album
		Albums deletedAlbum = albumRepo.findById(album.getID()).orElse(null);
		
		// Assets that the album was delete
		assertNull(deletedAlbum);
		
	}
}
