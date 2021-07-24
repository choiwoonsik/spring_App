package jpaBook.jpaShop.repository;

import jpaBook.jpaShop.domain.Member;

import javax.validation.constraints.Email;
import java.util.List;

public interface MemberDao {

	/*
	유저 저장
	 */
	void save(Member member);

	/*
	유저 고유 아이디로 검색 후 반환
	 */
	Member findOne(Long id);

	/*
	모든 유저 반환
	 */
	List<Member> findAll();

	/*
	이름으로 유저 검색 후 반환
	 */
	List<Member> findByName(String name);

	/*
	이메일로 유저 검색 후 반환
	 */
	List<Member> findByEmail(@Email String email);
}

