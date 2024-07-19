package net.revature.dao;

import net.revature.model.Person;

import java.util.List;

public interface PersonDAOInterface {

    List<Person> getAllPerson();

    Person getPersonById(int personId);

    Person addPerson(Person person);

    void deletePersonById(int personId);

    Person updatePerson(Person person);

}
