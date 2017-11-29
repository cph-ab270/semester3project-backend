alter table rating drop constraint if exists fk_rating_user_id;
drop index if exists ix_rating_user_id;

alter table rating drop constraint if exists fk_rating_rental_id;
drop index if exists ix_rating_rental_id;

alter table user_role drop constraint if exists fk_user_role_user;
drop index if exists ix_user_role_user;

alter table user_role drop constraint if exists fk_user_role_role;
drop index if exists ix_user_role_role;

drop table if exists location;

drop table if exists rating;

drop table if exists rental;

drop table if exists role;

drop table if exists user;

drop table if exists user_role;

