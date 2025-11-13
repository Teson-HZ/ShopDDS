package servlet1;

import bean1.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/SummarizeDataServlet")
public class SummarizeDataServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            summarizeInventory();
            summarizeApplications();
            req.setAttribute("Addup", "数据汇总成功！");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("Addup", "数据汇总失败：" + e.getMessage());
        }
        req.getRequestDispatcher("/HeadadminMainPage.jsp").forward(req, resp);
    }

    private void summarizeInventory() throws SQLException {
        String sql = "INSERT INTO allinv (commodity_Id, allinv_Amount) " +
                     "SELECT commodity_Id, SUM(inv_Amount) FROM inv " +
                     "GROUP BY commodity_Id " +
                     "ON DUPLICATE KEY UPDATE allinv_Amount = VALUES(allinv_Amount)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.executeUpdate();
        }
    }

    private void summarizeApplications() throws SQLException {
        String sql = "INSERT INTO allapl (commodity_Id, allapl_Amount) " +
                     "SELECT commodity_Id, SUM(apl_Amount) FROM apl " +
                     "GROUP BY commodity_Id " +
                     "ON DUPLICATE KEY UPDATE allapl_Amount = VALUES(allapl_Amount)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.executeUpdate();
        }
    }
}
