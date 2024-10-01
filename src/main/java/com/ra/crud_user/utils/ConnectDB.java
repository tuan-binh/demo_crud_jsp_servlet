package com.ra.crud_user.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB
{
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/test_db?createDatabaseIfNotExist=true";
    private static final String USER = "root";
    private static final String PASS = "123456";

    // Connection -> interface
    public static Connection openConnection()
    {
        Connection con;
        try
        {
            Class.forName(DRIVER);

            con = DriverManager.getConnection(URL, USER, PASS);
        }
        catch (SQLException | ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        return con;
    }

    public static void closeConnection(Connection con)
    {
        try
        {
            if (con != null)
            {
                con.close();
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

//    public static void main(String[] args)
//    {
//        Connection con = ConnectDB.openConnection();
//        System.out.println("Đã kết nối thành công");
//    }
}
