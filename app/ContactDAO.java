package app;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDAO {

    private PreparedStatement insertContactsStatement;
    private PreparedStatement getContactsStatement;
    private PreparedStatement getSingleContactByIDStatement;
    private DBConnection dBConnection;
    private Connection connection;

    public ContactDAO()
    {
        dBConnection = new DBConnection();
        connection = dBConnection.getConnection();

        try
        {
            insertContactsStatement = connection.prepareStatement("INSERT INTO contacts (Contact_Name, Email) VALUES(?,?)");
            getContactsStatement = connection.prepareStatement("SELECT * FROM contacts");
            getSingleContactByIDStatement = connection.prepareStatement("SELECT * FROM contacts WHERE Contact_ID=?");

        } catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    public int insertContact(Contacts c)
    {

        try
        {
            insertContactsStatement.setString(1, c.getContactName());
            insertContactsStatement.setString(2, c.getContactName());

            return insertContactsStatement.executeUpdate();
        } catch (SQLException e)
        {
            System.err.println("Cannot insert contacts " + e.getMessage());
        }
        return 0;
    }

    public ObservableList<Contacts> getAll()
    {
        ObservableList<Contacts> allContacts = FXCollections.observableArrayList();

        try
        {
            ResultSet result = getContactsStatement.executeQuery();
            while (result.next())
            {
                Contacts c = new Contacts();

                c.setContactID(result.getInt(1));
                c.setContactName(result.getString(2));
                c.setEmail(result.getString(3));

                allContacts.add(c);

            }

        }
        catch (SQLException e)
        {
            System.err.println("cannot retrieve contacts list " + e.getMessage());
        }

        return allContacts;
    }

    public String getSingleContactByID(int id){

        int temp = id;

        try
        {

            getSingleContactByIDStatement.setInt(1,temp);

            ResultSet result = getSingleContactByIDStatement.executeQuery();

            if (result.next())
            {
                return result.getString(2);
            }
        }
        catch (SQLException e)
        {
            System.err.println("cannot retrieve contacts " + e.getMessage());
        }

        return null;

    }
}
