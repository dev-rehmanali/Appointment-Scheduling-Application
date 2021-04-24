package app;

import db.DBConnection;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rehmanali
 * Appointment Data Access Object(DAO) class
 */
public class AppointmentDAO {

    private PreparedStatement insertAppointmentStatement;
    private PreparedStatement deleteAppointmentStatement;
    private PreparedStatement getAllAppointmentStatement;
    private PreparedStatement updateAppointmentStatement;
    private PreparedStatement getCountAppointmentStatement;
    private PreparedStatement getCountAppointmentByTypeStatement;
    private PreparedStatement getAppAppointmentByContactNameStatement;
    private PreparedStatement getAppointmentsThisMonthStatement;
    private PreparedStatement getAppointmentsThisWeekStatement;
    private PreparedStatement getDistinctAppointmentTypesStatement;
    private PreparedStatement getAllAppointmentByTypeStatement;
    private DBConnection dBConnection;
    private Connection connection;
    private ContactDAO contactDAO;
    private TimeAPI timApi;

    /**
     * Default constructor communication with Database
     */
    public AppointmentDAO()
    {
        dBConnection = new DBConnection();
        connection = dBConnection.getConnection();
        contactDAO = new ContactDAO();
        timApi = new TimeAPI();

        try
        {
            insertAppointmentStatement = connection.prepareStatement("INSERT INTO appointments (Title, Description,"
            		+ " Location, Type, Start, End, Create_Date, Created_By, Last_Updated_By, Customer_ID, User_ID,"
            		+ " Contact_ID) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
            deleteAppointmentStatement = connection.prepareStatement("DELETE FROM appointments WHERE Appointment_ID = ?");
            getAllAppointmentStatement = connection.prepareStatement("SELECT * FROM appointments");
            updateAppointmentStatement = connection.prepareStatement("UPDATE appointments SET Title=?,Description=?,Location=?,"
            		+ "Type=?,Start=?,End=?,Create_Date=?,Created_By=?,Last_Updated_By=?,Customer_ID=?,"
            		+ "Contact_ID=? WHERE Appointment_ID=?");
            getCountAppointmentStatement = connection.prepareStatement("SELECT COUNT(Appointment_ID) FROM appointments WHERE Start LIKE CONCAT('_____',?,'%')");
            getCountAppointmentByTypeStatement = connection.prepareStatement("SELECT COUNT(Appointment_ID) FROM appointments WHERE Type=?");
            getDistinctAppointmentTypesStatement = connection.prepareStatement("SELECT DISTINCT Type FROM appointments");
            getAllAppointmentByTypeStatement = connection.prepareStatement("SELECT * FROM appointments WHERE Type=?");
            getAppAppointmentByContactNameStatement = connection.prepareStatement(""
                    + "SELECT * FROM appointments a INNER JOIN contacts c ON a.Contact_ID = c.Contact_ID"
                    + " WHERE c.Contact_Name=?;");
            getAppointmentsThisMonthStatement = connection.prepareStatement("SELECT * FROM appointments WHERE MONTH(Start) = MONTH(CURRENT_DATE())");
            getAppointmentsThisWeekStatement = connection.prepareStatement("SELECT *" +
                    " FROM appointments" +
                    " WHERE YEARWEEK(Start, 1) = YEARWEEK(CURDATE(),1)");
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    public int insertAppointment(Appointment ap)
    {
        try
        {
        	this.timApi.setLocalStr(ap.getStartDateTime());
        	String startDate = this.timApi.getUtcStr();
        	this.timApi.setLocalStr(ap.getEndDateTime());
        	String endDate = this.timApi.getUtcStr();
        	
            insertAppointmentStatement.setString(1, ap.getTitle());
            insertAppointmentStatement.setString(2, ap.getDescription());
            insertAppointmentStatement.setString(3, ap.getLocation());
            insertAppointmentStatement.setString(4, ap.getType());
            insertAppointmentStatement.setString(5, startDate);
            insertAppointmentStatement.setString(6, endDate);
            insertAppointmentStatement.setDate(7, Date.valueOf(ap.getCreateDate()));
            insertAppointmentStatement.setString(8, ap.getCreatedBy());
            insertAppointmentStatement.setString(9, ap.getLastUpdateBy());
            insertAppointmentStatement.setInt(10, ap.getCustomerID());
            insertAppointmentStatement.setInt(11, ap.getUserID());
            insertAppointmentStatement.setInt(12, ap.getContactID());
            
            return insertAppointmentStatement.executeUpdate();
        } catch (SQLException e)
        {
            System.err.println("Cannot insert appointment " + e.getMessage());
        }
        return 0;
    }

    public int updateAppointment(int id,Appointment ap)
    {
        try
        {
        	this.timApi.setLocalStr(ap.getStartDateTime());
        	String startDate = this.timApi.getUtcStr();
        	this.timApi.setLocalStr(ap.getEndDateTime());
        	String endDate = this.timApi.getUtcStr();
        	
        	updateAppointmentStatement.setString(1, ap.getTitle());
        	updateAppointmentStatement.setString(2, ap.getDescription());
        	updateAppointmentStatement.setString(3, ap.getLocation());
        	updateAppointmentStatement.setString(4, ap.getType());
        	updateAppointmentStatement.setString(5, startDate);
        	updateAppointmentStatement.setString(6, endDate);
        	updateAppointmentStatement.setDate(7, Date.valueOf(ap.getCreateDate()));
        	updateAppointmentStatement.setString(8, ap.getCreatedBy());
        	updateAppointmentStatement.setString(9, ap.getLastUpdateBy());
            updateAppointmentStatement.setInt(10, ap.getCustomerID());
        	updateAppointmentStatement.setInt(11, ap.getContactID());

            
        	updateAppointmentStatement.setInt(12, id);
            
            return updateAppointmentStatement.executeUpdate();
        } catch (SQLException e)
        {
            System.err.println("Cannot update appointment " + e.getMessage());
        }
        return 0;
    }

    public int deleteAppointmentByID(int id)
    {
        try
        {
        	deleteAppointmentStatement.setInt(1, id);
            return deleteAppointmentStatement.executeUpdate();
        } catch (SQLException e)
        {
            System.err.println("Cannot delete from appointments " + e.getMessage());

        }
        return 0;
    }

    public ObservableList<Appointment> getAllAppointments()
    {
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

        try
        {
            ResultSet result = getAllAppointmentStatement.executeQuery();
            while (result.next())
            {
            	Appointment ap = new Appointment();
            	
            	this.timApi.setUtcStr(result.getString(6));
            	String startDate = this.timApi.getLocalStr();
            	this.timApi.setUtcStr(result.getString(7));
            	String endDate = this.timApi.getLocalStr();
            	
                ap.setId(result.getInt(1));
                ap.setTitle(result.getString(2));
                ap.setDescription(result.getString(3));
                ap.setLocation(result.getString(4));
                ap.setType(result.getString(5));
                ap.setStartDateTime(startDate);
                ap.setEndDateTime(endDate);
                ap.setCreateDate(result.getDate(8).toLocalDate());
                ap.setCreatedBy(result.getString(9));
                ap.setLastUpdate(result.getTime(10).toLocalTime());
                ap.setLastUpdateBy(result.getString(11));
                ap.setCustomerID(result.getInt(12));
                ap.setUserID(result.getInt(13));
                ap.setContactID(result.getInt(14));
                ap.setContact(contactDAO.getSingleContactByID(result.getInt(14)));

//                System.out.println(ap.toString());

                allAppointments.add(ap);

            }

        }
        catch (SQLException e)
        {
            System.err.println("cannot retrieve appointments list " + e.getMessage());
        }

        return allAppointments;
    }

    public int getCountAppointmentByMonth(String month){

        int count = 0;

        try {

            getCountAppointmentStatement.setString(1,month);
            ResultSet result = getCountAppointmentStatement.executeQuery();

            if(result.next()){
                count = result.getInt(1);
            }

        } catch (SQLException e) {
            System.err.println("cannot count appointments " + e.getMessage());
        }

        return count;

    }

    public int getCountAppointmentByType(String type){

        int count = 0;

        try {

            getCountAppointmentByTypeStatement.setString(1,type);

            ResultSet result = getCountAppointmentByTypeStatement.executeQuery();

            if(result.next()){
                count = result.getInt(1);
            }

        } catch (SQLException e) {
            System.err.println("cannot count appointments " + e.getMessage());
        }

        return count;

    }

    public ObservableList<Appointment> getAppointmentsByContact(String contactName){
        ObservableList<Appointment> temp = FXCollections.observableArrayList();
        try {
            getAppAppointmentByContactNameStatement.setString(1, contactName);
            ResultSet result= getAppAppointmentByContactNameStatement.executeQuery();
            while(result.next()) {
                Appointment ap = new Appointment();
                
            	this.timApi.setUtcStr(result.getString(6));
            	String startDate = this.timApi.getLocalStr();
            	this.timApi.setUtcStr(result.getString(7));
            	String endDate = this.timApi.getLocalStr();
                
                ap.setId(result.getInt(1));
                ap.setTitle(result.getString(2));
                ap.setDescription(result.getString(3));
                ap.setType(result.getString(5));
                ap.setStartDateTime(startDate);
                ap.setEndDateTime(endDate);
                ap.setCustomerID(result.getInt(12));
                temp.add(ap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return temp;
    }

    public ObservableList<Appointment> getAppointmentThisMonth(){

        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

        try
        {

            ResultSet result = getAppointmentsThisMonthStatement.executeQuery();
            while (result.next())
            {
                Appointment ap = new Appointment();
                
            	this.timApi.setUtcStr(result.getString(6));
            	String startDate = this.timApi.getLocalStr();
            	this.timApi.setUtcStr(result.getString(7));
            	String endDate = this.timApi.getLocalStr();
                
                ap.setId(result.getInt(1));
                ap.setTitle(result.getString(2));
                ap.setDescription(result.getString(3));
                ap.setLocation(result.getString(4));
                ap.setType(result.getString(5));
                ap.setStartDateTime(startDate);
                ap.setEndDateTime(endDate);
                ap.setCreateDate(result.getDate(8).toLocalDate());
                ap.setCreatedBy(result.getString(9));
                ap.setLastUpdate(result.getTime(10).toLocalTime());
                ap.setLastUpdateBy(result.getString(11));
                ap.setCustomerID(result.getInt(12));
                ap.setUserID(result.getInt(13));
                ap.setContactID(result.getInt(14));
                ap.setContact(contactDAO.getSingleContactByID(result.getInt(14)));

                allAppointments.add(ap);

            }

        }
        catch (SQLException e)
        {
            System.err.println("cannot retrieve appointments list " + e.getMessage());
        }

        return allAppointments;
    }

    public ObservableList<Appointment> getAppointmentThisWeek(){

        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

        try
        {

            ResultSet result = getAppointmentsThisWeekStatement.executeQuery();
            while (result.next())
            {
                Appointment ap = new Appointment();
                
            	this.timApi.setUtcStr(result.getString(6));
            	String startDate = this.timApi.getLocalStr();
            	this.timApi.setUtcStr(result.getString(7));
            	String endDate = this.timApi.getLocalStr();
                
                ap.setId(result.getInt(1));
                ap.setTitle(result.getString(2));
                ap.setDescription(result.getString(3));
                ap.setLocation(result.getString(4));
                ap.setType(result.getString(5));
                ap.setStartDateTime(startDate);
                ap.setEndDateTime(endDate);
                ap.setCreateDate(result.getDate(8).toLocalDate());
                ap.setCreatedBy(result.getString(9));
                ap.setLastUpdate(result.getTime(10).toLocalTime());
                ap.setLastUpdateBy(result.getString(11));
                ap.setCustomerID(result.getInt(12));
                ap.setUserID(result.getInt(13));
                ap.setContactID(result.getInt(14));
                ap.setContact(contactDAO.getSingleContactByID(result.getInt(14)));

                allAppointments.add(ap);

            }

        }
        catch (SQLException e)
        {
            System.err.println("cannot retrieve appointments list " + e.getMessage());
        }

        return allAppointments;
    }



}
