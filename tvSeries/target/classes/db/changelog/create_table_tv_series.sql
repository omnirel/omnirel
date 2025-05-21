create table tv_series (
    id          int         not null        auto_increment      primary key,
    title       varchar(50) not null,
    premiere    int         not null,
    imdb_rating double      not null,
    genre    varchar(30) not null,

    foreign key (genre) references genre (id)
);