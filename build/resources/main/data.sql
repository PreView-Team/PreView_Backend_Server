INSERT INTO USER (user_id, nickname, email, kakao_id, password, activated) values (1,'일심짱태', 'tovbskvb@daunm.net', 123131, 123123, 'TRUE');

INSERT INTO CATEGORY (category_id, category_name) values (1, '프로그래밍');
INSERT INTO CATEGORY (category_id, category_name) values (2, '디자인');
INSERT INTO CATEGORY (category_id, category_name) values (3, '마케팅');
INSERT INTO CATEGORY (category_id, category_name) values (4, 'PM');

INSERT INTO AUTHORITY (AUTHORITY_NAME) values ('ROLE_USER');
INSERT INTO AUTHORITY (AUTHORITY_NAME) values ('ROLE_ADMIN');

INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_NAME) values (1, 'ROLE_USER');

insert into job (job_name) values ('프로그래밍');
insert into job (job_name) values ('디자인');
insert into job (job_name) values ('마케팅');

insert into mentor_job (mentor_job_name) values ('프로그래밍');
insert into mentor_job (mentor_job_name) values ('디자인');
insert into mentor_job (mentor_job_name) values ('마케팅');

insert into enterprise (enterprise_name) values ('삼성전자');
insert into enterprise (enterprise_name) values ('네이버');
insert into enterprise (enterprise_name) values ('카카오');