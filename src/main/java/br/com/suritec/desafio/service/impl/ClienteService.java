package br.com.suritec.desafio.service.impl;

import br.com.suritec.desafio.domain.Cliente;
import br.com.suritec.desafio.repository.ClienteRepository;
import br.com.suritec.desafio.service.AbstractService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class ClienteService implements AbstractService<Cliente> {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Optional<Cliente> findByCode(Long code) {
        return this.clienteRepository.findById(code);
    }

    @Override
    public Collection<Cliente> findAll() {
        return this.clienteRepository.findAll();
    }

    @Override
    public Cliente save(Cliente cliente) {
        return this.clienteRepository.save(cliente);
    }

    public Cliente edit(Cliente cliente) {
        Optional<Cliente> clienteSalvo = findByCode(cliente.getCode());
        if (clienteSalvo.get() == null) {
            throw new EmptyResultDataAccessException(1);
        }
        BeanUtils.copyProperties(cliente, clienteSalvo, "code");
        return this.clienteRepository.save(clienteSalvo.get());
    }

    @Transactional(readOnly = true)
    public Optional<Cliente> findByCpf(String cpf) {
        return this.clienteRepository.findOneByCpf(cpf);
    }

    @Override
    public void delete(Long code) {
        clienteRepository.findById(code).ifPresent(aluno -> {
            clienteRepository.delete(aluno);
        });
    }

}
