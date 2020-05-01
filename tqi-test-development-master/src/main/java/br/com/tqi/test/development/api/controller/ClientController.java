package br.com.tqi.test.development.api.controller;

import br.com.tqi.test.development.domain.service.ClientService;
import br.com.tqi.test.development.domain.model.AddressEntity;
import br.com.tqi.test.development.domain.model.ClientEntity;
import br.com.tqi.test.development.domain.repository.ClientRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author JGaray
 */
@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientRepository clientRepository;

    /**
     *
     * @return ResponseEntity
     */
    @GetMapping
    public ResponseEntity<List<ClientEntity>> getAllClient() {
        return ResponseEntity.ok(clientRepository.findAll());
    }

    /**
     *
     * @param id
     * @return ResponseEntity
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientEntity> getClientById(@PathVariable("id") Long id) {
        Optional<ClientEntity> client = clientRepository.findById(id);

        if (client.isPresent()) {
            return ResponseEntity.ok(client.get());
        }
        return ResponseEntity.notFound().build();
    }

    /**
     *
     * @param clientEntity
     * @return ResponseEntity
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientEntity> saveNewClient(@RequestBody ClientEntity clientEntity) {
        return ResponseEntity.ok(clientService.saveNewClient(clientEntity));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/{id}/change-address/{idAddress}")
    public ResponseEntity<Void> changeClientAddress(@PathVariable("id") Long id, @PathVariable("idAddress") Long idAddress, @RequestBody AddressEntity addressEntity) {
        clientService.changeClientAddress(id, idAddress, addressEntity);
        return ResponseEntity.noContent().build();
    }

}
