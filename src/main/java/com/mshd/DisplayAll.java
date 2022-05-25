package com.mshd;

import com.google.gson.Gson;

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

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //显示数据库中所有数据
        try {
            response.setContentType("text/html;charset=utf-8");
            response.setCharacterEncoding("UTF-8");

            //在数据库中查询
            new JDBC();
            Connection conn = JDBC.getConnection();
            PreparedStatement prep = conn.prepareStatement("select * from disaster");
            ResultSet result = prep.executeQuery();

            //输出结果到页面上
            PrintWriter pw = response.getWriter();
            while (result.next()) {
                //新建一个decode对象
                Decode dc = new Decode(result.getString("id"), result.getString("detail"));
                String res = new Gson().toJson(dc);
                response.getWriter().write(res);
                //pw.println(result.getString("id") + " 地址：" + dc.getAddress() + " 日期：" + dc.getDate() + " 来源：" + dc.getSource() + " 载体：" + dc.getCarrier() + " 分类：" + dc.getClassification() + " 指标：" + dc.getIndicator() + " 描述：" + result.getString("detail") + "</br>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
