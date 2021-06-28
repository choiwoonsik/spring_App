package hello.core.beanFind;

import hello.core.AppConfig;
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

public class ApplicationContextSameBeanFindTest {
	ApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

	@Test
	@DisplayName("타입으로 조회 시 같은 타입이 둘 이상 있으면, 중복 오류 발생")
	void findBeanByDuplicate()
	{
		Assertions.assertThrows(NoUniqueBeanDefinitionException.class,
				() -> ac.getBean(MemberRepository.class));
	}

	@Test
	@DisplayName("타입으로 조회 시 같은 타입이 둘 이상 있으면, 빈 이름으로 조회")
	void findBeanByDuplicateName()
	{
		MemberRepository memberRepository = ac.getBean("memberRepository1", MemberRepository.class);
		org.assertj.core.api.Assertions.assertThat(memberRepository).isInstanceOf(MemberRepository.class);
	}

	@Test
	@DisplayName("특정 타입을 모두 조회")
	void findAllBeanByType()
	{
		Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);

		for (String key : beansOfType.keySet())
		{
			System.out.println("key = " + key);
			System.out.println("beansOfType.get(key) = " + beansOfType.get(key));
		}
	}

	@Configuration
	static class SameBeanConfig {

		@Bean
		public MemberRepository memberRepository1() {
			return new MemoryMemberRepository();
		}

		@Bean
		public MemberRepository memberRepository2() {
			return new MemoryMemberRepository();
		}
	}

}
