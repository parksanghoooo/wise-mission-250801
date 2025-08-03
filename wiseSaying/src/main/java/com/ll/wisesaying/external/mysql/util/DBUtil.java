package com.ll.wisesaying.external.mysql.util;

import com.ll.wisesaying.external.mysql.config.DBConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DBConfig.URL,
                DBConfig.USER,
                DBConfig.PASSWORD
        );
    }

}
