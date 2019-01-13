package com.cinema.util;

import com.cinema.model.dao.DAOFactory;
import com.cinema.model.dao.connection.ConnDB;
import com.cinema.model.dao.impl.UserDAO;
import com.cinema.model.entity.User;
import com.cinema.model.entity.UserRole;
import com.cinema.util.constants.DAOKey;
import org.jasypt.util.password.BasicPasswordEncryptor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class InitDB {

    public static void main(String[] args) {
        initSchema("sql/db_init.sql");
        initSchema("sql/db_insert.sql");
        createAdmin("admin", "admin");
    }

    private static void initSchema(String filePath) {
        String queries = readSqlFile(filePath);

        try (Connection con = ConnDB.getConnection();
             Statement st = con.createStatement()) {
            for (String query : queries.split(";")) {
                st.execute(query);
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }

    private static String readSqlFile (String filePath) {
        String line;
        StringBuilder sb = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while ((line = reader.readLine()) != null) {
                if (line.trim().length() > 0) {
                    sb.append(line);
                }
            }
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        return sb.toString();
    }

    private static void createAdmin(String login, String password) {
        UserDAO dao = (UserDAO) DAOFactory.getDAO(DAOKey.USER_DAO);

        User admin = new User();
        admin.setLogin(login);
        admin.setPassword(new BasicPasswordEncryptor().encryptPassword(password));
        admin.setRole(new UserRole(1, "ADMIN"));

        dao.create(admin);
    }

}
