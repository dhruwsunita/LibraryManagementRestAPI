package com.glearning.Library.service;

import java.util.List;

import com.glearning.Library.entity.Book;

public interface BookService {
	
	public List<Book> findAll();
	
	public Book findById(int theId);
	
	public void save(Book theBook);
	
	public void deleteById(int theId);
	
	public List<Book> searchBy(String name, String author);
}
