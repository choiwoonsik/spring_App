package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

	@Test
	@DisplayName("싱글톤 미적용")
	void notSingletonTest()
	{
		AppConfig appConfig = new AppConfig();

		MemberService memberService = appConfig.memberService();
		MemberService memberService1 = appConfig.memberService();

		Assertions.assertThat(memberService).isNotSameAs(memberService1);
	}

	@Test
	@DisplayName("싱글톤 패턴 적용")
	void singletonTest()
	{
		SingletonService singletonService = SingletonService.getInstance();
		SingletonService singletonService1 = SingletonService.getInstance();

		Assertions.assertThat(singletonService).isSameAs(singletonService1);
	}

	@Test
	@DisplayName("스프링 컨테이너와 싱글톤")
	void springContainer()
	{
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

		MemberService memberService = ac.getBean("memberService", MemberService.class);
		MemberService memberService1 = ac.getBean("memberService", MemberService.class);

		System.out.println(memberService);
		System.out.println(memberService1);

		Assertions.assertThat(memberService).isSameAs(memberService1);
	}
}
