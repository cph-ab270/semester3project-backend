create table role (
  id                            integer auto_increment not null,
  name                          varchar(255),
  constraint pk_role primary key (id)
);

create table user (
  id                            integer auto_increment not null,
  username                      varchar(255) not null,
  password                      varchar(255) not null,
  salt                          varchar(255) not null,
  constraint pk_user primary key (id)
);

create table user_role (
  user_id                       integer not null,
  role_id                       integer not null,
  constraint pk_user_role primary key (user_id,role_id)
);

alter table user_role add constraint fk_user_role_user foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_user_role_user on user_role (user_id);

alter table user_role add constraint fk_user_role_role foreign key (role_id) references role (id) on delete restrict on update restrict;
create index ix_user_role_role on user_role (role_id);

