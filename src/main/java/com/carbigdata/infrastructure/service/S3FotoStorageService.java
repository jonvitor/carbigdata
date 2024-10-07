package com.carbigdata.infrastructure.service;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carbigdata.core.storage.MinioProperties;
import com.carbigdata.domain.model.FotoOcorrencia;
import com.carbigdata.domain.model.Ocorrencia;
import com.carbigdata.domain.model.exception.OcorrenciaInexistenteException;
import com.carbigdata.domain.model.repository.OcorrenciaRepository;
import com.carbigdata.domain.service.FotoOcorrenciaService;
import com.carbigdata.domain.service.OcorrenciaService;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import jakarta.transaction.Transactional;

@Service
public class S3FotoStorageService {

	@Autowired
	private MinioClient minioClient;

	@Autowired
	private MinioProperties minioProperties;
	
	@Autowired
	private FotoOcorrenciaService fotoOcorrenciaService;
	
	@Autowired
	private OcorrenciaRepository ocorrenciaRepository;

	@Transactional
	public String enviarFotoOcorrencia(String nomeFoto, FotoOcorrencia fotoOcorrencia, Long ocorrenciaId) {
		var nomeArquivo = gerarNomeArquivo(nomeFoto);
        byte[] imagemBytes = Base64.getDecoder().decode(fotoOcorrencia.getDscHash());
        var inputStream = new ByteArrayInputStream(imagemBytes);

		Optional<Ocorrencia> ocorrencia = ocorrenciaRepository.findById(ocorrenciaId);
		
		if (ocorrencia.isEmpty()) {
			throw new OcorrenciaInexistenteException("Ocorrencia inexistente!");
		}
        
		try {
			minioClient.putObject(PutObjectArgs.builder()
					.bucket(minioProperties.getBucketName())
					.object(nomeArquivo)
					.stream(inputStream, imagemBytes.length, -1)
					.contentType("image/jpeg")
					.build());
			
			var caminhoBucket = gerarCaminhoBucket(nomeArquivo);
			
			fotoOcorrencia.setDscPathBucket(caminhoBucket);
			fotoOcorrencia.setOcorrencia(ocorrencia.get());
			
			fotoOcorrenciaService.salvar(fotoOcorrencia);
		} catch (Exception e) {
			System.out.println(e);
		}

		return "File uploaded successfully: " + nomeArquivo;
	}
	
	private String gerarNomeArquivo(String nomeFoto) {
		return UUID.randomUUID().toString() + "_" + nomeFoto;
	}
	
	private String gerarCaminhoBucket(String nomeArquivo) {
		return minioProperties.getUrl() + "/browser/" + nomeArquivo;
	}
	
}
