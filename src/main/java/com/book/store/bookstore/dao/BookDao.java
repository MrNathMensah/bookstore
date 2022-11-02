package com.book.store.bookstore.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.book.store.bookstore.pojo.Book;

@Repository
public interface BookDao extends JpaRepository<Book, Integer> {

}
