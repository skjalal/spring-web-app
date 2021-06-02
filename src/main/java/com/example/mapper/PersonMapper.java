package com.example.mapper;

import com.example.domain.Person;
import com.example.model.PersonDto;
import org.mapstruct.Mapper;

@Mapper
public interface PersonMapper {

  PersonDto toPersonDto(Person person);

  Person toPerson(PersonDto dto);
}
