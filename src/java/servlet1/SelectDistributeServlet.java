package servlet1;

import bean1.DBUtil;
import bean1.Allocate; 

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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.sql.Date;
import java.text.SimpleDateFormat;

@WebServlet("/SelectDistributeServlet")
public class SelectDistributeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置请求和响应的编码
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        // 从session获取用户名
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("mainemployeeid");
        
        Map<String, String[]> map = req.getParameterMap();
        List<Allocate> CommodityAllList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            
            String getSupermarketIdSQL = "SELECT warehouse_Id FROM warehouse WHERE employee_Id=?";
            ps = conn.prepareStatement(getSupermarketIdSQL);
            ps.setString(1, username);
            rs = ps.executeQuery();

            String supermarketId = null;
            if (rs.next()) {
                supermarketId = rs.getString("warehouse_Id");
            }

            if (supermarketId == null) {
                throw new SQLException("Warehouse_Id ID not found for employee: " + username);
            }
            
            String sql = "SELECT supermarket_Id,commodity_Id,allocate_Amount FROM allocate where warehouse_Id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, supermarketId);
            rs = ps.executeQuery();
            
            while (rs.next()) {
               int allocateAmount = rs.getInt("allocate_Amount");
               Allocate commodity = new Allocate(
                    rs.getString("supermarket_Id"),
                    rs.getString("commodity_Id"),
                    allocateAmount
                );
                CommodityAllList.add(commodity);
            }
            // 设置属性以便在JSP页面中使用
            req.setAttribute("CommodityAllList", CommodityAllList);
            // 转发请求到JSP页面
            req.getRequestDispatcher("Show_wareAllocate.jsp").forward(req, resp);
            
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("error", "查询失败！");
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        } finally {
            // 关闭资源
            DBUtil.close(rs, ps, conn);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
