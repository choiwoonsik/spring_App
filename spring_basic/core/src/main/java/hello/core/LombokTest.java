package hello.core;

import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderServiceImpl;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LombokTest {

	private String name;
	private int age;

	public static void main(String[] args) {
		LombokTest lombokTest = new LombokTest();
		lombokTest.setAge(27);
		lombokTest.setName("woonsik");

		int age = lombokTest.getAge();
		String name = lombokTest.getName();

		System.out.println("age = " + age);
		System.out.println("name = " + name);
		System.out.println("lombokTest = " + lombokTest);

		OrderServiceImpl orderService = new OrderServiceImpl(new MemoryMemberRepository(), new RateDiscountPolicy());
		System.out.println(orderService);
	}
}
