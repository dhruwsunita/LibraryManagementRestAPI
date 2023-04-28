package com.glearning.Library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glearning.Library.dao.BookRepository;
import com.glearning.Library.entity.Book;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
    private BookRepository bookRepository;

	@Override
	public List<Book> findAll() {
		List<Book> books = bookRepository.findAll();
		return books;
	}

	@Override
	public void save(Book theBook) {
		bookRepository.save(theBook);

	}

	@Override
	public void deleteById(int theId) {
		bookRepository.deleteById(theId);

	}

	@Override
	public List<Book> searchBy(String name, String author) {
		List<Book> books = bookRepository.findByNameContainsAndAuthorContainsAllIgnoreCase(name, author);
		return books;
	}

	@Override
	public Book findById(int theId) {
		
		Optional<Book> result = bookRepository.findById(theId);

		Book theBook = null;

		if (result.isPresent()) {
			theBook = result.get();
		} else {
			// book not found
			throw new RuntimeException("Did not find book id - " + theId);
		}
		return theBook;
	
	}

}
