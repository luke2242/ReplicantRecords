package com.example.replicantrecords.domain;

import lombok.Data;

@Data
public class WriteArtistDTO {
	
    private Long id;
    private String artistName;
    private int yearFormed;
}
