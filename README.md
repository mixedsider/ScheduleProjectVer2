# 내일배움캠프 일정 관리 앱 만들기


## 개발 환경
- IntellJ IDEA Ultimate Edition 2024.3.1.1

- Java 17.0.11

- Github

- Docker version 27.5.1, build 9f9e405

- mysql  Ver 9.2.0 for Linux on x86_64 (MySQL Community Server - GPL) (in Docker)

- git 2.34.1

- ubuntu Ubuntu 22.04.5 LTS 64bit


## 개발자 블로그

    https://strnetwork.tistory.com/

### ERD

![image](https://github.com/user-attachments/assets/e685cb67-edf5-4bd5-8afc-49168056659e)


### 사이트 주소

    https://www.erdcloud.com/d/uQZCe5DyjBWHc5wqg

### API 명세서

|기능|URL|request|response|COMMENT|정상응답|잘못된 응답|
|---|---|---|---|-------|------|-------|
|일정 등록|POST|/api/v1/schedules|요청 body : CreateSchduleRequestDto|ScheduleResponseDto|201 : 정상등록|400 : DTO 요청,문제 401 : 로그인이 안됨|
|일정 조회|GET|/api/v1/schedules/{id}|요청 PathVariable : schedule_id|ScheduleResponseDto|200 : 정상조회|404 : 데이터가 없음|
|일정 목록 조회|GET|/api/v1/schedules|-|List(ScheduleResponseDto)|200 : 정상조회|-|
|일정 일괄 수정|PUT|/api/v1/schedules/{id}|요청 PathVariable : schedule_id, 요청 body : updateScheduleRequestDto|ScheduleResponseDto|200 : 정상수정|400 : DTO 요청 문제, 401 : 로그인이 안됨, 404 : 데이터가 없음|
|일정 부분 수정|PATCH|/api/v1/schedules/{id}|요청 PathVariable : schedule_id, 요청 body : updateScheduleRequestDto|ScheduleResponseDto|200 : 정상수정|400 : DTO 요청 문제, 401 : 로그인이 안됨, 404 : 데이터가 없음|
|일정 삭제|DELETE|/api/schedules/{scheduleId}|요청 PathVariable|-|200 : 정상삭제|401 : 로그인이 안됨,404 : 데이터가 없음|
|회원 가입|POST|/api/v1/users/signup|요청 body : SignUpUserRequestDto|userResponseDto|201 : 회원가입 완료|400 : DTO 요청 문제 or 이미 가입된 회원|
|로그인|POST|/api/v1/users/login|요청 body : LoginUserRequestDto|userResponseDto|200 : 정상 로그인|400 : DTO 요청 문제, 404 : 없는 회원|
|회원 조회|GET|/api/v1/users/{id}|요청 PathVariable : user_id|userResponseDto|200 : 정상 요청|401 : 권한 없음, 404 : 없는 회원|
|회원 이름 수정|PATCH|/api/v1/users/{id}|요청 PathVariable : user_id, 요청 body : PatchUserRequestDto|userResponseDto|200 : 정상수정|400 : DTO 요청 문제, 401 : 로그인이 안됨, 404 : 데이터가 없음|
|회원 삭제|DELETE|/api/v1/users/{id}|요청 PathVariable : user_id, 요청 body : deleteUserRequestDto|-|200 : 정상삭제|401 : 권한 없음, 404 : 없는 회원|

## 환경 변수

    DB_USER={your DB Id)
    DB_PASSWORD=(your DB password)
    DB_URL=jdbc:mysql://{your DB URL)


## 프로그램 사용법

    git clone https://github.com/mixedsider/ScheduleProject.git

프로젝트를 복사해주세요.

    ./ScheduleProject/src/main/resources/security/.db.env
    
.db.env 환경 변수 내용을 추가를 하고 서버에 환경변수 설정을 해주신 다음에

    ./ScheduleProject/src/main/java/com/example/scheduleprojectver2/ScheduleProjectVer2Application.java
    
ScheduleProjectVer2Application.java에 있는 main 을 실행시키면 됩니다.



