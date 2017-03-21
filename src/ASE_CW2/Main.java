package ASE_CW2;

public class Main {

	public static void main(String[] args) {
		
		ListsManager ListMan = new ListsManager();
		//shared object
    	Kiosk kiosk=new Kiosk(ListMan.getTaxiList(),ListMan.getPassengerGroupList());
    	
    	//consumer object
    	Worker worker1 = new Worker(kiosk);
    	Thread workerThread1 = new Thread(worker1);

    	Worker worker2 = new Worker(kiosk);
    	Thread workerThread2 = new Thread(worker2);

    	
    	GUI gui = new GUI(worker1, worker2);
    	workerThread1.start();
    	workerThread2.start();
    	
    	//kiosk1.start();
    	//kiosk2.start();
    	//System.out.println(kiosk.getCombinedTaxiandPassengersStr());
	}

}
