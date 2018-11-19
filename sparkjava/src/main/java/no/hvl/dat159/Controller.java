package no.hvl.dat159;
import static spark.Spark.*;

import java.io.IOException;

import com.google.gson.Gson;

public class Controller {
	static Temperature temp = null;
	
	public Controller() {
		
	}
	
	String state = "";
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
	private static double temperature;
	final static double upperTemp = 22;
	final static double lowerTemp = 18;
	
	
	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		
		
	
		DweetClient client = new DweetClient();
			
			for (int i = 1; i<=500; i++) {
				
				String klientTemp = client.get();
				String asd[] = klientTemp.split(":");
				String tempen[] = asd[1].split("}", 0);
				
				double temperature = Double.parseDouble(tempen[0]);
				
				
				
				if(temperature < lowerTemp) {
				
				client.publishState(1);
				
				} else if(temperature > upperTemp) {
				client.publishState(-1);
				
				}
			
				Thread.sleep(100);
				
			}	
			}

}



