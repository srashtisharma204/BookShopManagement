package swingex;
import java.sql.*;
import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import java.awt.Label;
import java.awt.Font;
import java.awt.Panel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class Jframeex {

	private JFrame frame;
	private JTextField txtbname;
	private JTable table;
	private JTable table_1;
	private JTextField txtedition;
	private JTextField txtprice;
	private JTextField txtbid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Jframeex window = new Jframeex();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Jframeex() {
		initialize();
		Connect();
		table_load();
	}
	Connection con;
	PreparedStatement  pst;
	ResultSet rs;
	public void Connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 con=DriverManager.getConnection("jdbc:mysql://localhost:3307/javacrud", "root", "");
//			 Statement st=con.createStatement();
//			 String query="select * from book";
//			 ResultSet rs=st.executeQuery(query);
//			 ResultSetMetaData rsmd=rs.getMetaData();
			 
		}
		
		
		catch(ClassNotFoundException ex) {
			
		}
		catch(SQLException e3) {
			
		}
		
		
	}
	
	public void table_load() {
		try {
			pst=con.prepareStatement("Select * from book");
			rs=pst.executeQuery();
			
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
//		catch(NullPointerException e) {
//			
//		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 788, 511);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Label label = new Label("Book Shop");
		label.setBounds(264, 6, 170, 44);
		label.setFont(new Font("Tahoma", Font.BOLD, 30));
		frame.getContentPane().add(label);
		
		JPanel panel = new JPanel();
		panel.setBounds(22, 78, 331, 209);
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(22, 48, 92, 24);
		panel.add(lblNewLabel);
		
		JLabel lblEdition = new JLabel("Edition");
		lblEdition.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEdition.setBounds(22, 91, 92, 24);
		panel.add(lblEdition);
		
		JLabel lblNewLabel_1_1 = new JLabel("Price");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(22, 137, 92, 24);
		panel.add(lblNewLabel_1_1);
		
		txtbname = new JTextField();
		txtbname.setBounds(146, 52, 109, 20);
		panel.add(txtbname);
		txtbname.setColumns(10);
		
		txtedition = new JTextField();
		txtedition.setColumns(10);
		txtedition.setBounds(146, 95, 109, 20);
		panel.add(txtedition);
		
		txtprice = new JTextField();
		txtprice.setColumns(10);
		txtprice.setBounds(146, 141, 109, 20);
		panel.add(txtprice);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bname,edition,price;
				bname=txtbname.getText();
				edition=txtedition.getText();
				price=txtprice.getText();
				try {
					pst=con.prepareStatement("insert into book(name,edition,price)values(?,?,?)");
					pst.setString(1,bname);
					pst.setString(2,edition);
					pst.setString(3,price);
					pst.executeUpdate();
					  JOptionPane.showMessageDialog(null, "Record Added!!!");
					 
					txtbname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txtbname.requestFocus();
					
					
				}
//				catch(NullPointerException e2) {
//					
//				}
				catch(SQLException e1) {
					e1.printStackTrace();
				}
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(32, 298, 96, 52);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnExit.setBounds(147, 298, 96, 52);
		frame.getContentPane().add(btnExit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnClear.setBounds(257, 298, 96, 52);
		frame.getContentPane().add(btnClear);
		
		table = new JTable();
		table.setBounds(362, 344, 377, -255);
		frame.getContentPane().add(table);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(362, 82, 385, 268);
		frame.getContentPane().add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(22, 361, 331, 70);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblBookId = new JLabel("Book ID");
		lblBookId.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBookId.setBounds(22, 24, 92, 24);
		panel_1.add(lblBookId);
		
		txtbid = new JTextField();
		txtbid.setColumns(10);
		txtbid.setBounds(96, 28, 159, 20);
		panel_1.add(txtbid);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnUpdate.setBounds(432, 370, 96, 52);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnDelete.setBounds(551, 370, 96, 52);
		frame.getContentPane().add(btnDelete);
	}
}
