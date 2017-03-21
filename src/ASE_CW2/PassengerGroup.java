package ASE_CW2;

public class PassengerGroup {
	private int passengersNumber;
	private String groupName;
	private String Destination;
	
	public PassengerGroup(int passengersNumber, String Destination, String groupName){
		this.Destination=Destination;
		this.groupName=groupName;
		this.passengersNumber=passengersNumber;
	}

	
	public String getGroupName() {
		return groupName;
	}


	public void setGroupName(String groupName) {
		this.groupName = groupName;
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
