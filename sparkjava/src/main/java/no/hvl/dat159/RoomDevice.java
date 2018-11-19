package no.hvl.dat159;

import static spark.Spark.after;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.put;

import java.io.IOException;

import com.google.gson.Gson;

public class RoomDevice {
	 Display display;
	 static double temp = 0;
	 static Controller controller;
	 
	 
	public static void main(String[] args) throws IOException, InterruptedException {
		Room room = new Room(20);
		Heating heating = new Heating(room);
		DweetClient client = new DweetClient();
		TemperatureSensor sensor = new TemperatureSensor(room);
		
		
		for (int i = 0; i<50; i++) {

				
				double temp = sensor.read();
				if (client.publish(temp)) {
					System.out.println("Publishing temperature: " + temp);
				} else
					System.out.println("FAIL");
		
				String klientTemp = client.getState();
				
				if(klientTemp.equals("{\"tempstate\":-1}")) {
					System.out.println("Heating message arrived: OFF");
					heating.write(false);
					
				} else if(klientTemp.equals("{\"tempstate\":1}")) {
					System.out.println("Heating message arrived: ON");
					heating.write(true);
				}
				
				
				
				
				Thread.sleep(500);

			}
			


	}
	
	 static String tempToJson () {
	    	
	    	Gson gson = new Gson();
	    	    
	    	String jsonInString = gson.toJson(temp);
	    	
	    	return jsonInString;
	    }
		


	}


