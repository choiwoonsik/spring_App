package hello.core;

import hello.core.discount.RateDiscountPolicy;
import hello.core.member.*;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
	public static void main(String[] args) {

//		AppConfig appConfig = new AppConfig();

//		MemberService memberService = appConfig.memberService();
//		OrderService orderService = appConfig.orderService();

		ApplicationContext ap = new AnnotationConfigApplicationContext(AppConfig.class);
		MemberService memberService = ap.getBean("memberService", MemberService.class);
		OrderService orderService = ap.getBean("orderService", OrderService.class);

		Member member = new Member(1L, "memberA", Grade.Vip);
		memberService.join(member);

		Order order = orderService.createOrder(member.getId(), "itemA", 15000);

		System.out.println("order = " + order);
	}
}
