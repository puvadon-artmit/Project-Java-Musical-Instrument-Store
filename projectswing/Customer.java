package Project;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
//import java.util.Properties;
import java.sql.ResultSet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Customer extends JDialog implements ActionListener {

	private static final int width = 1000;
	private static final int height = 500;

	JTextField idTxt;
	JTextField nameTxt;
	JTextField addressTxt;
	JTextField phoneTxt;
	JTextField emailTxt;

	private static final String addString = "Add";
	private static final String editString = "Edit";
	private static final String deleteString = "Delete";

	public Customer(JFrame frame) {
		super(frame, true);

		// Create pane as container
		Container pane = getContentPane();
		// set layout manager to manual
		pane.setLayout(null);
		
		JTable table = new JTable();
		Object[] columns = { "ID", "Name", "Address", "Phone", "Email"};
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		table.setModel(model);
		Show(model);
		
		JScrollPane pane1 = new JScrollPane(table);
		pane1.setBounds(0, 0, 1000, 200);
		setLayout(null);
		add(pane1);

		// create labels
		JLabel idLabel = new JLabel("ID:");
		JLabel nameLabel = new JLabel("Name:");
		JLabel addressLabel = new JLabel("Address:");
		JLabel phoneLabel = new JLabel("Phone:");
		JLabel emailLabel = new JLabel("Email:");

		// create buttons
		JButton addBtn = new JButton(addString);
		JButton editBtn = new JButton(editString);
		JButton deleteBtn = new JButton(deleteString);

		// create texts
		idTxt = new JTextField(50);
		nameTxt = new JTextField(50);
		addressTxt = new JTextField(50);
		phoneTxt = new JTextField(20);
		emailTxt = new JTextField(20);

		// create control buttons.
		addBtn.addActionListener(this);
		editBtn.addActionListener(this);
		deleteBtn.addActionListener(this);

		// add labels
		
		pane.add(idLabel);
		pane.add(nameLabel);
		pane.add(addressLabel);
		pane.add(phoneLabel);
		pane.add(emailLabel);


		// add text fields
		pane.add(idTxt);
		pane.add(nameTxt);
		pane.add(addressTxt);
		pane.add(phoneTxt);
		pane.add(emailTxt);

		// add control buttons
		pane.add(addBtn);
		pane.add(editBtn);
		pane.add(deleteBtn);

		// set sizes and positions for labels
		Dimension 
		size = idLabel.getPreferredSize();
		idLabel.setBounds(80, 200, 100, 30);
		size = nameLabel.getPreferredSize();
		nameLabel.setBounds(80, 250, 100, 30);
		size = addressLabel.getPreferredSize();
		addressLabel.setBounds(80, 300, 100, 30);
		size = phoneLabel.getPreferredSize();
		phoneLabel.setBounds(80, 350, 100, 30);
		size = emailLabel.getPreferredSize();
		emailLabel.setBounds(80, 400, 100, 30);

		// set sizes and positions for labels
		size = idTxt.getPreferredSize();
		idTxt.setBounds(150, 200, 150, 30);
		size = nameTxt.getPreferredSize();
		nameTxt.setBounds(150, 250, 150, 30);
		size = addressTxt.getPreferredSize();
		addressTxt.setBounds(150, 300, 150, 30);
		size = phoneTxt.getPreferredSize();
		phoneTxt.setBounds(150, 350, 150, 30);
		size = emailTxt.getPreferredSize();
		emailTxt.setBounds(150, 400, 150, 30);
		
		// set sizes and positions for controls buttons
		size = addBtn.getPreferredSize();
		addBtn.setBounds(350, 200, 100, 30);
		size = editBtn.getPreferredSize();
		editBtn.setBounds(350, 250, 100, 30);
		size = deleteBtn.getPreferredSize();
		deleteBtn.setBounds(350, 300, 100, 30);

		// set size and position for container
		pane.setPreferredSize(new Dimension(width, height));

		pack();
		setVisible(false);

		System.out.println("DialogAddStudent() done!");

		addBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Connection connect = null;
				Statement s = null;

				try {
					Class.forName("com.mysql.jdbc.Driver");

					connect = DriverManager.getConnection("" + "jdbc:mysql://localhost/project", "root", "");

					s = connect.createStatement();

					// SQL Insert
					String sql = "INSERT INTO Customer " + "(ID,Name,Address,Phone,Email) " + "VALUES (" + ""
							+ idTxt.getText() + ",'" + nameTxt.getText() + "','" + addressTxt.getText() + "',"
							+ phoneTxt.getText() + ",'" + emailTxt.getText() + "')";
					s.execute(sql);

					Show(model);
					// Reset Text Fields
					idTxt.setText("");
					nameTxt.setText("");
					addressTxt.setText("");
					phoneTxt.setText("");
					emailTxt.setText("");

					JOptionPane.showMessageDialog(null, "Record Inserted Successfully");

					

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
					e1.printStackTrace();
				}
			}

		});

		editBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Connection connect = null;
				Statement s = null;

				try {
					Class.forName("com.mysql.jdbc.Driver");
					connect = DriverManager.getConnection("jdbc:mysql://localhost/project", "root", "");

					s = connect.createStatement();

					System.out.println("UPDATE goods");
					// String sql = "UPDATE customer " +"SET Budget = '5000000' " +" WHERE
					// CustomerID = 'C005' ";

					String Sql = "UPDATE Customer SET  name='" + nameTxt.getText() + "', address='"
							+ addressTxt.getText() + "'" + ", phone=" + phoneTxt.getText() + ",email='"
							+ emailTxt.getText() + "' WHERE ID=" + idTxt.getText();

					s.execute(Sql);
					Show(model);
					JOptionPane.showMessageDialog(null, "Record Inserted Successfully");
					System.out.println(Sql);

					

				} catch (Exception e1) {// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// Close
			}
		});

		deleteBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Connection connect = null;
				Statement s = null;

				try {
					Class.forName("com.mysql.jdbc.Driver");
					connect = DriverManager.getConnection("jdbc:mysql://localhost/project", "root", "");

					s = connect.createStatement();

					String sql = "DELETE FROM Customer " + " WHERE ID=" + idTxt.getText();
					System.out.print(sql);
					// string Sql = "SELECT * FROM CUSTOMER";
					s.execute(sql);

					JOptionPane.showMessageDialog(null, "Record Inserted Successfully");

					
					Show(model);
				} catch (Exception e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
			}

		});
	}
	public void Show(DefaultTableModel model) {

		Connection connect = null;
		Statement s = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost/project", "root", "");

			String sql = "SELECT * FROM customer ORDER BY ID ASC";

			s = connect.createStatement();
			ResultSet rec = s.executeQuery(sql);
			System.out.println(sql);
			int row = 0;
			while ((rec != null) && (rec.next())) {
				model.addRow(new Object[0]);
				model.setValueAt(rec.getInt("ID"), row, 0);
				model.setValueAt(rec.getString("Name"), row, 1);
				model.setValueAt(rec.getString("Address"), row, 2);
				model.setValueAt(rec.getInt("Phone"), row, 3);
				model.setValueAt(rec.getString("Email"), row, 4);
				//model.setValueAt(rec.getString("gname"), row, 5);
				//model.setValueAt(rec.getDouble("quantity"), row, 6);
				//model.setValueAt(rec.getDouble("amount"), row, 7);
				//model.setValueAt(rec.getInt("status"), row, 8);
				row++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
