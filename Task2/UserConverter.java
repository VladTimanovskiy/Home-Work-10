package org.example.module10.Task2;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserConverter {
    public static void main(String[] args) {
        String inputFileName = "src/main/resources/Users.txt";
        String outputFileName = "user.json";

        List<User> users = readUsersFromFile(inputFileName);
        writeUsersToJsonFile(users, outputFileName);
    }

    private static List<User> readUsersFromFile(String fileName) {
        List<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String header = br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\s+");
                if (parts.length == 3) {
                    String name = parts[0];
                    int age = Integer.parseInt(parts[1]);
                    String email = parts[2];
                    User user = new User(name, age, email);
                    users.add(user);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    private static void writeUsersToJsonFile(List<User> users, String fileName) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try (FileWriter writer = new FileWriter(fileName)) {
            mapper.writeValue(writer, users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}