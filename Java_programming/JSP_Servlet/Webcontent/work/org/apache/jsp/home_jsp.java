/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.0.M17
 * Generated at: 2017-03-05 06:29:43 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.Map;
import java.util.*;
import java.io.*;
import java.sql.*;
import java.text.*;

public final class home_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("java.sql");
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("java.util");
    _jspx_imports_packages.add("java.text");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("java.io");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("java.util.Map");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\n');
      out.write('\n');

	Map<String, String> map = (Map<String, String>)session.getAttribute( "login_user" );

	String userEmail = "";
	String userPassword = "";
	String hiddenLogin = "";
	String hiddenLogout = "";
	String id = "";
	
	if ( null == map ) {
		// ログアウト中.
		hiddenLogout = "hidden";
	} else {
		// ログイン中.
		userEmail = (String)map.get( "Email" );
		userPassword = (String)map.get( "Password" );
		id = (String)map.get("id");
		hiddenLogin = "hidden";
	}

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("\t<head>\n");
      out.write("\t\t<meta charset=\"UTF-8\">\n");
      out.write("\t\t<title>藤田研究室</title>\n");
      out.write("\t\t<link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/main.css\">\n");
      out.write("\t</head>\n");
      out.write("\t<body>\n");
      out.write("\n");
      out.write("\t<header>\n");
      out.write("\t\t<h1><a href=\"./\" id=\"logo\">藤田研究室</a></h1>\n");
      out.write("\t</header>\n");
      out.write("\t\n");
      out.write("\n");
      out.write("\t<main>\n");
      out.write("\t    <nav class=\"navi\">\n");
      out.write("        \t<ul id=\"dropmenu\">\n");
      out.write("        \t\t<li ");
      out.print( hiddenLogin );
      out.write("><a href=\"#\">新規登録</a></li>\n");
      out.write("        \t\t<li ");
      out.print( hiddenLogout );
      out.write("><a href=\"./mypage\">マイページ</a></li>\n");
      out.write("          \t\t<li ");
      out.print( hiddenLogin );
      out.write("><a href=\"./login\">ログイン</a></li>\n");
      out.write("          \t\t<li ");
      out.print( hiddenLogout );
      out.write("><a href=\"./logout\">ログアウト</a></li>\n");
      out.write("         \t</ul>\n");
      out.write("       </nav>\n");
      out.write("       <table class=\"hometable\">\n");
      out.write("\t\t<tr>\n");

    Map<String, String> map2 = (Map<String, String>)session.getAttribute( "db_info" );

	Connection conn = null;
	Connection conn2 = null;
	String url = (String)map.get("url");
	String user = (String)map.get("user");
	String password = (String)map.get("password");

    Class.forName("com.mysql.jdbc.Driver").newInstance();
    conn = DriverManager.getConnection(url, user, password);
    conn2 = DriverManager.getConnection(url, user, password);
    Statement stmt = conn.createStatement();
    Statement stmt2 = conn.createStatement();
    
    String sql = "SELECT * FROM Test";
    ResultSet rs = stmt.executeQuery(sql);
    
    while(rs.next()){

      out.write("\n");
      out.write("\t\t\t<td>");
      out.print( rs.getString("name") );
      out.write("</td> \n");
      out.write("\t\t\t");
	
		    	String sql2 = "SELECT state FROM Log WHERE id=" + rs.getString("id") + " order by time desc";
		    	ResultSet rs2 = stmt2.executeQuery(sql2);
		    	String status  = "";
		    	while(rs2.next()){
		    		status = rs2.getString("state");
		    		break;
		    	}
		    	rs2.close();
		    	
		    	if(status.equals("in")){
		    		
      out.write("\n");
      out.write("\t\t    \t\t<td id=\"flag\">在室</td>\n");
      out.write("\t\t    \t\t<td>退室</td>\n");
      out.write("\t\t    \t\t</tr>\n");
      out.write("\t\t    \t\t");

		    	}else{
		    		
      out.write("\n");
      out.write("\t\t    \t\t<td>在室</td>\n");
      out.write("\t\t    \t\t<td id=\"flag\">退室</td>\n");
      out.write("\t\t    \t\t</tr>\n");
      out.write("\t\t    \t\t");
		    		
		    	}
   }

    rs.close();
    stmt.close();
    stmt2.close();
    conn.close();
    conn2.close();

      out.write("\n");
      out.write("\t</table>\n");
      out.write("\t\n");
      out.write("\t</main>\n");
      out.write("\t\n");
      out.write("\t<footer>\n");
      out.write("  \t\t<nav>\n");
      out.write("    \t\t<ul>\n");
      out.write("      \t\t\t<li class=\"footernavi\"><a href=\"http://alpha.sf.cs.it-chiba.ac.jp/mediawiki/\">SFwiki</a></li>\n");
      out.write("      \t\t\t<li class=\"footernavi\"><a href=\"http://alpha.sf.cs.it-chiba.ac.jp/gitbucket/\">GitBucket</a></li>  \n");
      out.write("    \t\t</ul>\n");
      out.write("  \t\t</nav>\n");
      out.write("\t</footer>\n");
      out.write("\t</body>\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}