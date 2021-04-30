package br.com.gerenciadorDeClientes.dto;

import javax.enterprise.inject.Model;
import java.io.Serializable;

@Model
public class PessoaJuridicaDto extends PessoaDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String razaoSocial;
    private String nomeFantasia;
    private String cnpj;
    private String site;

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

}
