package dao;

public class PropertyDAO {

	private int fieldNumber;
	private int houses;
	
	public PropertyDAO(int fieldNumber, int houses) {
		this.fieldNumber = fieldNumber;
		this.houses = houses;
	}

	public String toString() {
		return "Grund nr: " + fieldNumber + ", status: " + houses;
	}
	
	public int getFieldNumber() {
		return fieldNumber;
	}

	public void setFieldNumber(int fieldNumber) {
		this.fieldNumber = fieldNumber;
	}

	public int getHouses() {
		return houses;
	}

	public void setHouses(int houses) {
		this.houses = houses;
	}
}


