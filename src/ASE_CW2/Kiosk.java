package ASE_CW2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Kiosk {
	
	private ArrayList<Taxi> TaxiList;
	private ArrayList<PassengerGroup> PassengerGroupList;
	private boolean finished;
	
	public Kiosk(ArrayList<Taxi> TaxiList, ArrayList<PassengerGroup> PassengerGroupList){
		this.TaxiList=TaxiList;
		this.PassengerGroupList=PassengerGroupList;
		finished = false;
	}
	
	public boolean isFinished() {
		return finished;
	}
	
	public void stopEarly() {
		finished = true;
	}
	
	public void readyToStart() {
		finished = false;
	}
	
	public ArrayList<Taxi> getTaxiList() {
		return TaxiList;
	}
	
	public ArrayList<PassengerGroup> getPassengerGroupList() {
		return PassengerGroupList;
	}
	
	public void setTaxiList(ArrayList<Taxi> list) {
		TaxiList = list;
	}
	
	public void setPassengerGroupList(ArrayList<PassengerGroup> list) {
		PassengerGroupList = list;
	}
	
	/*the method that returns the index of the taxi 
	 * that has enough seats for the 
	 * first passenger group in the queue
	 * Tries to assign as small a taxi as possible */
	
	public int findAppropriateTaxi(int numPassengers) {
		if (numPassengers <= 3) {
			for (int i=0;i<TaxiList.size();i++)
			{
				if (TaxiList.get(i).getMaxPassengersNumber()==3){return i;}
			}
		}
		if (numPassengers <= 5) {
			for (int i=0;i<TaxiList.size();i++)
			{
				if (TaxiList.get(i).getMaxPassengersNumber()==5){return i;}
			}
		}
		if (numPassengers <= 7) {
			for (int i=0;i<TaxiList.size();i++)
			{
				if (TaxiList.get(i).getMaxPassengersNumber()==7){return i;}
			}
		}		
		return -1;
	}
	
	
	public synchronized String matchJourney(){
		String output="";
		try {
			int taxiNum = findAppropriateTaxi(PassengerGroupList.get(0).getPassengersNumber());
			if (taxiNum!=-1)
			{
				output="Destination:  "+PassengerGroupList.get(0).getDestination()+"\nPassengers:  "+PassengerGroupList.get(0).getPassengersNumber()+
						"\nTaxi No: "+TaxiList.get(taxiNum).getPlateNumber();
				TaxiList.remove(taxiNum);
				PassengerGroupList.remove(0);
				System.out.println(output);
				
			}
			else {
				System.out.println("Group: " + PassengerGroupList.get(0).getPassengersNumber());
				for (int i=0; i<TaxiList.size(); i++) {
					System.out.println("taxi: " + TaxiList.get(i).getPlateNumber());
				}
				
				PassengerGroupList.add(PassengerGroupList.get(0));
				PassengerGroupList.remove(0);
				output = "no taxis big enough";
			}
			if (TaxiList.size() == 0 || PassengerGroupList.size() == 0) {
				finished = true;
			}
		}
		
		catch (IndexOutOfBoundsException e) {
			//System.out.print("No enough parameters");
		} 
		if (TaxiList.isEmpty()) {
			output="No available taxis at the moment";
			//notifyObservers();
		} 
		else if (PassengerGroupList.isEmpty()) {
			output="No Passengers in the queue";
			finished = true;
			//notifyObservers();
		}
		return output;
		
	}


	

	
	

	
}
