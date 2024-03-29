package com.example.imdb.controller;

import com.example.imdb.model.requests.PersonRequest;
import com.example.imdb.model.responses.PersonResponse;
import com.example.imdb.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/people")
public class PersonController {

    private PersonService personService;

    @PostMapping
    public ResponseEntity<PersonResponse> addPerson(@RequestBody PersonRequest request) {
        return new ResponseEntity<>(personService.addPerson(request), HttpStatus.CREATED);
    }

    @PutMapping("/{personId}")
    public ResponseEntity<PersonResponse> updatePerson(@PathVariable String personId,
                                                       @RequestBody PersonRequest request) {
        return new ResponseEntity<>(personService.updatePerson(personId, request), HttpStatus.OK);
    }

    @DeleteMapping("/{personId}")
    public ResponseEntity<Void> deletePerson(@PathVariable String personId) {
        personService.deletePerson(personId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<PersonResponse>> getPeople() {
        return new ResponseEntity<>(personService.getAllPersons(), HttpStatus.OK);
    }

    @GetMapping("/{personId}")
    public ResponseEntity<PersonResponse> getPerson(@PathVariable String personId) {
        return new ResponseEntity<>(personService.getPerson(personId), HttpStatus.OK);
    }

    @GetMapping("/{month}/{day}")
    public ResponseEntity<List<PersonResponse>> getPerson(@PathVariable Integer month,
                                                          @PathVariable Integer day) {
        return new ResponseEntity<>(personService.getPeopleByBirthDate(month, day), HttpStatus.OK);
    }
}
