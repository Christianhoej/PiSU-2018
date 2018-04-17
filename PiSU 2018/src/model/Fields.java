package model;

//Bør klassen være abstract?
public abstract class Fields {
//asd
	protected int fieldNumber; 
	protected String fieldName;

	public Fields(int fieldNumber, String fieldName) {
		this.fieldNumber = fieldNumber;
		this.fieldName = fieldName;
	}

	protected int getFieldNumber() {
		return fieldNumber;
	}

	protected void setFieldNumber(int fieldNumber) {
		this.fieldNumber = fieldNumber;
	}

	protected String getFieldName() {
		return fieldName;

	}

	protected void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	public abstract void landOnField(Player player, Player[] playerArray);

	public abstract String toString(); 
		
	}


