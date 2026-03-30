package com.example.replicantrecords.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.replicantrecords.domain.Artist;
import com.example.replicantrecords.domain.ArtistRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class ArtistControllerIntegrationTest {

    @Autowired
    private MockMvc mockMVC;

    @Autowired
    private ArtistRepository artistRepo;

    @BeforeEach
    public void cleanDB() {
        artistRepo.deleteAll();
    }

    @Test
    public void testPostArtist() throws Exception {
        mockMVC.perform(post("/api/artists")
                .content("{\"artistName\": \"Metallica\", \"yearFormed\": 1981}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetArtists() throws Exception {
        artistRepo.save(new Artist("Metallica", 1981));
        artistRepo.save(new Artist("Alice in Chains", 1987));

        mockMVC.perform(get("/api/artists/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].artistName").value("Metallica"))
                .andExpect(jsonPath("$[1].artistName").value("Alice in Chains"));
    }

    @Test
    public void testGetArtistById() throws Exception {
        Artist artist = artistRepo.save(new Artist("Nirvana", 1987));

        mockMVC.perform(get("/api/artists/" + artist.getID()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.artistName").value("Nirvana"))
                .andExpect(jsonPath("$.yearFormed").value(1987));
    }


    @Test
    public void testDeleteArtist() throws Exception {
        Artist artist = artistRepo.save(new Artist("Pantera", 1981));

        mockMVC.perform(delete("/api/artists/" + artist.getID()))
                .andExpect(status().isNoContent());
    }
}
