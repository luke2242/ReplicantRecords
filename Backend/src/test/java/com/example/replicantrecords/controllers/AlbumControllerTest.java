package com.example.replicantrecords.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.replicantrecords.domain.Albums;
import com.example.replicantrecords.domain.AlbumsRepo;
import com.example.replicantrecords.domain.Artist;
import com.example.replicantrecords.domain.ArtistRepository;
import com.example.replicantrecords.service.AlbumNotFoundException;
import com.example.replicantrecords.service.AlbumsServiceImpl;

@ExtendWith(MockitoExtension.class)
public class AlbumControllerTest {
	
	//Our mock repository for testing the service
	@Mock
	private AlbumsRepo albumRepo;
	
	@Mock
	private ArtistRepository artistRepo;
	
	@InjectMocks
	private AlbumsServiceImpl albumServiceImpl; 
	
	@Test
	@DisplayName("Checks if the correct exception is thrown, for an album that does NOT exist")
	void testDeleteIncorrectAlbum() {
		Long id = 999L;
		// Throws an exception since the album id does NOT exist
		AlbumNotFoundException ex = assertThrows(
		AlbumNotFoundException.class,
		() -> albumServiceImpl.deleteByID(id));
		assertTrue(ex.getMessage().contains("Album with id: " + id + " not found!"));
	}
	
	
	@Test
	@DisplayName("Ensures findAll retrieves all albums from the repository")
	void getAllArtistsTest() {
		// Mock artists
		Artist artistOne = new Artist("Metallica", 2000);
		Artist artistTwo = new Artist("Alice in Chains", 2001);
	
		// Mock albums
		Albums albumOne = new Albums("Hello World", 2001, "Metal", artistOne);
		Albums albumTwo = new Albums("Hello World: Part 2", 2010, "Metal", artistTwo);
		
		given(albumRepo.findAll()).willReturn(List.of(albumOne, albumTwo));
		// Adds albums to Iterable
		Iterable<Albums> artistList = albumServiceImpl.findAll(); 
		// Ensures the list is not null
		assertThat(artistList).isNotNull();
		// Checks if it's the correct size
		assertThat(artistList).hasSize(2);
		// Verifies that it's functioning as intended
		verify(albumRepo).findAll();
	}

	
	

}
