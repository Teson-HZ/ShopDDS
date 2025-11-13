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
 
@WebServlet("/AddHeadAdminServlet")
public class AddHeadAdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //处理注册页面的请求
 
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
 
        Map<String, String[]> map = req.getParameterMap();
        PreparedStatement ps = null;
        Connection conn = null;
        
        if (map.get("headadmin_Name")[0].isEmpty() || 
        map.get("headadmin_Pwd")[0].isEmpty() ||
        map.get("headadmin_Tel")[0].isEmpty() ||
        map.get("headadmin_Name")[0].isEmpty()) {
        req.setAttribute("addheadadmin", "添加失败，请将所有信息填写完整");
        req.getRequestDispatcher("HeadadminMainPage.jsp").forward(req, resp);
        return;
        }
        
        try {
            conn = DBUtil.getConnection();
            String sql = "insert into headadmin(headadmin_Id,headadmin_Pwd,headadmin_Tel,headadmin_Name) values(?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, map.get("headadmin_Id")[0]);
            ps.setString(2, map.get("headadmin_Pwd")[0]);
            ps.setString(3, map.get("headadmin_Tel")[0]);
            ps.setString(4, map.get("headadmin_Name")[0]);

            int count = ps.executeUpdate();
            if (count > 0) {
                System.out.println("添加成功");
                req.setAttribute("addheadadmin", "添加成功！");
                req.getRequestDispatcher("HeadadminMainPage.jsp").forward(req, resp);
            } else {
                req.setAttribute("addheadadmin", "添加失败！");
                req.getRequestDispatcher("HeadadminMainPage.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            System.out.println("添加失败");
            req.setAttribute("addheadadmin", "添加失败，请输入合法正确的字段");
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