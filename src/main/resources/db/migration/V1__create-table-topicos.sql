create table topicos(

    id bigint not null auto_increment,
    titulo varchar(100) not null unique,
    mensaje varchar(250) not null unique,
    fecha_creacion date not null,
    status tinyint(1) not null,
    autor varchar(50) not null,
    curso varchar(50) not null,

    primary key(id)

);