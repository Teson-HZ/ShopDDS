package servlet1;

import bean1.DBUtil;
import bean1.Warehouse;
import org.json.JSONArray;
import org.json.JSONObject;

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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/WarehouseIdAcquireServlet")
public class WarehouseIdAcquireServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        List<Warehouse> managers = getWarehouseManagersFromDatabase();

        // Convert managers list to JSON format
        JSONArray jsonArray = new JSONArray();
        for (Warehouse manager : managers) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", manager.getWarehouse_Id());
            jsonObject.put("name", manager.getWarehouse_Name());
            jsonArray.put(jsonObject);
        }
       // Write JSON data directly to response
        resp.getWriter().write(jsonArray.toString());
    }

    private List<Warehouse> getWarehouseManagersFromDatabase() {
        List<Warehouse> managers = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Get database connection
            conn = DBUtil.getConnection();

            // Prepare SQL statement
            String sql = "SELECT warehouse_Id,warehouse_Name FROM warehouse";
            ps = conn.prepareStatement(sql);
            // Execute query
            rs = ps.executeQuery();

            // Process result set
            while (rs.next()) {
                Warehouse employee = new Warehouse();
                employee.setWarehouse_Id(rs.getString("warehouse_Id"));
                employee.setWarehouse_Name(rs.getString("warehouse_Name"));
                managers.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception: store error message in request attribute
            //req.setAttribute("errorMessage", "Error retrieving warehouse managers: " + e.getMessage());
        } finally {
            // Close resources in finally block to ensure they are always closed
            DBUtil.close(rs, ps, conn);
        }
        return managers;
    }
}
