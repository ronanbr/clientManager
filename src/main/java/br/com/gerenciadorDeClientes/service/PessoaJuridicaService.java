package br.com.gerenciadorDeClientes.service;

import br.com.gerenciadorDeClientes.dao.CrudDao;
import br.com.gerenciadorDeClientes.dao.PessoaJuridicaDao;
import br.com.gerenciadorDeClientes.dto.PessoaDto;
import br.com.gerenciadorDeClientes.dto.PessoaFisicaDto;
import br.com.gerenciadorDeClientes.dto.PessoaJuridicaDto;
import br.com.gerenciadorDeClientes.entity.PessoaEntity;
import br.com.gerenciadorDeClientes.entity.PessoaFisicaEntity;
import br.com.gerenciadorDeClientes.entity.PessoaJuridicaEntity;
import br.com.gerenciadorDeClientes.exception.DaoException;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Model
public class PessoaJuridicaService extends CrudService<PessoaJuridicaEntity, Long, PessoaJuridicaDto> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private PessoaJuridicaDao pessoaJuridicaDao;

    public void salvarOuAlterarPessoaJuridica(PessoaJuridicaDto pessoaJuridica) throws DaoException {
        pessoaJuridica.setTipo(PessoaEntity.TIPO_PESSOA_JURIDICA);
        pessoaJuridicaDao.salvarOuAlterar(toEntity(pessoaJuridica));
    }

    public void excluirPessoaJuridica(PessoaJuridicaDto pessoaJuridica) throws DaoException {
        pessoaJuridicaDao.excluir(pessoaJuridica.getId());
    }

    @Override
    protected CrudDao<PessoaJuridicaEntity, Long> getDao() {
        return pessoaJuridicaDao;
    }

    //TODO Passar para crud
    public List<PessoaJuridicaDto> listarTodos() {
        return converterListaEntityParaListaDto(pessoaJuridicaDao.listarTodos());
    }

    public List<PessoaJuridicaDto> buscar(PessoaJuridicaDto pessoaJuridicaDto) {
        return converterListaEntityParaListaDto(pessoaJuridicaDao.buscar(toEntity(pessoaJuridicaDto)));
    }

    public List<PessoaJuridicaDto> converterListaEntityParaListaDto(List<PessoaJuridicaEntity> listEntities){
        List<PessoaJuridicaDto> listDtos = new ArrayList<>();
        for (PessoaJuridicaEntity pessoaJuridicaEntity : listEntities) {
            listDtos.add(toDto(pessoaJuridicaEntity));
        }
        return listDtos;
    }

}
