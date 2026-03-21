package com.example.replicantrecords.domain;

import lombok.Data;

@Data
public class WriteArtistDTO {
	
    public Long id;
    public String artistName;
    public int yearFormed;
}
