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
 
@WebServlet("/EmployeeDelServlet")
public class EmployeeDelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //处理注册页面的请求
 
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
 
        Map<String, String[]> map = req.getParameterMap();
        PreparedStatement ps = null;
        Connection conn = null;
        
        if (map.get("employee_Id")[0].isEmpty()) {
            req.setAttribute("employeedel", "删除失败，请选择要删除的员工");
            req.getRequestDispatcher("HeadadminMainPage.jsp").forward(req, resp);
            return;
        }
        
        try {
            conn = DBUtil.getConnection();
            String sql = "delete from employee where employee_Id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, map.get("employee_Id")[0]);
            int count = ps.executeUpdate();
            if (count > 0) {
                System.out.println("删除成功");
                req.setAttribute("employeedel", "删除成功！");
                req.getRequestDispatcher("HeadadminMainPage.jsp").forward(req, resp);
            } else {
                req.setAttribute("employeedel", "删除失败！");
                req.getRequestDispatcher("HeadadminMainPage.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            System.out.println("删除失败");
            req.setAttribute("employeedel", "删除失败，该管理员正在负责某个超市/仓库，请先更换该超市/仓库的管理员后再删除");
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