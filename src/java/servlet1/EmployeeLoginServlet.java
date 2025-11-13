/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet1;
 
 
import bean1.DBUtil;
import bean1.Employee;
 
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

/**
 *
 * @author WHZ2002
 */
@WebServlet("/EmployeeLoginServlet")
public class EmployeeLoginServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            String role = req.getParameter("role");
            String employee_Id = req.getParameter("employee_Id");
            String employee_Pwd = req.getParameter("employee_Pwd");
            String inputCaptcha = req.getParameter("captcha").toLowerCase();
            String sessionCaptcha = (String) req.getSession().getAttribute("captcha");
    
            // 验证验证码是否正确
            if (!inputCaptcha.equals(sessionCaptcha)) {
                req.setAttribute("msg", "登录失败，验证码错误");
                req.getRequestDispatcher("EmployeeLogin.jsp").forward(req, resp);
                return;
            }

            conn = DBUtil.getConnection();
            // 先检查用户名是否存在
            String sql = "SELECT * FROM employee WHERE employee_Id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, employee_Id);
            rs = ps.executeQuery();

            if (rs.next()) {
                // 如果用户名存在，再检查角色是否匹配
                String employeeIde = rs.getString("employee_Ide");
                if (!role.equals(employeeIde)) {
                    req.setAttribute("msg", "登录失败，身份不匹配");
                    req.getRequestDispatcher("EmployeeLogin.jsp").forward(req, resp);
                    return;
                }

                String storedPassword = rs.getString("employee_Pwd");
                if (!employee_Pwd.equals(storedPassword)) {
                    req.setAttribute("msg", "登录失败，密码错误");
                    req.getRequestDispatcher("EmployeeLogin.jsp").forward(req, resp);
                    return;
                }

                // 如果用户名、密码和角色都正确
                Employee employee = new Employee();
                employee.setEmployee_Id(rs.getString("employee_Id"));
                employee.setEmployee_Pwd(rs.getString("employee_Pwd"));
                employee.setEmployee_Name(rs.getString("employee_Name"));
                employee.setEmployee_Ide(employeeIde);
                employee.setEmployee_Tel(rs.getString("employee_Tel"));

                req.getSession().setAttribute("employee", employee);
                req.getSession().setAttribute("helloemployee", employee.getEmployee_Name());
                req.getSession().setAttribute("mainemployeeid", employee.getEmployee_Id());
                
                // 根据角色跳转到不同页面
                if ("仓库管理员".equals(role)) {
                    String warename=rs.getString("employee_Id");
                    String getname = "SELECT warehouse_Name FROM warehouse WHERE employee_Id=?";
                    ps = conn.prepareStatement(getname);
                    ps.setString(1, warename);
                    rs = ps.executeQuery();

                    String warehouseName = null;
                    if (rs.next()) {
                        warehouseName = rs.getString("warehouse_Name");
                    }

                    if (warehouseName == null) {
                        throw new SQLException("Warehouse_Name ID not found for employee: " + warename);
                    }
                    req.getSession().setAttribute("warehouseName", warehouseName);
                    // 如果是仓库管理员
                    req.getRequestDispatcher("WarehouseMainPage.jsp").forward(req, resp);
                } else if ("超市管理员".equals(role)) {
                    String supername=rs.getString("employee_Id");
                    String getname = "SELECT supermarket_Name FROM supermarket WHERE employee_Id=?";
                    ps = conn.prepareStatement(getname);
                    ps.setString(1, supername);
                    rs = ps.executeQuery();

                    String supermarketName = null;
                    if (rs.next()) {
                        supermarketName = rs.getString("supermarket_Name");
                    }

                    if (supermarketName == null) {
                        throw new SQLException("supermarketName ID not found for employee: " + supername);
                    }
                    req.getSession().setAttribute("supermarketName", supermarketName);
                    // 如果是超市管理员
                    req.getRequestDispatcher("SupermarketMainPage.jsp").forward(req, resp);
                } else {
                    // 其他角色或角色不明确，可以重定向到通用页面或显示错误
                    req.getRequestDispatcher("GeneralDashboard.jsp").forward(req, resp);
                }
                
            } else {
                // 如果查询不到用户名，则意味着用户名错误
                req.setAttribute("msg", "登录失败，用户名错误");
                req.getRequestDispatcher("EmployeeLogin.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, ps, conn);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
