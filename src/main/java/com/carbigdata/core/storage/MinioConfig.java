package com.carbigdata.core.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.minio.MinioClient;

@Configuration
public class MinioConfig {

	@Autowired
	private MinioProperties minioProperties;

	@Bean
	public MinioClient minioClient() {
		return MinioClient.builder()
				.endpoint(minioProperties.getUrl())
				.credentials(minioProperties.getAccessKey(), minioProperties.getAccessSecret())
				.build();
	}
}
