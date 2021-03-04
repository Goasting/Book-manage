package cn.web.servlet;

import com.alibaba.druid.util.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/insertServlet")
public class InsertServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        String id = req.getParameter("id");
        String bookname = req.getParameter("bookname");
        String author = req.getParameter("author");
        String page = req.getParameter("page");
        String price = req.getParameter("price");
        String sql="INSERT INTO book(id, bookname, author, page, price) VALUES ("+id+", '"+bookname+"', '"+author+"'," +page+"," +price+")";
int i = 0;
        try{
            resp.getWriter().write(sql);
            Connection conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/day14","root","admin");
            PreparedStatement ps = conn.prepareStatement(sql);
            i = ps.executeUpdate(sql);
            resp.getWriter().write(i);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            resp.sendRedirect("/Tomcat/homeServlet");

    }
}}
