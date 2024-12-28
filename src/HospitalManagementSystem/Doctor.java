package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Doctor {
    private Connection connection;
    private Scanner scanner;

    public Doctor(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }



    public void showDoctor() {
        String query = "SELECT * FROM doctors;";

        System.out.println("---------------------------------------------");
        System.out.printf("%-5s %-20s %-20s%n", "ID", "Name", "Specialization");
        System.out.println("---------------------------------------------");
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String specialization = resultSet.getString("specialization");
                System.out.printf("%-5d %-20s %-20s%n", id, name, specialization);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public boolean getDoctorById(int id)
    {
        String query="select * from doctors where id=?;";
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet rs= preparedStatement.executeQuery();
            if(rs.next())
            {
                return true;
            }
            else return false;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
