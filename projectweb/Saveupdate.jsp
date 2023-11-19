<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% 
	if(request.getParameter("Action") != null) {
	
	Connection connect = null;
	Statement s = null;
	
	try {

		Class.forName("com.mysql.jdbc.Driver");
		connect =  DriverManager.getConnection("jdbc:mysql://localhost/project" , "root","");
		
		s = connect.createStatement();
		
		
		
		
		String UsernameTxt = request.getParameter("UsernameTxt");
		String PasswordTxt = request.getParameter("PasswordTxt");
		String nameTxt = request.getParameter("nameTxt");
		String addressTxt = request.getParameter("addressTxt");
		String phoneTxt = request.getParameter("phoneTxt");
		String emailTxt = request.getParameter("emailTxt");
		
		 int id = Integer.parseInt(session.getAttribute("ID").toString());
		String Sql = "UPDATE CUSTOMER SET  Name='" + nameTxt + "', Address='"+ addressTxt +"', Username='"+UsernameTxt +"',Password='"+PasswordTxt + "', Phone=" + phoneTxt + ", Email='"+emailTxt +"' WHERE ID="+ id;
		
		
         s.execute(Sql);
        
         out.println("register Successfully");
        
	  		
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
	}
	%>

</body>
</html>