package jpaBook.jpaShop.service;

import jpaBook.jpaShop.domain.Member;
import jpaBook.jpaShop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class MemberServiceTest {

	MemberService memberService;
	MemberRepository memberRepository;

	@Autowired
	public MemberServiceTest(MemberService memberService, MemberRepository memberRepository) {
		this.memberService = memberService;
		this.memberRepository = memberRepository;
	}

	@Test
	public void memberJoin() throws Exception {
		//given
		Member member = new Member();
		member.setName("memberA");

		//when
		Long saveId = memberService.join(member);

		//then
		assertThat(member).isSameAs(memberService.findMember(saveId));
	}

	@Test
	public void duplicatedMemberException() throws Exception {
		//given
		Member member1 = new Member();
		member1.setName("mem1");

		Member member2 = new Member();
		member2.setName("mem1");

		//when
		memberService.join(member1);

		//then
		assertThrows(IllegalStateException.class, () -> {
			memberService.join(member2);
		});
	}
}