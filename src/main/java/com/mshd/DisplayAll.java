package com.mshd;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/DisplayAll")
public class DisplayAll extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //显示数据库中所有数据
        try {
            response.setContentType("text/html;charset=utf-8");
            response.setCharacterEncoding("UTF-8");

            new JDBC();
            Connection conn = JDBC.getConnection();
            PreparedStatement prep = conn.prepareStatement("select * from disaster");
            ResultSet result = prep.executeQuery();

            PrintWriter pw = response.getWriter();
            while (result.next()) {
                pw.println(result.getString("id") + " " + result.getString("detail") + "</br>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
