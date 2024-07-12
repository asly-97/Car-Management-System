package net.revature;

import io.javalin.Javalin;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Javalin app = Javalin.create().start(8333);

        app.get("/",ctx -> ctx.result("Welcome to Revature"));

    }
}