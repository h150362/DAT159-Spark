package no.hvl.dat159;

public class TemperatureSensor {

	private Room room;
	Temperature temperature;

	public TemperatureSensor(Room room) {

		this.room = room;
	}
	public double read() {

		return room.sense();
	}

}
