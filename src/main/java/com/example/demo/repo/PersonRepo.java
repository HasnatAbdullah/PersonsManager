package com.example.demo.repo;

import com.example.demo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface PersonRepo extends JpaRepository<Person,Long> {

    @Query(value = "SELECT * FROM person", nativeQuery = true)
    public ArrayList<Person> getAllPerson();

    Optional<Person> findPersonById(Long id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE person u SET u.name=?2 , u.age=?3,  u.email=?4, u.number= ?5 WHERE u.id=?1", nativeQuery = true)
    void  updatePerson( Long id, String name, int age, String email, String number);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE from person WHERE id=?1", nativeQuery = true)
    void deletePersonById(Long id);


    @Query(
            value = "select id, age, email, name, number " +
                    "from person where number = :number",
            nativeQuery = true
    )
    Optional<Person> findPersonByPhoneNumber(
            @Param("number") String phoneNumber);


    @Query(
            value =" select id, age, email, name, number " +
            "from person where email = :email",
            nativeQuery = true
    )
    Optional<Person> findPersonByEmail(
            @Param("email") String email);
}
