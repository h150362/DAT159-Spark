package no.hvl.dat159;

public class Room {

	 int tempstate;
	private static double RATE = .00005; // change in temperature per time unit

	private static double temperature;
	private static long lastsense;

	public Room(int starttemp) {
		tempstate = -1;
		temperature = starttemp;
		lastsense = System.currentTimeMillis();
	}
	
	public Room() {
		tempstate = -1;
	}

	synchronized public double sense() {

		long timenow = System.currentTimeMillis();
		long timeinterval = timenow - lastsense;

		temperature = temperature + (RATE * tempstate * timeinterval);

		return temperature;
	}

	synchronized public void actuate(boolean newstate) {

		sense();
		if (newstate)
			tempstate = 1;
		else
			tempstate = -1;
	}
}
