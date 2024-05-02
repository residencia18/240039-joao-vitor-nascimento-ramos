create table users(

    id bigint not null auto_increment,
    login varchar(100) unique not null,
    name varchar(100) not null,
    email varchar(100) unique not null,
    password varchar(255) not null,

    primary key(id)

);

insert into users values (1, 'admin', 'admin' , 'admin@admin.com', '$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.');
