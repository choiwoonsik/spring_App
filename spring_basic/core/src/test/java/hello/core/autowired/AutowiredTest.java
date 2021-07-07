package hello.core.autowired;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.lang.reflect.Member;
import java.util.Optional;

public class AutowiredTest {



	@Test
	void AutowiredOption(){
		ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
	}

	static class TestBean {
		@Autowired(required = false)
		public void setNoBean1(Member member1)
		{
			// 주입해줄 대상 (Bean)이 없으면 메서드 자체가 호출이 안된다
			System.out.println("member = " + member1);
		}

		@Autowired
		public void setNoBean2(@Nullable Member member2)
		{
			// 주입해줄 대상 (Bean)이 없으면 null을 넣어줌
			System.out.println("member2 = " + member2);
		}

		@Autowired
		public void setNoBean3(Optional<Member> member3) {
			// 주입해줄 대상 (Bean)이 없으면 Optional.empty를 넣어줌
			System.out.println("member3 = " + member3);
		}
	}
}
