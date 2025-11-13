package bean1;
import java.sql.*;
 
public class DBUtil {
    private  static  final  String URL ="jdbc:mysql://localhost:3306/dds";
    private  static  final String USER="root";
    private  static  final String PASSWORD="888888";
    static {
        //加载驱动
        try{
            Class.forName("com.mysql.jdbc.Driver");
 
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("获取驱动失败");
        }
    }
    //连接数据库
    public static Connection getConnection()throws SQLException {
 
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }
    //关闭资源
    public static void close(ResultSet rs, PreparedStatement ps, Connection conn){
        if(rs!=null){
            try{
                rs.close();
            }catch (SQLException e){
                e.printStackTrace();
 
            }
        }
        if(ps!=null){
            try{
                ps.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if(conn!=null){
            try{
                conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
 
}