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
@WebServlet("/updateServlet")
public class UpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        String bookname = req.getParameter("bookname");
        String author = req.getParameter("author");
        String page = req.getParameter("page");
        String price=req.getParameter("price");
        Connection conn=null;
        PreparedStatement ps = null;
        int i =0;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/day14","root","admin");

            ps  = conn.prepareStatement("update book set bookname=?,author=?,page=?,price=? where id="+id);

            ps.setString(1, bookname);
            ps.setString(2, author);
            ps.setString(3, page);
            ps.setString(4, price);
             i =ps.executeUpdate();
            if(i != 0){
                resp.sendRedirect("/Tomcat/homeServlet");
            }else{
                resp.getWriter().write("更新失败");
                resp.getWriter().write("<form action=\"/Tomcat/homeServlet\" method=\"get\"> <input type=\"submit\" value=\"select\" /> </form>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }


    }
}




