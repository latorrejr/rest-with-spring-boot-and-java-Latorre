package br.com.alatorre.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alatorre.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {}
