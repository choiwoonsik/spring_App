package hello.core.singleton;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StateFullServiceTest {

	@Test
	@DisplayName("상태 유지 테스트")
	void stateFullTest()
	{
		ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
		StateFullService stateFullService = ac.getBean("stateFullService", StateFullService.class);
		StateFullService stateFullService1 = ac.getBean(StateFullService.class);

		stateFullService.order("userA", 10000);
		stateFullService1.order("userB", 20000);

		int price = stateFullService.getPrice();
		System.out.println(price);

		Assertions.assertThat(price).isEqualTo(20000);
	}

	static class TestConfig
	{
		@Bean
		public StateFullService stateFullService() {
			return new StateFullService();
		}
	}
}
