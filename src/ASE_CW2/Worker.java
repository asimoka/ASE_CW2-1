package ASE_CW2;
/*
 * Worker thread class:
 * As many instatiations of this class as required can be made.
 * Acts as a thread representing one of the workers at the kiosk.
 * Observed by the GUI. It updates the GUI when a match is made
 * by one the workers.
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

public class Worker extends Observable implements Runnable {
	//instance variables
	private Kiosk kiosk;
	private String output;
	private int pause = 0;
	private int speed = 0;
	
	//constructor
	public Worker(Kiosk k){
		this.kiosk = k;
	}
	
	//sets a new kiosk to this worker
	public void setKiosk(Kiosk k) {
		kiosk = k;
	}
	
	//gets the kiosk
	public Kiosk getKiosk() {
		return kiosk;
	}
	
	//get a string output for the most recent match made
	public String getStatus() {
		return output;
	}
	
	//pause or unpause this worker
	public void pause() {
		if (pause == 1) {
			pause = 0;
		}
		else {
			pause = 1;
		}
	}
	
	//se this worker's speed
	public void setSpeed(int sp) {
		speed = sp;
	}
	
	//set the kiosk's finished value to true
	public void stopEarly() {
		kiosk.stopEarly();
	}
	
	//sets the kiosk's finished value to false
	public void ready() {
		kiosk.readyToStart();
	}
	
	//returns the number of passenger groups in queue
	public int getNumPassengerGroups() {
		return kiosk.getPassengerGroupList().size();
	}
	
	//returns the number of taxis in queue
	public int getNumTaxis() {
		return kiosk.getTaxiList().size();
	}
	
	//returns a formatted string containing a description of all passenger groups in queue
	public String getPassengerQueue() {
		String queue = "QUEUE (" + getNumPassengerGroups() + ")\n";
		ArrayList<PassengerGroup> list = kiosk.getPassengerGroupList();
		
		//loop through arraylist of passenger groups and print each on a new line
		for (int i=0; i<list.size(); i++) {
			queue += "\n" +list.get(i).getDestination() + "\n" + list.get(i).getPassengersNumber() + " people. \n";
		}
		return queue;
	}
	
	//returns a formatted string containing a description of all taxis in queue
	public String getTaxiQueue() {
		String queue = "TAXIS (" + getNumTaxis() + ")\n";
		ArrayList<Taxi> list = kiosk.getTaxiList();
		
		//loop through arraylist of taxis and print each on a new line
		for (int i=0; i<list.size(); i++) {
			//queue += list.get(i).getGroupName() + "\n" + list.get(i).getDestination() + "\n" + list.get(i).getPassengersNumber() + " people. \n \n";
			queue += "\n" + list.get(i).getPlateNumber();
		}
		return queue;
	}
	
	//This method is called by the thread.
	//Creates matches by calling the kiosk's matchJourney method.
	//Runs until the kiosk's status is set to finished.
	public void run() {
		while (!kiosk.isFinished()){
			//depending on current speed setting, wait a certain amount of time in milliseconds
			switch (speed) {
				case 1:
					//for slow speed: Get a random number between 1 and 1000 and add 3000
					try {double rand=Math.random()*1000;
					int randInt = (int)rand+3000;
					Thread.sleep(randInt); }
					catch ( InterruptedException e) { }
				case 2:
					//for slow speed: Get a random number between 1 and 1000 and add 1500
					try {double rand=Math.random()*1000;
					int randInt = (int)rand+1500;
					Thread.sleep(randInt); }
					catch ( InterruptedException e) { }
				case 3:
					//for slow speed: Get a random number between 1 and 1000 and add 500
					try {double rand=Math.random()*1000;
					int randInt = (int)rand+500;
					Thread.sleep(randInt); }
					catch ( InterruptedException e) { }
				default:
					//if no speed given: Do the same as for fast speed
					try {double rand=Math.random()*1000;
					int randInt = (int)rand+500;
					Thread.sleep(randInt); }
					catch ( InterruptedException e) { }
			}
			//if worker is not paused, make a match and notify the observer (GUI)
			if (pause == 0) {
				output = speed + ": " + kiosk.matchJourney();
				notifyObservers();
			}
		}
	}
	
	
	////////////////////////////////////////////////////////
	//OBSERVER PATTERN
	//SUBJECT must be able to register, remove and notify observers
	//list to hold any observers
	private List<Observer> registeredObservers = new LinkedList<Observer>();
	//methods to register, remove and notify observers
	public void registerObserver( Observer obs)
	{
	registeredObservers.add( obs);
	}
	
	public void removeObserver( Observer obs)
	{
	registeredObservers.remove( obs);
	}
	
	public void notifyObservers()
	{
	for( Observer obs : registeredObservers)
	obs.update();
	}
	////////////////////////////////////////////////////////
		
		
		
}
