create table plane (
    id                  int         not null        auto_increment      primary key,
    type_id             int         not null,
    production_time     timestamp   not null,
    airline             varchar(50) not null,
    travelled_distance  int         not null,
    passengers          int         not null,

    foreign key (type_id) references plane_type (id)
);