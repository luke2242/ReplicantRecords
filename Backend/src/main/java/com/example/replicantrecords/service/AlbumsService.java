package com.example.replicantrecords.service;

import java.util.List;
import java.util.Optional;

import com.example.replicantrecords.domain.Albums;
import com.example.replicantrecords.domain.WriteAlbumDTO;

public interface AlbumsService {

	// CRUD Functionalities
	Albums save(WriteAlbumDTO album);

	Optional<Albums> findByID(Long id);

	Albums update(Long id, WriteAlbumDTO updated);

	void deleteByID(Long id);

	Iterable<Albums> findAll();

	// Queries
	List<Albums> findByAlbumNameAndYearFormed(String title, int releaseYear);

	List<Albums> findReleaseYearsBetween(int low, int high);
}
