package ASE_CW2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

public class Worker extends Observable implements Runnable {
	
	private Kiosk kiosk;
	private String output;
	
	
	public Worker(Kiosk k){
		this.kiosk = k;
	}
	
	public String getStatus() {
		return output;
	}
	
	public void run() {
		while (!kiosk.isFinished()){
			
			try {double rand=Math.random()*600;
			int randInt = (int)rand;
			Thread.sleep(randInt); }
			catch ( InterruptedException e) { }
			output = kiosk.matchJourney();
			notifyObservers();
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
