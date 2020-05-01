package br.com.tqi.test.development.domain.repository;

import br.com.tqi.test.development.domain.model.AddressEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<AddressEntity, Long> {
}
