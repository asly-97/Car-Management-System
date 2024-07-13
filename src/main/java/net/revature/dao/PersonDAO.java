package net.revature.dao;

import net.revature.model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {

    Connection con;

    public PersonDAO(Connection con){
        this.con = con;
    }

    public List<Person> getAllPerson(){
        List<Person> personList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM person";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Person p = new Person(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"));
                personList.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personList;
    }

    public Person getPersonById(int personId){
        return null;
    }

    public void addPerson(Person person){

    }

    public void deletePersonById(int personId){

    }

}
