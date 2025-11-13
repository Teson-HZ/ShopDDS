/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet1;
 
 
import bean1.DBUtil;
import bean1.Headadmin;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author WHZ2002
 */
@WebServlet("/HeadadminLoginServlet")
public class HeadadminLoginServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            String headadmin_Id = req.getParameter("headadmin_Id");
            String headadmin_Pwd = req.getParameter("headadmin_Pwd");
            String inputCaptcha = req.getParameter("captcha").toLowerCase();
            String sessionCaptcha = (String) req.getSession().getAttribute("captcha");
            
            // 验证验证码是否正确
            if (!inputCaptcha.equals(sessionCaptcha)) {
                req.setAttribute("msg", "登录失败，验证码错误");
                req.getRequestDispatcher("HeadadminLogin.jsp").forward(req, resp);
                return;
            }

            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM headadmin WHERE headadmin_Id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, headadmin_Id);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                String storedPassword = rs.getString("headadmin_Pwd");
                if (!headadmin_Pwd.equals(storedPassword)) {
                    req.setAttribute("msg", "登录失败，密码错误");
                    req.getRequestDispatcher("HeadadminLogin.jsp").forward(req, resp);
                    return;
                }

                // At this point, both username and password are correct
                Headadmin headadmin = new Headadmin();
                headadmin.setHeadadmin_Id(rs.getString("headadmin_Id"));
                headadmin.setHeadadmin_Pwd(rs.getString("headadmin_Pwd"));
                headadmin.setHeadadmin_Tel(rs.getString("headadmin_Tel"));
                headadmin.setHeadadmin_Name(rs.getString("headadmin_Name"));

                req.getSession().setAttribute("headadmin", headadmin);
                req.getSession().setAttribute("helloheadadmin", headadmin.getHeadadmin_Name()); // Store username in session for later use
                req.getSession().setAttribute("helloheadadminId", headadmin.getHeadadmin_Id());
                //req.setAttribute("mainR", "blank.jsp");
                req.getRequestDispatcher("HeadadminMainPage.jsp").forward(req, resp);
            } else {
                req.setAttribute("msg", "登录失败，用户名错误");
                req.getRequestDispatcher("HeadadminLogin.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, ps, conn);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
