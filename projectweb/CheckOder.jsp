<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>

<html>

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
			String sql = "SELECT invoice.ID,invoice.Date,supplier.ID as sid ,supplier.Name as sname,goods.ID as gid,goods.Name gname ,invoice_details.Quantity,invoice_details.Amount,invoice_details.Status\r\n"
					+ "FROM invoice\r\n"
					+ "INNER JOIN invoice_details ON invoice.ID = invoice_details.Invoice_ID \r\n"
					+ "INNER JOIN goods ON invoice_details.Goods_ID = goods.ID \r\n"
			        + "INNER JOIN supplier ON invoice.Supplier_ID = supplier.ID\r\n"
					+ "WHERE invoice_details.Status=1";
			s = connect.createStatement();
			ResultSet rec = s.executeQuery(sql);
	%>

	<div align="center">
		<h1 class="glow">Stock</h1>

		<table id="xx" align="center">
			<tr>
				<th><div align="center">InvoiceID</div></th>
				<th><div align="center">invoiceDate</div></th>
				<th><div align="center">SupplierID</div></th>
				<th><div align="center">SupplierName</div></th>
				<th><div align="center">GoodsID</div></th>
				<th><div align="center">GoodsName</div></th>
				<th><div align="center">Quantity</div></th>
				<th><div align="center">Amount</div></th>
			</tr>
			
			<%
				while ((rec != null) && (rec.next())) {
			%>
			
			<tr>
				<td><div align="center"><%=rec.getInt("ID")%></div></td>
				<td><div align="center"><%=rec.getString("Date")%></div></td>
				<td><div align="center"><%=rec.getString("sid")%></div></td>
				<td><div align="center"><%=rec.getString("sname")%></div></td>
				<td><div align="center"><%=rec.getInt("gid")%></div></td>
				<td><div align="center"><%=rec.getString("gname")%></div></td>
				<td><div align="center"><%=rec.getString("Quantity")%></div></td>
				<td><div align="center"><%=rec.getString("Amount")%></div></td>
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
</body>
</html>