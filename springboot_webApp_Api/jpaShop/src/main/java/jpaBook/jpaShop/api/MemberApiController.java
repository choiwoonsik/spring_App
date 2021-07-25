package jpaBook.jpaShop.api;

import jpaBook.jpaShop.domain.Address;
import jpaBook.jpaShop.domain.Member;
import jpaBook.jpaShop.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

	private final MemberService memberService;

	/*
	  등록 V1: 요청 값으로 Member 엔티티를 직접 받는다.

	@PostMapping("/api/v1/members")
	public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) {
		Long id = memberService.join(member);
		return new CreateMemberResponse(id);
	}
	 */

	/***
	 * 등록 V2: 요청 값으로 Member 엔티티 대신에 별도의 DTO를 받는다
	 */
	@PostMapping("api/v2/members")
	public CreateMemberResponse saveMemberV2 (@RequestBody @Valid CreateMemberRequest request) {

		Member member = new Member();
		member.setName(request.getName());
		member.setEmail(request.getEmail());
		member.setAddress(request.getAddress());

		Long id = memberService.join(member);
		return new CreateMemberResponse(id);
	}

	@PatchMapping("api/v2/members/{id}")
	public UpdateMemberResponse updateMemberV2 (
			@PathVariable("id") Long id,
			@RequestBody @Valid UpdateMemberRequest request)
	{
		memberService.update(id, request.getName());
		Member findMember = memberService.findMember(id);
		return new UpdateMemberResponse(findMember.getId(), findMember.getName());
	}

	@GetMapping("api/v2/members")
	public Result<?> memberV2() {
		List<Member> members = memberService.findAllMembers();
		List<MemberDto> collect = members.stream()
				.map(m -> new MemberDto(m.getName(), m.getEmail()))
				.collect(Collectors.toList());

		return new Result<>(collect);
	}


	@Data
	static class CreateMemberRequest {
		private String name;
		private String email;
		private Address address;
	}

	@Data
	@AllArgsConstructor
	static class CreateMemberResponse {
		private Long id;
	}

	@Data
	static class UpdateMemberRequest {
		private String name;
	}


	@Data
	@AllArgsConstructor
	static class UpdateMemberResponse {
		private Long id;
		private String name;
	}

	@Data
	@AllArgsConstructor
	static class Result<T> {
		private T data;
	}

	@Data
	@AllArgsConstructor
	static class MemberDto {
		private String name;
		private String email;
	}
}
