create table locomotive (
    id           int           not null        primary key     auto_increment,
    name         varchar(50)   not null,
    length_cm    int           not null,
    max_speed    double        not null,
    driving_id   varchar(50)   not null,

    foreign key (driving_id) references driving (id)
);