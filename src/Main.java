import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Optional;

import config.DatabbaseConfig;
import entities.Card;
import enums.Status;

public class Main {
    public static void main(String[] args) {
        System.setProperty("file.encoding", "UTF-8");

        // Print UTF-8 characters (French accents, emojis, Japanese, etc.)
        System.out.println("Élève, café, résumé, naïve, 日本, 😊");
        try{
            DatabbaseConfig configDB = new DatabbaseConfig();
            configDB.getConnection();
/* 
            Connection connection = DriverManager.getConnection("jdbc:mysql://database:3306/app","user" ,"password");

            System.out.println(connection); */

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        
        /* Card card = new Card(Optional.of(1), "Faire les courses", Status.TODO);
        System.out.println(card.toString()); */
}