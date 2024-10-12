package com.carbigdata.infrastructure.service;

import java.io.ByteArrayInputStream;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carbigdata.core.storage.MinioProperties;
import com.carbigdata.domain.model.FotoOcorrencia;
import com.carbigdata.domain.model.Ocorrencia;
import com.carbigdata.domain.model.exception.OcorrenciaFinalizadaException;
import com.carbigdata.domain.model.exception.OcorrenciaInexistenteException;
import com.carbigdata.domain.model.repository.FotoOcorrenciaRepository;
import com.carbigdata.domain.model.repository.OcorrenciaRepository;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class S3FotoStorageService {

	private MinioClient minioClient;
	private MinioProperties minioProperties;
	private FotoOcorrenciaRepository fotoOcorrenciaRepository;
	private OcorrenciaRepository ocorrenciaRepository;

	@Transactional
	public String enviarFotoOcorrencia(FotoOcorrencia fotoOcorrencia, Long ocorrenciaId) {
		var nomeArquivo = gerarNomeArquivo();
        byte[] imagemBytes = Base64.getDecoder().decode(fotoOcorrencia.getDscHash());
        var inputStream = new ByteArrayInputStream(imagemBytes);

		Optional<Ocorrencia> ocorrencia = ocorrenciaRepository.findById(ocorrenciaId);
		
		if (ocorrencia.isEmpty()) {
			throw new OcorrenciaInexistenteException();
		}
		
		if (ocorrencia.get().getStaOcorrencia().getDescricao().equals("Finalizada")) {
			throw new OcorrenciaFinalizadaException();
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
			
			fotoOcorrenciaRepository.save(fotoOcorrencia);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "File uploaded successfully: " + nomeArquivo;
	}
	
	private String gerarNomeArquivo() {
		return UUID.randomUUID().toString() + ".jpeg";
	}
	
	private String gerarCaminhoBucket(String nomeArquivo) {
		return minioProperties.getUrl() + "/browser/"  + minioProperties.getBucketName() + "/" + nomeArquivo;
	}
	
}
