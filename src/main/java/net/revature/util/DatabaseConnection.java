package net.revature.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static String url =  "jdbc:postgresql://localhost/revature";
    private static String username = "postgres";
    private static String password = "password";
    private static String ssl = "false";

    private static Connection connection;

    private DatabaseConnection(){};

    public static Connection getConnection(){
        if(connection == null){
            try {
                Properties props = new Properties();
                props.setProperty("user", username);
                props.setProperty("password", password);
                props.setProperty("ssl", ssl);

                connection = DriverManager.getConnection(url,props);
                System.out.println("Database connected successfully.");

                //FileReader fileReader = new FileReader("src/main/resources/data.sql");
                //String sql = FileUtil.parseSQLFile("src/main/resources/data.sql");
                //connection.createStatement().executeUpdate(sql);


            } catch (SQLException e) {
                System.out.println("---DB connection exception--- " + e.getMessage());
            }
        }
        return connection;
    }


}
