package com.mshd;

import java.io.*;
import java.sql.SQLException;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import static com.mshd.Disaster.disInsert;

@WebServlet(urlPatterns = "/MSServlet")
@MultipartConfig
public class ManualSubmit extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();

        //获取用户上传的文件
        Part part = request.getPart("file");
        //文件保存路径
        String path = request.getServletContext().getRealPath("/WEB_INF/upload");
        File file = new File(path);
        //如果目录不存在，创建目录
        if(!file.exists()){
            file.mkdirs();
        }
        //获取文件名
        String fileName = part.getSubmittedFileName();
        //执行写入
        part.write(path + fileName);

        //写入数据库
        Disaster dis = new Disaster(request.getParameter("id"), request.getParameter("detail"), fileName);

        try {
            disInsert(dis);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        pw.println("插入成功");
    }
}