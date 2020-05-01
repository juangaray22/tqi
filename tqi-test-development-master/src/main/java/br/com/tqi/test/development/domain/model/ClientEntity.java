package br.com.tqi.test.development.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author JGaray
 *
 */
@Entity
@Table(name = "CLIENT")
public class ClientEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "nome")
    private String nome;

    @Column(name = "sexo")
    private String sexo;

    @OneToOne(mappedBy = "client", fetch = FetchType.LAZY)
    private AddressEntity addressEntity;

    public ClientEntity() {
        super();
    }

    public ClientEntity(ClientEntity clientEntity) {
        this.id = clientEntity.getId();
        this.cpf = clientEntity.getCpf();
        this.nome = clientEntity.getNome();
        this.sexo = clientEntity.getSexo();
        this.addressEntity = null;
    }

    public ClientEntity(Long id) {
        this.id = id;
    }

    public ClientEntity(Long id, String cpf, String nome, String sexo, AddressEntity addressEntity) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.sexo = sexo;
        this.addressEntity = addressEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public AddressEntity getAddressEntity() {
        return addressEntity;
    }

    public void setAddressEntity(AddressEntity addressEntity) {
        this.addressEntity = addressEntity;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ClientEntity other = (ClientEntity) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
