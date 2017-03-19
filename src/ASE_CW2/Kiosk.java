package ASE_CW2;

import java.util.ArrayList;

public class Kiosk extends Thread {
	
	private ArrayList<Taxi> TaxiList;
	private ArrayList<PassengerGroup> PassengerGroupList;
	
	public Kiosk(ArrayList<Taxi> TaxiList, ArrayList<PassengerGroup> PassengerGroupList){
		this.TaxiList=TaxiList;
		this.PassengerGroupList=PassengerGroupList;
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
		String output="";
		try {
			while ((TaxiList.size()>0) && (PassengerGroupList.size()>0)){
				output="\nDestination:  "+PassengerGroupList.get(0).getDestination()+"\nPassengers:  "+PassengerGroupList.get(0).getPassengersNumber()+
						"\nTaxi No: "+TaxiList.get(0).getPlateNumber();
				System.out.print(output);
				TaxiList.remove(0);
				PassengerGroupList.remove(0);
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.print("No enough parameters");
		} 
		if (TaxiList.isEmpty()) {
			output="No available taxis at the moment";
		} 
		else if (PassengerGroupList.isEmpty()) {
			output="No Passengers in the queue";
		}
		System.out.print(output);
	}
	
	

	
}
