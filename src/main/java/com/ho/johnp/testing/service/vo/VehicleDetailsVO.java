/**
 * 
 */
package com.ho.johnp.testing.service.vo;

/**
 * This class id used in order to act as a Data Holder for the Vehicle Details.
 * @author JohnP
 *
 */
public class VehicleDetailsVO {

	private String vrm = null;
	
	private String make = null;
	
	private String color = null;

	/**
	 * @return the vrm
	 */
	public String getVrm() {
		return vrm;
	}

	/**
	 * @param vrm the vrm to set
	 */
	public void setVrm(String vrm) {
		this.vrm = vrm;
	}

	/**
	 * @return the make
	 */
	public String getMake() {
		return make;
	}

	/**
	 * @param make the make to set
	 */
	public void setMake(String make) {
		this.make = make;
	}

	/**
	 * @return the model
	 */
	public String getColor() {
		return this.color;
	}

	/**
	 * @param model the model to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

}
