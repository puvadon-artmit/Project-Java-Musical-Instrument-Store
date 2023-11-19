<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Profile</title>
</head>
<body>
<body bgcolor=#FF8247>
	<div align="center">
		<font class="glow">Update Profile</font>

		<%
		Object strID = session.getAttribute("uID");
		Connection connect = null;
		Statement s = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost/project" + "?user=root&password=");

			int id = Integer.parseInt(session.getAttribute("ID").toString());
			String sql = "SELECT * FROM  customer WHERE ID=" + id;

			s = connect.createStatement();
			ResultSet rec = s.executeQuery(sql);

			if (rec != null) {
				rec.next();
		%>


		<br>
		<form name="frmUpdate" method="post"
			action="Saveupdate.jsp?Action=Saveupdate">
			<table id="xx" align="center">

				<tr>
					<th><div align="left">Name</div></th>
					<td><input type="text" name="nameTxt" size="60"
						value="<%=rec.getString("Name")%>"></td>
				</tr>
				<tr>
					<th><div align="left">Username</div></th>
					<td><input type="text" name="UsernameTxt" size="60"
						value="<%=rec.getString("Username")%>"></td>
				</tr>
				<tr>
					<th><div align="left">Password</div></th>
					<td><input type="text" name="PasswordTxt" size="60"
						value="<%=rec.getString("Password")%>"></td>
				</tr>

				<tr>
					<th><div align="left">Address</div></th>
					<td><input type="text" name="addressTxt" size="60"
						value="<%=rec.getString("Address")%>"></td>
				</tr>

				<tr>
					<th><div align="left">Phone</div></th>
					<td><input type="text" name="phoneTxt" size="60"
						value="<%=rec.getString("Phone")%>"></td>
				</tr>

				<tr>
					<th><div align="left">Email</div></th>
					<td><input type="text" name="emailTxt" size="60"
						value="<%=rec.getString("Email")%>"></td>
				</tr>




			</table>
			<%
			}
			} catch (Exception e) {
			// TODO Auto-generated catch block
			out.println(e.getMessage());
			e.printStackTrace();
			}
			%>
			<br>
			<div align="center">
				<button type="submit" value="Save"
					style="width: 100px; height: 40px">Save</button>
			</div>
			<a href=Profile.jsp><input type="button" value="BLACK"
				style="width: 100px; height: 40px"></a>
		</form>


	</div>



</body>
</html>