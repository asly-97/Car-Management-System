package net.revature.exception.person;

public class PersonNotFoundException extends PersonException{

    public  PersonNotFoundException(int personId){
        super("Person with id("+personId+") was not found");
    }

}
