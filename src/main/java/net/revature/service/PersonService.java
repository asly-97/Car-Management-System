package net.revature.service;

import net.revature.dao.PersonDAO;
import net.revature.exception.person.PersonNotFoundException;
import net.revature.model.Person;

import java.util.List;

public class PersonService {
    PersonDAO personDAO;

    public PersonService(){
        this.personDAO = new PersonDAO();
    }

    public List<Person> getAllPerson(){
        return personDAO.getAllPerson();
    }

    public Person getPersonById(int personId) throws PersonNotFoundException{
        Person person = personDAO.getPersonById(personId);
        if(person == null){
            throw new PersonNotFoundException(personId);
        }

        return person;
    }

    public Person addPerson(Person person){
        return personDAO.addPerson(person);
    }

    public void deletePersonById(int personId){
        personDAO.deletePersonById(personId);
    }

    public Person updatePerson(int person_id, Person newPerson) throws PersonNotFoundException {

        Person oldPerson = getPersonById(person_id);

        newPerson.setId(person_id);

        if(newPerson.getFirst_name() == null){
            newPerson.setFirst_name(oldPerson.getFirst_name());
        }

        if(newPerson.getLast_name() == null){
            newPerson.setLast_name(oldPerson.getLast_name());
        }

        return personDAO.updatePerson(newPerson);
    }

}
