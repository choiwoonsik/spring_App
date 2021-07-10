package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

public class SingletonWithPrototypeTest1 {

	@Test
	void prototypeFind() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
		PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
		prototypeBean1.addCount();
		int count1 = prototypeBean1.getCount();
		Assertions.assertThat(count1).isEqualTo(1);

		PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
		prototypeBean2.addCount();
		int count = prototypeBean2.getCount();
		Assertions.assertThat(count).isEqualTo(1);
	}

	@Test
	void singletonClientUsePrototype() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

		ClientBean clientBean1 = ac.getBean(ClientBean.class);
		ClientBean clientBean2 = ac.getBean(ClientBean.class);

		int count1 = clientBean1.logic();
		Assertions.assertThat(count1).isEqualTo(1);

		int count2 = clientBean2.logic();
		Assertions.assertThat(count2).isEqualTo(1);
	}

	@Scope("singleton")
	public static class ClientBean {

		private final Provider<PrototypeBean> prototypeBeanProvider;

		@Autowired
		public ClientBean(Provider<PrototypeBean> prototypeBeanProvider) {
			this.prototypeBeanProvider = prototypeBeanProvider;
		}

		public int logic() {
			PrototypeBean prototypeBean = prototypeBeanProvider.get();
			System.out.println("prototypeBean = " + prototypeBean);
			prototypeBean.addCount();
			return prototypeBean.getCount();
		}
	}

	@Scope("prototype")
	public static class PrototypeBean {
		private int count = 0;

		public void addCount() {
			count++;
		}

		public int getCount() {
			return count;
		}
	}
}
