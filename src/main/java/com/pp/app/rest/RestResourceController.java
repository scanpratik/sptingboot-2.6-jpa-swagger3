package com.pp.app.rest;

import com.pp.app.data.entity.Person;
import com.pp.app.data.service.PersonService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class RestResourceController {


    private PersonService personService = null;

    @Autowired
    RestResourceController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> getAllPerson() {
        return personService.getAllPersons();
    }


    @GetMapping("/{personId}")
    public ResponseEntity<Person> getPerson(@PathVariable("personId") int personId) {
        return personService.getPersonById(personId).map(ResponseEntity::ok).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Person> addNewPerson(@RequestBody Person person) {
        return personService.saveUpdatePerson(person).map(ResponseEntity::ok).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping
    public ResponseEntity<Person> updatePerson(@RequestBody Person person) {

        return personService.saveUpdatePerson(person).map(ResponseEntity::ok).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{personId}")
    public ResponseEntity<Optional<Boolean>> deletePerson(@PathVariable("personId") int personId) {

        Optional<Boolean> response = personService.removePerson(personId);
        return ResponseEntity.ok().body(response);


    }
}
