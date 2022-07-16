INSERT INTO USER (user_id, nickname, email, kakao_id, password, activated) values (1,'일심짱태', 'tovbskvb@daunm.net', 123131, 123123, 'TRUE');

INSERT INTO AUTHORITY (AUTHORITY_NAME) values ('ROLE_USER');
INSERT INTO AUTHORITY (AUTHORITY_NAME) values ('ROLE_ADMIN');

INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_NAME) values (1, 'ROLE_USER');

insert into job (job_name) values ('프로그래머');
insert into job (job_name) values ('마케터');
insert into job (job_name) values ('선생님');

insert into enterprise (enterprise_name) values ('삼성전자');
insert into enterprise (enterprise_name) values ('네이버');
insert into enterprise (enterprise_name) values ('카카오');