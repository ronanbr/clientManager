package br.com.gerenciadorDeClientes.dto;

import javax.enterprise.inject.Model;
import java.io.Serializable;
import java.util.Date;

@Model
public class PessoaFisicaDto extends PessoaDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;
    private String cpf;
    private Date dtNascimento;
    private String telefone;
    private String email;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
