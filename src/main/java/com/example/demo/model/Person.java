package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Entity
@Table(name = "Person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;
    private int age;
    @NonNull
    private String email;
    private String number;

   public Person(){}

    public Person(String name,
                  int age,
                  String email,
                  String number) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.number = number;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
