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
		Connection connect = null;
		Statement s = null;

		try {
Class.forName("com.mysql.jdbc.Driver");
			
			connect =  DriverManager.getConnection("jdbc:mysql://localhost/project" +"?user=root&password=");
			String sql = "SELECT goods.ID, goods.name,goods.Unitprice,stock.Total FROM goods INNER JOIN stock ON goods.ID = stock.Goods_ID";

			s = connect.createStatement();
			ResultSet rec = s.executeQuery(sql);
	%>

	<div align="center">
		<h1 class="glow">Stock</h1>

		<table id="xx" align="center">
			<tr>
				<th><div align="center">Goods ID</div></th>
				<th><div align="center">Name</div></th>
				<th><div align="center">Unit_Price</div></th>
				<th><div align="center">Stock</div></th>
			</tr>
			
			<%
				while ((rec != null) && (rec.next())) {
			%>
			
			<tr>
				<td><div align="center"><%=rec.getInt("goods.ID")%></div></td>
				<td><div align="center"><%=rec.getString("goods.Name")%></div></td>
				<td><div align="center"><%=rec.getString("goods.Unitprice")%></div></td>
				<td><div align="center"><%=rec.getString("stock.Total")%></div></td>
			</tr>
			
			<%
				}
			%>
			
		</table>
		
		<%
			} catch (Exception e) {
				// TODO Auto-generated catch block
				out.println(e.getMessage());
				e.printStackTrace();
			}
		
		%>
		
	</div>
</body>
</html>