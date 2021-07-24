# 스프링 부트 웹 애플리케이션 개발

1. 진행기간 : 2021.06 ~ 07 (한달)
2. 주요내용 : 스프링 부트를 이용하여 회원, 상품, 주문 관리 서비스 구현
3. 본인이 기여한점 : 스프링 부트를 이용하여 회원, 상품, 주문을 관리할 수 있는 서비스를 개발해보았다.
4. 사용한 Skill : 스프링 부트, h2 DB, lombok, 카카오 도로명주소 api / 프론트 : thymeleaf, html, booststrap
5. 어려웠던점 : 주문 시점에 모든 회원과 상품을 목록으로 가져온 후 주문 생성 시 잘못된 orderForm을 생성한 경우 새로 form을 생성하도록 해야하는데 이때 회원목록과 상품목록을 다시 불러오는 방법을 구현하지 못해서 
단순 redirect로 구현한점이 아쉽다.
6. 결과 : spring boot를 사용해서 간단한 서비스를 구현해 볼 수 있어서 좋았다. 하지만 검색 쪽에서 단순 문자열 query를 짠 것이나, api를 사용하지 않고 구현한 것이 아쉽다. 이를 개선해서 새로 만들어봐야 겠다.

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

- controller [코드](https://github.com/choiwoonsik/spring_app/tree/main/springboot_WebApp/jpaShop/src/main/java/jpaBook/jpaShop/controller)
  - form
- domain [코드](https://github.com/choiwoonsik/spring_app/tree/main/springboot_WebApp/jpaShop/src/main/java/jpaBook/jpaShop/domain)
  - item (book, movie, album)
- exception
- repository [코드](https://github.com/choiwoonsik/spring_app/tree/main/springboot_WebApp/jpaShop/src/main/java/jpaBook/jpaShop/repository)
  - dao
  - daoImpl
- service [코드](https://github.com/choiwoonsik/spring_app/tree/main/springboot_WebApp/jpaShop/src/main/java/jpaBook/jpaShop/service)

## 구현
#### 홈 화면
<img width="400" alt="스크린샷 2021-07-24 오후 5 25 45" src="https://user-images.githubusercontent.com/42247724/126862573-2aa8721e-8bcd-4886-b243-e6e3bf0b09cc.png">

---

#### 회원등록 및 회원 목록
<img width="400" alt="스크린샷 2021-07-24 오후 5 26 50" src="https://user-images.githubusercontent.com/42247724/126862602-dd5f227a-d035-467c-8357-236a6eb49732.png">
<img width="400" alt="스크린샷 2021-07-24 오후 5 27 17" src="https://user-images.githubusercontent.com/42247724/126862622-01007dc4-7fb5-4473-b3ac-f9c5471a6b80.png">

---

#### 상품등록 및 상품 목록
<img width="400" alt="스크린샷 2021-07-24 오후 5 28 23" src="https://user-images.githubusercontent.com/42247724/126862694-c3eb6929-14e8-465d-b616-22b7ec6900f0.png">
<img width="400" alt="스크린샷 2021-07-24 오후 5 29 11" src="https://user-images.githubusercontent.com/42247724/126862695-d8d8047f-a76a-4c85-a4a2-241cde17216e.png">

---

#### 상품 주문 및 주문 목록
<img width="400" alt="스크린샷 2021-07-24 오후 5 29 24" src="https://user-images.githubusercontent.com/42247724/126862697-5540374a-9435-4a6a-951c-692efdd51b0d.png">
<img width="400" alt="스크린샷 2021-07-24 오후 5 29 53" src="https://user-images.githubusercontent.com/42247724/126862699-3f332ee9-9a7e-4ba3-b7b1-6104dbf3f50c.png">

