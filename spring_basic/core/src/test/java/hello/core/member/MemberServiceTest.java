package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberServiceTest {

	MemberService memberService;

	@BeforeEach
	public void beforeEach() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		memberService = ac.getBean("memberService", MemberService.class);
	}

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
