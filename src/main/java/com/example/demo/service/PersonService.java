package com.example.demo.service;

import com.example.demo.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface PersonService {
    ArrayList<Person> getAllPersons();
    Person deletePerson(Long id);
}
