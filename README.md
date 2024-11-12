# 🏃 HRM System - Backend

## 📋 HRM System 소개

HRM System은 체계적인 인사관리를 위한 시스템입니다.

## 🛠 기술 스택

### Backend

- Java
- Spring Boot 3.3.5
- MyBatis
- MySQL
- JWT 0.12.3

## 📁 프로젝트 구조

```
src
├── main
│   ├── java
│   │   └── com.ssafy.edu
│   │       ├── config
│   │       ├── controller
│   │       ├── model
│   │       │   ├── mapper
│   │       │   ├── dto
│   │       │   └── service
│   │       └── mapper
│   └── resources
│       └── mappers
└── test
```

## 💾 ERD 설계

![ERD 다이어그램](/Image/ssafit_ERD.PNG)

## 데이터베이스 구조 설명

### 1. user (사용자 테이블)

| 컬럼명    | 타입    | 제약조건      | 설명          |
| --------- | ------- | ------------- | ------------- |
| user_id   | VARCHAR | PRIMARY KEY   | 사용자 아이디 |
| password  | VARCHAR | NOT NULL      | 비밀번호      |
| user_name | VARCHAR | NOT NULL      | 사용자 이름   |
| isAdmin   | BOOLEAN | DEFAULT false | 관리자 여부   |

### 2. video (영상 테이블)

| 컬럼명     | 타입    | 제약조건                    | 설명           |
| ---------- | ------- | --------------------------- | -------------- |
| video_id   | INT     | PRIMARY KEY, AUTO_INCREMENT | 영상 고유 번호 |
| title      | VARCHAR | NOT NULL                    | 영상 제목      |
| part       | VARCHAR | NOT NULL                    | 운동 부위      |
| url        | VARCHAR | NOT NULL                    | 영상 URL       |
| view_count | INT     | DEFAULT 0                   | 조회수         |

### 3. review (리뷰 테이블)

| 컬럼명      | 타입      | 제약조건                    | 설명           |
| ----------- | --------- | --------------------------- | -------------- |
| review_id   | INT       | PRIMARY KEY, AUTO_INCREMENT | 리뷰 고유 번호 |
| video_id    | INT       | FOREIGN KEY                 | 비디오 참조    |
| title       | VARCHAR   | NOT NULL                    | 리뷰 제목      |
| content     | TEXT      | NOT NULL                    | 리뷰 내용      |
| user_id     | VARCHAR   | FOREIGN KEY                 | 작성자 참조    |
| create_time | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP   | 작성 시간      |

### 4. user_video (사용자-영상 관계 테이블)

| 컬럼명   | 타입    | 제약조건    | 설명        |
| -------- | ------- | ----------- | ----------- |
| user_id  | VARCHAR | FOREIGN KEY | 사용자 참조 |
| video_id | INT     | FOREIGN KEY | 영상 참조   |

## 관계 (Relationships)

1. **user ↔ video (N:M)**

   - `user_video` 테이블을 통한 다대다 관계
   - 사용자가 업로드한 영상 관리
   - 한 사용자가 여러 영상을 업로드 가능
   - 한 영상이 여러 사용자에 의해 공유될 수 있음

2. **user ↔ review (1:N)**

   - 한 사용자가 여러 리뷰를 작성 가능
   - 각 리뷰는 하나의 사용자에 의해 작성됨

3. **video ↔ review (1:N)**
   - 하나의 영상에 여러 리뷰가 작성될 수 있음
   - 각 리뷰는 하나의 영상에 대해 작성됨

## 🔍 주요 기능

1. **사용자 관리**

   - 회원가입/로그인
   - 프로필 관리

2. **운동 영상 관리**

   - 영상 업로드/수정/삭제
   - 영상 조회 및 검색
   - 카테고리별 분류

3. **커뮤니티 기능**
   - 댓글 작성/수정/삭제
   - 좋아요 기능
   - 사용자 간 상호작용

## 🔐 API 명세

API 문서는 서비스를 실행한 후 Swagger를 통해 확인하실 수 있습니다.

- [Swagger API 문서](http://localhost:8087/SpringProject/swagger-ui/index.html#/)
![Swagger 1](/Image/image2.png)
![Swagger 2](/Image/image1.png)

## 💻 실행 방법

프로젝트 클론

```bash

git clone [gitlab repository URL]
```

Maven 빌드

```bash
mvn clean package
```

애플리케이션 실행

```bash
mvn spring-boot:run
```

## 📌 프로젝트 특징

- RESTful API 설계 원칙 준수
- MyBatis를 활용한 효율적인 데이터베이스 관리
- Swagger를 통한 API 문서 자동화
- 확장성과 유지보수성을 고려한 계층형 아키텍처 적용

## 🔄 버전 관리

- GitLab을 통한 버전 관리
- 협업을 위한 브랜치 전략 적용
