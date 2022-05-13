package com.mshd;

import java.io.*;
import java.sql.SQLException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import static com.mshd.Disaster.disInsert;
import static com.mshd.Disaster.disValidate;

@WebServlet(urlPatterns = "/MSServlet")
public class ManualSubmit extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //拉取表单输入，调用数据库插入函数
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();

        Disaster dis = new Disaster(request.getParameter("id"), request.getParameter("detail"));

        if (!disValidate(dis)) {
            pw.println("插入失败");
            return;
        }

        try {
            disInsert(dis);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //输出成功提示
        pw.println("插入成功");
    }
}