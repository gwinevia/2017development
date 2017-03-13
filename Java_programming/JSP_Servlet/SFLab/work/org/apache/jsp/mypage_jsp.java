/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.0.M17
 * Generated at: 2017-03-13 06:40:41 UTC
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

public final class mypage_jsp extends org.apache.jasper.runtime.HttpJspBase
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
	String userEmail = (String)map.get( "Email" );;
	String userPassword = (String)map.get( "Password" );
	String id = (String)map.get("id");
	String userName = (String)map.get("name");
	String admin = "";
	
	if(userName.equals("SFLab")){
		admin = "true";
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
      out.write("\n");
      out.write("\t<main>\n");
      out.write("\t\t<nav class=\"navi\">\n");
      out.write("        \t<ul id=\"dropmenu\">\n");
      out.write("        \t\t<li><a href=\"#\">Menu</a>\n");
      out.write("        \t\t<ul>\n");
      out.write("        \t\t\t<li><a href=\"./update\">設定</a></li>\n");
      out.write("        \t\t\t");
 if(admin.equals("true")){ 
      out.write("\n");
      out.write("        \t\t\t\t<li ");
      out.print( admin );
      out.write("><a href=\"./member\">メンバー管理</a></li>\n");
      out.write("\t        \t\t");
	} 
      out.write("\n");
      out.write("        \t\t\t<li><a href=\"./logout\">ログアウト</a></li>\n");
      out.write("         \t\t</ul>\n");
      out.write("         \t\t</li>\n");
      out.write("         \t</ul>\n");
      out.write("       \t</nav>\n");
      out.write("\t\t<div>\n");
      out.write("  \t\t\t<aside>\n");
      out.write("    \t\t\t<section class=\"user_info\">\n");
      out.write("      \t\t\t\t<h1>");
      out.print( userName );
      out.write("</h1>\n");
      out.write("    \t\t\t</section>\n");
      out.write("    \t\t\t<hr width=80%>\n");

	if(!admin.equals("true")){
		Connection conn = null;
		Connection conn2 = null;
		String url = "jdbc:mysql://localhost/2017development";
		String user = "mmk";
		String password = "grqt58yj";

    	Class.forName("com.mysql.jdbc.Driver").newInstance();
    	conn = DriverManager.getConnection(url, user, password);
    	Statement stmt = conn.createStatement();
    
    	String sql = "SELECT state FROM Log WHERE id=" + id + " order by time desc";
    	ResultSet rs = stmt.executeQuery(sql);
    	rs.next();
    
		if(rs.getString("state").equals("in")){

      out.write("\n");
      out.write("\t\t\t\t<form method=\"post\" action=\"./mypage\">\n");
      out.write("\t\t\t\t    <input type=\"hidden\" name=\"state\" value=\"out\">\n");
      out.write("        \t\t\t<p class=\"mypage-b\"><input type=\"button\" name=\"state\" value=\"在室\" disabled id=\"mypage-submit\">\n");
      out.write("        \t\t\t<input type=\"submit\" value=\"退室\" id=\"mypage-submit\"/></p>\n");
      out.write("    \t\t \t</form>\n");

		}else {

      out.write("\n");
      out.write("\t\t\t\t<form method=\"post\" action=\"./mypage\">\n");
      out.write("\t\t\t\t\t<input type=\"hidden\" name=\"state\" value=\"in\">\n");
      out.write("    \t\t\t\t<p class=\"mypage-b\"><input type=\"submit\" value=\"在室\" id=\"mypage-submit\"/>\n");
      out.write("    \t\t\t\t<input type=\"button\" name=\"state\" value=\"退室\" disabled id=\"mypage-submit\"></p>\n");
      out.write("\t\t \t\t</form>\n");
	
		}
	
		rs.close();
		stmt.close();
    	conn.close();
	}

      out.write("\n");
      out.write("\t\t \t </aside>\n");
      out.write("\t\t</div>\n");
      out.write("\t</main>\n");
      out.write("\t\t\n");
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
