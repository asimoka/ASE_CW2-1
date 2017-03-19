package ASE_CW2;

public class PassengerGroup {
	private int passengersNumber;
	private String Destination;
	
	public PassengerGroup(int passengersNumber, String Destination){
		this.Destination=Destination;
		this.passengersNumber=passengersNumber;
	}

	public int getPassengersNumber() {
		return passengersNumber;
	}

	public void setPassengersNumber(int passengersNumber) {
		this.passengersNumber = passengersNumber;
	}

	public String getDestination() {
		return Destination;
	}

	public void setDestination(String destination) {
		Destination = destination;
	}
	
	
}
