package com.carbigdata.core.storage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.minio.MinioClient;

@Configuration
public class MinioConfig {

	@Bean
	public MinioClient minioClient(MinioProperties minioProperties) {
		return MinioClient.builder()
				.endpoint(minioProperties.getUrl())
				.credentials(minioProperties.getAccessKey(), minioProperties.getAccessSecret())
				.build();
	}
}
