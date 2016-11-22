package com.gcf.util;

//~--- JDK imports ------------------------------------------------------------

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
    private static String
        url,
        user     = "",
        password = "";
    private static Connection cnx;

    public static Connection getInstance() {
        if (cnx == null) {
            try {
                if (user.equals("")) {
                    cnx = DriverManager.getConnection(url);
                } else {
                    cnx = DriverManager.getConnection(url, user, password);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return cnx;
    }

    public static void reinit() {
        cnx = null;
    }

    public static void close() {
        try {
            if (cnx != null) {
                cnx.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        Connexion.url = url;
    }

    public static String getUser() {
        return user;
    }

    public static void setUser(String user) {
        Connexion.user = user;
    }

    public static void setPassword(String password) {
        Connexion.password = password;
    }
}
