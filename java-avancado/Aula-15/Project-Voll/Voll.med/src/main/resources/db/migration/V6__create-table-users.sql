create table users(

    id bigint not null auto_increment,
    login varchar(100) not null,
    password varchar(255) not null,

    primary key(id)

);

insert into users values (1, 'admin', '$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.');
