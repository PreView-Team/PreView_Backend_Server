INSERT INTO USER (user_id, nickname, email, kakao_id, password, activated) values (1,'일심짱태', 'tovbskvb@daunm.net', 123131, 123123, 'TRUE');

INSERT INTO CATEGORY (category_id, category_name) values (1, '개발');
INSERT INTO CATEGORY (category_id, category_name) values (2, '디자인');
INSERT INTO CATEGORY (category_id, category_name) values (3, '마케팅');

INSERT INTO AUTHORITY (AUTHORITY_NAME) values ('ROLE_USER');
INSERT INTO AUTHORITY (AUTHORITY_NAME) values ('ROLE_ADMIN');

INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_NAME) values (1, 'ROLE_USER');

insert into job (job_name) values ('개발');
insert into job (job_name) values ('디자인');
insert into job (job_name) values ('마케팅');

insert into mentor_job (mentor_job_name) values ('개발');
insert into mentor_job (mentor_job_name) values ('디자인');
insert into mentor_job (mentor_job_name) values ('마케팅');

insert into enterprise (enterprise_name) values ('개발');
insert into enterprise (enterprise_name) values ('디자인');
insert into enterprise (enterprise_name) values ('마케팅');