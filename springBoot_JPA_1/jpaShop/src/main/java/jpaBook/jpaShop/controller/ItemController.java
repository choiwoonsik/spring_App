package jpaBook.jpaShop.controller;

import jpaBook.jpaShop.controller.form.AlbumForm;
import jpaBook.jpaShop.controller.form.BookForm;
import jpaBook.jpaShop.controller.form.ItemForm;
import jpaBook.jpaShop.controller.form.MovieForm;
import jpaBook.jpaShop.domain.item.*;
import jpaBook.jpaShop.service.ItemService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ItemController {

	private final ItemService itemService;

	@GetMapping("/items/book/new")
	public String createBookForm(Model model) {
		model.addAttribute("form", new BookForm());
		return "items/book/createBookForm";
	}

	@GetMapping("/items/movie/new")
	public String createMovieForm(Model model) {
		model.addAttribute("form", new MovieForm());
		return "items/movie/createMovieForm";
	}

	@GetMapping("/items/album/new")
	public String createAlbumForm(Model model) {
		model.addAttribute("form", new AlbumForm());
		return "items/album/createAlbumForm";
	}

	@PostMapping("/items/book/new")
	public String createBookItem(@Valid BookForm form, BindingResult binding) {

		if (binding.hasErrors()) {
			log.info("BOOK ERROR !!!");
			return "redirect:/items/book/new";
		}

		Book book = new Book();
		book.setId(form.getId());
		book.setName(form.getName());
		book.setPrice(form.getPrice());
		book.setStockQuantity(form.getStockQuantity());
		book.setAuthor(form.getAuthor());
		book.setIsbn(form.getIsbn());
		book.setItemType(ItemType.Book);

		itemService.saveItem(book);

		return "redirect:/";
	}

	@PostMapping("/items/movie/new")
	public String createMovieItem(@Valid MovieForm form, BindingResult binding) {

		if (binding.hasErrors()) {
			return "items/movie/createMovieForm";
		}

		Movie movie = new Movie();
		movie.setId(form.getId());
		movie.setName(form.getName());
		movie.setPrice(form.getPrice());
		movie.setStockQuantity(form.getStockQuantity());
		movie.setDirector(form.getDirector());
		movie.setActor(form.getActor());
		movie.setItemType(ItemType.Movie);

		itemService.saveItem(movie);

		return "redirect:/";
	}

	@PostMapping("/items/album/new")
	public String createAlbumItem(@Valid AlbumForm form) {
		Album album = new Album();
		album.setId(form.getId());
		album.setName(form.getName());
		album.setPrice(form.getPrice());
		album.setStockQuantity(form.getStockQuantity());
		album.setArtist(form.getArtist());
		album.setArtist(form.getEtc());
		album.setItemType(ItemType.Album);

		itemService.saveItem(album);

		return "redirect:/";
	}

	@GetMapping("/items")
	public String list(Model model) {
		List<Item> items = itemService.findAllItems();
		model.addAttribute("items", items);
		return "items/itemList";
	}

	@GetMapping("/items/{type}/{itemId}/edit")
	public String updateItemForm(@PathVariable("itemId") Long itemId, @PathVariable("type") ItemType type, Model model) {
		if (type == ItemType.Book) {
			Book item = (Book) itemService.findItem(itemId);
			BookForm form = new BookForm();
			form.setId(item.getId());
			form.setName(item.getName());
			form.setPrice(item.getPrice());
			form.setStockQuantity(item.getStockQuantity());
			form.setAuthor(item.getAuthor());
			form.setIsbn(item.getIsbn());

			model.addAttribute("form", form);
			return "items/book/updateBookForm";
		} else if (type == ItemType.Album) {
			Album item = (Album) itemService.findItem(itemId);
			AlbumForm form = new AlbumForm();
			form.setId(item.getId());
			form.setName(item.getName());
			form.setPrice(item.getPrice());
			form.setStockQuantity(item.getStockQuantity());
			form.setArtist(item.getArtist());
			form.setEtc(item.getEtc());

			model.addAttribute("form", form);
			return "items/album/updateAlbumForm";
		} else {
			Movie item = (Movie) itemService.findItem(itemId);
			MovieForm form = new MovieForm();
			form.setId(item.getId());
			form.setName(item.getName());
			form.setPrice(item.getPrice());
			form.setStockQuantity(item.getStockQuantity());
			form.setDirector(item.getDirector());
			form.setActor(item.getActor());

			model.addAttribute("form", form);
			return "items/movie/updateMovieForm";
		}
	}

	@PostMapping("/items/Book/{itemId}/edit")
	public String updateBookItem(@PathVariable("itemId") Long itemId, @ModelAttribute("form") @Valid BookForm form, BindingResult binding) {

		if (binding.hasErrors()) {
			return "items/book/updateBookForm";
		}

//		Book book = new Book();
//		book.setId(form.getId());
//		book.setPrice(form.getPrice());
//		book.setStockQuantity(form.getStockQuantity());
//		book.setItemType(ItemType.Book);
//		itemService.saveItem(book);

		itemService.updateItem(itemId, form.getName(), form.getPrice(), form.getStockQuantity());

		return "redirect:/items";
	}

	@PostMapping("/items/Movie/{itemId}/edit")
	public String updateMovieItem(@PathVariable("itemId") Long itemId, @ModelAttribute("form") @Valid MovieForm form, BindingResult binding) {

		if (binding.hasErrors()) {
			return "items/movie/updateMovieForm";
		}

		Movie movie = new Movie();
		movie.setId(form.getId());
		movie.setName(form.getName());
		movie.setPrice(form.getPrice());
		movie.setStockQuantity(form.getStockQuantity());
		movie.setDirector(form.getDirector());
		movie.setActor(form.getActor());
		movie.setItemType(ItemType.Movie);
		itemService.saveItem(movie);

		return "redirect:/items";
	}

	@PostMapping("/items/Album/{itemId}/edit")
	public String updateAlbumItem(@PathVariable("itemId") Long itemId, @ModelAttribute("form") @Valid AlbumForm form, BindingResult binding) {

		if (binding.hasErrors()) {
			return "items/album/updateAlbumForm";
		}

		Album album = new Album();
		album.setId(form.getId());
		album.setName(form.getName());
		album.setPrice(form.getPrice());
		album.setStockQuantity(form.getStockQuantity());
		album.setArtist(form.getArtist());
		album.setArtist(form.getArtist());
		album.setItemType(ItemType.Movie);
		itemService.saveItem(album);

		return "redirect:/items";
	}

}
