package ASE_CW2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Kiosk extends Thread implements Subject {
	
	private ArrayList<Taxi> TaxiList;
	private ArrayList<PassengerGroup> PassengerGroupList;
	private String output;
	
	public Kiosk(ArrayList<Taxi> TaxiList, ArrayList<PassengerGroup> PassengerGroupList){
		this.TaxiList=TaxiList;
		this.PassengerGroupList=PassengerGroupList;
	}
	
	
	public String getStatus() {
		return output;
	}
	
	/*public String getCombinedTaxiandPassengersStr(){
		String output="";
		if (TaxiList.isEmpty()) {
			output="No available taxis at the moment";
		} 
		else if (PassengerGroupList.isEmpty()) {
			output="No Passengers in the queue";
		}
		else{
			output="Destination:  "+PassengerGroupList.get(0).getDestination()+"\nPassengers:  "+PassengerGroupList.get(0).getPassengersNumber()+
					"\nTaxi No: "+TaxiList.get(0).getPlateNumber();
			TaxiList.remove(0);
			PassengerGroupList.remove(0);
		}
		return output;
	}*/
	
	public void run(){
		output="";
		try {
			while ((TaxiList.size()>0) && (PassengerGroupList.size()>0)){
				output="\nDestination:  "+PassengerGroupList.get(0).getDestination()+"\nPassengers:  "+PassengerGroupList.get(0).getPassengersNumber()+
						"\nTaxi No: "+TaxiList.get(0).getPlateNumber();
				
				notifyObservers();
				try { Thread.sleep (500);  }
				catch ( InterruptedException e) { }
				//System.out.print(output);
				TaxiList.remove(0);
				PassengerGroupList.remove(0);
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.print("No enough parameters");
		} 
		if (TaxiList.isEmpty()) {
			output="No available taxis at the moment";
			//notifyObservers();
		} 
		else if (PassengerGroupList.isEmpty()) {
			output="No Passengers in the queue";
			//notifyObservers();
		}
		//System.out.print(output);
		
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
