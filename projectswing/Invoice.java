package Project;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Invoice extends JDialog implements ActionListener {
	
	private static final int width = 1000;
	private static final int height = 700;

	JTextField invoiceidTxt;
    JComboBox<String> supplieridTxt;
	JComboBox<String> goodidTxt;
	JTextField invoicedateTxt;
	JTextField quantityTxt;
	JTextField goodTxt;
	JTextField unitpriceTxt;
	
	public Invoice(JFrame frame) {
		super(frame, true);
	 	Container pane = getContentPane();
		 pane.setLayout(null);
		 pane.setPreferredSize(new Dimension(width, height));
		pack();
		setVisible(false);
		System.out.println("DialogAddstudent() done!");
		
		JTable table = new JTable();
		Object[] columns = { " Invoice ID ", "Invoice Date", "Supplier ID", "Supplier Name",
				"Good ID", "Good Name", "Quantity", "Total Amount", "Status" };
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		table.setModel(model);
		
		Connection connect = null;
		Statement s = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost/project", "root", "");

			String sql = "SELECT invoice.ID,invoice.Date,supplier.ID as sid\r\n"
					+ ",supplier.Name as sname,goods.ID as gid,goods.Name gname\r\n"
					+ ",invoice_details.Quantity,invoice_details.Amount,invoice_details.Status\r\n" + "FROM invoice\r\n"
					+ "INNER JOIN invoice_details ON invoice.ID = invoice_details.Invoice_ID\r\n"
					+ "INNER JOIN goods ON invoice_details.Goods_ID = goods.ID\r\n"
					+ "INNER JOIN supplier ON invoice.Supplier_ID = supplier.ID\r\n"
					+ "where invoice_details.Status=0\r\n";

			s = connect.createStatement();
			ResultSet rec = s.executeQuery(sql);
			System.out.println(sql);
			int row = 0;
			while ((rec != null) && (rec.next())) {
				model.addRow(new Object[0]);
				model.setValueAt(rec.getInt("ID"), row, 0);
				model.setValueAt(rec.getString("Date"), row, 1);
				model.setValueAt(rec.getDouble("sid"), row, 2);
				model.setValueAt(rec.getString("sname"), row, 3);
				model.setValueAt(rec.getDouble("gid"), row, 4);
				model.setValueAt(rec.getString("gname"), row, 5);
				model.setValueAt(rec.getInt("Quantity"), row, 6);
				model.setValueAt(rec.getDouble("Amount"), row, 7);
				model.setValueAt(rec.getDouble("Status"), row, 8);
				row++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JButton addBtn = new JButton("Add");
		JButton editBtn = new JButton( "Edit");
		JButton deleteBtn = new JButton("Delete");
		JButton confirmBtn = new JButton("Confirm");
		
	

		addBtn.addActionListener(this);
		editBtn.addActionListener(this);
		deleteBtn.addActionListener(this);
		confirmBtn.addActionListener(this);

		
		add(addBtn);
		add(editBtn);
		add(deleteBtn);
		add(confirmBtn);

		
		addBtn.setBounds(300, 300, 100, 35);
		editBtn.setBounds(300, 350, 100, 35);
		deleteBtn.setBounds(300, 400, 100, 35);
		confirmBtn.setBounds(800, 300, 100, 35);
	 
		JLabel invoiceidLabel = new JLabel("Invoice ID");
		JLabel supplieridLabel = new JLabel("Supplier ID");
		JLabel goodidLabel = new JLabel("Good ID");
		JLabel invoicedataLabel = new JLabel("Invoice Date");
		JLabel quantityLabel = new JLabel("Quantity");
		JLabel goodLabel = new JLabel("Good ID");
		JLabel unitpriceLabel = new JLabel("Unitprice");
		 
		add(invoiceidLabel);
		add(supplieridLabel);
		add(goodidLabel);
		add(invoicedataLabel);
		add(quantityLabel);
		add(goodLabel);
		add(unitpriceLabel);
		  
		invoiceidLabel.setBounds(20,300,100,50);
		supplieridLabel.setBounds(20,350,100,50);
		goodidLabel.setBounds(20,400,100,50);
		invoicedataLabel.setBounds(20,450,100,50);
		quantityLabel.setBounds(20,500,100,50);
		goodLabel.setBounds(600,300,200,50);
		unitpriceLabel.setBounds(20, 550, 100, 50);
		 
		invoiceidTxt = new JTextField(10);
		supplieridTxt = new JComboBox<String>();
		goodidTxt = new JComboBox<String>();
		invoicedateTxt = new JTextField(5);
		quantityTxt = new JTextField(5);
		goodTxt = new JTextField(5);
		unitpriceTxt = new JTextField(5);
			
			pane.add(invoiceidTxt);
			pane.add(supplieridTxt);
			pane.add(goodidTxt);
			pane.add(invoicedateTxt);
			pane.add(quantityTxt);
			pane.add(goodTxt);
			pane.add(unitpriceTxt);
			
		
			invoiceidTxt.setBounds(150,310,100,30);
			supplieridTxt.setBounds(150,360,100,30);
			goodidTxt.setBounds(150,410,100,30);
			invoicedateTxt.setBounds(150,460,100,30);
			quantityTxt.setBounds(150,510,100,30);
			goodTxt.setBounds(650,310,100,30);
			unitpriceTxt.setBounds(150, 560, 100, 30);

		JScrollPane pane1 = new JScrollPane(table);
		pane1.setBounds(0, 0, 800, 150);
		setLayout(null);
		add(pane1);
		
		setVisible(false);

		System.out.println("Supplier_goods done!");

		Connection connects = null;
		Statement ss = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connects = DriverManager.getConnection("jdbc:mysql://localhost/project" + "?user=root&password=");

			String sql = "SELECT ID FROM goods  ";

			ss = connects.createStatement();
			ResultSet r = ss.executeQuery(sql);
			System.out.println(sql);

			while (r.next()) {
				{
					goodidTxt.addItem(r.getString("ID"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		Connection connects2 = null;
		Statement ss2 = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connects2 = DriverManager.getConnection("jdbc:mysql://localhost/project" + "?user=root&password=");

			String sql = "SELECT ID FROM supplier  ";

			ss2 = connects2.createStatement();
			ResultSet r = ss2.executeQuery(sql);
			System.out.println(sql);

			while (r.next()) {
				{
					supplieridTxt.addItem(r.getString("ID"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		addBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Connection connect = null;
				Statement s = null;
				try {
					Class.forName("com.mysql.jdbc.Driver");
					connect = DriverManager.getConnection("jdbc:mysql://localhost/project", "root", "");

					String sql = "INSERT INTO invoice(ID,Supplier_ID,Date,Total) VALUES(" + invoiceidTxt.getText() + ","
							+ supplieridTxt.getSelectedItem() + ",'" + invoicedateTxt.getText() + "',"
							+ quantityTxt.getText() + ")";
					s = connect.createStatement();

					String sql1 = "INSERT INTO invoice_details (Invoice_ID,Goods_ID,Quantity,Amount) VALUES("
							+ invoiceidTxt.getText() + "," + goodidTxt.getSelectedItem() + "," + quantityTxt.getText()
							+ "," + quantityTxt.getText() + "*" + unitpriceTxt.getText() + ")";

					s.execute(sql);
					s.execute(sql1);

				} catch (Exception e1) {
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

					String sql = "UPDATE invoice SET  Supplier_ID = " + supplieridTxt.getSelectedItem() + "" + ",Date='"
							+ invoicedateTxt.getText() + "'" + ",Total=" + quantityTxt.getText() + " WHERE ID="
							+ invoiceidTxt.getText();
					s = connect.createStatement();
					String sql1 = "UPDATE invoice_details SET  Goods_ID = " + goodidTxt.getSelectedItem()
							+ ",Quantity=" + quantityTxt.getText() + "" + ",Amount=" + quantityTxt.getText() + "*"
							+ unitpriceTxt.getText() + " WHERE ID=" + invoiceidTxt.getText();
					s.execute(sql);
					s.execute(sql1);

				} catch (Exception e1) {
					e1.printStackTrace();
				}

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

					String sql = "DELETE FROM invoice WHERE ID =" + invoiceidTxt.getText();
					s = connect.createStatement();
					String sql1 = "DELETE FROM invoice_details WHERE Invoice_ID =" + invoiceidTxt.getText();
					s.execute(sql);
					s.execute(sql1);

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				Show(i, model);
			}
		});
		
		confirmBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Connection connect = null;
				Statement s = null;
				try {
					Class.forName("com.mysql.jdbc.Driver");
					connect = DriverManager.getConnection("jdbc:mysql://localhost/project", "root", "");

					String sql = "UPDATE invoice_details SET  Status=1 WHERE invoice_ID ="+ invoiceidTxt.getText();
					s = connect.createStatement();
					s.execute(sql);
					
					
					String selectStock = "SELECT Goods_ID FROM stock WHERE Goods_ID="+goodTxt.getText();
					ResultSet r = s.executeQuery(selectStock);
					if(r.next()) {
						String sql1 = "UPDATE stock SET Total=Total+"+quantityTxt.getText()+" WHERE Goods_ID="+ goodTxt.getText();
						s.execute(sql1);
					} else {
						String sql1 = "INSERT INTO stock(Goods_ID,Total) VALUES("+ goodTxt.getText()+","+ quantityTxt.getText()+")";
						s.execute(sql1);
					}
					
					

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}

		});
	}
	
	public void Show(int i, DefaultTableModel model) {
		invoiceidTxt.setText(model.getValueAt(i, 0).toString());
		invoicedateTxt.setText(model.getValueAt(i, 1).toString());
		quantityTxt.setText(model.getValueAt(i, 2).toString());
		goodTxt.setText(model.getValueAt(i, 3).toString());
		unitpriceTxt.setText(model.getValueAt(i, 3).toString());
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}