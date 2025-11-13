package servlet1;
 
 
import bean1.DBUtil;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
 
@WebServlet("/UpdateSupernameServlet")
public class UpdateSupernameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //处理注册页面的请求
 
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
 
        Map<String, String[]> map = req.getParameterMap();
        PreparedStatement ps = null;
        Connection conn = null;
        
        if (map.get("supermarket_Id")[0].isEmpty()||map.get("supermarket_Name")[0].isEmpty()) {
            req.setAttribute("upsupername", "修改失败，请将信息填写完整");
            req.getRequestDispatcher("HeadadminMainPage.jsp").forward(req, resp);
            return;
        }
        
        try {
            conn = DBUtil.getConnection();
            String sql = "UPDATE supermarket SET supermarket_Name=? WHERE supermarket_Id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, map.get("supermarket_Name")[0]);
            ps.setString(2, map.get("supermarket_Id")[0]);
            int count = ps.executeUpdate();
            if (count > 0) {
                System.out.println("修改成功");
                req.setAttribute("upsupername", "修改成功！");
                req.getRequestDispatcher("HeadadminMainPage.jsp").forward(req, resp);
            } else {
                req.setAttribute("upsupername", "修改失败！不存在该超市信息！");
                req.getRequestDispatcher("HeadadminMainPage.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            System.out.println("修改失败");
            req.setAttribute("upsupername", "修改失败，新的名字不合法");
            req.getRequestDispatcher("HeadadminMainPage.jsp").forward(req, resp);
        } finally {
            DBUtil.close(null,ps,conn);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}