--------------------------------------------
-- Drop triggers
--------------------------------------------
drop trigger active_attacks_trig;
drop trigger attack_windows_trig;

--------------------------------------------
-- Drop sequences
--------------------------------------------
drop sequence active_attacks_seq;
drop sequence attack_windows_seq;

--------------------------------------------
-- Drop tables
--------------------------------------------
drop table well_known_results;
drop table processors_performance_offline;
drop table processors_performance_online;
drop table attack_windows;
drop table dictionary_words;
drop table active_attacks;


--------------------------------------------
-- Create tables
--------------------------------------------
create table well_known_results (
	clear_pass		varchar2(50) primary key,
	times			number default 1 not null 
);

create table processors_performance_offline (
	processor_name			varchar2(60) primary key,
	total_words_processed	number default 0 not null,
	total_time_processing	number default 0 not null,
	words_per_minute		number default 0 not null 
);

create table processors_performance_online (
	processor_name			varchar2(60) primary key,
	total_words_processed	number default 0 not null,
	total_time_processing	number default 0 not null,
	words_per_minute		number default 0 not null
);

create table dictionary_words (
	word		varchar2(50) primary key
);

create table active_attacks (
	id					number primary key,
	tool				varchar2(50) not null,
	attack_type			varchar2(20) not null,
	remote_user			varchar2(60),
	remote_ip			varchar2(30),
	remote_port			number,
	extra_param			varchar2(256),
	hash_to_crack		varchar2(256),
	notify_to_email		varchar2(256) not null
);

create table attack_windows (
	id							number primary key,
	active_attack_id			number not null,
	attemps						number default 0 not null,
	sent_timestamp				timestamp,
	bf_pattern					varchar2(100),
	processed					number(1) default 0 not null,
	factor						number default 0 not null,
	first_dictionary_word		varchar2(50),
	last_dictionary_word		varchar2(50)
);

--------------------------------------------
-- Create sequences
--------------------------------------------
create sequence active_attacks_seq
  minvalue 1
  maxvalue 999999999999999999999999999
  start with 1
  increment by 1
  nocache;

create sequence attack_windows_seq
  minvalue 1
  maxvalue 999999999999999999999999999
  start with 1
  increment by 1
  nocache;  
--------------------------------------------
-- Create triggers
--------------------------------------------
create or replace trigger active_attacks_trig
before insert on active_attacks
for each row
begin
	if :new.id is null or :new.id <= 0 then
		select active_attacks_seq.nextval into :new.id from dual;
	end if;
end;
/

create or replace trigger attack_windows_trig
before insert on attack_windows
for each row
begin
	if :new.id is null or :new.id <= 0 then
		select attack_windows_seq.nextval into :new.id from dual;
	end if;
end;
/