-- apply changes
create table booking (
  id                            integer auto_increment not null,
  week                          datetime(6),
  user_id                       integer not null,
  rental_id                     integer not null,
  constraint pk_booking primary key (id)
);

create table location (
  id                            integer auto_increment not null,
  title                         varchar(255) not null,
  description                   TEXT not null,
  image_url                     varchar(255) not null,
  latitude                      double,
  longitude                     double,
  constraint pk_location primary key (id)
);

create table rating (
  id                            integer auto_increment not null,
  user_id                       integer not null,
  rental_id                     integer not null,
  rating                        integer not null,
  constraint pk_rating primary key (id)
);

create table rental (
  id                            integer auto_increment not null,
  title                         varchar(255) not null,
  city                          varchar(255) not null,
  zip                           varchar(255) not null,
  address                       varchar(255) not null,
  description                   TEXT not null,
  image_url                     varchar(255) not null,
  latitude                      double,
  longitude                     double,
  constraint pk_rental primary key (id)
);

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

alter table booking add constraint fk_booking_user_id foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_booking_user_id on booking (user_id);

alter table booking add constraint fk_booking_rental_id foreign key (rental_id) references rental (id) on delete restrict on update restrict;
create index ix_booking_rental_id on booking (rental_id);

alter table rating add constraint fk_rating_user_id foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_rating_user_id on rating (user_id);

alter table rating add constraint fk_rating_rental_id foreign key (rental_id) references rental (id) on delete restrict on update restrict;
create index ix_rating_rental_id on rating (rental_id);

alter table user_role add constraint fk_user_role_user foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_user_role_user on user_role (user_id);

alter table user_role add constraint fk_user_role_role foreign key (role_id) references role (id) on delete restrict on update restrict;
create index ix_user_role_role on user_role (role_id);

