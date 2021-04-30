package br.com.gerenciadorDeClientes.bean;

import br.com.gerenciadorDeClientes.dto.PessoaJuridicaDto;
import br.com.gerenciadorDeClientes.exception.BaseException;
import br.com.gerenciadorDeClientes.service.PessoaJuridicaService;
import br.com.gerenciadorDeClientes.util.MessageUtil;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named(value = "pessoaJuridicaBean")
@ViewScoped
public class PessoaJuridicaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private PessoaJuridicaDto clienteSelecionado;

    @Inject
    private PessoaJuridicaService service;

    private List<PessoaJuridicaDto> clientes;

    private boolean btExcluirRendered;

    @PostConstruct
    public void inicializar(){
        listarTodos();
    }

    public void salvarOuAlterarPessoaJuridica() {
        try {
            service.salvarOuAlterarPessoaJuridica(clienteSelecionado);
            this.listarTodos();
            this.resetCliente();
        } catch (BaseException e) {
            MessageUtil.mensagemAtencao(e.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void excluirPessoaJuridica() {
        try {
            service.excluirPessoaJuridica(clienteSelecionado);
            this.listarTodos();
            this.resetCliente();
        } catch (BaseException e) {
            MessageUtil.mensagemAtencao(e.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void resetCliente() {
        this.clienteSelecionado = new PessoaJuridicaDto();
        btExcluirRendered = false;
    }

    public void buscarPessoaJuridica() {
        clientes = service.buscar(clienteSelecionado);
    }

    public void listarTodos() {
        this.clientes = service.listarTodos();
    }

    public List<PessoaJuridicaDto> getClientes() {
        return clientes;
    }

    public void setClientes(List<PessoaJuridicaDto> clientes) {
        this.clientes = clientes;
    }

    public PessoaJuridicaDto getClienteSelecionado() {
        return clienteSelecionado;
    }

    public void setClienteSelecionado(PessoaJuridicaDto clienteSelecionado) {
        this.clienteSelecionado = clienteSelecionado;
    }

    public boolean isBtExcluirRendered() {
        return btExcluirRendered;
    }

    public void setBtExcluirRendered(boolean btExcluirRendered) {
        this.btExcluirRendered = btExcluirRendered;
    }
}
