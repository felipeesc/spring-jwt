package br.com.suritec.desafio.service.impl;

import br.com.suritec.desafio.domain.Cliente;
import br.com.suritec.desafio.domain.Funcoes;
import br.com.suritec.desafio.domain.OperationControl;
import br.com.suritec.desafio.repository.ClienteRepository;
import br.com.suritec.desafio.repository.OperationRepository;
import br.com.suritec.desafio.service.AbstractService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class ClienteService implements AbstractService<Cliente> {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private OperationRepository operationRepository;

    @Override
    public Optional<Cliente> findByCode(Long code) {
        registerOperation(Funcoes.BUSCAR.getDescricao());
        return this.clienteRepository.findById(code);
    }

    @Override
    public Collection<Cliente> findAll() {
        registerOperation(Funcoes.CONSULTAR.getDescricao());
        return this.clienteRepository.findAll();
    }

    @Override
    public Cliente save(Cliente cliente) {
        registerOperation(Funcoes.CADASTRAR.getDescricao());
        cliente.setCpf(retirarCaracteres(cliente.getCpf()));
        return this.clienteRepository.save(cliente);
    }

    public Cliente edit(Cliente cliente) {
        Optional<Cliente> clienteSalvo = findByCode(cliente.getCode());
        if (clienteSalvo.get() == null) {
            throw new EmptyResultDataAccessException(1);
        }
        cliente.setCpf(retirarCaracteres(cliente.getCpf()));
        BeanUtils.copyProperties(cliente, clienteSalvo, "code");
        registerOperation(Funcoes.ATUALIZAR.getDescricao());
        return this.clienteRepository.save(clienteSalvo.get());
    }

    @Transactional(readOnly = true)
    public Optional<Cliente> findByCpf(String cpf) {
        registerOperation(Funcoes.BUSCAR_CPF.getDescricao());
        return this.clienteRepository.findOneByCpf(retirarCaracteres(cpf));
    }

    @Override
    public void delete(Long code) {
        clienteRepository.findById(code).ifPresent(cliente -> {
            clienteRepository.delete(cliente);
        });
    }

    @Transactional
    void registerOperation(String funcao) {
        OperationControl operation = new OperationControl(funcao);
        operationRepository.save(operation);

    }

    public static String retirarCaracteres(String str) {
        StringBuilder retorno = new StringBuilder();
        if (notEmpty(str)) {
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) != '.' && str.charAt(i) != '-' && str.charAt(i) != '/') {
                    retorno.append(str.charAt(i));
                }
            }
        }
        return retorno.toString();
    }

    public static boolean notEmpty(Object atributo) {
        return isEmpty(atributo) ^ true;
    }

    public static Boolean isEmpty(Object param) {

        if (param == null) {
            return true;
        }

        if (param instanceof String && ((String) param).trim().equals("")) {
            return true;
        }
        if (param instanceof Collection && ((Collection<?>) param).isEmpty()) {
            return true;
        }
        if (param instanceof Map && ((Map<?, ?>) param).isEmpty()) {
            return true;
        }

        return false;
    }

}
