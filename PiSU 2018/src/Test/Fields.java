package Test;

public class Fields {

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

}


