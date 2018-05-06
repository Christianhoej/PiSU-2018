package model;

import controller.GameController;

/**
 * Contains methods for the real estates on the gameboard.
 * This includes methods for houses build on real estates.
 * @author 
 *
 */
public class RealEstate extends Property {
	protected int houses;
	protected Player player;
	protected int buildingPrice;

	/**
	 * Constructor
	 * 
	 * @param fieldNumber
	 */
	public RealEstate(int fieldNumber) {
		super(fieldNumber);
	}

	@Override
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
		notifyChange();
	}

	/**
	 * offers the real estate to the player who lands on it. 
	 */
	@Override
	public void landOnField(GameController gameController) {
		gameController.offerToBuyProperty(this);
		notifyChange();
	}

	public void setHouses(int houses) {
		this.houses = houses;
		notifyChange();
	}

	public int getHouses() {
		return houses;
	}

	public void sellHouse() {
		this.houses--;
		notifyChange();
	}

	public void buyHouse() {
		this.houses++;
		notifyChange();
	}

	public void setBuildingPrice(int buildingPrice) {
		this.buildingPrice = buildingPrice;
	}

	public int getBuildingPrice() {
		return buildingPrice;
	}
}