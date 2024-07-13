package net.revature;

import io.javalin.Javalin;
import net.revature.dao.PersonDAO;
import net.revature.model.Person;
import net.revature.service.PersonService;
import net.revature.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Javalin app = Javalin.create().start(8333);

        app.get("/",ctx -> ctx.result("Welcome to Revature"));

        Connection con = DatabaseConnection.getConnection();
        PersonDAO personDAO = new PersonDAO(con);
        PersonService personService = new PersonService(personDAO);

        app.get("/person",ctx->{
            List<Person> personList = personService.getAllPerson();
            ctx.json(personList);
        });




    }
}