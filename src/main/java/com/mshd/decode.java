package com.mshd;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * 解码
 * 一共是36位，分为震情编码（26位）来源码（3位）载体码（1位）灾情码（6位）
 * 震情编码（26位）细分为地理信息码（12位）时间码（14位）（年4月2日2时2分2秒2）
 * 来源码三位，具体信息见项目需求
 * 载体编码一位，具体信息见项目需求
 * 灾情编码分为灾害编码（3位）灾情指标代码（3位）
*/
public class decode {

    public static String address_decode(String address_code){
        DB db=new DB();
        String sql="SELECT *FROM regionTest";
        ResultSet rs=db.query(sql);
        try {
            while(rs.next()) {
                String province_id=rs.getString(1);
                String province_name=rs.getString(2);
                if(province_id.equals(address_code)){
                    //System.out.println("find it,address code is"+province_name);
                    return province_name;
                }
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return "address code not exist!";
    }

    public static String date_decode(String date_code){
        String year = date_code.substring(0,4);
        String month = date_code.substring(4,6);
        String day = date_code.substring(6,8);
        String hour = date_code.substring(8,10);
        String minute = date_code.substring(10,12);
        String second = date_code.substring(12,14);
        return "事件发生具体时间 :"+year+" "+month+" "+day+" "+hour+":"+minute+":"+second;
    }

    public static String source_decode(String source_code){
        DB db=new DB();
        String sql="SELECT *FROM source";
        ResultSet rs=db.query(sql);
        try {
            while(rs.next()) {
                String source_id=rs.getString(1);
                String detail_info=rs.getString(2);
                if(source_id.equals(source_code)){
                    //System.out.println("find it,information source is "+detail_info);
                    return detail_info;
                }
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return "source code not exist!";
    }

    public static String carrier_decode(String carrier_code){
        DB db=new DB();
        String sql="SELECT *FROM carrier";
        ResultSet rs=db.query(sql);
        try {
            while(rs.next()) {
                String carrier_id=rs.getString(1);
                String carrier_info=rs.getString(2);
                if(carrier_id.equals(carrier_code)){
                    //System.out.println("find it,carrier  is "+carrier_info);
                    return carrier_info;
                }
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return "carrier code not exist!";
    }

    public static String disaster_classification_decode(String disaster_classification_code){
        DB db=new DB();
        String sql="SELECT *FROM disaster_classification";
        ResultSet rs=db.query(sql);
        try {
            while(rs.next()) {
                String disaster_id=rs.getString(1);
                String disaster_name=rs.getString(2);
                if(disaster_id.equals(disaster_classification_code)){
                    //System.out.println("find it,carrier  is "+disaster_name);
                    return disaster_name;
                }
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return "disaster classification code not exist!";
    }

    public static String disaster_indicator_decode(String disaster_indicator_code){
        DB db=new DB();
        String sql="SELECT *FROM disaster_indicator";
        ResultSet rs=db.query(sql);
        try {
            while(rs.next()) {
                String indicator_id=rs.getString(1);
                String indicator=rs.getString(2);
                if(indicator_id.equals(disaster_indicator_code)){
                    //System.out.println("find it,carrier  is "+indicator);
                    return indicator;
                }
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return "disaster indicator code not exist!";
    }

    /**
     * 总体的解码函数，各个分函数也可以在上面根据函数名字来调用
    * */
    public static String decode(String code){
        String address = address_decode(code.substring(0,12));
        String date = date_decode(code.substring(12,26));
        String source = source_decode(code.substring(26,29));
        String carrier = carrier_decode(code.substring(29,30));
        String disaster_classification = disaster_classification_decode(code.substring(30,33));
        String indicator = disaster_indicator_decode(code.substring(33,36));
        return "解码结果:详细地址："+ address+" "+date+" 灾情来源: "+source
                +" 信息载体是: "+carrier+" 灾情分类为: "+disaster_classification
                +"灾情指标: "+indicator;
    }


    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner get=new Scanner(System.in);
        String code = "123456789000202205131422521001201101";
        System.out.println(code+" is waiting to handle:");
        String result = decode("123456789000202205131422521001201101");
        System.out.println(result);
        get.close();
    }

}
