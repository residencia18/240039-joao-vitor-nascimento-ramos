package br.com.cepedi.Voll.api.security.service;

import br.com.cepedi.Voll.api.security.model.entitys.User;
import br.com.cepedi.Voll.api.security.model.records.details.DataDetailsRegisterUser;
import br.com.cepedi.Voll.api.security.model.records.input.DataRegisterUser;
import br.com.cepedi.Voll.api.security.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return repository.findByLogin(login);
    }


    public DataDetailsRegisterUser register( DataRegisterUser dataRegisterUser){
        User user = new User(dataRegisterUser, passwordEncoder);
        repository.save(user);
        return new DataDetailsRegisterUser(user);
    }
}
