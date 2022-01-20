drop table role, user, users_roles;
create table role
(
    id   bigint auto_increment
        primary key,
    role varchar(255) null
);

create table user
(
    id                         bigint auto_increment
        primary key,
    age                        int          not null,
    email                      varchar(255) null,
    is_account_non_expired     bit          not null,
    is_account_non_locked      bit          not null,
    is_credentials_non_expired bit          not null,
    is_enabled                 bit          not null,
    lastname                   varchar(255) null,
    name                       varchar(255) null,
    password                   varchar(255) null,
    constraint UK_ob8kqyqqgmefl0aco34akdtpe
        unique (email)
);

create table users_roles
(
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id),
    constraint FKgd3iendaoyh04b95ykqise6qh
        foreign key (user_id) references user (id),
    constraint FKt4v0rrweyk393bdgt107vdx0x
        foreign key (role_id) references role (id)
);



insert into user (lastname, name, age, password, email, is_account_non_expired, is_account_non_locked,
                  is_credentials_non_expired, is_enabled)
VALUES
#        ("pupkin", "vasya", 11, "qwerty", "pupkin@qwerty.com", true, true, true, true),
#        ("ivanov", "kolya", "+78914576943", "qwerty", "ivanov@qwerty.com"),
#        ("ponomarev", "vlad", "+78916793579", "qwerty", "ponomarev@qwerty.com"),
#        ("pluskin", "igor", "+78914558012", "qwerty", "pluskin@qwerty.com"),
#        ("vorobeichik", "igor", "+78914590908", "qwerty", "vorobeichik@qwerty.com"),
("Userovich", "User", 12, "qwerty", "user@qwerty.com", true, true, true, true),
("Adminovich", "admin", 13, "qwerty", "admin@qwerty.com", true, true, true, true),
("Inn", "All", 14, "qwerty", "all@qwerty.com", true, true, true, true);
insert into role(role)
VALUES ("ROLE_USER"),
       ("ROLE_ADMIN");

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2),
       (3, 2),
       (3, 1);
# insert into role_user (Role_id, user_id)
# values (1,1),
#        (2, 2),
