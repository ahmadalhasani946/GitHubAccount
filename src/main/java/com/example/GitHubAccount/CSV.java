package com.example.GitHubAccount;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSV {
    
    public static void createForksFile(Repository[] repositories, String accountName){
        try {
            FileWriter myWriter = new FileWriter( "/Files/" + accountName + "_repos.csv");

            myWriter.write(Repository.titles());
            for(Repository repository : repositories){
                myWriter.write("\n");
                myWriter.write(repository.getInfo());
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void createUsersFile(List<Contributor> contributors, String accountName){
        try {
            FileWriter myWriter = new FileWriter(accountName + "_users.csv");

            myWriter.write(Contributor.titles());
            for(Contributor contributor : contributors){
                myWriter.write("\n");
                myWriter.write(contributor.getInfo());
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}