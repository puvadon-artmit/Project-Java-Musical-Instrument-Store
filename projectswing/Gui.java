package Project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Gui extends JPanel implements ActionListener {
	
	private static final String mainString = "Main Menu";
	private static final String customerString = "Customer";
	private static final String goodString = "Good";
	private static final String supplierString = "Supplier";
	private static final String suppliergoodString = "Supplier Good";
	private static final String invoiceString = "Invoice";
	private static final String receiptString = "Receipt";
	private static final String exitString = "Exit";

	private static final int frameWidth = 800;
	private static final int frameHeight = 500;

	Customer DialogCustomer;
	Good DialogGood;
	Supplier DialogSupplier;
	SupplierGood DialogSupplierGood;
	Invoice DialogInvoice;
	Receipt DialogReceipt;

	public Gui(JFrame frame) {

		// use super class constructor
		super();

		// create a dialog for adding student
		DialogCustomer = new Customer(frame);
		DialogGood = new Good(frame);
		DialogSupplier = new Supplier(frame);
		DialogSupplierGood = new SupplierGood(frame);
		DialogInvoice = new Invoice(frame);
		DialogReceipt = new Receipt(frame);

		// use manual layout manager
		setLayout(null);

	
		JButton customerBtn = new JButton(customerString);
		JButton goodBtn = new JButton(goodString);
		JButton supplierBtn = new JButton(supplierString);
		JButton suppliergoodBtn = new JButton(suppliergoodString);
		JButton invoiceBtn = new JButton(invoiceString);
		JButton receiptBtn = new JButton(receiptString);
		JButton exitBtn = new JButton(exitString);

		// add listener to buttons
		customerBtn.addActionListener(this);
		goodBtn.addActionListener(this);
		supplierBtn.addActionListener(this);
		suppliergoodBtn.addActionListener(this);
		invoiceBtn.addActionListener(this);
		receiptBtn.addActionListener(this);
		exitBtn.addActionListener(this);
		
		JLabel mainLabel = new JLabel("Main Menu");

		// add label and buttons to main frame, i.e. GuiStudent
		add(mainLabel);
		add(customerBtn);
		add(goodBtn);
		add(supplierBtn);
		add(suppliergoodBtn);
		add(invoiceBtn);
		add(receiptBtn);
		add(exitBtn);

		// set sizes and positions for label and buttons
		Dimension size = mainLabel.getPreferredSize();
		mainLabel.setBounds((frameWidth - size.width) / 2, 40, size.width, size.height);
		size = customerBtn.getPreferredSize();
		customerBtn.setBounds((frameWidth - size.width) / 2, 80, size.width, size.height);
		goodBtn.setBounds((frameWidth - size.width) / 2, 120, size.width, size.height);
		size = supplierBtn.getPreferredSize();
		supplierBtn.setBounds((frameWidth - size.width) / 2, 160, size.width, size.height);
		size = suppliergoodBtn.getPreferredSize();
		suppliergoodBtn.setBounds((frameWidth - size.width) / 2, 200, size.width, size.height);
		size = invoiceBtn.getPreferredSize();
		invoiceBtn.setBounds((frameWidth - size.width) / 2, 240, size.width, size.height);
		size = receiptBtn.getPreferredSize();
		receiptBtn.setBounds((frameWidth - size.width) / 2, 280, size.width, size.height);
		size = exitBtn.getPreferredSize();
		exitBtn.setBounds((frameWidth - size.width) / 2, 320, size.width, size.height);

		// set size for main frame
		this.setPreferredSize(new Dimension(frameWidth, frameHeight));
	}

	public void actionPerformed(ActionEvent evt) {
		String actionCommand = evt.getActionCommand();
		System.out.println("ActionCommand:" + actionCommand);
		if (actionCommand.equals(customerString)) {
			System.out.println("equals " + customerString);
			DialogCustomer.setVisible(true);
		} else if (actionCommand.equals(goodString)) {
			System.out.println("equals " + goodString);
			DialogGood.setVisible(true);
		} else if (actionCommand.equals(supplierString)) {
			System.out.println("equals " + supplierString);
			DialogSupplier.setVisible(true);
		} else if (actionCommand.equals(suppliergoodString)) {
			System.out.println("equals " + suppliergoodString);
			DialogSupplierGood.setVisible(true);
		} else if (actionCommand.equals(invoiceString)) {
			System.out.println("equals " + invoiceString);
			DialogInvoice.setVisible(true);
		} else if (actionCommand.equals(receiptString)) {
			System.out.println("equals " + receiptString);
			DialogReceipt.setVisible(true);
		} else if (actionCommand.equals(exitString)) {
			System.out.println("equals " + exitString);
			System.exit(0);
		}
	}
	/**
	 * Create the GUI and show it. For thread safety, this method should be invoked
	 * from the event dispatch thread.
	 */
	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("GUI Mini Scm");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add contents to the window.
		frame.add(new Gui(frame));

		// Display the window.
		// frame.setPreferredSize(new Dimension(frameWidth, frameHeight));
		// frame.setSize(frameWidth, frameHeight);

		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// Schedule a job for the event dispatch thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
