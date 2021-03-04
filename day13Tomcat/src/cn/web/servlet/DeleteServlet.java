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
import java.sql.ResultSet;

@WebServlet("/deleteServlet")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        //2.获取请求参数
        String id = request.getParameter("id");

        PreparedStatement pstmt = null;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/day14","root","admin");
            String sql ="delete  from book where id ="+id;
            pstmt = conn.prepareStatement(sql);
            int i = pstmt.executeUpdate(sql);
        if(i != 0){
            response.sendRedirect("/Tomcat/homeServlet");
            }else{
                response.getWriter().write("删除失败");
            response.getWriter().write("<form action=\"/Tomcat/homeServlet\" method=\"get\"> <input type=\"submit\" value=\"select\" /> </form>");
            }



        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            JdbcUtils.close(pstmt);
            JdbcUtils.close(conn);
        }

    }
}
