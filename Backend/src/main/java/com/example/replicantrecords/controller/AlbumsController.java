package com.example.replicantrecords.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.replicantrecords.domain.Albums;
import com.example.replicantrecords.domain.AlbumsDTO;
import com.example.replicantrecords.domain.WriteAlbumDTO;
import com.example.replicantrecords.service.AlbumsService;
import com.example.replicantrecords.service.AlbumNotFoundException;

@RestController
@RequestMapping("/api/albums")
@CrossOrigin
public class AlbumsController {

    @Autowired
    private AlbumsService albumsService;

    // CRUD Functions

    @GetMapping("/album")
    public List<AlbumsDTO> fetchAlbums() {

        List<AlbumsDTO> albumDTOs = new ArrayList<>();

        for (Albums album : albumsService.findAll()) {
            albumDTOs.add(new AlbumsDTO(album));
        }

        return albumDTOs;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlbumsDTO> getAlbumByID(@PathVariable Long id) {

        Optional<Albums> result = albumsService.findByID(id);

        if (result.isPresent()) {
            AlbumsDTO albumDTO = new AlbumsDTO(result.get());
            return ResponseEntity.ok(albumDTO);
        }

        throw new AlbumNotFoundException("Album with id: " + id + " does not exist");
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AlbumsDTO save(@RequestBody WriteAlbumDTO album) {

        Albums saved = albumsService.save(album);
        return new AlbumsDTO(saved);
    }

    @PutMapping("/{id}")
    public AlbumsDTO update(@PathVariable Long id,
                           @RequestBody WriteAlbumDTO update) {

        Albums updatedAlbum = albumsService.update(id, update);
        return new AlbumsDTO(updatedAlbum);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        albumsService.deleteByID(id);
    }

    
    // Queries

    @GetMapping("/albumNameAndReleaseYear/{title}/{releaseYear}")
    public List<AlbumsDTO> findByAlbumNameAndYearFormed(
            @PathVariable String title,
            @PathVariable int releaseYear) {

        List<Albums> albums =
                albumsService.findByAlbumNameAndYearFormed(title, releaseYear);

        List<AlbumsDTO> dtos = new ArrayList<>();

        for (Albums album : albums) {
            dtos.add(new AlbumsDTO(album));
        }

        return dtos;
    }

    @GetMapping("/releaseYearBetween/{low}/{high}")
    public List<AlbumsDTO> findReleaseYearsBetween(
            @PathVariable int low,
            @PathVariable int high) {

        List<Albums> albums =
                albumsService.findReleaseYearsBetween(low, high);

        List<AlbumsDTO> dtos = new ArrayList<>();

        for (Albums album : albums) {
            dtos.add(new AlbumsDTO(album));
        }

        return dtos;
    }
}