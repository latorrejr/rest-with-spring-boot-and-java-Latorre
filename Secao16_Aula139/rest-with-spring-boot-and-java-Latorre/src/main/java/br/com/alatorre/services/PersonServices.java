package br.com.alatorre.services;

import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import br.com.alatorre.controllers.PersonController;
import br.com.alatorre.data.vo.v1.PersonVO;
import br.com.alatorre.data.vo.v2.PersonVOV2;
import br.com.alatorre.exceptions.RequiredObjectIsNullException;
import br.com.alatorre.exceptions.ResourceNotFoundException;
import br.com.alatorre.mapper.DozerMapper;
import br.com.alatorre.mapper.custom.PersonMapper;
import br.com.alatorre.model.Person;
import br.com.alatorre.repositories.PersonRepository;

@Service
public class PersonServices {
	
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	@Autowired
	PersonRepository repository;
	
	@Autowired
	PersonMapper mapper;

	public List<PersonVO> findAll() {

		logger.info("Finding all people!");

		var persons = DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
		persons
			.stream()
			.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
		return persons;
	}

	public PersonVO findById(Long id) {
		
		logger.info("Finding one person!");
		
		var entity = repository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		//return DozerMapper.parseObject(entity, PersonVO.class);
		PersonVO vo = DozerMapper.parseObject(entity, PersonVO.class);
		System.out.println("PC 10");
		System.out.println("vo.getKey() = " + vo.getKey());
		System.out.println("vo.getFirstName() = " + vo.getFirstName());
		System.out.println("vo.getLastName() = " + vo.getLastName());
		System.out.println("vo.getGender() = " + vo.getGender());
		System.out.println("vo.getAddress() = " + vo.getAddress());
		vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return vo;
	}
	
	public PersonVO create(PersonVO person) {

		if(person==null) throw new RequiredObjectIsNullException();
		
		logger.info("Creating one person!");
		var entity = DozerMapper.parseObject(person, Person.class);
		var vo =  DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}
	
	public PersonVOV2 createV2(PersonVOV2 person) {
		
		logger.info("Creating one person na V2!");
		var entity = mapper.convertVoToEntity(person);
		var vo =  mapper.convertEntityToVo(repository.save(entity));
		return vo;
	}
	
	public PersonVO update(PersonVO person) {
		if(person==null) throw new RequiredObjectIsNullException();
		logger.info("Updating one person!");
		
		var entity = repository.findById(person.getKey())
			.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
/*
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());*/
		
		entity = DozerMapper.parseObject(person, Person.class);
		
		var vo =  DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}
	
	public void delete(Long id) {
		
		logger.info("Deleting one person!");
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		repository.delete(entity);
	}
}
