package projectmcm.model.domain;

import java.io.Serializable;

public class Endereco implements Serializable {
    private int idEndereco;
    private String cidade;
    private String logradouro;
    private int numero;
    private String complemento;
    private String bairro;
    private String estado;

    public int getIdEndereco() {
        return idEndereco;
    }

    public String getCidade() {
        return cidade;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public String getEstado() {
        return estado;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
