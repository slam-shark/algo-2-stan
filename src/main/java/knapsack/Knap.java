package main.java.knapsack;


import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import main.java.stuff.MyFileChooser;

public class Knap extends JFrame {

    private static final long serialVersionUID = -4039212345879967939L;
    private JPanel contentPane;
    private JTextField txtFilePath;
    private JTextField txtResult;
    private JComboBox<Knapsack> cmbSelectType;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	System.out.println(args.length);
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    Knap frame = new Knap();
		    frame.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    /**
     * Create the frame.
     */
    public Knap() {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 450, 300);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);

	JButton btnChooseFile = new JButton("ChooseFile");
	btnChooseFile.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		JFileChooser jfc = new MyFileChooser();
		int res = jfc.showOpenDialog(null);
		if (res == JFileChooser.APPROVE_OPTION) {
		    txtFilePath.setText(jfc.getSelectedFile().getAbsolutePath());

		}
	    }
	});
	contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	contentPane.add(btnChooseFile);

	txtFilePath = new JTextField();
	contentPane.add(txtFilePath);
	txtFilePath.setColumns(20);

	JButton btnStart = new JButton("GO");
	btnStart.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		File f = new File(txtFilePath.getText());
		if (f.exists()) {
		    Knapsack s = new KnapsackBnB(f.getAbsolutePath());
		    
		    txtResult.setText(""+s.getTotalResult());
		}
	    }
	});
	contentPane.add(btnStart);

	cmbSelectType = new JComboBox<Knapsack>();
	contentPane.add(cmbSelectType);
	cmbSelectType.setRenderer(new DefaultListCellRenderer() {
	    private static final long serialVersionUID = -2697428668350742048L;

	    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
		    boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		if (value != null) {
		  //  Knapsack j = (Knapsack) value;
		    setText("");
		}
		return this;
	    }
	});

	txtResult = new JTextField();
	contentPane.add(txtResult);
	txtResult.setColumns(10);
	cmbSelectType.addItem(new KnapsackBnB());

    }

}

