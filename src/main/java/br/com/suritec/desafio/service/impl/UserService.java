package br.com.suritec.desafio.service.impl;

import br.com.suritec.desafio.domain.User;
import br.com.suritec.desafio.repository.UserRepository;
import br.com.suritec.desafio.service.AbstractService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class UserService implements AbstractService<User> {

    private UserRepository userRepository;

    @Override
    public Optional<User> findByCode(Long code) {
        return this.userRepository.findById(code);
    }

    @Override
    public Collection<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User save(User user) {
        return this.userRepository.save(user);
    }


    @Override
    public void delete(Long code) {
        userRepository.findById(code).ifPresent(usuario -> {
            userRepository.delete(usuario);
        });
    }

    public Optional<User> buscarPorLogin(String login) {
        return this.userRepository.findOneByLogin(login);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthoritiesByLogin(String login) {
        return userRepository.findOneWithAuthoritiesByLogin(login);
    }

    @Transactional(readOnly = true)
    public User getUserWithAuthorities(Long codigo) {
        return userRepository.findOneWithAuthoritiesByCode(codigo);
    }
}
