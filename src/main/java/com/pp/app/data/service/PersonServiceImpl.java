package com.pp.app.data.service;

import com.pp.app.data.entity.Person;
import com.pp.app.data.repository.PersonRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class PersonServiceImpl implements PersonService {
    private PersonRepository personRepo = null;

    @Autowired
    PersonServiceImpl(PersonRepository personRepo) {
        this.personRepo = personRepo;
    }

    @Override
    public Optional<Person> getPersonById(int personId) {
        return personRepo.findById(personId);
    }

    @Override
    public List<Person> getAllPersons() {
        return personRepo.findAll();
    }

    @Override
    public Optional<Boolean> removePerson(int personId) {
        personRepo.deleteById(personId);
        return Optional.of(true);
    }

    @Override
    public Optional<Person> saveUpdatePerson(Person person) {
        if (person.getPersonId() == 0 || personRepo.existsById(person.getPersonId())) {
           Person updatedPerson = personRepo.save(person);
            return Optional.of(updatedPerson);
        } else {
            return Optional.empty();
        }
    }
}