create table public.flags
(
    key   varchar(255) not null
        primary key,
    date  timestamp(6),
    value varchar(255)
);

alter table public.flags
    owner to nosithouss;

create table public.images
(
    name varchar(255) not null
        primary key,
    data oid
);

alter table public.images
    owner to nosithouss;

create table public.users
(
    user_name  varchar(255) not null
        primary key,
    active     boolean      not null,
    first_name varchar(255),
    last_name  varchar(255),
    password   varchar(255),
    pdp        varchar(255),
    roles      smallint[]
);

alter table public.users
    owner to nosithouss;

create table public.contacts
(
    id         bigserial
        primary key,
    last_chat  timestamp(6),
    contact_id varchar(255)
        constraint fkbwdla3daipku5af60p84menq5
            references public.users,
    user_id    varchar(255)
        constraint fkna8bddygr3l3kq1imghgcskt8
            references public.users
);

alter table public.contacts
    owner to nosithouss;

create table public.posts
(
    type                  varchar(31)  not null,
    id                    bigserial
        primary key,
    content               text,
    created_at            timestamp(6) not null,
    nb_dislike            integer      not null,
    nb_like               integer      not null,
    title                 varchar(255) not null,
    additional_properties varchar(255),
    images                varchar(255)[],
    coordinatex           real,
    coordinatey           real,
    end_guarding_at       timestamp(6),
    guarding_at           timestamp(6),
    author_id             varchar(255)
        constraint fk6xvn0811tkyo3nfjk2xvqx6ns
            references public.users,
    last_version_id       bigint
        constraint fk268l0ibw80sligk73yx3vdi8c
            references public.posts,
    guard_claimer         varchar(255)
        constraint fkobac4feo24nork1nuikismtt
            references public.users
);

alter table public.posts
    owner to nosithouss;

create table public.comments
(
    id         bigserial
        primary key,
    content    text,
    created_at timestamp(6) not null,
    author_id  varchar(255)
        constraint fkn2na60ukhs76ibtpt9burkm27
            references public.users,
    post_id    bigint
        constraint fkh4c7lvsc298whoyd4w9ta25cr
            references public.posts
);

alter table public.comments
    owner to nosithouss;

create table public.messages
(
    type        varchar(31)  not null,
    id          bigserial
        primary key,
    content     text,
    created_at  timestamp(6) not null,
    accept      boolean,
    receiver_id varchar(255)
        constraint fkt05r0b6n0iis8u7dfna4xdh73
            references public.users,
    sender_id   varchar(255)
        constraint fk4ui4nnwntodh6wjvck53dbk9m
            references public.users,
    post_id     bigint
        constraint fk86vxjyfsjfddqi277p88mjt3f
            references public.posts
);

alter table public.messages
    owner to nosithouss;

