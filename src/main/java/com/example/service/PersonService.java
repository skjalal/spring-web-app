package com.example.service;

import com.example.mapper.PersonMapper;
import com.example.model.PersonDto;
import com.example.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

  private final PersonRepository personRepository;
  private final PersonMapper personMapper;

  @Autowired
  public PersonService(PersonRepository personRepository, PersonMapper personMapper) {
    this.personRepository = personRepository;
    this.personMapper = personMapper;
  }

  public void save(PersonDto personDto) {
    Optional.ofNullable(personDto).map(personMapper::toPerson).ifPresent(personRepository::save);
  }

  public List<PersonDto> findAll() {
    return personRepository.findAll().stream()
        .map(personMapper::toPersonDto)
        .collect(Collectors.toList());
  }

  public void deleteById(Integer id) {
    personRepository.deleteById(id);
  }

  public PersonDto findById(int id) {
    return personMapper.toPersonDto(personRepository.findById(id));
  }
}
