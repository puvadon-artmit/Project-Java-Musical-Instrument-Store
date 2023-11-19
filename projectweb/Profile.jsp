<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<html>
<head>
	<title>PROFILE</title>
</head>
<body>
<body bgcolor= #CAFF70>
	
	<%
	Connection connect = null;
	Statement s = null;
	
	try {
		Class.forName("com.mysql.jdbc.Driver");

		connect =  DriverManager.getConnection("jdbc:mysql://localhost/project" +
				"?user=root&password=");
		
		s = connect.createStatement();
	    int id = Integer.parseInt(session.getAttribute("ID").toString());
		String sql = "SELECT * FROM  customer WHERE ID="+ id;
		
		ResultSet rec = s.executeQuery(sql);
		%>
		<table width="800" border="1">
		  <tr>
		    <th width="200"> <div align="center">ID </div></th>
		     <th width="198"> <div align="center">Username </div></th>
		      <th width="198"> <div align="center">Password </div></th>
		    <th width="200"> <div align="center">Name </div></th>
		    <th width="198"> <div align="center">Address </div></th>
		    <th width="97"> <div align="center">Phone</div></th>
		    <th width="250"> <div align="center">Email </div></th>
		  
		  </tr>	
			<%while((rec!=null) && (rec.next())) { %>
				  <tr>
				    <td><div align="center"><%=rec.getString("ID")%></div></td>
				    <td><div align="center"><%=rec.getString("Username")%></div></td>
				    <td><div align="center"><%=rec.getString("Password")%></div></td>
				    <td><%=rec.getString("Name")%></td>
				    <td><%=rec.getString("Address")%></td>
				    <td><div align="center"><%=rec.getString("Phone")%></div></td>
				    <td align="right"><%=rec.getString("Email")%></td>
				  
				  </tr>
	       	<%}%>
	  	</table>      
	    <%	
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
		
		
	%>
	<br>
	
	<a href=Main.jsp><input type="button" value="BACK"style="width:100px;height:40px"></a>
	<a href=Edit.jsp><input type="button" value="EDIT"style="width:100px;height:40px"></a>
	
	</br>

</body>
</html>