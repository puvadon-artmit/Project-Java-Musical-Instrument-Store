<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>

<html>
<head>
	<title>Register</title>
</head>
<body background="https://images3.alphacoders.com/124/thumb-1920-124037.jpg"width="%130"height="10%">
<body bgcolor= #E6E6FA>
<center>

	REGISTER
	<form name="frmLogin" method="post" action="register.jsp?Action=resis">
	<table width="374" border="1">	
 
  <tr>
	<th width="140">
    <div align="left">Usnername </div></th>
		<td><input type="text" name="UsernameTxt" size="35"></td>
	</tr>
	<tr>
	<th width="140">
    <div align="left">Password </div></th>
		<td><input type="text" name="PasswordTxt" size="35"></td>
	</tr>
	
  <tr>
	<th width="140">
    <div align="left">Name </div></th>
		<td><input type="text" name="nameTxt" size="35"></td>
	</tr>
  <tr>
	<th width="140">
    <div align="left"> Address </div></th>
		<td><input type="text" name="addressTxt" size="35"></td>
	</tr>
  <tr>
	<th width="140">
    <div align="left">Phone </div></th>
		<td><input type="text" name="phoneTxt" size="35"></td>
	</tr>
  <tr>
	<th width="140">
    <div align="left">E-mail </div></th>
		<td><input type="text" name="emailTxt" size="35"></td>
	</tr>
 
	</table>
	<br>
	<input type="submit" value="Save"style="width:100px;height:40px">
	<a href=index.jsp><input type="button" value="BACK"style="width:100px;height:40px"></a></p>
	</form>
	</center>
	

	
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
		
		String sql = "INSERT INTO customer " + "(USERNAME,PASSWORD,NAME,ADDRESS,PHONE,EMAIL) VALUES ('"+ UsernameTxt +"','"+ PasswordTxt+"','"+ nameTxt + "','" + addressTxt + "'" + ","
				+ phoneTxt+ ",'" + emailTxt + "') ";

         s.execute(sql);
        
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