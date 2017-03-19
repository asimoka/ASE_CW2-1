package ASE_CW2;

public class Taxi {
	private String plateNumber;
	private int maxPassengersNumber;
	
	public Taxi(String plateNumber, int maxPassengersNumber){
		this.plateNumber=plateNumber;
		this.maxPassengersNumber=maxPassengersNumber;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public int getMaxPassengersNumber() {
		return maxPassengersNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public void setMaxPassengersNumber(int maxPassengersNumber) {
		this.maxPassengersNumber = maxPassengersNumber;
	}
	
	
}
