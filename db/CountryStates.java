package db;

import app.FirstLevelDivision;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.Country;

public class CountryStates {

    private PreparedStatement getAllCountriesStatement;
    private PreparedStatement getStatesStatement;
    private PreparedStatement getStateID;
    private DBConnection dbConnection;
//    private CountryDBConnection countryDBconnection;
    private Connection connection;

    public CountryStates() {

        dbConnection = new DBConnection();
        connection = dbConnection.getConnection();

        try
        {
            getAllCountriesStatement = connection.prepareStatement("SELECT * FROM countries WHERE 1");
            getStatesStatement = connection.prepareStatement("SELECT * FROM first_level_divisions WHERE Country_ID=?");
            getStateID = connection.prepareStatement("SELECT first_level_divisions.Division_ID, first_level_divisions.Division"
                    + " FROM first_level_divisions"
                    + " INNER JOIN countries ON first_level_divisions.Country_ID=countries.Country_ID WHERE countries.Country_ID = ?"
                    + " AND first_level_divisions.Division=?");
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    public List<Country> getAllCountries()
    {
        List<Country> allCountries = new ArrayList<>();

        try
        {
            ResultSet result = getAllCountriesStatement.executeQuery();
            while (result.next())
            {
                Country c = new Country();

                c.setCountryID(result.getInt(1));
                c.setCountry(result.getString(2));


                allCountries.add(c);

            }

        }catch (SQLException e){

                System.err.println(e.getStackTrace());

        }
            return allCountries;
    }

    public ObservableList<String> getAllStates(int id){
        
    	ObservableList<String> allStates = FXCollections.observableArrayList();

        try
        {
            getStatesStatement.setInt(1,id);

            ResultSet result = getStatesStatement.executeQuery();
            while (result.next())
            {

                allStates.add(result.getString(2));

            }

        }catch (SQLException e){

            System.err.println(e.getStackTrace());

        }
        return allStates;
    }

    public int getStateID(int countryID, String stateName){

//        ObservableList<FirstLevelDivision> divisions = FXCollections.observableArrayList();

        FirstLevelDivision division = new FirstLevelDivision();

        try
        {
            System.out.println(countryID + ", " + stateName);

            getStateID.setInt(1,countryID);
            getStateID.setString(2,stateName);

            ResultSet result = getStateID.executeQuery();
            while (result.next())
            {
                division.setDivisionID(result.getInt(1));
                division.setDivision(result.getString(2));
//                allStates.add(result.getString(2));

//                divisions.add(division);
            }



        }catch (SQLException e){

            System.err.println(e.getStackTrace());

        }
        return division.getDivisionID();
    }





    }
