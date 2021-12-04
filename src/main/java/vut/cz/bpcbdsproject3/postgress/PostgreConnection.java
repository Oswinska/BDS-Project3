package vut.cz.bpcbdsproject3.postgress;

import java.sql.*;

public class PostgreConnection
{
    public static void Connection()
    {
        String url = "jdbc:postgresql://localhost:5432";
        String user = "test";
        String password = "test";
        try (Connection con = DriverManager.getConnection(url, user, password))
            {
                System.out.print("Connected");
            } catch (SQLException e)
            {
                System.out.print(e);
            }
    }
}
