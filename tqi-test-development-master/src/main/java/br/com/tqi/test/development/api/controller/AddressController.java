package br.com.tqi.test.development.api.controller;

import br.com.tqi.test.development.domain.model.AddressEntity;
import br.com.tqi.test.development.domain.service.ClientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author JGaray
 */
@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private ClientService clientService;

    @GetMapping(value = "/{cep}")
    public ResponseEntity<AddressEntity> getAddressByCEP(@PathVariable("cep") String cep) throws JsonProcessingException {
        return ResponseEntity.ok(clientService.getAddressByCep(cep));
    }
}
