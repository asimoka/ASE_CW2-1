package ASE_CW2;
/* Class representing a group of passengers waiting for a taxi
 * Includes the group's name, number of passengers and the
 * required destination.
 */

public class PassengerGroup {
	private int passengersNumber;
	private String groupName;
	private String Destination;
	
	//constructor
	public PassengerGroup(int passengersNumber, String Destination, String groupName){
		this.Destination=Destination;
		this.groupName=groupName;
		this.passengersNumber=passengersNumber;
	}

	//returns the group's name
	public String getGroupName() {
		return groupName;
	}

	//sets the group's name
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	//returns the number of passengers
	public int getPassengersNumber() {
		return passengersNumber;
	}

	//sets the number of passengers
	public void setPassengersNumber(int passengersNumber) {
		this.passengersNumber = passengersNumber;
	}

	//returns the destination
	public String getDestination() {
		return Destination;
	}

	//sets the destination
	public void setDestination(String destination) {
		Destination = destination;
	}
	
	
}
