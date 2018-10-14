import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;
import java.util.ArrayList;

public class JdbcMain {
    public static void main(String[] args) throws ClassNotFoundException {
        // TODO: Load the SQLite JDBC driver (JDBC class implements java.sql.Driver)

        Class.forName("org.sqlite.JDBC");

        // TODO: Create a DB connection
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:contactmgr.db")) {

            // TODO: Create a SQL statement
            Statement statement = connection.createStatement();


            // TODO: Create a DB table
            statement.executeUpdate("DROP TABLE IF EXISTS contacts");
            statement.executeUpdate("CREATE TABLE contacts (id INTEGER PRIMARY KEY,firstname SUBSTRING ," +
                    "lastname SUBSTRING , email SUBSTRING,phone INT (10))");

            // TODO: Insert a couple contacts
            statement.executeUpdate("INSERT INTO  contacts(firstname,lastname,email,phone) " +
                    "VALUES ('Sherlock','Holmes','sl@gmail.com',513-238-9616)");
            statement.executeUpdate("INSERT INTO  contacts(firstname,lastname,email,phone) " +
                    "VALUES ('James','Watson','jw@gmail.com1',213-238-9616)");
            statement.executeUpdate("INSERT INTO  contacts(firstname,lastname,email,phone) " +
                    "VALUES ('Bruce','Wayne','wy@gmail.com',213-238-9616)");

            // TODO: Fetch all the records from the contacts table

            ResultSet rs = statement.executeQuery("SELECT * FROM contacts");

            // TODO: Iterate over the ResultSet & display contact info

            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");

                System.out.printf("%s %s (%d) %n", firstName, lastName, id);

            }

        } catch (SQLException ex) {
            // Display connection or query errors
            System.err.printf("There was a database error: %s%n", ex.getMessage());
        }
    }
}