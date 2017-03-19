package ASE_CW2;

public class Main {

	public static void main(String[] args) {
		ListsManager ListMan = new ListsManager();
    	Kiosk kiosk1=new Kiosk(ListMan.getTaxiList(),ListMan.getPassengerGroupList());
    	kiosk1.start();
    	
    	Kiosk kiosk2=new Kiosk(ListMan.getTaxiList(),ListMan.getPassengerGroupList());
    	kiosk2.start();
    	//System.out.println(kiosk.getCombinedTaxiandPassengersStr());
	}

}
