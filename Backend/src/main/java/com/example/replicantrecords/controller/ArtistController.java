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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.replicantrecords.domain.Albums;
import com.example.replicantrecords.domain.AlbumsDTO;
import com.example.replicantrecords.domain.Artist;
import com.example.replicantrecords.domain.ArtistDTO;
import com.example.replicantrecords.domain.WriteAlbumDTO;
import com.example.replicantrecords.domain.WriteArtistDTO;
import com.example.replicantrecords.service.ArtistNotFoundException;
import com.example.replicantrecords.service.ArtistService;

@RestController
@RequestMapping("/api/artists")
@CrossOrigin
public class ArtistController {
	
	@Autowired
	private ArtistService artistService;
	
	
	// CRUD Functions
	@GetMapping("/all")
	public List<ArtistDTO> fetchArtists(){
		
		List<ArtistDTO> artistDTO = new ArrayList<>();
		for(Artist artist : artistService.findAll()) {
			artistDTO.add(new ArtistDTO(artist));
		}
		
		return artistDTO;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ArtistDTO save(@RequestBody WriteArtistDTO dto) {
		Artist saved = artistService.save(dto);
		return new ArtistDTO(saved);
	}

	@GetMapping("/{id:\\d+}")
	public ResponseEntity<ArtistDTO> getArtistByID(@PathVariable Long id){
		
		Optional<Artist> result = artistService.findByID(id);
		
		if(result.isPresent()) {
			
		ArtistDTO artistDTO =  new ArtistDTO(result.get());
		return ResponseEntity.ok(artistDTO);
		}
		
		throw new ArtistNotFoundException("Artist with id: " + id + "does not exist");
	}
	
    @PutMapping("/{id}")
    public ArtistDTO update(@PathVariable Long id, @RequestBody WriteArtistDTO update) {
        Artist saved = artistService.update(id, update);
        return new ArtistDTO(saved);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        artistService.deleteByID(id);
    }
    
    // Queries
    @GetMapping("/artistNameAndYearFormed/{artistName}/{yearFormed}")
    public List<ArtistDTO> findByArtistNameAndYearFormed(
            @PathVariable String artistName,
            @PathVariable int yearFormed) {

        List<Artist> artists = artistService
                .findByArtistNameAndYearFormed(artistName, yearFormed);

        List<ArtistDTO> dtos = new ArrayList<>();

        for (Artist artist : artists) {
            dtos.add(new ArtistDTO(artist));
        }

        return dtos;
    }
    
    @GetMapping("/artists/yearBetween/{low}/{high}")
    public List<ArtistDTO> findArtistsYearBetween(
            @PathVariable int low,
            @PathVariable int high) {

        List<Artist> artists = artistService.findArtistsYearBetween(low, high);

        List<ArtistDTO> dtos = new ArrayList<>();
        for (Artist artist : artists) {
            dtos.add(new ArtistDTO(artist));
        }

        return dtos;
    }

}
