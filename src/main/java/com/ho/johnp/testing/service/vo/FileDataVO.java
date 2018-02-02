/**
 * 
 */
package com.ho.johnp.testing.service.vo;

import java.io.File;
import java.util.List;

/**
 * This is a Data Holder Object that is used to represent the Details required from the vehicle
 * @author JohnP
 *
 */
public class FileDataVO {

private String directoryPath = null;
	
	private String fileName = null;
	
	private String mimeType = null;
	
	private long fileSize = 0L;
	
	private String fileExtension = null;
	
	private File file = null;
	
	private List<VehicleDetailsVO> vehicleDetails = null;
	
	/**
	 * @return the directoryPath
	 */
	public String getDirectoryPath() {
		return directoryPath;
	}

	/**
	 * @param directoryPath the directoryPath to set
	 */
	public void setDirectoryPath(String directoryPath) {
		this.directoryPath = directoryPath;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the mimeType
	 */
	public String getMimeType() {
		return mimeType;
	}

	/**
	 * @param mimeType the mimeType to set
	 */
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	/**
	 * @return the fileSize
	 */
	public long getFileSize() {
		return fileSize;
	}

	/**
	 * @param fileSize the fileSize to set
	 */
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	/**
	 * @return the fileExtension
	 */
	public String getFileExtension() {
		return fileExtension;
	}

	/**
	 * @param fileExtension the fileExtension to set
	 */
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	/**
	 * @return the vehicleDetails
	 */
	public List<VehicleDetailsVO> getVehicleDetails() {
		return this.vehicleDetails;
	}

	/**
	 * @param vehicleDetails the vehicleDetails to set
	 */
	public void setVehicleDetails(List<VehicleDetailsVO> vehicleDetails) {
		this.vehicleDetails = vehicleDetails;
	}
}
