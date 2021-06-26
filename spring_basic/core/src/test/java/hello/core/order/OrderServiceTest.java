package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderServiceTest {

	MemberService memberService;
	OrderService orderService;

	@BeforeEach
	void BeforeEach() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

		memberService = ac.getBean("memberService", MemberService.class);
		orderService = ac.getBean("orderService", OrderService.class);
	}

	@Test
	void createOrder() {
		Member member = new Member(1L, "memberA", Grade.Vip);
		memberService.join(member);
		Order order = orderService.createOrder(member.getId(), "itemA", 10000);

		Assertions.assertThat(order.calculatorPrice()).isEqualTo(order.getItemPrice() - order.getDiscountPrice());
	}
}
