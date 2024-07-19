package net.revature.controller;

import io.javalin.http.Handler;
import net.revature.exception.person.PersonException;
import net.revature.model.Person;
import net.revature.service.PersonService;

import java.util.List;

public class PersonController {

    PersonService personService;

    public PersonController(){
        personService = new PersonService();
    }

    public Handler getAllPersonHandler= ctx -> {

        List<Person> personList = personService.getAllPerson();

        ctx.status(201);
        ctx.json(personList);

    };


    public Handler getPersonByIdHandler= ctx -> {

        int personId = Integer.parseInt(ctx.pathParam("id"));
        // ASK Ben about the custom exception was not checked inside the handler by the compiler

        try
        {
            Person person = personService.getPersonById(personId);
            ctx.status(202);
            ctx.json(person);
        }catch (PersonException e){
            ctx.status(404);
            ctx.result(e.getMessage());
        }
    };

    public Handler addPerson= ctx -> {

        Person person = ctx.bodyAsClass(Person.class);

        Person addedPerson = personService.addPerson(person);

        ctx.status(201);
        ctx.json(addedPerson);

    };

    public Handler deletePersonByIdHandler= ctx -> {

        // Only authenticated admin can perform this operation
        if (!AuthController.ensureAdminLoggedIn(ctx)) {
            return; // Stop further processing if admin is not logged in
        }

        int personId = Integer.parseInt(ctx.pathParam("id"));

        personService.deletePersonById(personId);
        ctx.status(200);
    };


    public Handler updatePerson= ctx -> {

        int personId = Integer.parseInt(ctx.pathParam("id"));
        Person person = ctx.bodyAsClass(Person.class);


        try
        {
            Person newPerson = personService.updatePerson(personId, person);

            ctx.status(202);
            ctx.json(newPerson);
        }catch (PersonException e){
            ctx.status(404);
            ctx.result(e.getMessage());
        }

    };







}
