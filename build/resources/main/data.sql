INSERT INTO USER(USER_ID, PASSWORD, NICKNAME, ACTIVATED, EMAIL, KAKAO_ID) VALUES (1, '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 1, 'tovbskvb', 123123123);
INSERT INTO USER(USER_ID, PASSWORD, NICKNAME, ACTIVATED, EMAIL, KAKAO_ID) VALUES (2, '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 1, 'tovbskvb', 1231231243);

INSERT INTO AUTHORITY (AUTHORITY_NAME) values ('ROLE_USER');
INSERT INTO AUTHORITY (AUTHORITY_NAME) values ('ROLE_ADMIN');

INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_NAME) values (1, 'ROLE_USER');
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_NAME) values (1, 'ROLE_ADMIN');
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_NAME) values (2, 'ROLE_USER');

insert into job (job_name) values ('프로그래머');
insert into job (job_name) values ('마케터');
insert into job (job_name) values ('선생님');

insert into enterprise (enterprise_name) values ('삼성전자');
insert into enterprise (enterprise_name) values ('네이버');
insert into enterprise (enterprise_name) values ('카카오');