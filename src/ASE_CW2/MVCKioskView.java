package ASE_CW2;
/*
 * View Class:
 * GUI containing 4 worker windows with apuse buttons, 2 windows containing 
 * customer queue and taxi queue and buttons to start simulation, emergency
 * stop, add more passengers and add more taxis.
 * Observes the worker classes and reacts when a worker creates a match.
 * 
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MVCKioskView extends JFrame implements Observer, ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//instance variables for 4 workers, their threads, the list manager and kiosk
	private Worker worker1, worker2, worker3, worker4;
	private Thread workerThread1, workerThread2, workerThread3, workerThread4;
	private ListsManager listMan;
	private Kiosk kiosk;
	//instance variables for display text, textboxes, buttons and radiobuttons
	private String output1, output2, output3, output4;
	private JTextArea area1, area2, area3, area4, areaPassengers, areaTaxis;
	private JRadioButton fast, medium, slow;
	private JButton go, pause1, pause2, pause3, pause4, stop, addPass, addTaxi;
	//colours
	private Color bgC = new Color(218,251,255);
	private Color stopC = new Color(255,87,98);
	private Color goC = new Color(0,203,235);
	private Color btnC = new Color(164,244,255);
	
	//GUI constructor
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
		setMinimumSize(new Dimension(460, 650));
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
		//outer panel contains 3 inner panels on separate lines
		JPanel outerPanel = new JPanel(new GridLayout(0,1));
		
		//panel containing start and stop buttons
		JPanel nPanel = new JPanel(new FlowLayout());
		nPanel.setBackground(bgC);
		//button to start the simulation and action listener
		go = new JButton("BEGIN SIMULATION");
		go.setBackground(goC);
        go.addActionListener(this);
        nPanel.add(go);
        
        //button for emergency stop and action listener
        stop = new JButton("STOP");
        stop.setBackground(stopC);
        stop.addActionListener(this);
        nPanel.add(stop);
        
        //panel containing buttons to add more data to simulation
        JPanel addPanel = new JPanel(new FlowLayout());
		addPanel.setBackground(bgC);
        //button to add new passengers and action listener
        addPass = new JButton("ADD PASSENGERS");
        addPass.addActionListener(this);
        addPass.setBackground(btnC);
        addPanel.add(addPass);
        //button to add new taxis and action listener
        addTaxi = new JButton("ADD TAXIS");
        addTaxi.addActionListener(this);
        addTaxi.setBackground(btnC);
        addPanel.add(addTaxi);
        
        //panel containing the radio buttons which set the speed
        JPanel radioPanel = new JPanel(new FlowLayout());
		radioPanel.setBackground(bgC);
        //fast, medium and slow radio buttons and action listeners
		fast = new JRadioButton("fast");
		fast.setBackground(bgC);
		fast.addActionListener(this);
		medium = new JRadioButton("med");
		medium.setBackground(bgC);
		medium.addActionListener(this);
		slow = new JRadioButton("slow");
		slow.setBackground(bgC);
		slow.addActionListener(this);
		
		//group the radio buttons together
		ButtonGroup group = new ButtonGroup();
		group.add(fast);
		group.add(medium);
		group.add(slow);
		//add them tot he panel
		radioPanel.add(fast);
		radioPanel.add(medium);
		radioPanel.add(slow);
		
		//add the three panels to the outer panel and add to the main frame
		outerPanel.add(nPanel);
		outerPanel.add(addPanel);
		outerPanel.add(radioPanel);
        this.add(outerPanel, BorderLayout.NORTH);
	}

	//West panel contains 2 of the workers
	public void setupPanelWest(){
		//set panel format, size and colour
		JPanel nPanel = new JPanel(new GridLayout(0,1));
		nPanel.setBorder(new EmptyBorder(15,15,15,15));
		nPanel.setBackground(bgC);
		nPanel.setSize(250,50);
		
		//label worker 1
		JLabel label1 = new JLabel("Window 1");
		nPanel.add(label1);
		//pause button for worker 1
        pause1 = new JButton("PAUSE");
        pause1.setBackground(btnC);
        pause1.addActionListener(this);
        nPanel.add(pause1);
		//text area showing worker 1's activity
		area1 = new JTextArea(" ");
		area1.setSize(200,80);
		area1.setEditable(false);
		area1.setLineWrap(true);
        area1.setWrapStyleWord(true);
        
		nPanel.add(area1);
		
		//label worker 3
		JLabel label3 = new JLabel("Window 3");
		nPanel.add(label3);
		//pause button for worker 3
        pause3 = new JButton("PAUSE");
        pause3.setBackground(btnC);
        pause3.addActionListener(this);
        nPanel.add(pause3);
        //text area showing worker 3's activity
		area3 = new JTextArea(" ");
		area3.setSize(200,80);
		area3.setEditable(false);
		area3.setLineWrap(true);
        area3.setWrapStyleWord(true);
		nPanel.add(area3);

		this.add(nPanel, BorderLayout.WEST);
	}
	
	//East panel contains 2 of the workers
	public void setupPanelEast(){
		//set panel format, size and colour
		JPanel nPanel = new JPanel(new GridLayout(0,1));
		nPanel.setBorder(new EmptyBorder(15,15,15,15));
		nPanel.setBackground(bgC);
		nPanel.setSize(250,50);
		
		//label worker 2
		JLabel label2 = new JLabel("Window 2");
		nPanel.add(label2);
		//pause button for worker 2
        pause2 = new JButton("PAUSE");
        pause2.setBackground(btnC);
        pause2.addActionListener(this);
        nPanel.add(pause2);
        //text area showing worker 2's activity
		area2 = new JTextArea(" ");
		area2.setSize(200,80);
		area2.setEditable(false);
		area2.setLineWrap(true);
        area2.setWrapStyleWord(true);
		nPanel.add(area2);
		
		//label worker 4
		JLabel label4 = new JLabel("Window 4");
		nPanel.add(label4);
		//pause button for worker 4
        pause4 = new JButton("PAUSE");
        pause4.setBackground(btnC);
        pause4.addActionListener(this);
        nPanel.add(pause4);
        //text area showing worker 4's activity
		area4 = new JTextArea(" ");
		area4.setSize(200,80);
		area4.setEditable(false);
		area4.setLineWrap(true);
        area4.setWrapStyleWord(true);
		nPanel.add(area4);
		
		this.add(nPanel, BorderLayout.EAST);
	}
	
	//empty center panel to separate workers
	public void setupPanelCenter() {
		JPanel nPanel = new JPanel();
		nPanel.setBackground(bgC);
		this.add(nPanel, BorderLayout.CENTER);
	}
	
	//South panel contains the passenger queue and the available taxis
	public void setupPanelSouth() {
		//set up panel colour
		JPanel nPanel = new JPanel();
		nPanel.setBackground(bgC);
		
		//textbox containing passenger queue
		areaPassengers = new JTextArea(worker1.getPassengerQueue());
		areaPassengers.setEditable(false);
		//scrollbox for passenger queue
        JScrollPane scroll1 = new JScrollPane(areaPassengers);
        scroll1.setPreferredSize(new Dimension(180,240)); 
        scroll1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        nPanel.add(scroll1);
        
        //textbox containing taxi queue
		areaTaxis = new JTextArea(worker1.getTaxiQueue());
		areaTaxis.setEditable(false);
		//scrollbox for taxi queue
        JScrollPane scroll2 = new JScrollPane(areaTaxis);
        scroll2.setPreferredSize(new Dimension(180,240)); 
        scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        nPanel.add(scroll2);

		this.add(nPanel, BorderLayout.SOUTH);
		
	}
	
	//method which restarts threads of execution after a stop
	public void go() {
		//set the workers to "not finished"
		worker1.ready();
		worker2.ready();
		worker3.ready();
		worker4.ready();
		
		//new thread for each worker
    	workerThread1 = new Thread(worker1);
    	workerThread2 = new Thread(worker2);
    	workerThread3 = new Thread(worker3);
    	workerThread4 = new Thread(worker4);
    	
    	//start all 4 threads
    	workerThread1.start();
    	workerThread2.start();
    	workerThread3.start();
    	workerThread4.start();
	}
	
	//method to add a new batch of passenger groups
	public void addPass() {
		//read passenger group list from the file manager and add to kiosk
		listMan.addPassengers();
		kiosk.setPassengerGroupList(listMan.getPassengerGroupList());
		areaPassengers.setText(worker1.getPassengerQueue());
	}
	
	//method to add a new batch of taxis
	public void addTaxi() {
		//read taxi list from the file manager and add to kiosk
		listMan.addTaxis();
		kiosk.setTaxiList(listMan.getTaxiList());
		//update text on display
		areaTaxis.setText(worker1.getTaxiQueue());
	}	

	//event listeners
	@Override
	public void actionPerformed(ActionEvent e) {
		//if user presses start, begin simulation
		if (e.getSource() == go) {
			go();
		}
		
		//if user presses pause1, pause worker 1
		else if (e.getSource() == pause1) {
			worker1.pause();
			if (pause1.getText() == "PAUSE") {
				pause1.setText("UNPAUSE");
			}
			else {
				pause1.setText("PAUSE");
			}
		}
		
		//if user presses pause2, pause worker 2
		else if (e.getSource() == pause2) {
			worker2.pause();
			if (pause2.getText() == "PAUSE") {
				pause2.setText("UNPAUSE");
			}
			else {
				pause2.setText("PAUSE");
			}
		}
		
		//if user presses pause3, pause worker 3
		else if (e.getSource() == pause3) {
			worker3.pause();
			if (pause3.getText() == "PAUSE") {
				pause3.setText("UNPAUSE");
			}
			else {
				pause3.setText("PAUSE");
			}
			
		}
		
		//if user presses pause4, pause worker 4
		else if (e.getSource() == pause4) {
			worker2.pause();
			if (pause4.getText() == "PAUSE") {
				pause4.setText("UNPAUSE");
			}
			else {
				pause4.setText("PAUSE");
			}
		}
		
		//if user presses stop, end both threads
		else if (e.getSource() == stop) {
			worker1.stopEarly();
			worker2.stopEarly();
			worker3.stopEarly();
			worker4.stopEarly();
		}
		
		//if user presses add passengers, add more passengers
		else if (e.getSource() == addPass) {
			addPass();
		}
		
		//if user presses add taxis, add more taxis
		else if (e.getSource() == addTaxi) {
			addTaxi();
		}
		
		//if user selects fast radio button, change speed of all 4 workers to fast
		else if (e.getSource() == fast) {
			worker1.setSpeed(3);
			worker2.setSpeed(3);
			worker3.setSpeed(3);
			worker4.setSpeed(3);
		}
		
		//if user selects medium radio button, change speed of all 4 workers to medium
		else if (e.getSource() == medium) {
			worker1.setSpeed(2);
			worker2.setSpeed(2);
			worker3.setSpeed(2);
			worker4.setSpeed(2);
		}
		
		//if user selects slow radio button, change speed of all 4 workers to slow
		else if (e.getSource() == slow) {
			worker1.setSpeed(1);
			worker2.setSpeed(1);
			worker3.setSpeed(1);
			worker4.setSpeed(1);
		}
		
	}
	
	//method to react when an observed worker makes a match
	@Override
	public void update() {
		
		//if the changed output was worker 1, display in window 1 and update log
		if (output1 != worker1.getStatus()) {
			output1 = worker1.getStatus();
			area1.setText(output1);
			KioskLog.log(worker1.getKiosk().logg + " by Worker 1 \r\n");
		}
		
		//if the changed output was worker 2, display in window 2 and update log
		if (output2 != worker2.getStatus()) {
			output2 = worker2.getStatus();
			area2.setText(output2);
			KioskLog.log(worker2.getKiosk().logg + " by Worker 2 \r\n");
		}
		
		//if the changed output was worker 3, display in window 3 and update log
		if (output3 != worker3.getStatus()) {
			output3 = worker3.getStatus();
			area3.setText(output3);
			KioskLog.log(worker3.getKiosk().logg + " by Worker 3 \r\n");
		}
		
		//if the changed output was worker 4, display in window 4 and update log
		if (output4 != worker4.getStatus()) {
			output4 = worker4.getStatus();
			area4.setText(output4);
			KioskLog.log(worker4.getKiosk().logg + " by Worker 4 \r\n");
		}
		
		//update both queues
		areaPassengers.setText(worker1.getPassengerQueue());
		areaTaxis.setText(worker1.getTaxiQueue());
		
		//if the kiosk is finished (one of the queues is empty),
		//enable start button and disable stop button
		if (worker1.getKiosk().isFinished()) {
			stop.setEnabled(false);
			go.setEnabled(true);
		}
		
		//if the kiosk is not finished (neither queue is empty),
		//enable stop button and disable start button
		else {
			go.setEnabled(false);
			stop.setEnabled(true);
		}
		
	}
	

}
