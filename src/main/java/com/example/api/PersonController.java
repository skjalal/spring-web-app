package com.example.api;

import com.example.model.PersonDto;
import com.example.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PersonController {

  private final PersonService personService;

  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  @GetMapping
  public ResponseEntity<List<PersonDto>> findAll() {
    return new ResponseEntity<>(personService.findAll(), HttpStatus.OK);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> addPerson(@RequestBody PersonDto personDto) {
    personService.save(personDto);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping("{id}")
  public ResponseEntity<PersonDto> getPerson(@PathVariable("id") int id) {
    return new ResponseEntity<>(personService.findById(id), HttpStatus.OK);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deletePerson(@PathVariable("id") int id) {
    personService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
  }
}
