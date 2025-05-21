create table hero (
    id                  int         not null        auto_increment      primary key,
    name                varchar(30) not null,
    nationality         varchar(30) not null,
    can_fly             tinyint(1)  not null,
    weapon_id           int         not null,

    foreign key (weapon_id) references weapon (id)
);