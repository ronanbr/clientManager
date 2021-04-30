package br.com.gerenciadorDeClientes.bean;

import br.com.gerenciadorDeClientes.dto.PessoaFisicaDto;
import br.com.gerenciadorDeClientes.exception.BaseException;
import br.com.gerenciadorDeClientes.service.PessoaFisicaService;
import br.com.gerenciadorDeClientes.util.MessageUtil;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named(value = "pessoaFisicaBean")
@ViewScoped
public class PessoaFisicaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private PessoaFisicaDto clienteSelecionado;

    @Inject
    private PessoaFisicaService service;

    private List<PessoaFisicaDto> clientes;

    private boolean btExcluirRendered;

    @PostConstruct
    public void inicializar(){
        listarTodos();
    }

    public void salvarOuAlterarPessoaFisica() {
        try {
            service.salvarOuAlterarPessoaFisica(clienteSelecionado);
            this.listarTodos();
            this.resetCliente();
        } catch (BaseException e) {
            MessageUtil.mensagemAtencao(e.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void excluirPessoaFisica() {
        try {
            service.excluirPessoaFisica(clienteSelecionado);
            this.listarTodos();
            this.resetCliente();
        } catch (BaseException e) {
            MessageUtil.mensagemAtencao(e.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void resetCliente() {
        this.clienteSelecionado = new PessoaFisicaDto();
        btExcluirRendered = false;
    }

    public void buscarPessoaFisica() {
        clientes = service.buscar(clienteSelecionado);
    }

    public void listarTodos() {
        this.clientes = service.listarTodos();
    }

    public List<PessoaFisicaDto> getClientes() {
        return clientes;
    }

    public void setClientes(List<PessoaFisicaDto> clientes) {
        this.clientes = clientes;
    }

    public PessoaFisicaDto getClienteSelecionado() {
        return clienteSelecionado;
    }

    public void setClienteSelecionado(PessoaFisicaDto clienteSelecionado) {
        this.clienteSelecionado = clienteSelecionado;
    }

    public boolean isBtExcluirRendered() {
        return btExcluirRendered;
    }

    public void setBtExcluirRendered(boolean btExcluirRendered) {
        this.btExcluirRendered = btExcluirRendered;
    }
}
