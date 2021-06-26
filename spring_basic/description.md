회원

- 회원을 가입하고 조회할 수 있다.
- 회원은 일반과 VIP 두 가지 등급이 있다.
- 회원 데이터는 자체 DB를 구축할 수 있고, 외부 시스템과 연동할 수 있다. (미확정)

주문과 할인 정책

- 회원은 상품을 주문할 수 있다.
- 회원 등급에 따라 할인 정책을 적용할 수 있다.
- 할인 정책은 모든 VIP는 1000원을 할인해주는 고정 금액 할인을 적용해달라. 
(나중에 변경 될 수 있다.)
- 할인 정책은 변경 가능성이 높다. 회사의 기본 할인 정책을 아직 정하지 못했고, 오픈 직전까지 고민을
미루고 싶다. 최악의 경우 할인을 적용하지 않을 수 도 있다. (미확정)

### 회원 도메인

클라이언트 → 회원 서비스 (회원가입, 회원조회) → 회원 저장소 < - - - (메모리 회원저장소, db 회원 저장소, 외부 시스템 연동 회원 저장소)

회원

MemberService

- 가입
- 조회

저장소

MemberRepository

- 저장
- 검색
    - Memory~

### 주문 & 할인 도메인

클라이언트 → 주문 서비스 (주문 생성) → 회원 조회 → 등급 별 할인 적용

주문

OrderService

- 주문서 생성

할인

DiscountPolicy

- 할인 정책
    - Fixed~
    - Rate~

### Junit 테스트

member 저장 확인

```java
package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

public class MemberServiceTest {

	MemberService memberService = new MemberServiceImpl();

	@Test
	void join() {
		// given
		Member member = new Member(1L, "memberA", Grade.Basic);

		// when
		memberService.join(member);
		Member getMember = memberService.findMember(member.getId());

		// then
		Assertions.assertThat(member).isEqualTo(getMember);
	}
}
```

할인 확인

```java
package hello.core.order;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
	MemberService memberService = new MemberServiceImpl();
	OrderService orderService = new OrderServiceImpl();

	@Test
	void createOrder() {
		Member member = new Member(1L, "memberA", Grade.Vip);
		memberService.join(member);
		Order order = orderService.createOrder(member.getId(), "itemA", 10000);

		Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
		Assertions.assertThat(order.calculatorPrice()).isEqualTo(order.getItemPrice() - order.getDiscountPrice());
	}
}
```

---

문제점

***DIP, OCP 원칙 위배 부분***

```java
package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

	private final MemberRepository memberRepository = new MemoryMemberRepository();
**//	private final DiscountPolicy discountPolicy = new FixDiscountPolicy();**
	**private final DiscountPolicy discountPolicy = new RateDiscountPolicy();**

	@Override
	public Order createOrder(Long memberId, String itemName, int itemPrice) {

		Member member = memberRepository.findById(memberId);
		int discountPrice;
		discountPrice = discountPolicy.discount(member, itemPrice);

		return new Order(memberId, itemName, itemPrice, discountPrice);
	}
}
```

(구현)클라이언트인 OrderServiceImpl는 (역활)인터페이스인 FixDiscountPolicy()에 뿐만 아니라, (구현) 클래스 OrderServiceImple에도 의존하고 있다 (DIP 위반)

→ 그래서 직접 코드를 변경해서 변경해야됨 (OCP 위반)

즉, 구현 클래스가 구현 클래스를 고르고 있는 상황이므로 (배우가 상대 배우를 고르는 경우) 감독과 같은 관리자 클래스를 만들어서 거기서 변경해주게 하자

→ AppConfig 클래스

```java
package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {

	private static final MemberRepository memberRepository = new MemoryMemberRepository();
	private static final DiscountPolicy discountPolicy = new RateDiscountPolicy();

	// 저장소 설정
	public MemberService memberService() {
		return new MemberServiceImpl(memberRepository());
	}

	// 주문 설정
	public OrderService orderService() {
		return new OrderServiceImpl(memberRepository(), discountPolicy());
	}

	// 저장 방식 설정
	public MemberRepository memberRepository() {
		return memberRepository;
	}

	// 할인 방식 설정
	public DiscountPolicy discountPolicy() {
		return discountPolicy;
	}

}
```

이제 구현 클래스에서는 생성자를 통해서 의존성을 주입받는다. 따라서 코드에 Memory라는 단어가 전혀 나오지 않게 된다

```java
package hello.core.member;

public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;

	public MemberServiceImpl(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Override
	public void join(Member member) {
		memberRepository.save(member);
	}

	@Override
	public Member findMember(Long memberId) {
		return memberRepository.findById(memberId);
	}
}
```