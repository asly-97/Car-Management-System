package net.revature.controller;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import jakarta.servlet.http.HttpSession;
import net.revature.exception.auth.AdminPrivilegesRequiredException;
import net.revature.exception.auth.InvalidAdminCredentialsException;
import net.revature.model.Admin;
import net.revature.model.LoginDTO;
import net.revature.service.AuthService;

//This Controller deals with Authentication functionalities
public class AuthController {


    AuthService authService = new AuthService();

    //uninitialized HttpSession object - to be filled upon successful login
    private static HttpSession auth_sess = null;

    public static boolean isAdminLoggedIn(){
        if (auth_sess != null){
            //admin already logged in
            return true;
        }else{
            //not logged in
            return false;
        }
    }

    public static boolean ensureAdminLoggedIn(Context ctx) {
        if (!AuthController.isAdminLoggedIn()) {
            ctx.status(401); // Unauthorized access
            ctx.result("Admin privileges are required to perform this operation.");
            return false; // Indicate that the check failed
        }
        return true; // Indicate that the check passed
    }


    public Handler adminAuthHandler = ctx -> {

        try {
            //extract the Request body as a LoginDTO
            LoginDTO lDTO = ctx.bodyAsClass(LoginDTO.class);

            //send the login credentials to the AuthService
            Admin loggedInAdmin = authService.login(lDTO);

            //set the session object
            auth_sess = ctx.req().getSession();

            //we can use .setAttribute() to set user attributes in the session
            //This is how we can set user-specific data within a session to confirm things like
            //...their identity or role in application, which will determine their privileges
            auth_sess.setAttribute("username", loggedInAdmin.getUsername());
            auth_sess.setAttribute("person_id", loggedInAdmin.getId());

            ctx.status(202); //accepted
            //if the login was successful, return the Admin object
            ctx.json(loggedInAdmin);
        }
        catch (InvalidAdminCredentialsException e){
            ctx.status(401);
            ctx.result(e.getMessage());
        }

    };


    public Handler adminLogoutHandler = ctx ->{
        //if you need to have logout functionality, we can use .invalidate() to end the session
        auth_sess.invalidate();
        auth_sess = null;
        ctx.status(200);
        ctx.result("Admin logged out!");
    };

}