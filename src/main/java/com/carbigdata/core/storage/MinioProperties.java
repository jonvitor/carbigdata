package com.carbigdata.core.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties("minio")
public class MinioProperties {

	private String url;
	private String accessKey;
	private String accessSecret;
	private String bucketName;

}
