package com.example.replicantrecords;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.replicantrecords.domain.Albums;
import com.example.replicantrecords.domain.Artist;
import com.example.replicantrecords.service.AlbumsService;
import com.example.replicantrecords.service.ArtistService;

import org.springframework.boot.CommandLineRunner;


@SpringBootApplication
public class ReplicantrecordsApplication implements CommandLineRunner {
	
	private final ArtistService artistService;
	private final AlbumsService albumsService;
	
	private static final Logger logger = LoggerFactory.getLogger(ReplicantrecordsApplication.class);
	
	public ReplicantrecordsApplication(ArtistService artistService, AlbumsService albumsService) {
		
		this.artistService = artistService;
		this.albumsService = albumsService;
		
	}

	public static void main(String[] args) {
		SpringApplication.run(ReplicantrecordsApplication.class, args);
		logger.info("Application started");
	}
	
	@Override
	public void run(String... args) throws Exception{
		

	    //Logs artists
	    for (Artist artist : artistService.findAll()) {
	        logger.info("name: {}, yearFormed: {}", artist.getArtistName(), artist.getYearFormed());
	    }
	    
	    // Returns artists based on the inputed years entered
		for (Artist artist: artistService.findArtistsYearBetween(1970, 2020))
		{
			logger.info("name: {}, yearFormed: {}", artist.getArtistName(), artist.getYearFormed());
		}
		
		//Logs albums returned from DB
	    for (Albums album : albumsService.findAll()) {
	        logger.info("title: {}, yearFormed: {}", album.getTitle(), album.getReleaseYear());
	    }
	    
	    for (Albums album : albumsService.findReleaseYearsBetween(1980, 2050)) {
	        logger.info("title: {}, yearFormed: {}", album.getTitle(), album.getReleaseYear());
	    }
		
		
	}

}
