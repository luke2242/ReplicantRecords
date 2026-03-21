package com.example.replicantrecords.domain;

import lombok.Data;

@Data
public class WriteAlbumDTO {
	
    private Long id;
    private String title;
    private int releaseYear;
    private Long artistId;

}
