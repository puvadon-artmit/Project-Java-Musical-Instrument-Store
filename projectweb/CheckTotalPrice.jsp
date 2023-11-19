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
			String sql = "SELECT receipt.ID,receipt.Date,customer.ID as cid\r\n"
					+ ",customer.Name as cname,goods.ID as gid,goods.Name gname\r\n"
					+ ",receipt_details.Quantity,receipt_details.Amount\r\n"
					+ " FROM receipt\r\n"
					+ "INNER JOIN receipt_details ON receipt.ID = receipt_details.Receipt_ID\r\n"
					+ "INNER JOIN goods ON receipt_details.Goods_ID = goods.ID\r\n"
					+ "INNER JOIN customer ON receipt.Customer_ID = customer.ID";
			
			s = connect.createStatement();
			ResultSet rec = s.executeQuery(sql);
	%>

	<div align="center">
		<h1 class="glow">Stock</h1>

		<table id="xx" align="center">
			<tr>
				<th><div align="center">Receipt.ID</div></th>
				<th><div align="center">Receipt.Date</div></th>
				<th><div align="center">Customer_ID</div></th>
				<th><div align="center">Customer_name</div></th>
				<th><div align="center">Goods_ID</div></th>
				<th><div align="center">Goods_name</div></th>
				<th><div align="center">Quantity</div></th>
				<th><div align="center">Amount</div></th>
			</tr>
			
			<%
				while ((rec != null) && (rec.next())) {
			%>
			
			<tr>
				<td><div align="center"><%=rec.getInt("receipt.ID")%></div></td>
				<td><div align="center"><%=rec.getString("receipt.Date")%></div></td>
				<td><div align="center"><%=rec.getString("cid")%></div></td>
				<td><div align="center"><%=rec.getString("cname")%></div></td>
				<td><div align="center"><%=rec.getString("gid")%></div></td>
				<td><div align="center"><%=rec.getString("gname")%></div></td>
				<td><div align="center"><%=rec.getString("receipt_details.Quantity")%></div></td>
				<td><div align="center"><%=rec.getString("receipt_details.Amount")%></div></td>
			
				
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