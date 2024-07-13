package net.revature.service;

import net.revature.dao.PersonDAO;
import net.revature.model.Person;

import java.util.List;

public class PersonService {
    PersonDAO personDAO;

    public PersonService(PersonDAO personDAO){
        this.personDAO = personDAO;
    }

    public List<Person> getAllPerson(){
        return personDAO.getAllPerson();
    }

    public Person getPersonById(int personId){
        return personDAO.getPersonById(personId);
    }

}
