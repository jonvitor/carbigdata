package com.carbigdata;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import com.carbigdata.domain.model.Cliente;
import com.carbigdata.domain.model.Endereco;
import com.carbigdata.domain.model.FotoOcorrencia;
import com.carbigdata.domain.model.Ocorrencia;
import com.carbigdata.domain.service.OcorrenciaService;
import com.carbigdata.util.DatabaseCleaner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class CadastroFotoIT {

	@LocalServerPort
	private int port;

	@Autowired
	private DatabaseCleaner cleaner;
	
	@Autowired
	private OcorrenciaService ocorrenciaService;
	
	private Ocorrencia ocorrenciaSalva;
	private Ocorrencia ocorrenciaFinalizada;

	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/ocorrencias/foto";
		
		cleaner.clearTables();
		preparaDados();
	}

	@Test
	void deveRetornar201_QuandoAdicionarFotoAocorrencia() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

		RestAssured.given()
						.pathParam("ocorrenciaId", ocorrenciaSalva.getId())
						.body(montarBody())
						.contentType(ContentType.JSON).accept(ContentType.JSON)
					.when()
						.post("{ocorrenciaId}")
					.then()
						.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	void deveRetornar404_QuandoAdicionarFotoAocorrenciaInexistente() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

		RestAssured.given()
						.pathParam("ocorrenciaId", 0)
						.body(montarBody())
						.contentType(ContentType.JSON).accept(ContentType.JSON)
					.when()
						.post("{ocorrenciaId}")
					.then()
						.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	@Test
	void deveRetornar400_QuandoAdicionarFotoAocorrenciaFinalizada() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

		RestAssured.given()
						.pathParam("ocorrenciaId", ocorrenciaFinalizada.getId())
						.body(montarBody())
						.contentType(ContentType.JSON).accept(ContentType.JSON)
					.when()
						.post("{ocorrenciaId}")
					.then()
						.statusCode(HttpStatus.BAD_REQUEST.value());
	}

	private void preparaDados() {
		var cliente = new Cliente();
		
		cliente.setCpf("99999999999");
		cliente.setDataNascimento(new Date());
		cliente.setDataCriacao(OffsetDateTime.now());
		cliente.setNome("João");
		
		var endereco = new Endereco();
		
	    endereco.setLogradouro("Avenida Paulista");
	    endereco.setBairro("Bela Vista");
	    endereco.setCep("01311000");
	    endereco.setCidade("São Paulo");
	    endereco.setEstado("SP");
		
	    var ocorrencia = new Ocorrencia();
	    
	    ocorrencia.setCliente(cliente);
	    ocorrencia.setEndereco(endereco);
	    ocorrencia.setDataOcorrencia(OffsetDateTime.now(ZoneOffset.UTC));
	    
	    var ocorrencia2 = new Ocorrencia();
	    
	    ocorrencia2.setCliente(cliente);
	    ocorrencia2.setEndereco(endereco);
	    ocorrencia2.setDataOcorrencia(OffsetDateTime.now(ZoneOffset.UTC));
	    
	    var foto = new FotoOcorrencia();
	    
	    foto.setDscHash("SDFSDFSDGSDGSD");
	    
	    ocorrenciaSalva = ocorrenciaService.salvar(ocorrencia, foto, "evidencia01.png");
	    ocorrenciaFinalizada = ocorrenciaService.salvar(ocorrencia2, foto, "evidencia01.png");
	    ocorrenciaService.finalizar(ocorrenciaFinalizada.getId());
	}
	
	private String montarBody() {
		return "{"
					+ "\"nomeFoto\": \"evidencia02.png\","
					+ "\"dscHash\": \"SDFSDFSDGSDGSD\""
				+ "}";
	}
}
