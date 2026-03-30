package com.example.replicantrecords;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.replicantrecords.controller.AlbumsController;

@SpringBootTest
public class ReplicantRecordsAppTest {
	
	@Autowired
	private AlbumsController albumController;
	
	@Test
	@DisplayName("Check if album controller loads correctly")
	void contextLoads() {
		assertThat(albumController).isNotNull();
	}

}
