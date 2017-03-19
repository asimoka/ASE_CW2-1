package ASE_CW2;


import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class GUI extends JFrame implements Observer {
	
	//instance variables
	private Kiosk kiosk1, kiosk2;
	private String worker1, worker2;
	private JTextArea area1, area2;
	
	public GUI(Kiosk inKiosk1, Kiosk inKiosk2) {
		this.kiosk1 = inKiosk1;
		this.kiosk2 = inKiosk2;
		
		kiosk1.registerObserver(this);
		kiosk2.registerObserver(this);
		
		setSize(100,100);

		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setTitle("Kiosks");

		setLayout(new BorderLayout());

		setupPanelEast();
		setupPanelWest();

		pack();
		setVisible(true);
	}
	
	public void setupPanel() {
		JPanel nPanel = new JPanel(new BorderLayout(3,3));
		nPanel.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(nPanel);
		
		JPanel labels = new JPanel(new GridLayout(1,0));
		JPanel kiosks = new JPanel(new GridLayout(1,0));
		nPanel.add(labels, BorderLayout.NORTH);
		nPanel.add(labels, BorderLayout.CENTER);
		
		JLabel label1 = new JLabel("Worker 1");
		labels.add(label1);
		
		area1 = new JTextArea("hi\n \n \n \n \n");
		area1.setEditable(false);
		area1.setLineWrap(true);
        area1.setWrapStyleWord(true);
        area1.setBorder(new EmptyBorder(5,5,5,5));
        kiosks.add(label1);
        
		JLabel label2 = new JLabel("Worker 2");
		labels.add(label2);
		
		area2 = new JTextArea("hi\n \n \n \n \n");
		area2.setEditable(false);
		area2.setLineWrap(true);
        area2.setWrapStyleWord(true);
        area2.setBorder(new EmptyBorder(5,5,5,5));
		kiosks.add(area2);
	}
	
	public void setupPanelWest(){
		//Using the NORTH panel to display a message asking what the user would like to do
		JPanel nPanel = new JPanel(new GridLayout(0,1));
		nPanel.setBorder(new EmptyBorder(15,15,15,15));
		
		JLabel label1 = new JLabel("Worker 1");
		nPanel.add(label1);
		
		area1 = new JTextArea("\n \n \n \n \n");
		area1.setEditable(false);
		area1.setLineWrap(true);
        area1.setWrapStyleWord(true);
		

		nPanel.add(area1);

		this.add(nPanel, BorderLayout.WEST);
	}
	
	public void setupPanelEast(){
		//Using the NORTH panel to display a message asking what the user would like to do
		JPanel nPanel = new JPanel(new GridLayout(0,1));
		nPanel.setBorder(new EmptyBorder(15,15,15,15));
		
		JLabel label2 = new JLabel("Worker 2");
		nPanel.add(label2);
		
		area2 = new JTextArea("\n \n \n \n \n");
		area2.setEditable(false);
		area2.setLineWrap(true);
        area2.setWrapStyleWord(true);

		nPanel.add(area2);

		this.add(nPanel, BorderLayout.EAST);
	}

	@Override
	public void update() {
		
		worker1 = kiosk1.getStatus();
		worker2 = kiosk2.getStatus();
		
		area1.setText(worker1);
		//JOptionPane.showMessageDialog(this, "worker 1: \n" + worker1);
		//System.out.println("Would have said: " + worker1);
		area2.setText(worker2);
		//JOptionPane.showMessageDialog(this, "worker 2: \n" + worker2);
		//System.out.println("Would have said: " + worker2);
		
	}




}
