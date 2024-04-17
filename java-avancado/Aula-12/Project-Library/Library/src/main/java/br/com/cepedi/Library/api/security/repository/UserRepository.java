package br.com.cepedi.Library.api.security.repository;

import br.com.cepedi.Library.api.security.model.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User,Long> {


    UserDetails findByLogin(String login);
}
