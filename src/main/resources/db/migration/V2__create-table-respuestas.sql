create table respuestas(

    id_respuestas bigint not null auto_increment,
    autor varchar(50) not null,
    mensaje varchar(250) not null,
    fecha_creacion date not null,
    id_topico bigint not null,

    primary key(id_respuestas)
);