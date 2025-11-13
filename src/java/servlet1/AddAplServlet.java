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
import java.sql.SQLException;
import java.util.Map;
import java.sql.ResultSet;
import java.time.LocalTime;
import java.sql.Time;
 
@WebServlet("/AddAplServlet")
public class AddAplServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //处理注册页面的请求
 
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        
        // 从session获取超市id
        HttpSession session = req.getSession();
        String emid = (String) session.getAttribute("mainemployeeid");
 
        Map<String, String[]> map = req.getParameterMap();
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        
        if (map.get("commodity_Id")[0].isEmpty() || 
        map.get("apl_Amount")[0].isEmpty()) {
        req.setAttribute("addapl", "添加失败，请将所有信息填写完整");
        req.getRequestDispatcher("SupermarketMainPage.jsp").forward(req, resp);
        return;
        }
        
        try {
            conn = DBUtil.getConnection();
            
            // 查询超市ID
            String sqlSelect = "SELECT supermarket_Id FROM supermarket WHERE employee_Id = ?";
            ps = conn.prepareStatement(sqlSelect);
            ps.setString(1, emid);
            rs = ps.executeQuery();
            String supermarketId = null;
            if (rs.next()) {
                supermarketId = rs.getString("supermarket_Id");
            }
            rs.close();
            ps.close();

            if (supermarketId == null) {
                req.setAttribute("addapl", "未找到管理的超市！");
                req.getRequestDispatcher("SupermarketMainPage.jsp").forward(req, resp);
                return;
            }
            
            String sql = "insert into apl(supermarket_Id,commodity_Id,apl_Amount,apl_Time) values(?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,supermarketId);
            ps.setString(2, map.get("commodity_Id")[0]);
            int apl_AmountValue = Integer.parseInt(map.get("apl_Amount")[0]);
            ps.setInt(3, apl_AmountValue);
            ps.setTime(4, Time.valueOf(LocalTime.now()));
            
            int count = ps.executeUpdate();
            if (count > 0) {
                System.out.println("添加成功");
                req.setAttribute("addapl", "添加成功！");
                req.getRequestDispatcher("SupermarketMainPage.jsp").forward(req, resp);
            } else {
                req.setAttribute("addapl", "添加失败！");
                req.getRequestDispatcher("SupermarketMainPage.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            System.out.println("添加失败");
            req.setAttribute("addapl", "添加失败，请输入合法正确的字段");
            req.getRequestDispatcher("SupermarketMainPage.jsp").forward(req, resp);
        } finally {
            DBUtil.close(null,ps,conn);
        }
 
    }
 
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}