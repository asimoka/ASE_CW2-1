package ASE_CW2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

public class Worker extends Observable implements Runnable {
	
	private Kiosk kiosk;
	private String output;
	private int pause = 0;
	private enum speedEnum {SLOW, MEDIUM, FAST};
	speedEnum speed = speedEnum.SLOW;
	
	
	public Worker(Kiosk k){
		this.kiosk = k;
	}
	
	public void setKiosk(Kiosk k) {
		kiosk = k;
	}
	
	public Kiosk getKiosk() {
		return kiosk;
	}
	
	public String getStatus() {
		return output;
	}
	
	public void pause() {
		if (pause == 1) {
			pause = 0;
		}
		else {
			pause = 1;
		}
	}
	
	public void stopEarly() {
		kiosk.stopEarly();
	}
	
	public void ready() {
		kiosk.readyToStart();
	}
	
	public int getNumPassengerGroups() {
		return kiosk.getPassengerGroupList().size();
	}
	
	public int getNumTaxis() {
		return kiosk.getTaxiList().size();
	}
	
	public String getPassengerQueue() {
		String queue = "QUEUE (" + getNumPassengerGroups() + ")\n";
		ArrayList<PassengerGroup> list = kiosk.getPassengerGroupList();
		
		for (int i=0; i<list.size(); i++) {
			//queue += list.get(i).getGroupName() + "\n" + list.get(i).getDestination() + "\n" + list.get(i).getPassengersNumber() + " people. \n \n";
			queue += "\n" +list.get(i).getDestination() + "\n" + list.get(i).getPassengersNumber() + " people. \n";
		}
		return queue;
	}
	
	public String getTaxiQueue() {
		String queue = "TAXIS (" + getNumTaxis() + ")\n";
		ArrayList<Taxi> list = kiosk.getTaxiList();
		
		for (int i=0; i<list.size(); i++) {
			//queue += list.get(i).getGroupName() + "\n" + list.get(i).getDestination() + "\n" + list.get(i).getPassengersNumber() + " people. \n \n";
			queue += "\n" + list.get(i).getPlateNumber();
		}
		return queue;
	}
	
	public void run() {
		while (!kiosk.isFinished()){
			switch (speed) {
				case SLOW:
					try {double rand=Math.random()*1000;
					int randInt = (int)rand+1500;
					Thread.sleep(randInt); }
					catch ( InterruptedException e) { }
				case MEDIUM:
					try {double rand=Math.random()*1000;
					int randInt = (int)rand+1000;
					Thread.sleep(randInt); }
					catch ( InterruptedException e) { }
				case FAST:
					try {double rand=Math.random()*1000;
					int randInt = (int)rand+500;
					Thread.sleep(randInt); }
					catch ( InterruptedException e) { }
			}
			if (pause == 0) {
				output = kiosk.matchJourney();
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
