package servlet1;

import bean1.DBUtil;
import bean1.Headadmin; 

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

@WebServlet("/SelectAdminServlet")
public class SelectAdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置请求和响应的编码
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        
//        // 从session获取用户名
//        HttpSession session = req.getSession();
//        String username = (String) session.getAttribute("");
        
        Map<String, String[]> map = req.getParameterMap();
        List<Headadmin> CommodityAllList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM headadmin";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                    // 假设date属性的列名为"date_column"
//                    Date date = rs.getDate("Visitdate");
//                    // 将日期转换为字符串
//                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                    String dateString = sdf.format(date);
                // Admin类有一个构造函数，用于从结果集初始化数据
                    Headadmin commodity = new Headadmin(
                    rs.getString("headadmin_Id"),
                    rs.getString("headadmin_Name"),
                    rs.getString("headadmin_Pwd"),
                    rs.getString("headadmin_Tel")
                );
                CommodityAllList.add(commodity);
            }
            // 设置属性以便在JSP页面中使用
            req.setAttribute("CommodityAllList", CommodityAllList);
            // 转发请求到JSP页面
            req.getRequestDispatcher("Show_Headadmin.jsp").forward(req, resp);
            
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
