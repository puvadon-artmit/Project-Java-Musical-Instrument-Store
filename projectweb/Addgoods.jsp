<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body background= "https://images.hdqwalls.com/wallpapers/bthumb/destiny-2-homage-4k-te.jpg">

	<%
	int ID = Integer.parseInt(request.getParameter("IDTxt"));

	if (ID != 0) {

		Connection connect = null;
		Statement s = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost/project", "root", "");

			s = connect.createStatement();
			ResultSet rs = null;
			double price = Double.parseDouble(request.getParameter("goodsunitprice")); //แปลงเป็นจำนวนจริง

			int stock = Integer.parseInt(request.getParameter("stocktotal")); //แปลงเป็นจำนวนเต็ม

			int quantity = Integer.parseInt(request.getParameter("St"));

			int cusid = Integer.parseInt(session.getAttribute("ID").toString());
			double total = price * quantity;

			if (stock < quantity) {
		out.print("สินค้าไม่เพียงพอ<br><a href='goods.jsp'>กลับไปหน้าซื้อ</a>");
			} else {
		String Sql = "INSERT INTO RECEIPT(Customer_ID,Total) VALUES (" + cusid + "," + total + ")";
		int lastInsertedID = s.executeUpdate(Sql, Statement.RETURN_GENERATED_KEYS); //lastid
		if (lastInsertedID == 1) {
			int rid = 0;
			rs = s.getGeneratedKeys();
			if (rs.next()) {
				rid = rs.getInt(1);
				String sql2 = "INSERT INTO RECEIPT_DETAILS(Receipt_ID,Goods_ID,Quantity,Amount) VALUES (" + rid
						+ "," + ID + "," + quantity + "," + total + ")";
				int recieptdetailsID = s.executeUpdate(sql2);
				if (recieptdetailsID == 1) {
					String UPDATE = "UPDATE STOCK SET TOTAL = Total-'" + quantity + "' WHERE GOODS_ID =" + ID;

					int UPDATESUC = s.executeUpdate(UPDATE);
					if (UPDATESUC == 1) {
						out.println("ซื้อสำเร็จ <br><br><a href='goods.jsp'>กลับไปหน้าซื้อ</a> ");
					}
				}
			}

		}

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			out.println(e.getMessage());
			e.printStackTrace();
		}

		try {
			if (s != null) {
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