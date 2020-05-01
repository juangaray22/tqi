package br.com.tqi.test.development.domain.service;

import br.com.tqi.test.development.domain.exception.EntidadeNaoEncontradaException;
import br.com.tqi.test.development.domain.exception.NegocioException;
import br.com.tqi.test.development.domain.model.AddressEntity;
import br.com.tqi.test.development.domain.model.ClientEntity;
import br.com.tqi.test.development.domain.repository.AddressRepository;
import br.com.tqi.test.development.domain.repository.ClientRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author JGaray
 */
@Service
public class ClientService {

    private ClientRepository clientRepository;

    private AddressRepository addressRepository;

    private RestTemplate restTemplate;

    /**
     *
     * @param clientRepository
     * @param addressRepository
     * @param restTemplate
     */
    @Autowired
    public ClientService(ClientRepository clientRepository, AddressRepository addressRepository, RestTemplate restTemplate) {
        this.clientRepository = clientRepository;
        this.addressRepository = addressRepository;
        this.restTemplate = restTemplate;
    }

    /**
     *
     * @param clientEntity
     * @return ClientEntity
     */
    public ClientEntity saveNewClient(ClientEntity clientEntity) {
        AddressEntity addressEntity = clientEntity.getAddressEntity();

        ClientEntity newClient = clientRepository.save(new ClientEntity(clientEntity));

        if (addressEntity.getId() == null) {
            addressEntity.setClient(newClient);
            addressRepository.save(addressEntity);
        }

        newClient.setAddressEntity(addressEntity);

        return newClient;
    }

    /**
     *
     * @param idClient
     * @param idAddress
     * @param addressEntity
     */
    public void changeClientAddress(Long idClient, Long idAddress, AddressEntity addressEntity)  {

        ClientEntity clientEntity = clientRepository.findById(idClient).orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente nao encontrado"));

        AddressEntity actualAddress = addressRepository.findById(idAddress).orElseThrow(()-> new EntidadeNaoEncontradaException("Endereco nao encontrado"));

        if (actualAddress.getClient().equals(idClient)) {
            throw new NegocioException("Endereço de outro cliente");
        }

        addressEntity.setClient(clientEntity);
        addressEntity.setId(actualAddress.getId());

        addressRepository.save(addressEntity);
    }

    /**
     *
     * @param  cep string
     * @return AddressEntity
     * @throws JsonProcessingException
     */
    public AddressEntity getAddressByCep(String cep) throws JsonProcessingException {
        ResponseEntity<String> reAddress = restTemplate.getForEntity("https://viacep.com.br/ws/" + cep + "/json", String.class);

        ObjectNode objectNode = new ObjectMapper().readValue(reAddress.getBody(), ObjectNode.class);

        AddressEntity addressEntity = new AddressEntity();

        if (objectNode.has("cep")) {
            addressEntity.setCep(objectNode.get("cep").toString().replace("\"", ""));
        }

        if (objectNode.has("logradouro")) {
            addressEntity.setEndereco(objectNode.get("logradouro").toString().replace("\"", ""));
        }

        if (objectNode.has("bairro")) {
            addressEntity.setBairro(objectNode.get("bairro").toString().replace("\"", ""));
        }

        if (objectNode.has("localidade")) {
            addressEntity.setCidade(objectNode.get("localidade").toString().replace("\"", ""));
        }

        if (objectNode.has("uf")) {
            addressEntity.setEstado(objectNode.get("uf").toString().replace("\"", ""));
        }

        addressEntity.setPais("Brasil");

        return addressEntity;
    }
}
