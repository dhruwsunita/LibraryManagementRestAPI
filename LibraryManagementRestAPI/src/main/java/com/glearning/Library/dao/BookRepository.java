package com.glearning.Library.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.glearning.Library.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
	
	List<Book> findByNameContainsAndAuthorContainsAllIgnoreCase(String name, String author);

}
