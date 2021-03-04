package cn.web.servlet;

import cn.web.domain.User;
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
import java.sql.ResultSet;
@WebServlet("/homeServlet")
public class HomeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取request域中共享的user对象


            //给页面写一句话
            //设置编码
            response.setContentType("text/html;charset=utf-8");
            //输出

            response.getWriter().write("</br>");
            response.getWriter().write("<form action=\"/Tomcat/select.html\" method=\"get\"> <input type=\"submit\" value=\"select\" /> </form>");
            response.getWriter().write("<form action=\"/Tomcat/insert.html\" method=\"get\"> <input type=\"submit\" value=\"insert\" /> </form>");
            response.getWriter().write("<form action=\"/Tomcat/delete.html\" method=\"get\"> <input type=\"submit\" value=\"delete\" /> </form>");
            response.getWriter().write("<form action=\"/Tomcat/update.html\" method=\"get\"> <input type=\"submit\" value=\"update\" /> </form>");
            ResultSet res = null;
            PreparedStatement pstmt = null;
            Connection con = null;
            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/day14","root","admin");
                String sql ="select * from book";
                pstmt = conn.prepareStatement("select * from book");
                res = pstmt.executeQuery(sql);
                response.getWriter().write("id");
                response.getWriter().write("&nbsp;&nbsp;书名");
                response.getWriter().write("&nbsp;&nbsp;作者");
                response.getWriter().write(" &nbsp;&nbsp;页数");
                response.getWriter().write(" &nbsp;&nbsp; 价格");
                response.getWriter().write("</br>");
                while (res.next()) {
                    int id = res.getInt("id");
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}

