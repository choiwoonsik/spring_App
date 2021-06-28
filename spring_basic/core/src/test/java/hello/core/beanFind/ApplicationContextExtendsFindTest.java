package hello.core.beanFind;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

public class ApplicationContextExtendsFindTest {

	ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

	@Test
	@DisplayName("부모 타입으로 조회, 자식이 둘 이상 있으면, 중복 오류 발생")
	void findBeanByParentTypeDuplicate()
	{
		Assertions.assertThrows(NoUniqueBeanDefinitionException.class,
				() -> ac.getBean(DiscountPolicy.class));
	}

	@Test
	@DisplayName("부모 타입으로 조회, 자식이 둘 이상 있으면, 자식 이름으로 조회")
	void findBeanByParentTypeWithName()
	{
		DiscountPolicy fixDiscountPolicy = ac.getBean("fixDiscount", DiscountPolicy.class);
		org.assertj.core.api.Assertions.assertThat(fixDiscountPolicy).isInstanceOf(FixDiscountPolicy.class);

		DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscount", DiscountPolicy.class);
		org.assertj.core.api.Assertions.assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
	}

	@Test
	@DisplayName("부모 타입으로 모두 조회")
	void findAllBeanByParentType()
	{
		Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);

		for (String beanName : beansOfType.keySet())
		{
			System.out.println(beanName);
			System.out.println(beansOfType.get(beanName));
		}

		org.assertj.core.api.Assertions.assertThat(beansOfType.size()).isEqualTo(2);
	}

	@Test
	@DisplayName("부모 타입으로 모두 조회 - Object")
	void findAllBeanByObjectType()
	{
		Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);

		for (String beanName : beansOfType.keySet()) {
			System.out.println(beanName);
		}
	}


	@Configuration
	static class TestConfig {

		@Bean
		public DiscountPolicy fixDiscount() {
			return new FixDiscountPolicy();
		}

		@Bean
		public DiscountPolicy rateDiscount() {
			return new RateDiscountPolicy();
		}
	}
}
