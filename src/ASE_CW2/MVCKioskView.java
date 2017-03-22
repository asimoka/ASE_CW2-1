package ASE_CW2;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MVCKioskView extends JFrame implements Observer, ActionListener{
	
	//instance variables
	private Thread workerThread1, workerThread2, workerThread3, workerThread4;
	private Worker worker1, worker2, worker3, worker4;
	private ListsManager listMan;
	private Kiosk kiosk;
	
	private String output1, output2, output3, output4;
	private JTextArea area1, area2, area3, area4, areaPassengers, areaTaxis;
	private JRadioButton fast, medium, slow;
	//colours
	Color bgC = new Color(218,251,255);
	Color stopC = new Color(255,87,98);
	Color goC = new Color(0,203,235);
	Color btnC = new Color(164,244,255);
	
	private JButton go, pause1, pause2, pause3, pause4, stop, resetPass, resetTaxi;
	
	public MVCKioskView() {
		//reads passenger groups and taxi details from the GUI
		listMan = new ListsManager();
		//shared object contains both queues
    	kiosk = new Kiosk(listMan.getTaxiList(),listMan.getPassengerGroupList());
    	//consumer objects, two worker threads
    	worker1 = new Worker(kiosk);
    	worker2 = new Worker(kiosk);
    	worker3 = new Worker(kiosk);
    	worker4 = new Worker(kiosk);
    	//register workers as subjects to be observed by GUI
		worker1.registerObserver(this);
		worker2.registerObserver(this);
		worker3.registerObserver(this);
		worker4.registerObserver(this);
		//GUI details
		setSize(480,700);
		setMinimumSize(new Dimension(480, 650));
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
		JPanel outerPanel = new JPanel(new GridLayout(0,1));
		JPanel nPanel = new JPanel(new FlowLayout());
		nPanel.setBackground(bgC);
		
		go = new JButton("BEGIN SIMULATION");
		go.setBackground(goC);
        go.addActionListener(this);
        nPanel.add(go);
        
        stop = new JButton("STOP");
        stop.setBackground(stopC);
        stop.addActionListener(this);
        nPanel.add(stop);
        
        JPanel addPanel = new JPanel(new FlowLayout());
		addPanel.setBackground(bgC);
        
        resetPass = new JButton("ADD PASSENGERS");
        resetPass.addActionListener(this);
        resetPass.setBackground(btnC);
        addPanel.add(resetPass);

        resetTaxi = new JButton("ADD TAXIS");
        resetTaxi.addActionListener(this);
        resetTaxi.setBackground(btnC);
        addPanel.add(resetTaxi);
        
        JPanel radioPanel = new JPanel(new FlowLayout());
		radioPanel.setBackground(bgC);
        
		fast = new JRadioButton("fast");
		fast.setBackground(bgC);
		medium = new JRadioButton("med");
		medium.setBackground(bgC);
		slow = new JRadioButton("slow");
		slow.setBackground(bgC);
		
		ButtonGroup group = new ButtonGroup();
		group.add(fast);
		group.add(medium);
		group.add(slow);
		
		radioPanel.add(fast);
		radioPanel.add(medium);
		radioPanel.add(slow);
		
		outerPanel.add(nPanel);
		outerPanel.add(addPanel);
		outerPanel.add(radioPanel);
        this.add(outerPanel, BorderLayout.NORTH);
	}

	public void setupPanelWest(){
		JPanel nPanel = new JPanel(new GridLayout(0,1));
		nPanel.setBorder(new EmptyBorder(15,15,15,15));
		nPanel.setBackground(bgC);
		nPanel.setSize(250,50);
		
		
		JLabel label1 = new JLabel("Window 1");
		nPanel.add(label1);
		
        pause1 = new JButton("PAUSE");
        pause1.setBackground(btnC);
        pause1.addActionListener(this);
        nPanel.add(pause1);
		
		area1 = new JTextArea(" ");
		area1.setSize(200,80);
		area1.setEditable(false);
		area1.setLineWrap(true);
        area1.setWrapStyleWord(true);
        
		nPanel.add(area1);
		
		
		JLabel label3 = new JLabel("Window 3");
		nPanel.add(label3);
		
        pause3 = new JButton("PAUSE");
        pause3.setBackground(btnC);
        pause3.addActionListener(this);
        nPanel.add(pause3);
		
		area3 = new JTextArea(" ");
		area3.setSize(200,80);
		area3.setEditable(false);
		area3.setLineWrap(true);
        area3.setWrapStyleWord(true);
		nPanel.add(area3);

		this.add(nPanel, BorderLayout.WEST);
	}
	
	public void setupPanelEast(){

		JPanel nPanel = new JPanel(new GridLayout(0,1));
		nPanel.setBorder(new EmptyBorder(15,15,15,15));
		nPanel.setBackground(bgC);
		nPanel.setSize(250,50);
		
		JLabel label2 = new JLabel("Window 2");
		nPanel.add(label2);

        pause2 = new JButton("PAUSE");
        pause2.setBackground(btnC);
        pause2.addActionListener(this);
        nPanel.add(pause2);
		
		area2 = new JTextArea(" ");
		area2.setSize(200,80);
		area2.setEditable(false);
		area2.setLineWrap(true);
        area2.setWrapStyleWord(true);
    
		nPanel.add(area2);
		
		
		JLabel label4 = new JLabel("Window 4");
		nPanel.add(label4);
		
        pause4 = new JButton("PAUSE");
        pause4.setBackground(btnC);
        pause4.addActionListener(this);
        nPanel.add(pause4);
		
		area4 = new JTextArea(" ");
		area4.setSize(200,80);
		area4.setEditable(false);
		area4.setLineWrap(true);
        area4.setWrapStyleWord(true);
		nPanel.add(area4);
		
		this.add(nPanel, BorderLayout.EAST);
	}
	
	public void setupPanelCenter() {
		JPanel nPanel = new JPanel();
		nPanel.setBackground(bgC);
		
		this.add(nPanel, BorderLayout.CENTER);
	}
	
	public void setupPanelSouth() {

		JPanel nPanel = new JPanel();
		nPanel.setBackground(bgC);
		
		areaPassengers = new JTextArea(worker1.getPassengerQueue());
		areaPassengers.setEditable(false);

        JScrollPane scroll1 = new JScrollPane(areaPassengers);
        scroll1.setPreferredSize(new Dimension(180,240)); 
        scroll1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        nPanel.add(scroll1);
		
		areaTaxis = new JTextArea(worker1.getTaxiQueue());
		areaTaxis.setEditable(false);

        JScrollPane scroll2 = new JScrollPane(areaTaxis);
        scroll2.setPreferredSize(new Dimension(180,240)); 
        scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        nPanel.add(scroll2);

		this.add(nPanel, BorderLayout.SOUTH);
		
	}
	
	public void go() {
		
		worker1.ready();
		worker2.ready();
		worker3.ready();
		worker4.ready();
    	workerThread1 = new Thread(worker1);
    	workerThread2 = new Thread(worker2);
    	workerThread3 = new Thread(worker3);
    	workerThread4 = new Thread(worker4);
    	workerThread1.start();
    	workerThread2.start();
    	workerThread3.start();
    	workerThread4.start();
		
	}
	
	public void resetPass() {
		listMan.addPassengers();
		kiosk.setPassengerGroupList(listMan.getPassengerGroupList());
		
		worker1.setKiosk(kiosk);
		worker2.setKiosk(kiosk);
		worker3.setKiosk(kiosk);
		worker4.setKiosk(kiosk);
		
		areaPassengers.setText(worker1.getPassengerQueue());
	}
	
	public void resetTaxi() {
		listMan.addTaxis();
		kiosk.setTaxiList(listMan.getTaxiList());
		
		worker1.setKiosk(kiosk);
		worker2.setKiosk(kiosk);
		worker3.setKiosk(kiosk);
		worker4.setKiosk(kiosk);
		
		areaTaxis.setText(worker1.getTaxiQueue());
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
		else if (e.getSource() == pause3) {
			worker3.pause();
			if (pause3.getText() == "PAUSE") {
				pause3.setText("UNPAUSE");
			}
			else {
				pause3.setText("PAUSE");
			}
			
		}
		else if (e.getSource() == pause4) {
			worker2.pause();
			if (pause4.getText() == "PAUSE") {
				pause4.setText("UNPAUSE");
			}
			else {
				pause4.setText("PAUSE");
			}
		}
		else if (e.getSource() == stop) {
			worker1.stopEarly();
			worker2.stopEarly();
			worker3.stopEarly();
			worker4.stopEarly();
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
		if (output1 != area1.getText()) {
			//////
		}
		area1.setText(output1);
		
		output2 = worker2.getStatus();
		if (output2 != area2.getText()) {
			//////
		}
		area2.setText(output2);

		output3 = worker3.getStatus();
		if (output3 != area3.getText()) {
			//////
		}
		area3.setText(output3);
		
		output4 = worker4.getStatus();
		if (output4 != area4.getText()) {
			//////
		}
		area4.setText(output4);
		
		areaPassengers.setText(worker1.getPassengerQueue());
		areaTaxis.setText(worker1.getTaxiQueue());
		
		if (worker1.getKiosk().isFinished()) {
			stop.setEnabled(false);
			go.setEnabled(true);
		}
		else {
			go.setEnabled(false);
			stop.setEnabled(true);
		}
		
	}
	

}
