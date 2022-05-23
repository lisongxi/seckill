package com.lsx.seckill.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lsx.seckill.pojo.User;
import com.lsx.seckill.vo.RespBean;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 生成用户，插入数据库
 * @author LiSongXi
 * @date
 */
public class UserUtil {
    private static void createUser(int count) throws Exception {
        List<User> users = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            User user = new User();
            user.setId(18000000000L + i);
            user.setLoginCount(1);
            user.setNickname("user" + i);
            user.setRegisterDate(new Date());
            user.setSlat("1a2b3c4d");
            user.setPassword(MD5Util.inputPassToDBPass("123456", user.getSlat()));
            users.add(user);
        }
        System.out.println("创建用户");

        //插入数据库
        Connection conn = getConn();
        String sql = "insert into t_user(login_count,nickname,register_date,slat,password,id)values(?,?,?,?,?,?)";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            pstmt.setInt(1, user.getLoginCount());
            pstmt.setString(2, user.getNickname());
            pstmt.setTimestamp(3, new Timestamp(user.getRegisterDate().getTime()));
            pstmt.setString(4, user.getSlat());
            pstmt.setString(5, user.getPassword());
            pstmt.setLong(6, user.getId());
            pstmt.addBatch();
        }
        pstmt.executeBatch();
        pstmt.close();
        conn.close();
        System.out.println("插入数据库完成");

        //登录,生成uerTicket
        String urlString = "http://localhost:8080/login/doLogin";
        File file = new File("D:\\apache-jmeter-5.4.3\\myJmx\\config.txt");
        if (file.exists()) {
            file.delete();
        }

        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        raf.seek(0);
        for (User user : users) {
            //请求
            URL url = new URL(urlString);
            HttpURLConnection co = (HttpURLConnection) url.openConnection();
            co.setRequestMethod("POST");
            //设置输入网页密码（相当于输出到页面）
            co.setDoOutput(true);
            OutputStream outputStream = co.getOutputStream();
            String params = "mobile=" + user.getId() + "&password=" + MD5Util.inputPassToFromPass("123456");
            outputStream.write(params.getBytes());
            outputStream.flush();
            //获取网页输出，（得到输入流，把结果得到，再输出到ByteArrayOutputStream内）
            InputStream inputStream = co.getInputStream();
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(bytes)) >= 0) {
                bout.write(bytes, 0, len);
                //System.out.println(bout);
            }
            inputStream.close();
            bout.close();
            //把ByteArrayOutputStream内的东西转换为respBean对象
            String response = bout.toString();
            ObjectMapper mapper = new ObjectMapper();
            //System.out.println(response);
            RespBean respBean = mapper.readValue(response, RespBean.class);
            //得到userTicket
            String userTicket = (String) respBean.getObj();
            System.out.println("create userTicket: " + userTicket);
            String row = user.getId() + "," + userTicket;
            //写入指定文件
            raf.seek(raf.length());
            raf.write(row.getBytes());
            raf.write("\r\n".getBytes());
            System.out.println("write to file:" + user.getId());
        }
        raf.close();
        System.out.println("over");
    }
        private static Connection getConn() throws Exception {
            String url = "jdbc:mysql://localhost:3306/seckill?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
            String username = "root";
            String password = "778699";
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            return DriverManager.getConnection(url, username, password);
        }

    public static void main(String[] args) throws Exception {
        createUser(5000);
    }
}
