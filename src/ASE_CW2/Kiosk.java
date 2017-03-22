package ASE_CW2;
/*
 * Kiosk class contains the taxi and passenger groups queue data from the input file
 * Contains a method which matches passenger groups to the first appropriate taxi
 * from the queue.
 */
import java.util.ArrayList;

public class Kiosk {
	
	private ArrayList<Taxi> TaxiList;
	private ArrayList<PassengerGroup> PassengerGroupList;
	private boolean finished;
	public String logg;
	
	//constructor
	public Kiosk(ArrayList<Taxi> TaxiList, ArrayList<PassengerGroup> PassengerGroupList){
		this.TaxiList=TaxiList;
		this.PassengerGroupList=PassengerGroupList;
		finished = false;
	}
	//check is the kiosk is finished running
	public boolean isFinished() {
		return finished;
	}
	//stop the kiosk early
	public void stopEarly() {
		finished = true;
	}
	//set the kiosk to be ready to start
	public void readyToStart() {
		finished = false;
	}
	//get the taxi list
	public ArrayList<Taxi> getTaxiList() {
		return TaxiList;
	}
	//get the passenger group list
	public ArrayList<PassengerGroup> getPassengerGroupList() {
		return PassengerGroupList;
	}
	//set the taxi list
	public void setTaxiList(ArrayList<Taxi> list) {
		TaxiList = list;
	}
	//set the passenger group list
	public void setPassengerGroupList(ArrayList<PassengerGroup> list) {
		PassengerGroupList = list;
	}
	
	/*the method that returns the index of the taxi 
	 * that has enough seats for the 
	 * first passenger group in the queue
	 * Tries to assign as small a taxi as possible */
	
	public int findAppropriateTaxi(int numPassengers) {
		//if the number of passengers in the group is less than or equal to 3,
		//search for the first 3 person taxi in the list
		//if a taxi is found, return it and exit,
		//if no such taxi exists, move to search for a 5 person taxi
		if (numPassengers <= 3) {
			KioskLog.log("Searching for Taxis with 3 seats \r\n");
			for (int i=0;i<TaxiList.size();i++)
			{
				if (TaxiList.get(i).getMaxPassengersNumber()==3){return i;}
				/*else if (TaxiList.get(i).getMaxPassengersNumber() > 3) {
					KioskLog.log(TaxiList.get(i).getPlateNumber() + " too large for group " + PassengerGroupList.get(0).getGroupName() + "\r\n");
				}*/
			}
		}
		//if the number of passengers in the group is less than or equal to 5,
		//search for the first 5 person taxi in the list
		//if a taxi is found, return it and exit,
		//if no such taxi exists, move to search for a 7 person taxi
		if (numPassengers <= 5) {
			KioskLog.log("Searching for Taxis with 5 seats \r\n");
			for (int i=0;i<TaxiList.size();i++)
			{
				if (TaxiList.get(i).getMaxPassengersNumber()==5){return i;}
				/*else if (TaxiList.get(i).getMaxPassengersNumber() > 5) {
					KioskLog.log(TaxiList.get(i).getPlateNumber() + " too large for group " + PassengerGroupList.get(0).getGroupName() + "\r\n");
				}
				else if (TaxiList.get(i).getMaxPassengersNumber() < 5) {
					KioskLog.log(TaxiList.get(i).getPlateNumber() + " too small for group " + PassengerGroupList.get(0).getGroupName() + "\r\n");
				}*/
			}
		}
		//if the number of passengers in the group is less than or equal to 7,
		//search for the first 5 person taxi in the list
		//if a taxi is found, return it and exit
		if (numPassengers <= 7) {
			KioskLog.log("Searching for Taxis with 7 seats \r\n");
			for (int i=0;i<TaxiList.size();i++)
			{
				if (TaxiList.get(i).getMaxPassengersNumber()==7){return i;}
				/*else if (TaxiList.get(i).getMaxPassengersNumber() < 7) {
					KioskLog.log(TaxiList.get(i).getPlateNumber() + " too small for group " + PassengerGroupList.get(0).getGroupName() + "\r\n");
				}*/
			}
		}
		if (TaxiList.size() == 0) {
			return -2;
		}
		//if no such taxi exists, return -1 to signify this and exit
		return -1;
	}
	
	//method which matches a taxi to a passenger group
	//uses the above method to search for an appropriate taxi,
	//then prints a formatted output and logs the event
	public synchronized String matchJourney(){
		String output="";
		try {
			int taxiNum = findAppropriateTaxi(PassengerGroupList.get(0).getPassengersNumber());
			if (taxiNum!=-1)
			{
				//KioskLog.log("MATCH~~ Taxi: " + TaxiList.get(taxiNum).getPlateNumber() + " ~~ Matched with Group: " + PassengerGroupList.get(0).getGroupName() + " ~~ Destination: " + PassengerGroupList.get(0).getDestination() + "\r\n");
				
				logg = "MATCH~~ Taxi: " + TaxiList.get(taxiNum).getPlateNumber() + " ~~ Matched with Group: " + PassengerGroupList.get(0).getGroupName() + " ~~ Destination: " + PassengerGroupList.get(0).getDestination();
				
				output="Destination:  "+PassengerGroupList.get(0).getDestination()+"\nPassengers:  "+PassengerGroupList.get(0).getPassengersNumber()+
						"\nTaxi No: "+TaxiList.get(taxiNum).getPlateNumber();
				TaxiList.remove(taxiNum);
				PassengerGroupList.remove(0);
				System.out.println(output);
				
			}
			//if no appropriate taxi exists, print to console
			else {
				System.out.println("Group: " + PassengerGroupList.get(0).getPassengersNumber());
				for (int i=0; i<TaxiList.size(); i++) {
					System.out.println("taxi: " + TaxiList.get(i).getPlateNumber());
				}
				//move passenger group to the end of the list so that the other passengers may find a taxi
				PassengerGroupList.add(PassengerGroupList.get(0));
				PassengerGroupList.remove(0);
				output = "no taxis big enough";
			}
			//if either queue is empty, stop execution
			if (TaxiList.size() == 0 || PassengerGroupList.size() == 0) {
				finished = true;
			}
		}
		catch (IndexOutOfBoundsException e) {
		}
		//if there are no taxis left
		if (TaxiList.isEmpty()) {
			output="No available taxis at the moment";
		} 
		//if no more passengers are waiting
		else if (PassengerGroupList.isEmpty()) {
			output="No Passengers in the queue";
			finished = true;
		}
		return output;
		
	}
	
}
