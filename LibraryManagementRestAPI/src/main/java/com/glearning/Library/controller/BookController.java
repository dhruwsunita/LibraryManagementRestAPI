package com.glearning.Library.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.glearning.Library.entity.Book;
import com.glearning.Library.service.BookService;

@Controller
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookService bookService;

	@RequestMapping("/list")
	public String listBooks(Model model) {
		List<Book> theBooks = bookService.findAll();
		model.addAttribute("Books", theBooks);
		return "list-books";

	}

	@RequestMapping("/showFormForAdd")
	public String addBook(Model model) {
		Book theBook = new Book();
		model.addAttribute("Book", theBook);
		return "add_book";
	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("bookId") int theId, Model theModel) {

		// get the Book from the service
		Book theBook = bookService.findById(theId);

		// set Book as a model attribute to pre-populate the form
		theModel.addAttribute("Book", theBook);

		// send over to our form
		return "add_book";
	}

	// saving the book
	@PostMapping("/save")
	public String saveBook(@RequestParam("id") int id, @RequestParam("name") String name,
			@RequestParam("category") String category, @RequestParam("author") String author) {

		Book theBook;
		if (id != 0) {
			theBook = bookService.findById(id);
			theBook.setName(name);
			theBook.setCategory(category);
			theBook.setAuthor(author);
		} else {
			theBook = new Book( name, category, author);
		}
		bookService.save(theBook);
		return "redirect:/books/list";
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("bookId") int theId) {
		bookService.deleteById(theId);
		return "redirect:/books/list";
	}

	@RequestMapping("/403")
	public ModelAndView accessDenied(Principal user) {
		ModelAndView model = new ModelAndView();
		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() + " you do not have permission to access to this page!");
		} else {
			model.addObject("msg", "You have permission to access this page!");
		}
		model.setViewName("403");
		return model;
	}

	@RequestMapping("/search")
	public String search(@RequestParam("name") String name, @RequestParam("author") String author, Model model) {
		if (name.trim().isEmpty() && author.trim().isEmpty()) {
			return "redirect:/books/list";
		} else {
			List<Book> theBooks = bookService.searchBy(name, author);
			model.addAttribute("Books", theBooks);
			return "list-books";
		}
	}

}
