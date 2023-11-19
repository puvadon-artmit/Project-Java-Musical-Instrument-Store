<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>

<html>
<head>
	<title>Login</title>
</head>

<body background="https://images5.alphacoders.com/100/thumbbig-1004111.jpg"width="%130"height="10%">


<body bgcolor= #CDB7B5>
	<%
	if(request.getParameter("txtUsername") != "" && request.getParameter("txtPassword") != "")	
	{

		Connection connect = null;
		Statement s = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			connect =  DriverManager.getConnection("jdbc:mysql://localhost/project" +"?user=root&password=");
			
			String Username = request.getParameter("txtUsername");
			String password = request.getParameter("txtPassword");
			
			s = connect.createStatement();
			
			String sql = "SELECT * FROM customer WHERE Username = '" + Username + "' AND password='" + password + "'";
			
			ResultSet rec = s.executeQuery(sql);
			
			if(!rec.next())
			{
				out.print("<font color=red>Username and Password Incorrect!</font>");
			} else {
				rec.first();
				session.setAttribute("ID",rec.getString("ID"));	
				response.sendRedirect("Main.jsp");
			}
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				out.println(e.getMessage());
				e.printStackTrace();
			}
		
			try {
				if(s!=null){
					s.close();
					connect.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				out.println(e.getMessage());
				e.printStackTrace();
			}
		
	} else {
		out.print("<font color=red>กรุณากรอกข้อมูล</font>");
	}
		
	%>
	

	<form name="frmLogin" method="post" action="Login.jsp?Action=Login">
	<center>
		Login<br>
		<table border="1" style="width: 300px">
			<tbody>
				<tr>
					<td>&nbsp;Username</td>
					<td><input name="txtUsername" type="text" id="txtUsername">
					</td>
				</tr>
				<tr>
					<td>&nbsp;Password</td>
					<td><input name="txtPassword" type="password" id="txtPassword">
					</td>
				</tr>
			</tbody>
		</table>
		<br>
		<a><input type="submit" name="Submit" value="LOGIN"style="width:100px;height:40px"></a>	
		<a href=index.jsp><input type="button" value="BACK"style="width:100px;height:40px"></a>
	
	</form>
	
	
	
</center>
</body>
</html>