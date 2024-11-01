package br.com.alatorre.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alatorre.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {}