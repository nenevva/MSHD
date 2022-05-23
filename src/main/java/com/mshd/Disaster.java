package com.mshd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Disaster {
    private String id;
    private String detail;
    private String file;
    public Disaster(String id, String detail, String file) {
        this.id = id;
        this.detail = detail;
        this.file = file;
    }

    public String getId() {
        return id;
    }

    public String getDetail() {
        return detail;
    }

    public String getFile() {
        return file;
    }
    public static boolean disValidate(Disaster dis) {
        //TODO 校验数据是否完整有效
        return true;
    }

    public static void disInsert(Disaster dis) throws SQLException, ClassNotFoundException {

        //在数据库中插入一行灾情数据
        new JDBC();
        Connection conn = JDBC.getConnection();
        PreparedStatement pst = conn.prepareStatement("INSERT INTO disaster values(?,?,?)");
        pst.setString(1, dis.getId());
        pst.setString(2, dis.getDetail());
        pst.setString(3, dis.getFile());
        pst.execute();
    }
}
