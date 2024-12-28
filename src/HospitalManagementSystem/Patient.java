package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Patient {
    private Connection connection;
    private Scanner scanner;

    public Patient(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }

    public void addPatient() {
        System.out.println("Enter Patient Name:");
        String name = scanner.nextLine();
        System.out.println("Enter Patient Age:");
        int age = scanner.nextInt();
        System.out.println("Enter Patient Gender:");
        String gender = scanner.nextLine();

        try{
            String query="insert into patients(name,age,gender) values(?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,name);
            preparedStatement.setInt(2,age);
            preparedStatement.setString(3,gender);
            int count = preparedStatement.executeUpdate();
            if(count>0){
                System.out.println("Patient added successfully");
            }
            else{
                System.out.println("Patient not added");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void showPatient() {
        String query="select * from patients;";
        System.out.printf("%-5s %-20s %-5s %-10s%n", "ID", "Name", "Age", "Gender");
        System.out.println("------------------------------------------------");
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                int id=resultSet.getInt("id");
                String name=resultSet.getString("name");
                int age=resultSet.getInt("age");
                String gender=resultSet.getString("gender");
                System.out.printf("%-5d %-20s %-5d %-10s%n", id, name, age, gender);

            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    public boolean getPatientById(int id)
    {
        String query="select * from patients where id=?;";
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
