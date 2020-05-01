package br.com.tqi.test.development.domain.model;

import br.com.tqi.test.development.domain.model.ClientEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author JGaray
 */
@Entity
@Table(name = "ADDRESS")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "endereco")
    private String endereco = "";

    @Column(name = "numero")
    private String numero = "";

    @Column(name = "complemento")
    private String complemento = "";

    @Column(name = "bairro")
    private String bairro = "";

    @Column(name = "cidade")
    private String cidade = "";

    @Column(name = "estado")
    private String estado = "";

    @Column(name = "pais")
    private String pais = "";

    @Column(name = "cep")
    private String cep = "";

    @OneToOne
    @JoinColumn(name = "id_client", columnDefinition = "id_client", referencedColumnName = "id")
    private ClientEntity client;

    public AddressEntity() {
        super();
    }

    public AddressEntity(Long id) {
        this.id = id;
    }

    public AddressEntity(Long id, String endereco, String numero, String complemento, String bairro, String cidade, String estado, String pais, String cep, ClientEntity client) {
        this.id = id;
        this.endereco = endereco;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
        this.cep = cep;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    @JsonProperty("client")
    public ClientEntity returnClientNull() {
        return null;
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
        AddressEntity other = (AddressEntity) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
