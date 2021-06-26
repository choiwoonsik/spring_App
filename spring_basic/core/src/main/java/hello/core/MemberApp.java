package hello.core;

import hello.core.member.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class MemberApp {
	public static void main(String[] args) {

//		AppConfig appConfig = new AppConfig();
//		MemberService memberService = appConfig.memberService();

		// spring을 이용해서 Bean 호출
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

		Member member = new Member(1L, "memberA", Grade.Vip);
		memberService.join(member);

		Member findMember = memberService.findMember(member.getId());
		System.out.println("findMember = " + findMember.getName());
		System.out.println("member = " + member.getName());
	}
}
