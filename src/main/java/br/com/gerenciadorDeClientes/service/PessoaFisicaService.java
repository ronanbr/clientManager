package br.com.gerenciadorDeClientes.service;

import br.com.gerenciadorDeClientes.dao.CrudDao;
import br.com.gerenciadorDeClientes.dao.PessoaFisicaDao;
import br.com.gerenciadorDeClientes.dto.PessoaDto;
import br.com.gerenciadorDeClientes.dto.PessoaFisicaDto;
import br.com.gerenciadorDeClientes.entity.PessoaEntity;
import br.com.gerenciadorDeClientes.entity.PessoaFisicaEntity;
import br.com.gerenciadorDeClientes.exception.DaoException;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Model
public class PessoaFisicaService extends CrudService<PessoaFisicaEntity, Long, PessoaFisicaDto> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private PessoaFisicaDao pessoaFisicaDao;

    public void salvarOuAlterarPessoaFisica(PessoaFisicaDto pessoaFisica) throws DaoException {
        pessoaFisica.setTipo(PessoaEntity.TIPO_PESSOA_FISICA);
        pessoaFisicaDao.salvarOuAlterar(toEntity(pessoaFisica));
    }

    public void excluirPessoaFisica(PessoaFisicaDto pessoaFisica) throws DaoException {
        pessoaFisicaDao.excluir(pessoaFisica.getId());
    }

    @Override
    protected CrudDao<PessoaFisicaEntity, Long> getDao() {
        return pessoaFisicaDao;
    }

    //TODO Passar para crud
    public List<PessoaFisicaDto> listarTodos() {
        return converterListaEntityParaListaDto(pessoaFisicaDao.listarTodos());
    }

    public List<PessoaFisicaDto> buscar(PessoaFisicaDto pessoaFisicaDto) {
        return converterListaEntityParaListaDto(pessoaFisicaDao.buscar(toEntity(pessoaFisicaDto)));

    }

    public List<PessoaFisicaDto> converterListaEntityParaListaDto(List<PessoaFisicaEntity> listEntities){
        List<PessoaFisicaDto> listDtos = new ArrayList<>();
        for (PessoaFisicaEntity pessoaFisicaEntity : listEntities) {
            listDtos.add(toDto(pessoaFisicaEntity));
        }
        return listDtos;
    }
}
