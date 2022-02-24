package com.example.demo.service;

import com.example.demo.exception.PersonNotFoundException;
import com.example.demo.model.Person;
import com.example.demo.repo.PersonRepo;
import com.example.demo.utils.EmailValidator;
import com.example.demo.utils.PhoneNumberValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService{

    private final PersonRepo personRepo;
    private final PhoneNumberValidator phoneNumberValidator;
    private final EmailValidator emailValidator;

    @Autowired
    public PersonServiceImpl(PersonRepo personRepo,
                             PhoneNumberValidator phoneNumberValidator,
                             EmailValidator emailValidator){
        this.personRepo=personRepo;
        this.phoneNumberValidator=phoneNumberValidator;
        this.emailValidator=emailValidator;
    }

    public ArrayList<Person> getAllPersons(){
        ArrayList<Person> allPersons = personRepo.getAllPerson();
        return allPersons;
    }

    public void addPerson(Person personToBeSaved){
        String phoneNumber = personToBeSaved.getNumber();
        String email2 = personToBeSaved.getEmail();
/*      This part is for phoneNumberValidation*/
        if (!phoneNumberValidator.test(phoneNumber)) {
            throw new IllegalStateException("Phone Number " + phoneNumber + " is not valid");
        }
        Optional<Person> personOptional = personRepo
                .findPersonByPhoneNumber(phoneNumber);
        if(personOptional.isPresent()){
            Person person = personOptional.get();
            if (person.getName().equals(personToBeSaved.getName())){
                return ;
            }
            throw new IllegalStateException(String.format("phone number [%s] is taken", phoneNumber));
        }

        /*      This part is for emailValidator*/
        if(!emailValidator.test(personToBeSaved.getEmail())){
            throw new IllegalStateException(String.format("The email [%s] is not valid", email2));
        }
        Optional<Person> personOptional1 = personRepo
                .findPersonByEmail(email2);
        if(personOptional1.isPresent()){
            Person person2 = personOptional1.get();
            if (person2.getEmail().equals(email2)){
                return ;
            }
            throw new IllegalStateException(String.format("The Email [%s] is taken", email2));
        }
//        if (!isEmailExists(personToBeSaved)){
//            throw new IllegalStateException(String.format("The email [%s] is taken", email2));
//        }

         personRepo.save(personToBeSaved);
    }
//    private boolean isEmailExists(Person person){
//        Optional<Person> personOptional = personRepo
//                .findPersonByEmail(person.getEmail());
//        if (personOptional == null){
//            return true;
//        }
//        return false;
//    }

    public Person updatePerson(Person person) {
         personRepo.updatePerson(
                 person.getId(),
                 person.getName(),
                 person.getAge(),
                 person.getEmail(),
                 person.getNumber() );
         return null;
    }


    public Person findPersonById(Long id) {
        return personRepo.findPersonById(id)
                .orElseThrow(() -> new PersonNotFoundException("Person By id " + id + " Not Found"));
    }

    @Override
    public Person deletePerson(Long id) {
        personRepo.deletePersonById(id);
        return null;
    }
}
