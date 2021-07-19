package jpaBook.jpaShop.controller;

import jpaBook.jpaShop.controller.form.AlbumForm;
import jpaBook.jpaShop.controller.form.BookForm;
import jpaBook.jpaShop.controller.form.MovieForm;
import jpaBook.jpaShop.domain.item.*;
import jpaBook.jpaShop.service.ItemService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

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
	public String create(@Valid BookForm form) {
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
	public String create(@Valid MovieForm form) {
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
	public String create(@Valid AlbumForm form) {
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

}
