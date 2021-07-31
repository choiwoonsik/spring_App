# 스프링 부트 웹 애플리케이션 개발 (API 추가 구현)

[진행기간]
  - 2021.07 ~ 08 (약 2주)

[주요내용]
  - 기존 스프링 부트 회원 및 상품 관리 앱의 RestAPI 구현

[사용한 Skill]
  - 스프링 부트, h2 DB, lombok

[어려웠던점]
  - RestAPI를 구현할 때 Entity가 아닌 DTO로 변환해서 반환하도록 구조를 짤 때 어려움을 겪었다.
  - spring DATA JPA, QueryDsl을 적용해보려 했으나 아직 관련 문법에 익숙하지 않아서 정리 후 적용해 보려한다.

[결과]
  - spring boot를 사용해서 REST API를 구현한 서비스를 구현해 볼 수 있어서 좋았다.
    - 다음 프로젝트에는 QueryDSL을 이용해서 문자열 방식의 쿼리가 아니라 자바코드기반의 쿼리 적용해 보고
  JpaRepository를 적용할 수 있는 모델로 구현해봐야겠다.

### 기능목록

- 회원 기능
  - 회원 등록
  - 회원 조회
- 상품 기능
  - 상품 등록
  - 상품 수정
  - 상품 삭제
  - 상품 조회
- 주문 기능
  - 상품 주문
  - 주문 내역 조회
  - 주문 취소
- 기타 요구 사항
  - 상품은 재고 관리가 필요
  - 상품의 종류는 도서, 음반, 영화
  - 상품을 카테고리로 구분
  - 상품 주문 시 배송 정보를 입력

---

### 패키지 구조

jpaBook.jpaShop

- api [코드](https://github.com/choiwoonsik/spring_app/tree/main/springboot_webApp_Api/jpaShop/src/main/java/jpaBook/jpaShop/api)
- controller [코드](https://github.com/choiwoonsik/spring_app/tree/main/springboot_webApp_Api/jpaShop/src/main/java/jpaBook/jpaShop/controller)
  - form
- domain [코드](https://github.com/choiwoonsik/spring_app/tree/main/springboot_webApp_Api/jpaShop/src/main/java/jpaBook/jpaShop/domain)
  - item (book, movie, album)
- exception
- repository [코드](https://github.com/choiwoonsik/spring_app/tree/main/springboot_webApp_Api/jpaShop/src/main/java/jpaBook/jpaShop/repository)
  - dao
  - Repository
- service [코드](https://github.com/choiwoonsik/spring_app/tree/main/springboot_webApp_Api/jpaShop/src/main/java/jpaBook/jpaShop/service)

## 구현
#### API 정리 글
[https://ws-pace.tistory.com - api 정리](https://ws-pace.tistory.com/category/Web/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8%20%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8%20%EC%A0%95%EB%A6%AC%20%EC%9A%A9%EB%8F%84)

#### 홈 화면
<img width="300" alt="스크린샷 2021-07-24 오후 5 25 45" src="https://user-images.githubusercontent.com/42247724/126862573-2aa8721e-8bcd-4886-b243-e6e3bf0b09cc.png">

---

#### 회원등록 및 회원 목록
<img width="300" alt="스크린샷 2021-07-24 오후 5 26 50" src="https://user-images.githubusercontent.com/42247724/126862602-dd5f227a-d035-467c-8357-236a6eb49732.png">
<img width="300" alt="스크린샷 2021-07-24 오후 5 27 17" src="https://user-images.githubusercontent.com/42247724/126862622-01007dc4-7fb5-4473-b3ac-f9c5471a6b80.png">

---

#### 상품등록 및 상품 목록
<img width="300" alt="스크린샷 2021-07-24 오후 5 28 23" src="https://user-images.githubusercontent.com/42247724/126862694-c3eb6929-14e8-465d-b616-22b7ec6900f0.png">
<img width="300" alt="스크린샷 2021-07-24 오후 5 29 11" src="https://user-images.githubusercontent.com/42247724/126862695-d8d8047f-a76a-4c85-a4a2-241cde17216e.png">

---

#### 상품 주문 및 주문 목록
<img width="300" alt="스크린샷 2021-07-24 오후 5 29 24" src="https://user-images.githubusercontent.com/42247724/126862697-5540374a-9435-4a6a-951c-692efdd51b0d.png">
<img width="300" alt="스크린샷 2021-07-24 오후 5 29 53" src="https://user-images.githubusercontent.com/42247724/126862699-3f332ee9-9a7e-4ba3-b7b1-6104dbf3f50c.png">

