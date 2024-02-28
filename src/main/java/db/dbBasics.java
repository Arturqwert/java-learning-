package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class dbBasics {

    public static void main(String[] args) throws SQLException {
        String connectionString = "jdbc:postgresql://localhost:5432/postgres";
        String login = "postgres";
        String password = "password";

       try(Connection connection = DriverManager.getConnection(connectionString, login, password)) {

           var ps = connection.prepareStatement("insert into public.test (name) values(?)");
           ps.setString(1, "Luka");

           var rowsAffected = ps.executeUpdate();
           // var rowsAffected = connection.createStatement().executeUpdate("insert into public.test (name) values('?')");
           ResultSet resultSet = connection.createStatement().executeQuery("select * from public.test");

           List<usersDto> users = new ArrayList<>();

           while (resultSet.next()) {
               int id = resultSet.getInt("id");
               String name = resultSet.getString("name");

               usersDto user = new usersDto(id, name);
               users.add(user);
           }

           System.out.println(users);
           System.out.println("rows affected = " + rowsAffected);
       }
       catch (SQLException ex) {
           System.err.println(ex.getMessage());
       }
    }

}
