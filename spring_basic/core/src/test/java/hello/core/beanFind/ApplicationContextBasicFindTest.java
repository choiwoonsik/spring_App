package hello.core.beanFind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest {
	ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

	@Test
	@DisplayName("Bean 이름으로 조회")
	void findBeanByName()
	{
		MemberService memberService = ac.getBean("memberService", MemberService.class);
		System.out.println("memberService = " + memberService);
		System.out.println("memberService.getClass() = " + memberService.getClass());
	}

	@Test
	@DisplayName("Bean 클래스로 조회")
	void findBeanByClass()
	{
		MemberService memberServiceClass = ac.getBean(MemberService.class);
		System.out.println("memberServiceClass = " + memberServiceClass);
		System.out.println("memberService.getClass() = " + memberServiceClass.getClass());

		Assertions.assertThat(memberServiceClass).isInstanceOf(MemberServiceImpl.class);
	}

	@Test
	@DisplayName("Bean 구체 타입으로 조회")
	void findBeanByType()
	{
		MemberService memberService = ac.getBean(MemberServiceImpl.class);
		Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
	}

	@Test
	@DisplayName("Bean 조회 실패")
	void findBeanFail()
	{
		assertThrows(NoSuchBeanDefinitionException.class, ()->
			ac.getBean("noname", MemberService.class));
	}

}
