<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
			
			
			
			<form action="Addgoods.jsp" method="post">
				<tr>
					<td><div align="center"><%=rec.getInt("goods.id")%></div></td>
					<td><div align="center"><%=rec.getString("goods.name")%></div></td>
					<td><div align="center"><%=rec.getString("goods.unitprice")%></div></td>
					<td><div align="center"><%=rec.getString("stock.Total")%></div></td>
					
					<input type="hidden" name="IDTxt" size="60"value="<%=rec.getInt("ID")%>">
					<input type="hidden" name="goodsunitprice" size="60"value="<%=rec.getInt("goods.unitprice")%>">
					<input type="hidden" name="stocktotal" size="60"value="<%=rec.getInt("stock.Total")%>">
					<td><select id="St" name="St">
							<option value="0">0</option>
			<%for (int i =1 ; i<20 ; i++){ %>
							<option value="<%=i%>"><%=i %></option>
				<% } %>			
					</select></td>
					<td>
						<div><button type="submit" name="Add">ซื้อสินค้า</button></div>
					</td>
				</tr>
			</form>			
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