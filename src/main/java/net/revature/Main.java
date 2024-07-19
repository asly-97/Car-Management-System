package net.revature;

import io.javalin.Javalin;
import net.revature.controller.CarController;
import net.revature.controller.PersonController;
import net.revature.dao.PersonDAO;
import net.revature.model.Person;
import net.revature.service.PersonService;
import net.revature.util.ConnectionUtil;
import net.revature.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Javalin app = Javalin.create().start(8333);

        app.get("/",ctx -> ctx.result("Welcome to Revature"));


        //person endpoints handling
        PersonController pc = new PersonController();
        app.get("/person",pc.getAllPersonHandler);
        app.get("/person/{id}",pc.getPersonByIdHandler);
        app.post("/person",pc.addPerson);
        app.delete("/person/{id}",pc.deletePersonByIdHandler);
        app.put("/person/{id}",pc.updatePerson);


        //cars endpoints
        CarController cc = new CarController();
        app.get("/car",cc.getAllCarsHandler);
        app.get("/car/{id}",cc.getCarById);
        app.get("/person/{id}/car",cc.getCarsByOwnerId);
        app.patch("/car/{car_id}/assign_owner/{person_id}",cc.assignCarOwnerHandler);
        app.post("/car",cc.addNewCarHandler);
        app.delete("/car/{id}",cc.deleteCarController);






    }
}