package ASE_CW2;

public class Main {

	public static void main(String[] args) {
		
		ListsManager ListMan = new ListsManager();
    	Kiosk kiosk1=new Kiosk(ListMan.getTaxiList(),ListMan.getPassengerGroupList());

    	
    	Kiosk kiosk2=new Kiosk(ListMan.getTaxiList(),ListMan.getPassengerGroupList());

    	
    	GUI gui = new GUI(kiosk1, kiosk2);
    	
    	kiosk1.start();
    	kiosk2.start();
    	//System.out.println(kiosk.getCombinedTaxiandPassengersStr());
	}

}
