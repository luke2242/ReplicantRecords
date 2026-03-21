package com.example.replicantrecords.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.replicantrecords.domain.Artist;
import com.example.replicantrecords.domain.ArtistDTO;
import com.example.replicantrecords.domain.ArtistRepository;
import com.example.replicantrecords.domain.WriteArtistDTO;

@Service
public class ArtistServiceImpl implements ArtistService {
	
    private final ArtistRepository artistRepository;

    
    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }
    
    @Override
    @Transactional
    public Artist save(WriteArtistDTO dto) {
        Artist artist = new Artist(dto.getArtistName(), dto.getYearFormed());
        return artistRepository.save(artist);
    }



	@Override
	@Transactional
	public Optional<Artist> findByID(Long id) {
		return artistRepository.findById(id);
	}

	@Override
	@Transactional
	public Artist update(Long id, WriteArtistDTO dto) {
	    Artist existing = artistRepository.findById(id)
	        .orElseThrow(() -> new RuntimeException("Artist not found"));

	    existing.setArtistName(dto.getArtistName());
	    existing.setYearFormed(dto.getYearFormed());

	    return artistRepository.save(existing); 
	}

	@Override
	@Transactional
	public void deleteByID(Long id) {
		artistRepository.deleteById(id);
	}

	@Override
	@Transactional
	public Iterable<Artist> findAll() {
		return artistRepository.findAll();
	}

	@Override
	@Transactional
	public List<Artist> findByArtistNameAndYearFormed(String artistName, int yearFormed) {
		return artistRepository.findByArtistNameAndYearFormed(artistName, yearFormed);
	}

	@Override
	@Transactional
	public List<Artist> findArtistsYearBetween(int low, int high) {
		return artistRepository.findArtistsYearBetween(low, high);
	}

	

}
