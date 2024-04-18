package br.com.cepedi.Business.api.security.repository;

import br.com.cepedi.Business.api.security.model.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User,Long> {


    UserDetails findByLogin(String login);
}
