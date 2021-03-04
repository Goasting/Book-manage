package cn.web.servlet;

import com.alibaba.druid.util.JdbcUtils;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet("/selectServlet")
public class SelectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        //2.获取请求参数
        String id = request.getParameter("id");
        ResultSet res = null;
        PreparedStatement pstmt = null;
        Connection con = null;
        response.getWriter().write("<form action=\"/Tomcat/homeServlet\" method=\"get\"> <input type=\"submit\" value=\"Home\" /> </form>");
        try {
            Connection conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/day14","root","admin");
            String sql ="select * from book where id ="+id;
            pstmt = conn.prepareStatement(sql);
            res = pstmt.executeQuery(sql);
            response.getWriter().write("id");
            response.getWriter().write("&nbsp;&nbsp;书名");
            response.getWriter().write("&nbsp;&nbsp;作者");
            response.getWriter().write(" &nbsp;&nbsp;页数");
            response.getWriter().write(" &nbsp;&nbsp; 价格");
            response.getWriter().write("</br>");
            while (res.next()) {
                int Id = res.getInt("Id");
                String bookname = res.getString("bookname");
                String author = res.getString("author");
                int page = res.getInt("page");
                int price = res.getInt("price");
                response.getWriter().write(""+id);
                response.getWriter().write(" &nbsp;&nbsp; "+bookname);
                response.getWriter().write(" &nbsp;&nbsp; "+author);
                response.getWriter().write(" &nbsp;&nbsp; "+page);
                response.getWriter().write(" &nbsp;&nbsp; "+price+"元");
                response.getWriter().write("</br>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(res);
            JdbcUtils.close(pstmt);
            JdbcUtils.close(con);
        }

    }
}














