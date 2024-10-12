package com.carbigdata;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
class CadastroOcorrenciaIT {

	@LocalServerPort
	private int port;

	@Autowired
	private DatabaseCleaner cleaner;
	
	@Autowired
	private OcorrenciaService ocorrenciaService;
	
	private Ocorrencia ocorrenciaSalva;

	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/ocorrencias";
		
		cleaner.clearTables();
		preparaDados();
	}

	@Test
	void deveRetornar201_QuandoAdicionarOcorrencia() {
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
	void deveRetornar200_QuandoListarOcorrencia() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

		RestAssured.given()
						.accept(ContentType.JSON)
					.when()
						.get()
					.then()
						.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	void deveRetornar200_QuandoPesquisarOcorrenciaPorNome() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

		RestAssured.given()
						.param("nome", "João")
						.accept(ContentType.JSON)
					.when()
						.get()
					.then()
						.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	void deveRetornar200_QuandoPesquisarOcorrenciaPorCidade() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

		RestAssured.given()
						.param("cidade", "São Paulo")
						.accept(ContentType.JSON)
					.when()
						.get()
					.then()
						.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	void deveRetornar200_QuandoPesquisarOcorrenciaPorData() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

		RestAssured.given()
						.param("dataOcorrencia", ocorrenciaSalva.getDataOcorrencia().toString())
						.accept(ContentType.JSON)
					.when()
						.get()
					.then()
						.statusCode(HttpStatus.OK.value());
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
	    
	    List<FotoOcorrencia> fotos = new ArrayList<>();
	    fotos.add(foto);
	    
	    ocorrenciaSalva = ocorrenciaService.salvar(ocorrencia, fotos);
	    ocorrenciaService.salvar(ocorrencia2, fotos);

	}
	
	private String montarBody() {
		return """
				{
				    "cliente": {
				        "nome": "joao",
				        "cpf": "09399061981",
				        "dataNascimento": "2024-10-05T03:00:00.000+00:00"
				    },
				    "endereco": {
				        "logradouro": "sdfsdfsd",
				        "bairro": "centro",
				        "cep": "88131420",
				        "cidade": "imbituba",
				        "estado": "sc"
				    },
				    "dataOcorrencia": "2024-12-10T12:30:00Z",
				    "fotoOcorrencia": {
				        "nomeFoto": "supertesteocorrencia.png",
				        "dscHash": "JCSAHCHSCACSAKCSAKL"
				    }
				}
				""";
	}
}
