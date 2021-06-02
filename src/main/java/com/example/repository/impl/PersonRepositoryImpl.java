package com.example.repository.impl;

import com.example.domain.Person;
import com.example.repository.PersonRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class PersonRepositoryImpl implements PersonRepository {

  private final SessionFactory sessionFactory;

  @Autowired
  public PersonRepositoryImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public void save(Person person) {
    Session session = getSession();
    session.saveOrUpdate(person);
  }

  @Override
  public List<Person> findAll() {
    Session session = getSession();
    return session.createQuery("from Person", Person.class).getResultList();
  }

  @Override
  public void deleteById(Integer id) {
    Session session = getSession();
    session.delete(session.get(Person.class, id));
  }

  @Override
  public Person findById(Integer id) {
    Session session = getSession();
    return session.get(Person.class, id);
  }

  private Session getSession() {
    return sessionFactory.getCurrentSession();
  }
}
