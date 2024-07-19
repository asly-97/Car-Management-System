package net.revature.dao;

import net.revature.model.Person;
import net.revature.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO implements PersonDAOInterface{

    public List<Person> getAllPerson(){

        try(Connection con = ConnectionUtil.getConnection()){
            List<Person> personList = new ArrayList<>();

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

            return personList;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Person getPersonById(int personId){

        try(Connection con = ConnectionUtil.getConnection()) {
            String sql = "select * from person where id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, personId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Person person = new Person(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"));
                return person;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public Person addPerson(Person person){
        try(Connection con = ConnectionUtil.getConnection()) {
            String sql = "insert into person(first_name,last_name) values(?,?)";

            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,person.getFirst_name());
            ps.setString(2,person.getLast_name());
            ps.executeUpdate();

            ResultSet keys_rs = ps.getGeneratedKeys();
            if(keys_rs.next()){
                int person_id = keys_rs.getInt("id");
                person.setId(person_id);
            }

            return person;

        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public void deletePersonById(int personId){
        try(Connection con = ConnectionUtil.getConnection()) {

            String sql = "delete from person where id = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,personId);
            ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Person updatePerson(Person person) {
        try(Connection con = ConnectionUtil.getConnection()) {
            String sql = "update person " +
                    "set first_name = ?, last_name = ? " +
                    "where id = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,person.getFirst_name());
            ps.setString(2,person.getLast_name());
            ps.setInt(3,person.getId());
            ps.executeUpdate();

            return getPersonById(person.getId());

        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

}
