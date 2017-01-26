package DataBaseTest;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class databasetest extends HttpServlet {
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException{

    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<html>");
    out.println("<head>");
    out.println("<title>データベーステスト</title>");
    out.println("</head>");
    out.println("<body>");
    
    Connection conn = null;
    String url = "jdbc:mysql://localhost/2017development";
    String user = "mmk";
    String password = "grqt58yj";


    try {
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      conn = DriverManager.getConnection(url, user, password);

      Statement stmt = conn.createStatement();
      String sql = "SELECT * FROM Test";
      ResultSet rs = stmt.executeQuery(sql);

      while(rs.next()){
        int code = rs.getInt("id");
        String company = rs.getString("name");
        out.println("<p>");
        out.println("ID:" + code + ", 名前:" + company);
        out.println("</p>");
      }

      rs.close();
      stmt.close();
    }catch (ClassNotFoundException e){
      out.println("ClassNotFoundException:" + e.getMessage());
    }catch (SQLException e){
      out.println("SQLException:" + e.getMessage());
    }catch (Exception e){
      out.println("Exception:" + e.getMessage());
    }finally{
      try{
        if (conn != null){
          conn.close();
        }
      }catch (SQLException e){
        out.println("SQLException:" + e.getMessage());
      }
    }

    out.println("</body>");
    out.println("</html>");
  }
}
