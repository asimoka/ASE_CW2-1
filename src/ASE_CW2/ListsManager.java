package ASE_CW2;

/*
 * This class deals with all of the I/O between the program
 * and the input/output text files. 
 * 
 * 
 */
import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class ListsManager  {

	private ArrayList<Taxi> TaxiList;
	private ArrayList<PassengerGroup> PassengerGroupList;
	
	private String	 regNumber;
	private String 	 destination;
	private String   groupName;
	private int 	 numPassengers;
	private int		 maxPassNo;
	

	public ListsManager()
	{
		//Initialize empty list of Competitors
    	TaxiList = new ArrayList<Taxi>();
    	PassengerGroupList=new ArrayList<PassengerGroup>();
        addPassengers();
        addTaxis();
	}
	
	 public ArrayList<Taxi> getTaxiList() {
		return TaxiList;
	}

	public ArrayList<PassengerGroup> getPassengerGroupList() {
		return PassengerGroupList;
	}

	public void addPassengers() 
	    {
	        //load staff data from file
	        BufferedReader passBuff = null;
	        
	        try 
	        {
	        
	        	passBuff = new BufferedReader(new FileReader("passengerGroupInput.csv"));
		    	String inputPassengersLine = passBuff.readLine();  //read first line
		    	while(inputPassengersLine != null)
		    	{  
		    		//stores details from this line in Kiosk class
		    		processLinePassengers(inputPassengersLine);
					KioskLog.log("Passenger Group: " + groupName + " added to Passenger Queue list ~~ Size: " + numPassengers + " Destination: " + destination + "\r\n");
		            //read next line
		            inputPassengersLine = passBuff.readLine();
		        }
	        }
	        catch(FileNotFoundException e) 
	        {
	        	System.out.println(e.getMessage());
	            System.exit(1);
	        }
	        catch (IOException e) 
	        {
	        	e.printStackTrace();
	            System.exit(1);        	
	        }
	        finally  
	        {
	        	try
	        	{
	        		passBuff.close();
	        	}
	        	catch (IOException ioe) 
	        	{
	        		//don't do anything
	        	}
	        }   	
	    }
	
	public void addTaxis() 
    {
        //load staff data from file
        BufferedReader taxiBuff = null;
        
        try 
        {
	    	taxiBuff = new BufferedReader(new FileReader("taxiDetailsInput.csv"));
	    	String inputTaxiLine = taxiBuff.readLine();  //read first line
	    	while(inputTaxiLine != null)
	    	{  
	    		//stores details from this line in Kiosk class
	    		processLineTaxi(inputTaxiLine);
				KioskLog.log("Taxi: " + regNumber + " added to Taxi Queue list ~~ Max Passengers: " + maxPassNo + "\r\n");

	            //read next line
	            inputTaxiLine = taxiBuff.readLine();
	        }
        }
        catch(FileNotFoundException e) 
        {
        	System.out.println(e.getMessage());
            System.exit(1);
        }
        catch (IOException e) 
        {
        	e.printStackTrace();
            System.exit(1);        	
        }
        finally  
        {
        	try
        	{
        		taxiBuff.close();
        	}
        	catch (IOException ioe) 
        	{
        		//don't do anything
        	}
        }   	
    }
	 //
	
	public void processLinePassengers(String line) {
		try {
			String [] inline = line.split(",");
			numPassengers = Integer.valueOf(inline[0]);
			destination=inline[1];
			groupName=inline[2];			
			PassengerGroup pg = new PassengerGroup(numPassengers, destination, groupName);
			PassengerGroupList.add(pg);
		} 
		catch (InputMismatchException ime) {
			String error = "Input mismatch error in '"
					+ line + "' - " + ime.getMessage();
			System.out.println(error);
		}
		//this catches missing items if only one or two items
		//other omissions will result in other errors
		catch (ArrayIndexOutOfBoundsException air) {
			String error = "Not enough items in : '" + line
					+ "' index position : " + air.getMessage();
			System.out.println(error);
		}

	}

	public void processLineTaxi(String line) {
		try {
			String [] inline = line.split(",");
			regNumber = inline[0];
			maxPassNo=Integer.valueOf(inline[1]);
			

			Taxi j = new Taxi(regNumber,maxPassNo);
			TaxiList.add(j);
		} catch (NumberFormatException nfe) {
			String error = "Number conversion error in '"
					+ line + "' - " + nfe.getMessage();
			System.out.println(error);
		}
		catch (InputMismatchException ime) {
			String error = "Input mismatch error in '"
					+ line + "' - " + ime.getMessage();
			System.out.println(error);
		}
		//this catches missing items if only one or two items
		//other omissions will result in other errors
		catch (ArrayIndexOutOfBoundsException air) {
			String error = "Not enough items in : '" + line
					+ "' index position : " + air.getMessage();
			System.out.println(error);
		}
	
	}
	
	/* these two methods are just for checking
	 * if the input from the file is correct 
	 * or not
	 */
	public String printListofTaxi(){
		String report="";
		for (Taxi taxi:TaxiList){
			report+="\n"+taxi.getPlateNumber();
		}
		return report;
	}
	
	public String printListofPassGroups(){
		String report="";
		for (PassengerGroup pg:PassengerGroupList){
			report+="\n"+pg.getDestination()+" "+pg.getPassengersNumber();
		}
		return report;
	}
	
	
	
	public String getCombinedTaxiandPassengersStr(){
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
	}
	
	

}
