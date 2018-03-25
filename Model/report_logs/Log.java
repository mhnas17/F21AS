package report_logs;
// This is not original code, it is inspired by a github repository
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
// These are imported so that the log file name contains the date & time as well
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public final class Log{
	//Implements a singleton logger instance
	private static final Log instance = new Log();

	//Retrieve the execution directory. Note that this is wherever this process was launched
	public String logname = "Aiport Log";
	protected String env = System.getProperty("user.dir");
	private static File logFile;

	public static Log getInstance(){
		return instance;
	}

	public static Log getInstance(String withName){
		instance.logname = withName;
		instance.createLogFile();
		return instance;
	}

	public void createLogFile(){
		//Determine if a logs directory exists or not.
		File logsFolder = new File(env + '/' + "logs");
		if(!logsFolder.exists()){
			//Create the directory 
			System.err.println("INFO: Creating new logs directory in " + env);
			logsFolder.mkdir();
			
		}

		//Get the current date and time
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	   	Calendar cal = Calendar.getInstance();
	   	
	   	//Create the name of the file from the path and current time
		logname =  logname + '-' +  dateFormat.format(cal.getTime()) + ".log";
		Log.logFile = new File(logsFolder.getName(),logname);
		try{
			if(logFile.createNewFile()){
				//New file made
				System.err.println("INFO: Creating new log file");	
			}
		}catch(IOException e){
			System.err.println("ERROR: Cannot create log file");
			System.exit(1);
		}
	}

	private Log(){
		if (instance != null){
			//Prevent Reflection
			throw new IllegalStateException("Cannot instantiate a new singleton instance of log");
		}
		this.createLogFile();
	}

	public static void log(String message){
		try{
			FileWriter out = new FileWriter(Log.logFile, true);
			out.write(message);
			out.write(System.getProperty( "line.separator" ));
		 	out.close();
		}catch(IOException e){
			System.err.println("ERROR: Could not write to log file");
		}
	}


}