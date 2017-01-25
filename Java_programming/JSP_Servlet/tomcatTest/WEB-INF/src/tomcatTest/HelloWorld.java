package tomcatTest;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class HelloWorld extends HttpServlet {
	
    public HelloWorld() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	response.sendRedirect("/tomcat9/tomcatTest/HelloWorld.jsp");
    }
}
