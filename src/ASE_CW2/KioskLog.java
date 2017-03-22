package ASE_CW2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public final class KioskLog{
	//Creates a singleton log instance
	private static final KioskLog log = new KioskLog();

	//Retrieve the execution directory. Note that this is where ever this process was launched
	public String lognm = "KioskLog";
	protected String str = System.getProperty("user.dir");
	private static File logFile;

	public static KioskLog getInstance(){
		return log;
	}

	public static KioskLog getInstance(String name){
		log.lognm = name;
		log.createLogFile();
		return log;
	}

	public void createLogFile(){
		//If the directory doesn't exist, create it
		File logsFolder = new File(str + '/' + "logs");
		if(!logsFolder.exists()){
			//Create the directory 
			System.err.println("INFO: Creating new logs directory in " + str);
			logsFolder.mkdir();

		}

		//Get the current date and time
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();

		//Create filename from path and current time
		lognm =  lognm + '-' +  dateFormat.format(cal.getTime()) + ".log";
		KioskLog.logFile = new File(logsFolder.getName(),lognm);
		try{
			if(logFile.createNewFile()){
				//New file made
				System.err.println("MSG: New Log File Made");	
			}
		}catch(IOException e){
			System.err.println("ERROR: Log File Not Created");
			System.exit(1);
		}
	}

	private KioskLog(){
		if (log != null){
			//Prevent Reflection
			throw new IllegalStateException("Cannot instantiate a new singleton instance of log");
		}
		this.createLogFile();
	}

	public static void log(String message){
		try{
			FileWriter out = new FileWriter(KioskLog.logFile, true);
			out.write(message.toCharArray());
			out.close();
		}catch(IOException e){
			System.err.println("ERROR: Could not write to log file");
		}
	}
//Use this format to insert logging in process
//KioskLog.log("Taxi: " + TaxiList.get(taxiNum).getPlateNumber() + " ~~ Matched with Group: " + PassengerGroupList.get(0).getGroupName() + " ~~ Destination: " + PassengerGroupList.get(0).getDestination() + "\r\n");
	
}