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
 
@WebServlet("/AddCommodityServlet")
public class AddCommodityServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //处理注册页面的请求
 
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
 
        Map<String, String[]> map = req.getParameterMap();
        PreparedStatement ps = null;
        Connection conn = null;
        
        if (map.get("commodity_Id")[0].isEmpty() || 
        map.get("commodity_Barcode")[0].isEmpty() ||
        map.get("commodity_Name")[0].isEmpty() ||
        map.get("commodity_Class")[0].isEmpty() ||
        map.get("commodity_Price")[0].isEmpty() ||
        map.get("commodity_Unit")[0].isEmpty()) {
        req.setAttribute("addcommodity", "添加失败，请将所有信息填写完整");
        req.getRequestDispatcher("HeadadminMainPage.jsp").forward(req, resp);
        return;
        }
        
        try {
            conn = DBUtil.getConnection();
            String sql = "insert into commodity(commodity_Id,commodity_Barcode,commodity_Name,commodity_Class,commodity_Price,commodity_Unit) values(?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, map.get("commodity_Id")[0]);
            ps.setString(2, map.get("commodity_Barcode")[0]);
            ps.setString(3, map.get("commodity_Name")[0]);
            ps.setString(4, map.get("commodity_Class")[0]);
            ps.setString(5, map.get("commodity_Price")[0]);
            ps.setString(6, map.get("commodity_Unit")[0]);
            
            int count = ps.executeUpdate();
            if (count > 0) {
                System.out.println("添加成功");
                req.setAttribute("addcommodity", "添加成功！");
                req.getRequestDispatcher("HeadadminMainPage.jsp").forward(req, resp);
            } else {
                req.setAttribute("addcommodity", "添加失败！");
                req.getRequestDispatcher("HeadadminMainPage.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            System.out.println("添加失败");
            req.setAttribute("addcommodity", "添加失败，请输入合法正确的字段");
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