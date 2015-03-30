create tablespace name_server_ts datafile 'name_server.dbf' size 200M autoextend on;
create user name_server identified by master1 default tablespace name_server_ts;
grant CONNECT, CREATE SEQUENCE, CREATE TABLE, CREATE TYPE, CREATE VIEW, CREATE TRIGGER to name_server;
alter user name_server default role "CONNECT" quota unlimited on name_server_ts;
