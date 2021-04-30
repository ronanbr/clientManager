package br.com.gerenciadorDeClientes.dto;

import java.io.Serializable;

public abstract class PessoaDto implements Serializable {

    private static final long serialVersionUID = 1L;

    protected Long id;

    private String tipo;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
