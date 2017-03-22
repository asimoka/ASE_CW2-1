package ASE_CW2;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MVCKioskView extends JFrame implements Observer, ActionListener{
	
	//instance variables
	private Thread workerThread1;
	private Thread workerThread2;
	private Worker worker1, worker2;
	private ListsManager listMan;
	private Kiosk kiosk;
	
	private JLabel passLabel, taxiLabel; 
	private String output1, output2;
	private JTextArea area1, area2, area3, area4;
	Color bg = new Color(188,238,255);
	
	private JButton go, pause1, pause2, stop, resetPass, resetTaxi;
	
	public MVCKioskView() {
		//reads passenger groups and taxi details from the GUI
		listMan = new ListsManager();
		//shared object contains both queues
    	kiosk = new Kiosk(listMan.getTaxiList(),listMan.getPassengerGroupList());
    	//consumer objects, two worker threads
    	worker1 = new Worker(kiosk);
    	worker2 = new Worker(kiosk);
    	//register workers as subjects to be observed by GUI
		worker1.registerObserver(this);
		worker2.registerObserver(this);
		//GUI details
		setSize(500,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Tax Kiosk");
		//setup 4 border panels
		setLayout(new BorderLayout());
		setupPanelNorth();
		setupPanelEast();
		setupPanelWest();
		setupPanelSouth();
		setupPanelCenter();
		setVisible(true);
	}
	
	//North panel contains buttons to start the simulation, stop it and reset
	public void setupPanelNorth() {
		JPanel nPanel = new JPanel(new FlowLayout());
		
		go = new JButton("BEGIN SIMULATION");
		//Color goColor = new Color(0,188,255);
		//go.setBackground(goColor);
        go.addActionListener(this);
        nPanel.add(go);
        
        stop = new JButton("STOP");
        //stop.setBackground(Color.red);
        stop.addActionListener(this);
        nPanel.add(stop);
        
        resetPass = new JButton("MORE PASSENGERS");
        resetPass.addActionListener(this);
        //resetPass.setBackground(bg);
        nPanel.add(resetPass);

        resetTaxi = new JButton("MORE TAXIS");
        resetTaxi.addActionListener(this);
        //resetTaxi.setBackground(bg);
        nPanel.add(resetTaxi);
        
        this.add(nPanel, BorderLayout.NORTH);
	}

	public void setupPanelWest(){
		JPanel nPanel = new JPanel(new GridLayout(0,1));
		nPanel.setBorder(new EmptyBorder(15,15,15,15));
		nPanel.setBackground(bg);
		nPanel.setSize(250,50);
		
		JLabel label1 = new JLabel("Window 1");
		nPanel.add(label1);
		
        pause1 = new JButton("PAUSE");
        pause1.addActionListener(this);
        nPanel.add(pause1);
		
		area1 = new JTextArea(" ");
		area1.setSize(200,80);
		area1.setEditable(false);
		area1.setLineWrap(true);
        area1.setWrapStyleWord(true);
        
		nPanel.add(area1);

		this.add(nPanel, BorderLayout.WEST);
	}
	
	public void setupPanelEast(){

		JPanel nPanel = new JPanel(new GridLayout(0,1));
		nPanel.setBorder(new EmptyBorder(15,15,15,15));
		nPanel.setBackground(bg);
		nPanel.setSize(250,50);
		
		JLabel label2 = new JLabel("Window 2");
		nPanel.add(label2);

        pause2 = new JButton("PAUSE");
        pause2.addActionListener(this);
        nPanel.add(pause2);
		
		area2 = new JTextArea(" ");
		area2.setSize(200,80);
		area2.setEditable(false);
		area2.setLineWrap(true);
        area2.setWrapStyleWord(true);

		nPanel.add(area2);
		this.add(nPanel, BorderLayout.EAST);
	}
	
	public void setupPanelCenter() {
		JPanel nPanel = new JPanel();
		nPanel.setBackground(bg);
		this.add(nPanel, BorderLayout.CENTER);
	}
	
	public void setupPanelSouth() {

		JPanel nPanel = new JPanel();
		nPanel.setBackground(bg);
		
		area3 = new JTextArea(worker1.getPassengerQueue());
		area3.setEditable(false);

        JScrollPane scroll1 = new JScrollPane(area3);
        scroll1.setPreferredSize(new Dimension(180,240)); 
        scroll1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        nPanel.add(scroll1);
		
		area4 = new JTextArea(worker1.getTaxiQueue());
		area4.setEditable(false);

        JScrollPane scroll2 = new JScrollPane(area4);
        scroll2.setPreferredSize(new Dimension(180,240)); 
        scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        nPanel.add(scroll2);

		this.add(nPanel, BorderLayout.SOUTH);
		
	}
	
	public void go() {
		
		worker1.ready();
		worker1.ready();
    	workerThread1 = new Thread(worker1);
    	workerThread2 = new Thread(worker2);
    	workerThread1.start();
    	workerThread2.start();
		
	}
	
	public void resetPass() {
		listMan.addPassengers();
		kiosk.setPassengerGroupList(listMan.getPassengerGroupList());
		
		worker1.setKiosk(kiosk);
		worker2.setKiosk(kiosk);
		
		area3.setText(worker1.getPassengerQueue());
	}
	
	public void resetTaxi() {
		listMan.addTaxis();
		kiosk.setTaxiList(listMan.getTaxiList());
		
		worker1.setKiosk(kiosk);
		worker2.setKiosk(kiosk);
		
		area4.setText(worker1.getTaxiQueue());
	}	

	//event listeners
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == go) {
			go();
		}
		else if (e.getSource() == pause1) {
			worker1.pause();
			if (pause1.getText() == "PAUSE") {
				pause1.setText("UNPAUSE");
			}
			else {
				pause1.setText("PAUSE");
			}
			
		}
		else if (e.getSource() == pause2) {
			worker2.pause();
			if (pause2.getText() == "PAUSE") {
				pause2.setText("UNPAUSE");
			}
			else {
				pause2.setText("PAUSE");
			}
		}
		else if (e.getSource() == stop) {
			worker1.stopEarly();
			worker2.stopEarly();
		}
		else if (e.getSource() == resetPass) {
			resetPass();
		}
		else if (e.getSource() == resetTaxi) {
			resetTaxi();
		}
		
	}
	
	@Override
	public void update() {
		
		output1 = worker1.getStatus();
		area1.setText(output1);
		//JOptionPane.showMessageDialog(this, "worker 1: \n" + output1);
		//System.out.println("W1: " + output1);
		
		output2 = worker2.getStatus();
		area2.setText(output2);
		//JOptionPane.showMessageDialog(this, "worker 2: \n" + output2);
		//System.out.println("2: " + output2);
		
		//passLabel.setText("QUEUE (" + worker1.getNumPassengerGroups() + ")");
		area3.setText(worker1.getPassengerQueue());
		area4.setText(worker1.getTaxiQueue());
		//taxiLabel.setText("TAXIS (" + worker1.getNumTaxis() + ")");
		
		//pack();
		
	}
	

}
