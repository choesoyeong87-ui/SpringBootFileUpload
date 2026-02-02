# 📝 MYBATIS기반 상품리스트 데이터베이스 설계 (Oracle)

이 저장소는 Java와 Database를 연동하는 **MYBATIS기반 실습을 위한 Oracle SQL 스크립트**를 포함하고 있습니다. 
컴퓨터 전공자 및 시니어 입문자분들이 DB 구축 원리를 쉽게 이해할 수 있도록 구성되었습니다.


# SpringBott MYBATIS 상품리스트

## 🖥️ 소개
MYBATIS 방식으로 CURD 테스팅<br>

## 🕰️ 개발 기간
* 2026.02.02 - 2026.02.02

### ⚙️ 개발 환경
- 운영체제: Windows 11 home
- 개발 도구: SpringBoot 4.1.9
- JDK 버전: OpenJDK 21.0.6
- 프로그래밍 언어: Java 21
- 형상관리 도구: Git, GitHub


## 🚀 데이타 베이스 정보


drop table item;
CREATE TABLE item( 
    id NUMBER(5), 
    name VARCHAR2(20),
    price NUMBER(6), 
    description VARCHAR2(300), 
    url VARCHAR2(300), 
    PRIMARY KEY (id) 
    ); 

desc item;

drop SEQUENCE item_seq;
create sequence item_seq 
start with 1 
increment by 1;
