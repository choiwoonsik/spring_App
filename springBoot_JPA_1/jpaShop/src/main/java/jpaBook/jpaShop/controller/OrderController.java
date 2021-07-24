package jpaBook.jpaShop.controller;

import jpaBook.jpaShop.domain.Member;
import jpaBook.jpaShop.domain.Order;
import jpaBook.jpaShop.domain.item.Item;
import jpaBook.jpaShop.repository.OrderSearch;
import jpaBook.jpaShop.service.ItemService;
import jpaBook.jpaShop.service.MemberService;
import jpaBook.jpaShop.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;
	private final MemberService memberService;
	private final ItemService itemService;

	@GetMapping("/order")
	public String createForm(Model model) {
		List<Member> members = memberService.findAllMembers();
		List<Item> items = itemService.findAllItems();

		model.addAttribute("members", members);
		model.addAttribute("items", items);

		return "order/createOrder";
	}

	@PostMapping("/order")
	public String order(@RequestParam("memberId") @NotNull Long memberId,
						@RequestParam("itemId") @NotNull Long itemId,
						@RequestParam("count") @Min(1) int count) {
		try {
			orderService.order(memberId, itemId, count);
		} catch (Exception e) {
			return "redirect:/order";
		}
		return "redirect:/orders";
	}

	@GetMapping("/orders")
	public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model) {
		List<Order> orders = orderService.findAllByString(orderSearch);
		model.addAttribute("orders", orders);

		return "order/orderList";
	}

	@PostMapping("/orders/{orderId}/cancel")
	public String cancelOrder(@PathVariable("orderId") Long orderId) {
		orderService.cancelOrder(orderId);
		return "redirect:/orders";
	}
}
