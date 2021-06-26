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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppConfig {

	private static final MemberRepository memberRepository = new MemoryMemberRepository();
	private static final DiscountPolicy discountPolicy = new RateDiscountPolicy();

	// 저장소 설정
	@Bean
	public MemberService memberService() {
		return new MemberServiceImpl(memberRepository());
	}

	// 주문 설정
	@Bean
	public OrderService orderService() {
		return new OrderServiceImpl(memberRepository(), discountPolicy());
	}

	// 저장 방식 설정
	@Bean
	public MemberRepository memberRepository() {
		return memberRepository;
	}

	// 할인 방식 설정
	@Bean
	public DiscountPolicy discountPolicy() {
		return discountPolicy;
	}

}
