package br.com.alatorre.unittests.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alatorre.data.vo.v1.BookVO;
import br.com.alatorre.data.vo.v1.PersonVO;
import br.com.alatorre.mapper.DozerMapper;
import br.com.alatorre.model.Book;
import br.com.alatorre.model.Person;
import br.com.alatorre.unittests.mapper.mocks.MockBook;
import br.com.alatorre.unittests.mapper.mocks.MockPerson;

public class DozerConverterTest {
    
    MockPerson personInputObject;
    MockBook bookInputObject;

    @BeforeEach
    public void setUp() {
        personInputObject = new MockPerson();
        bookInputObject = new MockBook();
    }

    @Test
    public void parseEntityToVOTest() {
        PersonVO PersonOutput = DozerMapper.parseObject(personInputObject.mockEntity(), PersonVO.class);
        BookVO BookOutput = DozerMapper.parseObject(bookInputObject.mockEntity(), BookVO.class);
        assertEquals(Long.valueOf(0L), PersonOutput.getKey());
        assertEquals("First Name Test0", PersonOutput.getFirstName());
        assertEquals("Last Name Test0", PersonOutput.getLastName());
        assertEquals("Addres Test0", PersonOutput.getAddress());
        assertEquals("Male", PersonOutput.getGender());
        
        assertEquals(Long.valueOf(0L), BookOutput.getKey());
		assertEquals("Author Test0", BookOutput.getAuthor());
		assertEquals("Title Test0", BookOutput.getTitle());
		assertNotNull(BookOutput.getLaunch_date());
		assertEquals("100.0", BookOutput.getPrice().toString());
       
    }

    @Test
    public void parseEntityListToVOListTest() {
        List<PersonVO> personOutputList = DozerMapper.parseListObjects(personInputObject.mockEntityList(), PersonVO.class);
        List<BookVO> bookOutputList = DozerMapper.parseListObjects(bookInputObject.mockEntityList(), BookVO.class);
        
        PersonVO personOutputUm = personOutputList.get(1);
        BookVO bookOutputUm = bookOutputList.get(0);
        
        assertEquals(Long.valueOf(1L), personOutputUm.getKey());
        assertEquals("First Name Test1", personOutputUm.getFirstName());
        assertEquals("Last Name Test1", personOutputUm.getLastName());
        assertEquals("Addres Test1", personOutputUm.getAddress());
        assertEquals("Female", personOutputUm.getGender());
        
        assertEquals(Long.valueOf(1L), bookOutputUm.getKey());
		assertEquals("Author Test1", bookOutputUm.getAuthor());
		assertEquals("Title Test1", bookOutputUm.getTitle());
		assertNotNull(bookOutputUm.getLaunch_date());
		assertEquals("101.0", bookOutputUm.getPrice().toString());
        
        PersonVO personOutputSeven = personOutputList.get(7);
        BookVO bookOutputSeven = bookOutputList.get(6);
        
        assertEquals(Long.valueOf(7L), personOutputSeven.getKey());
        assertEquals("First Name Test7", personOutputSeven.getFirstName());
        assertEquals("Last Name Test7", personOutputSeven.getLastName());
        assertEquals("Addres Test7", personOutputSeven.getAddress());
        assertEquals("Female", personOutputSeven.getGender());
        
        assertEquals(Long.valueOf(7L), bookOutputSeven.getKey());
		assertEquals("Author Test7", bookOutputSeven.getAuthor());
		assertEquals("Title Test7", bookOutputSeven.getTitle());
		assertNotNull(bookOutputSeven.getLaunch_date());
		assertEquals("107.0", bookOutputSeven.getPrice().toString());
        
        PersonVO personOutputTwelve = personOutputList.get(12);
        BookVO bookOutputTwelve = bookOutputList.get(11);
        
        assertEquals(Long.valueOf(12L), personOutputTwelve.getKey());
        assertEquals("First Name Test12", personOutputTwelve.getFirstName());
        assertEquals("Last Name Test12", personOutputTwelve.getLastName());
        assertEquals("Addres Test12", personOutputTwelve.getAddress());
        assertEquals("Male", personOutputTwelve.getGender());
        
        assertEquals(Long.valueOf(12L), bookOutputTwelve.getKey());
		assertEquals("Author Test12", bookOutputTwelve.getAuthor());
		assertEquals("Title Test12", bookOutputTwelve.getTitle());
		assertNotNull(bookOutputTwelve.getLaunch_date());
		assertEquals("112.0", bookOutputTwelve.getPrice().toString());
    }

    @Test
    public void parseVOToEntityTest() {
        Person personOutput = DozerMapper.parseObject(personInputObject.mockVO(), Person.class);
        Book bookOutput = DozerMapper.parseObject(bookInputObject.mockVO(), Book.class);
        assertEquals(Long.valueOf(0L), personOutput.getId());
        assertEquals("First Name Test0", personOutput.getFirstName());
        assertEquals("Last Name Test0", personOutput.getLastName());
        assertEquals("Addres Test0", personOutput.getAddress());
        assertEquals("Male", personOutput.getGender());        
        assertEquals(Long.valueOf(0L), bookOutput.getId());
		assertEquals("Author Test0", bookOutput.getAuthor());
		assertEquals("Title Test0", bookOutput.getTitle());
		assertNotNull(bookOutput.getLaunch_date());
		assertEquals("100.0", bookOutput.getPrice().toString());               
    }

    @Test
    public void parseVOListToEntityListTest() {
        List<Person> personOutputList = DozerMapper.parseListObjects(personInputObject.mockVOList(), Person.class);
        List<Book> bookOutputList = DozerMapper.parseListObjects(bookInputObject.mockVOList(), Book.class);
        Person personOutputUm = personOutputList.get(1);
        Book bookOutputUm = bookOutputList.get(0);
        
        assertEquals(Long.valueOf(1L), personOutputUm.getId());
        assertEquals("First Name Test1", personOutputUm.getFirstName());
        assertEquals("Last Name Test1", personOutputUm.getLastName());
        assertEquals("Addres Test1", personOutputUm.getAddress());
        assertEquals("Female", personOutputUm.getGender());
        
        assertEquals(Long.valueOf(1L), bookOutputUm.getId());

		assertEquals("Author Test1", bookOutputUm.getAuthor());
		assertEquals("Title Test1", bookOutputUm.getTitle());
		assertNotNull(bookOutputUm.getLaunch_date());
		assertEquals("101.0", bookOutputUm.getPrice().toString());
                       
        Person personOutputSeven = personOutputList.get(7);
        Book bookOutputSeven = bookOutputList.get(6);
        
        assertEquals(Long.valueOf(7L), personOutputSeven.getId());
        assertEquals("First Name Test7", personOutputSeven.getFirstName());
        assertEquals("Last Name Test7", personOutputSeven.getLastName());
        assertEquals("Addres Test7", personOutputSeven.getAddress());
        assertEquals("Female", personOutputSeven.getGender());
        
        assertEquals(Long.valueOf(7L), bookOutputSeven.getId());
		assertEquals("Author Test7", bookOutputSeven.getAuthor());
		assertEquals("Title Test7", bookOutputSeven.getTitle());
		assertNotNull(bookOutputSeven.getLaunch_date());
		assertEquals("107.0", bookOutputSeven.getPrice().toString());
        
        Person personOutputTwelve = personOutputList.get(12);
        Book bookOutputTwelve = bookOutputList.get(11);
        
        assertEquals(Long.valueOf(12L), personOutputTwelve.getId());
        assertEquals("First Name Test12", personOutputTwelve.getFirstName());
        assertEquals("Last Name Test12", personOutputTwelve.getLastName());
        assertEquals("Addres Test12", personOutputTwelve.getAddress());
        assertEquals("Male", personOutputTwelve.getGender());
        
        assertEquals(Long.valueOf(12L), bookOutputTwelve.getId());
		assertEquals("Author Test12", bookOutputTwelve.getAuthor());
		assertEquals("Title Test12", bookOutputTwelve.getTitle());
		assertNotNull(bookOutputTwelve.getLaunch_date());
		assertEquals("112.0", bookOutputTwelve.getPrice().toString());
    }
}
