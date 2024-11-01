package br.com.alatorre.unittests.mapper.mocks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.alatorre.data.vo.v1.BookVO;
import br.com.alatorre.model.Book;

public class MockBook {


    public Book mockEntity() {
        return mockEntity(0);
    }
    
    public BookVO mockVO() {
        return mockVO(0);
    }
    
    public List<Book> mockEntityList() {
        List<Book> books = new ArrayList<Book>();
        for (int i = 1; i < 15; i++) {
            books.add(mockEntity(i));
        }
        return books;
    }

    public List<BookVO> mockVOList() {
        List<BookVO> books = new ArrayList<>();
        for (int i = 1; i < 15; i++) {
            books.add(mockVO(i));
        }
        return books;
    }
    
    public Book mockEntity(Integer number) {
    	Double priceDouble = 100.00 + number;
        Book book = new Book();
        book.setAuthor("Author Test" + number);
        System.out.print("Author = "+book.getAuthor());
        book.setTitle("Title Test" + number);
        System.out.print(" || Title= " + book.getTitle());
        book.setLaunch_date(new Date());
        System.out.print(" || Launch_date = " + book.getLaunch_date());
        book.setId(number.longValue());
        System.out.print(" || Id = " + book.getId());
        book.setPrice(priceDouble);
        System.out.print(" || Price = " + book.getPrice());
        return book;
    }

    public BookVO mockVO(Integer number) {
    	Double priceDouble = 100.00 + number;
        BookVO book = new BookVO();
        book.setAuthor("Author Test" + number);
        book.setTitle("Title Test" + number);
        book.setLaunch_date(new Date());
        book.setKey(number.longValue());
        book.setPrice(priceDouble);
        return book;
    }
}
