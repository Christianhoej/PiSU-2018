package Test;

public class Fields {

	protected int position; //position ift feltets nummer, eller for spilleren??
	protected String fieldName;
	//protected int fieldNumber;

	public Fields(int position, String fieldName) {
		this.position = position;
		this.fieldName = fieldName;
	}

	protected int getPosition() {
		return position;
	}

	protected void setPosition(int position) {
		this.position = position;
	}

	protected String getFieldName() {
		return fieldName;

	}

	protected void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

}


