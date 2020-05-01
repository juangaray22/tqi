package br.com.tqi.test.development.domain.repository;

import br.com.tqi.test.development.domain.model.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
}
