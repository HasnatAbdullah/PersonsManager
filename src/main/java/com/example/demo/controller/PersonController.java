package com.example.demo.controller;


import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import com.example.demo.service.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class PersonController {


    @Autowired
    PersonServiceImpl personServiceImpl;

    @GetMapping("/person")
    public String personDashboard(){
        return "person";
    }

    @GetMapping("/all")
    public String allPerson(Model model){

        ArrayList<Person> allPersons = personServiceImpl.getAllPersons();
        model.addAttribute("allPerson", allPersons);
        return "person_list";
    }

//    First addPerson is to create a new person object that the second one is expecting
    @GetMapping("/addPerson")
    public String addPersonForm(Model model) {

        Person person = new Person();
        model.addAttribute("person", person);
        return "addPerson";
    }
//      This person will save the data
//    @PostMapping("/add")
//    public String savePerson(@ModelAttribute("person")Person person, BindingResult result, Model model){
//        personServiceImpl.addPerson(person);
//        String msg = "Added Person";
//        model.addAttribute("successMessage", msg);
//        model.addAttribute("person", new Person());
//        return "addPerson";
//    }
//    This one is trying to validate data
    @PostMapping("/add")
    public String savePerson(@ModelAttribute("person")Person person,
                             BindingResult result, Model model){
        if(result.hasErrors()){
            return "addPerson";
        }
        else {
            personServiceImpl.addPerson(person);
            String msg = "Added Person";
            model.addAttribute("successMessage", msg);
            model.addAttribute("person", new Person());
            return "redirect:/all";
        }
    }

    @RequestMapping(value= "edit/{id}", method = RequestMethod.GET)
    public String editPerson(@PathVariable("id") Long id, ModelMap model) {
        model.put("editPerson", personServiceImpl.findPersonById(id));
        return  "editPerson";
    }
//    @RequestMapping(value = "/update", method = RequestMethod.POST)
//    public String updatePerson(@RequestBody Person person){
//        Person updatePerson = personServiceImpl.updatePerson(person);
//        return "redirect:/person_list";
//    }
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public String updatePerson(@ModelAttribute("editPerson") Person personToBeUpdated) {
        personServiceImpl.updatePerson(personToBeUpdated);
        return "redirect:/all";
    }

    @RequestMapping(value= "delete/{id}", method = RequestMethod.GET)
    public String deletePerson(@PathVariable("id") Long id, ModelMap model) {
        model.put("deletePerson", personServiceImpl.findPersonById(id));
         personServiceImpl.deletePerson(id);
        return  "redirect:/all";
    }
}
