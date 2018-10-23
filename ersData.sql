CREATE TABLE USERS (
    U_ID NUMBER NOT NULL,
    U_USERNAME varchar2(255) NOT NULL,
    U_PASSWORD varchar2(255) NOT NULL,
    U_FIRSTNAME varchar2(255),
    U_LASTNAME varchar2(255),
    U_EMAIL varchar2(255),
    UR_ID NUMBER NOT NULL,
    PRIMARY KEY (U_ID)
);

CREATE TABLE USERROLES (
    UR_ID NUMBER NOT NULL,
    UR_ROLE varchar2(255) NOT NULL,
    PRIMARY KEY (UR_ID)
);

CREATE TABLE REIMBURSEMENTS (
    R_ID NUMBER NOT NULL,
    R_AMOUNT NUMBER NOT NULL,
    R_DESCRIPTION varchar2(255),
    R_RECEIPT BLOB,
    R_SUBMITTED TIMESTAMP NOT NULL,
    R_RESOLVED TIMESTAMP,
    U_ID_AUTHOR NUMBER NOT NULL,
    U_ID_RESOLVER NUMBER,
    RT_ID NUMBER NOT NULL,
    RS_ID NUMBER NOT NULL,
    PRIMARY KEY (R_ID)
);

CREATE TABLE REIMBURSEMENTSTATUS (
    RS_ID NUMBER NOT NULL,
    RS_STATUS varchar2(255) NOT NULL,
    PRIMARY KEY (RS_ID)
);

CREATE TABLE REIMBURSEMENTTYPE (
    RT_ID NUMBER NOT NULL,
    RT_TYPE varchar2(255) NOT NULL,
    PRIMARY KEY (RT_ID)
);

ALTER TABLE REIMBURSEMENTS
ADD FOREIGN KEY (U_ID_AUTHOR) REFERENCES USERS(U_ID);

ALTER TABLE REIMBURSEMENTS
ADD FOREIGN KEY (U_ID_RESOLVER) REFERENCES USERS(U_ID);

ALTER TABLE REIMBURSEMENTS
ADD FOREIGN KEY (RT_ID) REFERENCES REIMBURSEMENTTYPE(RT_ID);

ALTER TABLE REIMBURSEMENTS
ADD FOREIGN KEY (RS_ID) REFERENCES REIMBURSEMENTSTATUS(RS_ID);

INSERT INTO users (
    u_id,
    u_username,
    u_password,
    u_firstname,
    u_lastname,
    ur_id
) VALUES (
    0,
    'First',
    'test',
    'First',
    'Guy',
    0
);

INSERT INTO users (
    u_id,
    u_username,
    u_password,
    u_firstname,
    u_lastname,
    ur_id
) VALUES (
    1,
    'JohnSmith',
    'test',
    'John',
    'Smith',
    0
);

INSERT INTO users (
    u_id,
    u_username,
    u_password,
    u_firstname,
    u_lastname,
    u_email,
    ur_id
) VALUES (
    2,
    'Manager',
    'revature',
    'Mana',
    'Ger',
    'test@gmail.com',
    1
);

INSERT INTO userroles (
    ur_id,
    ur_role
) VALUES (
    0,
    'employee'
);

INSERT INTO userroles (
    ur_id,
    ur_role
) VALUES (
    1,
    'manager'
);

INSERT INTO reimbursementtype (
    rt_id,
    rt_type
) VALUES (
    0,
    'Travel'
);

INSERT INTO reimbursementstatus (
    rs_id,
    rs_status
) VALUES (
    0,
    'pending'
);

INSERT INTO reimbursementstatus (
    rs_id,
    rs_status
) VALUES (
    1,
    'approved'
);

INSERT INTO reimbursementstatus (
    rs_id,
    rs_status
) VALUES (
    2,
    'denied'
);

CREATE SEQUENCE SEQUENCE_REIMBURSEMENTS
START WITH 0
INCREMENT BY 1
MINVALUE 0
MAXVALUE 9999
NOCYCLE;