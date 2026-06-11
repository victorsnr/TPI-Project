package database;

import java.sql.Connection;

public class ConnectionTest {
	public static void main(String[] args) {

        try (Connection conn = DatabaseConnection.conectar()) {
            System.out.println("Conectado!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
