create tablespace master_ts datafile 'master.dbf' size 200M autoextend on;
create user master identified by master1 default tablespace master_ts;
grant CONNECT, CREATE SEQUENCE, CREATE TABLE, CREATE TYPE, CREATE VIEW, CREATE TRIGGER to master;
alter user master default role "CONNECT" quota unlimited on master_ts;
