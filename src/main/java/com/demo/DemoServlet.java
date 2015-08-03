package com.demo;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by suvasish on 03-08-2015.
 */
public class DemoServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException{

        // JDBC driver name and database URL
        final String JDBC_DRIVER="com.mysql.jdbc.Driver";
        final String DB_URL="jdbc:mysql://localhost/pdweb";

        //  Database credentials
        final String USER = "root";
        final String PASS = "p@ssw0rd";

        List<DemoBean> items = new ArrayList<DemoBean>();
        try {
            // Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Open a connection
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Execute SQL query
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT id, name FROM brand limit 10";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                //Retrieve by column name
                String id  = rs.getString("id");
                String name = rs.getString("name");

                DemoBean demoBean = new DemoBean();
                demoBean.setId(id);
                demoBean.setName(name);

                items.add(demoBean);
            }

            request.setAttribute("items", items);
            request.getRequestDispatcher("/page.jsp").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
