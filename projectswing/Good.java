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

public class Good extends JDialog implements ActionListener {

	private static final int width = 1000;
	private static final int height = 500;

	JTextField goodidTxt;
	JTextField nameTxt;
	JTextField unitcostTxt;
	JTextField unitpriceTxt;


	private static final String addString = "Add";
	private static final String editString = "Edit";
	private static final String deleteString = "Delete";

	public Good(JFrame frame) {
		super(frame, true);

		// Create pane as container
		Container pane = getContentPane();
		// set layout manager to manual
		pane.setLayout(null);
		
		JTable table = new JTable();
		Object[] columns = { "ID", "Name", "Unitcost", "Unitprice"};
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		table.setModel(model);
		Show(model);
		
		JScrollPane pane1 = new JScrollPane(table);
		pane1.setBounds(0, 0, 1000, 200);
		setLayout(null);
		add(pane1);

		// create labels
		JLabel goodidLabel = new JLabel("Good Id:");
		JLabel nameLabel = new JLabel("Name:");
		JLabel unitcostLabel = new JLabel("Unit Cost:");
		JLabel unitpriceLabel = new JLabel("Unit Price:");
		

		// create buttons
		JButton addBtn = new JButton(addString);
		JButton editBtn = new JButton(editString);
		JButton deleteBtn = new JButton(deleteString);

		// create texts
		goodidTxt = new JTextField(10);
		nameTxt = new JTextField(20);
		unitcostTxt = new JTextField(20);
		unitpriceTxt = new JTextField(5);
		

		// create control buttons.
		addBtn.addActionListener(this);
		editBtn.addActionListener(this);
		deleteBtn.addActionListener(this);

		// add labels
		
		pane.add(goodidLabel);
		pane.add(nameLabel);
		pane.add(unitcostLabel);
		pane.add(unitpriceLabel);


		// add text fields
		pane.add(goodidTxt);
		pane.add(nameTxt);
		pane.add(unitcostTxt);
		pane.add(unitpriceTxt);
		

		// add control buttons
		pane.add(addBtn);
		pane.add(editBtn);
		pane.add(deleteBtn);

		// set sizes and positions for labels
		Dimension 
		size = goodidLabel.getPreferredSize();
		goodidLabel.setBounds(80, 200, 100, 30);
		size = nameLabel.getPreferredSize();
		nameLabel.setBounds(80, 250, 100, 30);
		size = unitcostLabel.getPreferredSize();
		unitcostLabel.setBounds(80, 300, 100, 30);
		size = unitpriceLabel.getPreferredSize();
		unitpriceLabel.setBounds(80, 350, 100, 30);
		

		// set sizes and positions for labels
		size = goodidTxt.getPreferredSize();
		goodidTxt.setBounds(150, 200, 100, 30);
		size = nameTxt.getPreferredSize();
		nameTxt.setBounds(150, 250, 100, 30);
		size = unitcostTxt.getPreferredSize();
		unitcostTxt.setBounds(150, 300, 100, 30);
		size = unitpriceTxt.getPreferredSize();
		unitpriceTxt.setBounds(150, 350, 100, 30);
		
		
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
					String sql = "INSERT INTO goods " + "(ID,Name,Unitcost,Unitprice) " + "VALUES (" + ""
							+ goodidTxt.getText() + ",'" + nameTxt.getText() + "'," + unitcostTxt.getText() + ","
							+ unitpriceTxt.getText() + ")";
					s.execute(sql);
					Show(model);
					// Reset Text Fields
					nameTxt.setText("");
					goodidTxt.setText("");
					
					unitcostTxt.setText("");
					unitpriceTxt.setText("");

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

					

					String Sql = "UPDATE goods SET  name='" + nameTxt.getText() + 
							"', unitcost="+ unitcostTxt.getText() + "" + ", unitprice=" + unitpriceTxt.getText() + " WHERE ID="+ goodidTxt.getText();

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

					String sql = "DELETE FROM goods " + " WHERE ID=" + goodidTxt.getText();
					System.out.print(sql);
					// string Sql = "SELECT * FROM CUSTOMER";
					s.execute(sql);
Show(model);
					JOptionPane.showMessageDialog(null, "Record Inserted Successfully");

					

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

			String sql = "SELECT * FROM goods ORDER BY ID ASC";

			s = connect.createStatement();
			ResultSet rec = s.executeQuery(sql);
			System.out.println(sql);
			int row = 0;
			while ((rec != null) && (rec.next())) {
				model.addRow(new Object[0]);
				model.setValueAt(rec.getInt("ID"), row, 0);
				model.setValueAt(rec.getString("Name"), row, 1);
				model.setValueAt(rec.getInt("Unitcost"), row, 2);
				model.setValueAt(rec.getInt("Unitprice"), row, 3);
				//model.setValueAt(rec.getString("Email"), row, 4);
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