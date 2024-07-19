package net.revature.dao;

import net.revature.model.Admin;
import net.revature.model.Person;
import net.revature.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminAuthDAO {

    PersonDAO personDAO = new PersonDAO();

    public Admin login(String username, String password){

        try(Connection con = ConnectionUtil.getConnection()) {
            String sql = "select * from admin_auth where " +
                    "username = ? and password = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int person_id = rs.getInt("person_id");
                Person person = personDAO.getPersonById(person_id);

                Admin admin = new Admin(
                        rs.getString("username"),
                        rs.getString("password"),
                        person_id,
                        person.getFirst_name(),
                        person.getLast_name()
                );

                return admin;

            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }
}
