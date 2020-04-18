package com.pp.app.data.service;

import com.pp.app.data.entity.Person;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PersonService {

    public Optional<Person> getPersonById(int personId);

    public List<Person> getAllPersons();

    public Optional<Boolean> removePerson(int personId);

    public Optional<Person> saveUpdatePerson(Person person);

}
