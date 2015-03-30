--------------------------------------------
-- Drop triggers
--------------------------------------------
drop trigger servers_trig;

--------------------------------------------
-- Drop sequences
--------------------------------------------
drop sequence id_seq;

--------------------------------------------
-- Drop tables
--------------------------------------------
drop table servers;


--------------------------------------------
-- Create tables
--------------------------------------------
create table servers (
	id				number primary key,
	ip				varchar2(30) not null,
	port			number not null,
	processor_name	varchar2(60) not null,
	locked			number(1) default 0
);

--------------------------------------------
-- Create sequences
--------------------------------------------
create sequence id_seq
  minvalue 1
  maxvalue 999999999999999999999999999
  start with 1
  increment by 1
  nocache;
 
--------------------------------------------
-- Create triggers
--------------------------------------------
create or replace trigger servers_trig
before insert on servers
for each row
begin
	select id_seq.nextval into :new.id from dual;
end;
/