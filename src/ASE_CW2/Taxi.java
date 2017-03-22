package ASE_CW2;
/* Class representing a taxi
 * Includes the taxi's license plate and the maximum number of
 * passengers it can accomodate.
 */
public class Taxi {
	private String plateNumber;
	private int maxPassengersNumber;
	
	//constructor
	public Taxi(String plateNumber, int maxPassengersNumber){
		this.plateNumber=plateNumber;
		this.maxPassengersNumber=maxPassengersNumber;
	}

	//returns the license plate number
	public String getPlateNumber() {
		return plateNumber;
	}

	//returns the maximum number of passengers a taxi can accomodate
	public int getMaxPassengersNumber() {
		return maxPassengersNumber;
	}

	//sets the taxi#s license plate number
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	//sets the maximum number of passengers the taxi can accomodate
	public void setMaxPassengersNumber(int maxPassengersNumber) {
		this.maxPassengersNumber = maxPassengersNumber;
	}
	
	
}
