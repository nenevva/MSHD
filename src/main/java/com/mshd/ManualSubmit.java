package com.mshd;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.io.*;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import static com.mshd.Disaster.disInsert;
import static com.mshd.Disaster.disValidate;

@WebServlet(urlPatterns = "/MSServlet")
@MultipartConfig
public class ManualSubmit extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();

        Part part = request.getPart("file");
        String path = request.getServletContext().getRealPath("/");
        String fileName = part.getSubmittedFileName();
        part.write(path + fileName);

        Disaster dis = new Disaster(request.getParameter("id"), request.getParameter("detail"), fileName);

        try {
            disInsert(dis);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        pw.println("插入成功");
    }

    private String makeFileName(String filename){
        //为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名
        return UUID.randomUUID() + "_" + filename;
    }

    private String makePath(String filename,String savePath){
        //得到文件名的hashCode的值，得到的就是filename这个字符串对象在内存中的地址
        int hashcode = filename.hashCode();
        int dir1 = hashcode&0xf; //0--15
        int dir2 = (hashcode&0xf0)>>4; //0-15
        //构造新的保存目录
        String dir = savePath + "\\" + dir1 + "\\" + dir2; //upload\2\3 upload\3\5
        //File既可以代表文件也可以代表目录
        File file = new File(dir);
        //如果目录不存在
        if(!file.exists()){
            //创建目录
            file.mkdirs();
        }
        return dir;
    }
}