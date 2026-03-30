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

import com.example.replicantrecords.domain.Artist;
import com.example.replicantrecords.domain.ArtistRepository;
import com.example.replicantrecords.domain.WriteArtistDTO;
import com.example.replicantrecords.service.ArtistServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ArtistServiceTest {

	//Our mock repository for testing the service
	@Mock
	private ArtistRepository artistRepo;
	
	// Injects mocks
	@InjectMocks
	private ArtistServiceImpl artistServiceImpl;
	
	@Test
	@DisplayName("Ensures findAll retrieves all artists from the repository")
	void getAllArtistsTest() {
		Artist artistOne = new Artist("Metallica", 2000);
		Artist artistTwo = new Artist("Alice in Chains", 2001);
		given(artistRepo.findAll()).willReturn(List.of(artistOne, artistTwo));
		Iterable<Artist> artistList = artistServiceImpl.findAll(); 
		assertThat(artistList).isNotNull();
		assertThat(artistList).hasSize(2);
		verify(artistRepo).findAll();
	}
	
	@Test
	@DisplayName("Attempts to update artist by id")
	void updateArtistTest() {
	    Artist existing = new Artist("Metallica", 2000);

	    WriteArtistDTO dto = new WriteArtistDTO();
	    dto.setArtistName("Megadeth");
	    dto.setYearFormed(1983);

	    Artist updated = new Artist("Megadeth", 1983);

	    given(artistRepo.findById(1L)).willReturn(java.util.Optional.of(existing));
	    given(artistRepo.save(existing)).willReturn(updated);

	    Artist result = artistServiceImpl.update(1L, dto);

	    assertNotNull(result);
	    assertEquals("Megadeth", result.getArtistName());
	    assertEquals(1983, result.getYearFormed());

	    verify(artistRepo).findById(1L);
	    verify(artistRepo).save(existing);
	}
}
