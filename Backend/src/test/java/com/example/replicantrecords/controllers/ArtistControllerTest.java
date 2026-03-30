package com.example.replicantrecords.controllers;

import static org.assertj.core.api.Assertions.assertThat;
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

import com.example.replicantrecords.domain.Artist;
import com.example.replicantrecords.domain.ArtistRepository;
import com.example.replicantrecords.service.ArtistNotFoundException;
import com.example.replicantrecords.service.ArtistServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ArtistControllerTest {

    @Mock
    private ArtistRepository artistRepo;

    @InjectMocks
    private ArtistServiceImpl artistServiceImpl;

    @Test
    @DisplayName("Throws correct exception when deleting a non-existent artist")
    void testDeleteIncorrectArtist() {
        Long id = 999L;

        ArtistNotFoundException ex = assertThrows(
                ArtistNotFoundException.class,
                () -> artistServiceImpl.deleteByID(id)
        );

        assertTrue(ex.getMessage().contains("Artist with id: " + id + " not found!"));
    }

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
}
