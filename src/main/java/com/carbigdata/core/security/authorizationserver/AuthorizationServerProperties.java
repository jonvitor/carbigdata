package com.carbigdata.core.security.authorizationserver;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
@Validated
@ConfigurationProperties("authorization.server")
public class AuthorizationServerProperties {

	@NotBlank
	private String clientId;
	
	@NotBlank
	private String clientSecret;
	
}
