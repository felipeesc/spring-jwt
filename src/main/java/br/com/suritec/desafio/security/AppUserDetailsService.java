package br.com.suritec.desafio.security;

import br.com.suritec.desafio.domain.User;
import br.com.suritec.desafio.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> usuarioOptional = userService.buscarPorLogin(login);
        User user = usuarioOptional.orElseThrow(() -> new UsernameNotFoundException("Usuario e/ou senha incorretos"));
        return new org.springframework.security.core.userdetails.User(login, user.getPassword(), getPermissoes(user));
    }

    private Collection<? extends GrantedAuthority> getPermissoes(User user) {
        Set<SimpleGrantedAuthority> auhorities = new HashSet<>();
        user.getAuthorities().forEach(auth -> auhorities.add(new SimpleGrantedAuthority(auth.getName().toUpperCase())));
        return auhorities;
    }
}
