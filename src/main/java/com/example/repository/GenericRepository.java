package com.example.repository;

import com.example.domain.Person;

import java.util.List;

public interface GenericRepository<T, E> {

  void save(T t);

  List<T> findAll();

  void deleteById(E id);

  Person findById(E id);
}
