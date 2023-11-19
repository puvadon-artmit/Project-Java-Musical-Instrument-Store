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

public class Receipt extends JDialog implements ActionListener {

	private static final int width = 1000;
	private static final int height = 700;

	JTextField invoiceidTxt;
	JTextField receivedataTxt;
	JTextField supplieridTxt;
	JTextField suppliernameTxt;
	JTextField goodsidTxt;
	JTextField goodsnameTxt;
	JTextField quantityTxt;
	JTextField totalamountTxt;
	JTextField statusTxt;
	

	public Receipt(JFrame frame) {
		super(frame, true);
		Container pane = getContentPane();
		pane.setLayout(null);
		pane.setPreferredSize(new Dimension(width, height));
		pack();
		setVisible(false);
		System.out.println("DialogAddstudent() done!");

		JTable table = new JTable();
		Object[] columns = { " 	Invoice Id ", "Invoice Date", "Supplier Id", "Supplier Name", "Goods Id", "Goods Name",
				"Quantity", "Total Amount", "Status" };
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		table.setModel(model);
		realtime(model);

		
		JButton CancelBtn = new JButton("Cancel");

		CancelBtn.addActionListener(this);

		add(CancelBtn);

		CancelBtn.setBounds(800, 300, 100, 40);

		JLabel invoiceidLabel = new JLabel("Invoice Id ");
		JLabel goodsidLabel = new JLabel("Goods Id ");
		JLabel quantityLabel = new JLabel("Quantity ");


		add(invoiceidLabel);
		add(goodsidLabel);
		add(quantityLabel);

		invoiceidLabel.setBounds(20, 300, 100, 50);
		goodsidLabel.setBounds(20, 350, 100, 50);
		quantityLabel.setBounds(20, 400, 100, 50);

		invoiceidTxt = new JTextField(10);
		goodsidTxt = new JTextField(10);
		quantityTxt = new JTextField(10);

		pane.add(invoiceidTxt);
		pane.add(goodsidTxt);
		pane.add(quantityTxt);

		invoiceidTxt.setBounds(100, 310, 100, 30);
		goodsidTxt.setBounds(100, 360, 100, 30);
		quantityTxt.setBounds(100, 410, 100, 30);

		JScrollPane pane1 = new JScrollPane(table);
		pane1.setBounds(0, 0, 800, 150);
		setLayout(null);
		add(pane1);
		
		
		
		CancelBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Connection connect = null;
				Statement s = null;
				try {
					Class.forName("com.mysql.jdbc.Driver");
					connect = DriverManager.getConnection("jdbc:mysql://localhost/project", "root", "");

					String sql = "UPDATE invoice_details SET  Status=0 WHERE Invoice_ID ="+ invoiceidTxt.getText();
					s = connect.createStatement();
					s.execute(sql);
					

						String sql1 = "UPDATE stock SET Total=Total-"+quantityTxt.getText()+" WHERE Goods_ID="+ goodsidTxt.getText();
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
	}

	public void Show(int i, DefaultTableModel model) {
		invoiceidTxt.setText(model.getValueAt(i, 0).toString());
		goodsidTxt.setText(model.getValueAt(i, 4).toString());
		quantityTxt.setText(model.getValueAt(i, 6).toString());
		
	}
		

	

	public void realtime(DefaultTableModel model) {

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
					+ "INNER JOIN supplier ON invoice.Supplier_ID = supplier.ID\r\n" + "WHERE invoice_details.Status=1";

			s = connect.createStatement();
			ResultSet rec = s.executeQuery(sql);
			System.out.println(sql);
			int row = 0;
			while ((rec != null) && (rec.next())) { 
				model.addRow(new Object[0]);
				model.setValueAt(rec.getInt("ID"), row, 0);
				model.setValueAt(rec.getString("Date"), row, 1);
				model.setValueAt(rec.getInt("sid"), row, 2);
				model.setValueAt(rec.getString("sname"), row, 3);
				model.setValueAt(rec.getInt("gid"), row, 4);
				model.setValueAt(rec.getString("gname"), row, 5);
				model.setValueAt(rec.getInt("Quantity"), row, 6);
				model.setValueAt(rec.getDouble("Amount"), row, 7);
				model.setValueAt(rec.getInt("Status"), row, 8);
				row++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			
			
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
}