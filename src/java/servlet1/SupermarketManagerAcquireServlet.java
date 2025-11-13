package servlet1;

import bean1.DBUtil;
import bean1.Employee;
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

@WebServlet("/SupermarketManagerAcquireServlet")
public class SupermarketManagerAcquireServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        List<Employee> managers = getWarehouseManagersFromDatabase();

        // Convert managers list to JSON format
        JSONArray jsonArray = new JSONArray();
        for (Employee manager : managers) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", manager.getEmployee_Id());
            jsonObject.put("name", manager.getEmployee_Name()); // 添加名字信息
            jsonArray.put(jsonObject);
        }
       // Write JSON data directly to response
        resp.getWriter().write(jsonArray.toString());
    }

    private List<Employee> getWarehouseManagersFromDatabase() {
        List<Employee> managers = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Get database connection
            conn = DBUtil.getConnection();

            // Prepare SQL statement
            String sql = "SELECT employee_Id, employee_Name FROM employee WHERE employee_Ide = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,"超市管理员");

            // Execute query
            rs = ps.executeQuery();

            // Process result set
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEmployee_Id(rs.getString("employee_Id"));
                employee.setEmployee_Name(rs.getString("employee_Name")); // 添加名字信息
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
