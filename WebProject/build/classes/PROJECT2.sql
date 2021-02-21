/*회원테이블*/
create table member(
  idx varchar2(50),
  name varchar2(20) NOT NULL,
  userid varchar2(20) PRIMARY KEY,
  password varchar2(20) NOT NULL,
  email varchar2(20) NOT NULL,
  hp1 varchar2(10) NOT NULL,
  hp2 varchar2(10) NOT NULL,
  hp3 varchar2(10) NOT NULL,
  zipcode varchar2(10) NOT NULL,
  addr1 varchar2(100) NOT NULL,
  addr2 varchar2(100) NOT NULL,
  jdate DATE
);
/*상품 테이블*/
create table product(
  pnum varchar2(50) PRIMARY KEY,
  pname varchar2(50) NOT NULL,
  upcode varchar2(50) NOT NULL,
  downcode varchar2(50) NOT NULL,
  pimage1 varchar2(50) NOT NULL,
  price varchar2(50) NOT NULL,
  pcont  varchar2(1000) NOT NULL,
  pdate DATE
);

/*상품번호 시퀀스*/
CREATE SEQUENCE pnum_seq
START WITH 1
increment BY 1 maxvalue 10000;
/*게시판 테이블*/
create table board(
  board_idx NUMBER NOT NULL,
  board_mid VARCHAR2(50),
  board_subject VARCHAR2(100),
  board_content VARCHAR2(2000),
  board_file VARCHAR2(100),
  board_date date,
  board_pk NUMBER NOT NULL,
  CONSTRAINT PK_Member_Board PRIMARY KEY(board_idx)
);

/*보드번호 시퀀스*/
create sequence BOARD_NUM
START WITH 1
increment BY 1 maxvalue 20000;
/*기본키 부여*/
alter table board
add CONSTRAINT fk_board_mid foreign KEY (board_mid)
REFERENCES member(userid);

/*댓글 게시판 생성*/
Create table review(
  review_idx NUMBER,
  review_mid VARCHAR2(50),
  review_content VARCHAR2(300),
  review_date date,
  review_num NUMBER
);

/*댓글 시퀀스 생성*/
CREATE SEQUENCE r_seq
START WITH 1
increment BY 1 maxvalue 10000;

/*검색관련 테스트*/
SELECT * FROM board WHERE board_subject Like '%에어%' and board_pk=2;
SELECT * FROM BOARD WHERE BOARD_MID Like '%Ad%' and board_pk=2;
select count(*) as totalcount from board where board_pk=2;

/*페이징처리 관련*/
select * from (
select rownum rn, a.* from
(select * from board order by board_idx desc) a 
) where rn BETWEEN 1 and 30 and board_pk=2;

select * from review;
select count(*) from board where board_pk=2;
select count(*) as totalcount from review where review_idx=34;

select * from board where board_pk=2;

select * from(select * from(select rownum rn, a.* from(select * from board  where board_pk=2 order by board_idx desc) a) where rn BETWEEN 1 and 5);
select * from review;
select * from board where board_pk=2 and BETWEEN RANK() AND RANK(); 