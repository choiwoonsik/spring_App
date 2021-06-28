package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

@Component
public class RateDiscountPolicy implements DiscountPolicy {

	private final double discountRateAmount = 0.1d;

	@Override
	public int discount(Member member, int price) {
		if (member.getGrade() == Grade.Vip)
			return (int) (price * discountRateAmount);
		return 0;
	}
}
