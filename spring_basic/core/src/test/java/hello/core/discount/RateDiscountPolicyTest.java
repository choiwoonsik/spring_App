package hello.core.discount;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class RateDiscountPolicyTest {

	DiscountPolicy discountPolicy;

	@BeforeEach
	void BeforeEach() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		discountPolicy = ac.getBean("discountPolicy", DiscountPolicy.class);
	}

	@Test
	@DisplayName("Vip : 10% discount")
	void vip10() {
		// given
		Member member = new Member(1L, "memberVIP", Grade.Vip);

		//when
		int discount = discountPolicy.discount(member, 10000);

		//then
		Assertions.assertThat(discount).isEqualTo(1000);
	}

	@Test
	@DisplayName("Not Vip : no discount")
	void Basic0()
	{
		// given
		Member member = new Member(2L, "memberBasic", Grade.Basic);

		//when
		int discount = discountPolicy.discount(member, 10000);

		//then
		Assertions.assertThat(discount).isEqualTo(0);
	}
}