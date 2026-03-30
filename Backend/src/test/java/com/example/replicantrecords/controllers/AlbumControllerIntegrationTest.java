package com.example.replicantrecords.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.example.replicantrecords.domain.Albums;
import com.example.replicantrecords.domain.AlbumsRepo;
import com.example.replicantrecords.domain.Artist;
import com.example.replicantrecords.domain.ArtistRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class AlbumControllerIntegrationTest {

    @Autowired
    private MockMvc mockMVC;

    @Autowired
    private AlbumsRepo albumRepo;

    @Autowired
    private ArtistRepository artistRepo;
    
    @BeforeEach
    public void cleanDB() {
        albumRepo.deleteAll();
        artistRepo.deleteAll();
    }

    @Test
    public void testPostAlbum() throws Exception {

        Artist artist = artistRepo.save(new Artist("Hello World", 1980));

        mockMVC.perform(post("/api/albums")
                .content("{\"title\": \"Voices\", \"releaseYear\": 1982, \"genre\": \"Pop\", \"artistId\": " + artist.getID() + "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetAlbums() throws Exception {

        Artist a1 = artistRepo.save(new Artist("Metallica", 2000));
        Artist a2 = artistRepo.save(new Artist("Alice in Chains", 2001));

        albumRepo.save(new Albums("John Doe", 5000, "Metal", a1));
        albumRepo.save(new Albums("John Doe", 5000, "Metal", a2));

        mockMVC.perform(get("/api/albums/album"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("John Doe"))
                .andExpect(jsonPath("$[1].title").value("John Doe"));
    }

    @Test
    public void testDeleteAlbum() throws Exception {

        Artist artist = artistRepo.save(new Artist("Hello", 2000));
        Albums album = albumRepo.save(new Albums("Hello World", 2000, "Electronic", artist));

        mockMVC.perform(delete("/api/albums/" + album.getID()))
                .andExpect(status().isNoContent());
    }
}
