package net.revature.controller;

import io.javalin.http.Handler;
import jakarta.servlet.http.HttpSession;
import net.revature.model.Admin;
import net.revature.model.LoginDTO;
import net.revature.service.AuthService;

//This Controller deals with Authentication functionalities
//in the future, I'll have register here too, but for now we have insertEmployee in the EmpController
public class AuthController {

    //Instantiate the AuthService to use the login() method
    AuthService as = new AuthService();

    //uninitialized HttpSession object - to be filled upon successful login
    public static HttpSession ses;

    //login will be a POST request, since we're sending login creds in the HTTP Request body
    public Handler loginHandler = ctx -> {

        //extract the Request body as a LoginDTO
        LoginDTO lDTO = ctx.bodyAsClass(LoginDTO.class);

        //send the login credentials to the AuthService
        Admin loggedInAdmin = as.login(lDTO);

        //if the login was successful, return the Employee object
        if(loggedInAdmin != null){

            //set the session object
            ses = ctx.req().getSession();

            //we can use .setAttribute() to set user attributes in the session
            //This is how we can set user-specific data within a session to confirm things like
            //...their identity or role in application, which will determine their privileges
            ses.setAttribute("username", loggedInAdmin.getUsername());
            ses.setAttribute("person_id", loggedInAdmin.getId());

            //Foreshadowing for P1: Each Employee will have a role (employee/manager)
            //managers will be able to do things employees can't.
            //Saving the logged in user's role in their session would be very important in this case

            //ok... so how do we access these attribute then? .getAttribute()!
            System.out.println(ses.getAttribute("username"));

            //if you need to have logout functionality, we can use .invalidate() to end the session
            ses.invalidate();

            ctx.status(202); //accepted
            ctx.json(loggedInAdmin);

        }

    };

}