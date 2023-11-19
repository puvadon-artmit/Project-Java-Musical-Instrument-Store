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

public class SupplierGood extends JDialog implements ActionListener {

	private static final int width = 1000;
	private static final int height = 700;

	JTextField suppliergoodsidTxt;
	JComboBox<String> supplieridTxt;
	JTextField suppliernameTxt;
	JComboBox<String> goodidTxt;
	JTextField goodnameTxt;

	public SupplierGood(JFrame frame) {
		super(frame, true);
		Container pane = getContentPane();
		pane.setLayout(null);
		pane.setPreferredSize(new Dimension(width, height));
		pack();
		setVisible(false);
		System.out.println("DialogAddstudent() done!");

		JTable table = new JTable();
		Object[] columns = { " 	Supplier GoodsId ", "Supplier Id", "Supplier Name", "Good Id", "Good Name" };
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		table.setModel(model);

		realtime(model);

		Connection connect = null;
		Statement s = null;

		JButton addBtn = new JButton("Add");
		JButton editBtn = new JButton("Edit");
		JButton deleteBtn = new JButton("Delete");
		JButton clearBtn = new JButton("Clear");

		addBtn.addActionListener(this);
		editBtn.addActionListener(this);
		deleteBtn.addActionListener(this);
		clearBtn.addActionListener(this);

		add(addBtn);
		add(editBtn);
		add(deleteBtn);
		add(clearBtn);

		addBtn.setBounds(700, 100, 100, 35);
		editBtn.setBounds(700, 150, 100, 35);
		deleteBtn.setBounds(700, 200, 100, 35);
		clearBtn.setBounds(700, 250, 100, 35);

		JLabel suppliergoodsidLabel = new JLabel("Suppliergoods Id");
		JLabel supplieridLabel = new JLabel("Supplier Id");
		JLabel goodidLabel = new JLabel("Goods Id");

		add(suppliergoodsidLabel);
		add(supplieridLabel);
		add(goodidLabel);

		suppliergoodsidLabel.setBounds(20, 300, 100, 50);
		supplieridLabel.setBounds(20, 350, 100, 50);
		goodidLabel.setBounds(20, 400, 100, 50);

		suppliergoodsidTxt = new JTextField(10);
		supplieridTxt = new JComboBox<String>();
		suppliernameTxt = new JTextField(20);
		goodidTxt = new JComboBox<String>();
		goodnameTxt = new JTextField(5);

		pane.add(suppliergoodsidTxt);
		pane.add(supplieridTxt);
		pane.add(suppliernameTxt);
		pane.add(goodidTxt);
		pane.add(goodnameTxt);

		suppliergoodsidTxt.setBounds(150, 310, 100, 30);
		supplieridTxt.setBounds(150, 360, 100, 30);
		goodidTxt.setBounds(150, 410, 100, 30);

		suppliergoodsidTxt.setEnabled(false);

		JScrollPane pane1 = new JScrollPane(table);
		pane1.setBounds(0, 0, 500, 150);
		setLayout(null);
		add(pane1);

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
					// goodnameTxt.setText(r.getString("Name"));
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

					String sql = "INSERT INTO supplier_goods(Supplier_ID,Goods_ID) VALUES("
							+ supplieridTxt.getSelectedItem() + "," + goodidTxt.getSelectedItem() + ")";
					s = connect.createStatement();
					s.execute(sql);

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

					String sql = "UPDATE supplier_goods SET supplier_id=" + supplieridTxt.getSelectedItem() + ""
							+ ", Goods_id=" + goodidTxt.getSelectedItem() + "" + " WHERE ID="
							+ suppliergoodsidTxt.getText();
					s = connect.createStatement();
					s.execute(sql);

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

					String sql = "DELETE FROM supplier_goods WHERE ID =" + suppliergoodsidTxt.getText();
					s = connect.createStatement();
					s.execute(sql);

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

		clearBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				suppliergoodsidTxt.setText(null);
				suppliernameTxt.setText(null);
				goodnameTxt.setText(null);
			}

		});
	}

	public void Show(int i, DefaultTableModel model) {
		suppliergoodsidTxt.setText(model.getValueAt(i, 0).toString());
		// supplieridTxt.setToolTipText(model.getValueAt(i, 1).toString());
		suppliernameTxt.setText(model.getValueAt(i, 2).toString());
		// goodidTxt.setToolTipText(model.getValueAt(i, 3).toString());
		goodnameTxt.setText(model.getValueAt(i, 3).toString());

	}

	public void realtime(DefaultTableModel model) {

		Connection connect = null;
		Statement s = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost/project", "root", "");

			String sql = "SELECT supplier_goods.ID,supplier.ID AS sid,supplier.Name AS sname,goods.ID AS gid,goods.Name AS gname\r\n"
					+ "FROM supplier_goods\r\n" + "INNER JOIN goods ON supplier_goods.Goods_ID=goods.ID\r\n"
					+ "INNER JOIN  supplier ON supplier_goods.Supplier_ID=supplier.ID";

			s = connect.createStatement();
			ResultSet rec = s.executeQuery(sql);
			System.out.println(sql);
			int row = 0;
			while ((rec != null) && (rec.next())) {
				model.addRow(new Object[0]);
				model.setValueAt(rec.getInt("ID"), row, 0);
				model.setValueAt(rec.getInt("sid"), row, 1);
				model.setValueAt(rec.getString("sname"), row, 2);
				model.setValueAt(rec.getInt("gid"), row, 3);
				model.setValueAt(rec.getString("gname"), row, 4);
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