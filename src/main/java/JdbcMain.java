import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;


public class JdbcMain {
    public static void main(String[] args) throws ClassNotFoundException {


        Class.forName("org.sqlite.JDBC");


        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:contactmgr.db")) {


            Statement statement = connection.createStatement();

            //Create a table
            statement.executeUpdate("DROP TABLE IF EXISTS contacts");
            statement.executeUpdate("CREATE TABLE contacts (id INTEGER PRIMARY KEY,firstname SUBSTRING ," +
                    "lastname SUBSTRING , email SUBSTRING,phone INT (10))");


            //INSERT into Table
            Contact c = new Contact("Harvey", "Dent", "had@gmail.com", 123456789L);
            save(c, statement);

            c = new Contact("Harvey1", "Dent1", "had@gmail.com", 223456789L);
            save(c, statement);

            c = new Contact("Harvey2", "Dent2", "had@gmail.com", 323456789L);
            save(c, statement);

            c = new Contact("Harvey3", "Dent3", "had@gmail.com", 423456789L);
            save(c, statement);

/*
            statement.executeUpdate("INSERT INTO  contacts(firstname,lastname,email,phone) " +
                    "VALUES ('Sherlock','Holmes','sl@gmail.com',513-238-9616)");
            statement.executeUpdate("INSERT INTO  contacts(firstname,lastname,email,phone) " +
                    "VALUES ('James','Watson','jw@gmail.com1',213-238-9616)");
            statement.executeUpdate("INSERT INTO  contacts(firstname,lastname,email,phone) " +
                    "VALUES ('Bruce','Wayne','wy@gmail.com',213-238-9616)");*/


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


    public static void save(Contact contact, Statement statement) throws SQLException {

        //Compose query
        String sql = "INSERT INTO contacts (firstname,lastname,email,phone) VALUES('%s','%s','%s',%d)";
        sql = String.format(sql, contact.getFirstName(), contact.getLastName(), contact.getEmail(), contact.getPhone());


        //Execute the query
        statement.executeUpdate(sql);

    }
}