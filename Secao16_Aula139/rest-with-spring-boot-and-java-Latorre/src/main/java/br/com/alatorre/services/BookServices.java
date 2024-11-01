package br.com.alatorre.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alatorre.controllers.BookController;
import br.com.alatorre.data.vo.v1.BookVO;
import br.com.alatorre.exceptions.RequiredObjectIsNullException;
import br.com.alatorre.exceptions.ResourceNotFoundException;
import br.com.alatorre.mapper.DozerMapper;
import br.com.alatorre.model.Book;
import br.com.alatorre.repositories.BookRepository;

@Service
public class BookServices {
	
	private Logger logger = Logger.getLogger(BookServices.class.getName());	
	
	@Autowired
	BookRepository repository;
	

	public List<BookVO> findAll() {

		logger.info("Finding all books!");

		var books = DozerMapper.parseListObjects(repository.findAll(), BookVO.class);
		books
			.stream()
			.forEach(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()));
		return books;
	}

	public BookVO findById(Long id) {
		
		logger.info("Finding one book!");
		
		var entity = repository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		System.out.println("PC 12");
		System.out.println("entity.getId() = " + entity.getId());
		System.out.println("entity.getAuthor_() = " + entity.getAuthor());
		System.out.println("entity.getTitle_() = " + entity.getTitle());
		System.out.println("entity.getPrice_() = " + entity.getPrice());
		System.out.println("entity.getLaunch_date_() = " + entity.getLaunch_date());
		System.out.println("entity.id = " + entity.getId() + " e entity.author = " + entity.getAuthor());
		//return DozerMapper.parseObject(entity, PersonVO.class);
		BookVO vo = DozerMapper.parseObject(entity, BookVO.class);
		System.out.println("PC 13");
		System.out.println("vo.getKey() = " + vo.getKey());
		System.out.println("vo.getAuthor() = " + vo.getAuthor());
		System.out.println("vo.getTitle() = " + vo.getTitle());
		System.out.println("vo.getPrice() = " + vo.getPrice());
		System.out.println("vo.getLaunch_date() = " + vo.getLaunch_date());
		vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		System.out.println("vo (key) = " + vo.getKey() + " e vo (author) = " + vo.getAuthor());
		return vo;
	}
	
	public BookVO create(BookVO book) {

		if(book==null) throw new RequiredObjectIsNullException();
		
		logger.info("Creating one book!");
		var entity = DozerMapper.parseObject(book, Book.class);
		var vo =  DozerMapper.parseObject(repository.save(entity), BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}
	
	
	public BookVO update(BookVO book) {
		if(book==null) throw new RequiredObjectIsNullException();
		logger.info("Updating one book!");
		
		var entity = repository.findById(book.getKey())
			.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
/*
		entity.setFirstName(book.getFirstName());
		entity.setLastName(book.getLastName());
		entity.setAddress(book.getAddress());
		entity.setGender(book.getGender());*/
		
		entity = DozerMapper.parseObject(book, Book.class);
		
		var vo =  DozerMapper.parseObject(repository.save(entity), BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}
	
	public void delete(Long id) {
		
		logger.info("Deleting one book!");
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		repository.delete(entity);
	}
}
