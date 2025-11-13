
package servlet1;
import bean1.DBUtil;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/UpdatePwdServlet")
public class UpdatePwdServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        
        // 从表单获取数据
        String sourcePwd = request.getParameter("SoursePwd");
        String newPwd = request.getParameter("NewPwd");
        
        // 从session获取用户名
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("helloheadadminId");
        
        // 用来传递信息回前端页面
        String message = "";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;        
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        
        try {
            // 连接数据库
            conn = DBUtil.getConnection();
            
            // 查询原密码是否正确
            String sql = "SELECT headadmin_Pwd FROM headadmin WHERE headadmin_Id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                String correctPwd = rs.getString("headadmin_Pwd");
                if (correctPwd.equals(sourcePwd)) {
                    // 原密码正确，更新新密码
                    sql = "UPDATE headadmin SET headadmin_Pwd=? WHERE headadmin_Id=?";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, newPwd);
                    pstmt.setString(2, username);
                    int updateCount = pstmt.executeUpdate();
                    
                    if (updateCount > 0) {
                        message = "密码更新成功！";
                    } else {
                        message = "密码更新失败，请重试！";
                    }
                } else {
                    message = "原密码错误，请重新输入！";
                }
            } else {
                message = "用户不存在！";
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "出现异常：" + e.getMessage();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        // 设置请求属性以传递更新信息
        request.setAttribute("UpD", message);
        
        // 请求转发回原JSP页面
        request.getRequestDispatcher("HeadadminMainPage.jsp").forward(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}