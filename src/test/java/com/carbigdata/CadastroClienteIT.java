package com.carbigdata;

import java.time.OffsetDateTime;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import com.carbigdata.domain.model.Cliente;
import com.carbigdata.domain.model.repository.ClienteRepository;
import com.carbigdata.util.DatabaseCleaner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class CadastroClienteIT {

	@LocalServerPort
	private int port;

	@Autowired
	private DatabaseCleaner cleaner;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/clientes";
		
		cleaner.clearTables();
		preparaDados();
	}

	@Test
	void deveRetornar200_QuandoConsultarClientes() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		
		RestAssured.given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	void deveRetornar201_QuandoAdicionarCliente() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

		RestAssured.given()
						.body(montarBody())
						.contentType(ContentType.JSON)
						.accept(ContentType.JSON)
					.when()
						.post()
					.then()
						.statusCode(HttpStatus.CREATED.value());
		
	}
	
	@Test
	void deveRetornar400_QuandoAdicionarClienteComCpfInvalido() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

		RestAssured.given()
						.body(montarBodyInvalido())
						.contentType(ContentType.JSON)
						.accept(ContentType.JSON)
					.when()
						.post()
					.then()
						.statusCode(HttpStatus.BAD_REQUEST.value());
		
	}
	
	private void preparaDados() {
		var cliente = new Cliente();
		
		cliente.setCpf("99999999999");
		cliente.setDataNascimento(new Date());
		cliente.setDataCriacao(OffsetDateTime.now());
		cliente.setNome("João");
		
		var cliente2 = new Cliente();
		
		cliente2.setCpf("88888888888");
		cliente2.setDataNascimento(new Date());
		cliente2.setDataCriacao(OffsetDateTime.now());
		cliente2.setNome("Alice");
		
		clienteRepository.save(cliente);
		clienteRepository.save(cliente2);
	}
	
	private String montarBody() {
		return "{"
				+ "\"nome\": \"Rita\","
				+ "\"cpf\": \"77777777777\","
				+ "\"dataNascimento\": \"2024-10-05T03:00:00.000+00:00\""
			+ "}";
	}
	
	private String montarBodyInvalido() {
		return "{"
				+ "\"nome\": \"Rita\","
				+ "\"dataNascimento\": \"2024-10-05T03:00:00.000+00:00\""
			+ "}";
	}

}
