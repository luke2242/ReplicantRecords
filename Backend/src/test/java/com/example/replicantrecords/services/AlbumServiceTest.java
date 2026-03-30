package com.example.replicantrecords.services;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.verify;

import com.example.replicantrecords.domain.AlbumsRepo;
import com.example.replicantrecords.domain.Artist;
import com.example.replicantrecords.domain.Albums;
import com.example.replicantrecords.domain.ArtistRepository;
import com.example.replicantrecords.domain.WriteAlbumDTO;
import com.example.replicantrecords.service.AlbumsServiceImpl;

@ExtendWith(MockitoExtension.class)
public class AlbumServiceTest {

	//Our mock repository for testing the service
	@Mock
	private ArtistRepository artistRepo;
	
	@Mock
	private AlbumsRepo albumRepo;
	
	// Injects mocks
	@InjectMocks
	private AlbumsServiceImpl albumsServiceImpl;
	
	@Test
	@DisplayName("Ensures findAll retrieves all album from the repository")
	void getAllAlbumTest() {
		Artist artistOne = new Artist("Metallica", 2000);
		Artist artistTwo = new Artist("Alice in Chains", 2001);
		
		// Mock albums
		Albums albumOne = new Albums("Hello World", 2001, "Metal", artistOne);
		Albums albumTwo = new Albums("Hello World: Part 2", 2010, "Metal", artistTwo);
		
		given(albumRepo.findAll()).willReturn(List.of(albumOne, albumTwo));
		Iterable<Albums> albumList = albumsServiceImpl.findAll(); 
		assertThat(albumList).isNotNull();
		assertThat(albumList).hasSize(2);
		verify(albumRepo).findAll();
	}
	
	@Test
	@DisplayName("Attempts to update album by id")
	void updateAlbumTest() {
	    Artist artist = new Artist("Metallica", 2000);
	    Albums existing = new Albums("Hello World", 2001, "Metal", artist);

	    WriteAlbumDTO dto = new WriteAlbumDTO();
	    dto.setTitle("Rust in Peace");
	    dto.setReleaseYear(1990);
	    dto.setGenre("Thrash Metal");
	    dto.setArtistId(1L);


	    Albums updated = new Albums("Rust in Peace", 1990, "Thrash Metal", artist);

	    given(albumRepo.findById(1L)).willReturn(java.util.Optional.of(existing));
	    given(artistRepo.findById(1L)).willReturn(java.util.Optional.of(artist));
	    given(albumRepo.save(existing)).willReturn(updated);

	    Albums result = albumsServiceImpl.update(1L, dto);

	    assertNotNull(result);
	    assertEquals("Rust in Peace", result.getTitle());
	    assertEquals(1990, result.getReleaseYear());
	    assertEquals("Thrash Metal", result.getGenre());
	    assertEquals(artist, result.getArtist());

	    verify(albumRepo).findById(1L);
	    verify(artistRepo).findById(1L);
	    verify(albumRepo).save(existing);
	}
}
