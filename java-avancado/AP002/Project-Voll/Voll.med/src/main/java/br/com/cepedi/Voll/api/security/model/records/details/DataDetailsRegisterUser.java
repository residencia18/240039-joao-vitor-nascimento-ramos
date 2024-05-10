package br.com.cepedi.Voll.api.security.model.records.details;

import br.com.cepedi.Voll.api.security.model.entitys.User;

public record DataDetailsRegisterUser(

        String login,

        String name,

        String email

) {




    public DataDetailsRegisterUser(User user) {
        this(user.getLogin(),user.getName(),user.getEmail());
    }
}
