package com.carbigdata.api.assembler;


import java.util.Collection;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carbigdata.api.model.ClienteModel;
import com.carbigdata.domain.model.Cliente;

import lombok.AllArgsConstructor;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class ClienteModelAssembler {

	private ModelMapper modelMapper;
	
	public ClienteModel toModel(Cliente cliente) {
		return modelMapper.map(cliente, ClienteModel.class);
	}
	
	public List<ClienteModel> toCollectionModel(Collection<Cliente> clientes) {
		return clientes.stream()
				.map(this::toModel)
				.toList();
	}
	
}