package com.example.replicantrecords.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.replicantrecords.domain.Albums;
import com.example.replicantrecords.domain.AlbumsRepo;
import com.example.replicantrecords.domain.Artist;
import com.example.replicantrecords.domain.ArtistRepository;
import com.example.replicantrecords.domain.WriteAlbumDTO;

@Service
public class AlbumsServiceImpl implements AlbumsService {

	private AlbumsRepo albumsRepo;
	private ArtistRepository artistRepository;

	public AlbumsServiceImpl(AlbumsRepo albumsRepo, ArtistRepository artistRepository) {
	    this.albumsRepo = albumsRepo;
	    this.artistRepository = artistRepository;
	}


	@Override
	@Transactional
	public Albums save(WriteAlbumDTO dto) {
		Artist artist = artistRepository.findById(dto.getArtistId())
		.orElseThrow(() -> new RuntimeException("Artist not found"));
		Albums album = new Albums(dto.getTitle(), dto.getReleaseYear(), dto.getGenre(), artist);
		return albumsRepo.save(album);
	}

	@Override
	@Transactional
	public Optional<Albums> findByID(Long id) {
		return albumsRepo.findById(id);
	}

	@Override
	@Transactional
	public Albums update(Long id, WriteAlbumDTO dto) {
		Albums existing = albumsRepo.findById(id).orElseThrow(() -> new RuntimeException("Album not found"));																			
		existing.setTitle(dto.getTitle());
		existing.setReleaseYear(dto.getReleaseYear());
		existing.setGenre(dto.getGenre());
		Artist artist = artistRepository.findById(dto.getArtistId()).orElseThrow(() -> new RuntimeException("Artist not found"));
		existing.setArtist(artist);
		return albumsRepo.save(existing);
	}

	@Override
	@Transactional
	public void deleteByID(Long id) {
		// Checks if the album exists, and if true it deletes it from our database
		if(albumsRepo.findById(id).isPresent()) {
			albumsRepo.deleteById(id);
			return;
		}
		throw new AlbumNotFoundException("Album with id: " + id + " not found!");
	}

	@Override
	@Transactional
	public Iterable<Albums> findAll() {
		return albumsRepo.findAll();
	}

	@Override
	@Transactional
	public List<Albums> findByAlbumNameAndYearFormed(String title, int releaseYear) {
		return albumsRepo.findByTitleAndReleaseYear(title, releaseYear);
	}

	@Override
	@Transactional
	public List<Albums> findReleaseYearsBetween(int low, int high) {
		return albumsRepo.findReleaseYearBetween(low, high);
	}

}
