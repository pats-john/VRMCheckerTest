package com.ho.johnp.testing.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;

import com.ho.johnp.testing.service.vo.FileDataVO;
import com.ho.johnp.testing.service.vo.VehicleDetailsVO;

public class FileFilter {

	/**
	 * This method  is used to process all the relevant Files in a given directory 
	 * and give back information about a list of Data Value Objects.... 
	 * @param directoryPath
	 * @param filterExtensions
	 * @return - List of FileDataValueObjects...
	 */
	public List<FileDataVO> processFiles(String directoryPath, 
			String[] filterExtensions) {

		File file = new File(directoryPath);
		FileDataVO fileDataVO = new FileDataVO();
		List<FileDataVO> filteredFiles = new ArrayList<FileDataVO>();

		if (!file.isDirectory()) {
			throw new RuntimeException("The given path is not valid");
		} else {
			fileDataVO.setDirectoryPath(directoryPath);
		}

		List<File> processedFiles = processFiles(filterExtensions, file);
		for (File processedFile : processedFiles) {

			long length = processedFile.length();
			fileDataVO.setFileSize(length);
			fileDataVO.setFileName(processedFile.getName());
			fileDataVO.setFileExtension(getFileExtension(file));
			fileDataVO.setFile(processedFile);
			try {
				fileDataVO.setMimeType(getFileMIMEType(file));
			} catch (IOException ioException) {
				throw new RuntimeException("Error Retrieving the MIME Type of the File");
			}
			filteredFiles.add(fileDataVO);
		}
		return filteredFiles;
	}

	/**
	 * This is a method that filters a list of Files based on 
	 * a list of extensions passed in.
	 * @param filterExtensions - An array of file extensions...
	 * @param filePath - The File having a File Path of where Files would have to read from.
	 */
	private List<File> processFiles(String[] filterExtensions, File filePath) {

		File[] listOfFiles = filePath.listFiles();
		List<File> filteredFiles = new ArrayList<File>();

		for (File file : listOfFiles) {

			String extension = getFileExtension(file);
			List<String> filterExtensionList = Arrays.asList(filterExtensions);
			if (filterExtensionList.contains(extension)) {
				filteredFiles.add(file);
			}

		}
		return filteredFiles;
	}

	/**
	 * Getter for the File Extension of the File...
	 * @param - The File
	 * @return - The Extension of the File.
	 */
	private String getFileExtension(File f) {

		String fileName = f.getName();
		int index = fileName.lastIndexOf('.');
		int length = fileName.length();

		String extension = fileName.substring(index + 1, length);
		return extension;
	}
	
	/**
	 * Getter for the MIME Type of the File Object.
	 * @param file
	 * @return - The MIME Type of the File....
	 * @throws IOException
	 */
	private String getFileMIMEType(File file) throws IOException {

		MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
		String mimeType = mimeTypesMap.getContentType(file);
		return mimeType;
	}

	
	/**
	 * This method gets the vehicle Data for the processed Files...
	 * @param fileFilter
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public List<FileDataVO> getVehicleDataForFiles(FileFilter fileFilter) 
				throws FileNotFoundException, IOException {
		
		List<FileDataVO> processedFiles 
			= fileFilter.processFiles("./src/main/resources", new String[] { "csv" });
		populateVehicleData(processedFiles);
		return processedFiles;
	}

	/**
	 * This method populated the vehicle data based on the processed Files avaialable....
	 * @param processedFiles
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void populateVehicleData(List<FileDataVO> processedFiles) throws FileNotFoundException, IOException {
		String data = null;

		// Getting the Data
		for (FileDataVO fileDataVO : processedFiles) {
			File file = fileDataVO.getFile();

			BufferedReader br = new BufferedReader(new FileReader(file));
			try {
				StringBuilder sb = new StringBuilder();
				String line = br.readLine();

				while (line != null) {
					sb.append(line);
					sb.append("\n");
					line = br.readLine();
				}
				data = sb.toString();
				String[] datas = data.split("\n");
				List<VehicleDetailsVO> vehicleDetails = null;
				
				if (datas != null && datas.length > 0) {
					vehicleDetails = new ArrayList<VehicleDetailsVO>();
				}

				for (String dataItem : datas) {

					String vehicleDatas[] = dataItem.split(",");
					VehicleDetailsVO vehicleDetail = new VehicleDetailsVO();
					int index = 0;
					
					vehicleDetail.setVrm(vehicleDatas[index++]);
					vehicleDetail.setMake(vehicleDatas[index++]);
					vehicleDetail.setColor(vehicleDatas[index++]);

					vehicleDetails.add(vehicleDetail);
				}
				fileDataVO.setVehicleDetails(vehicleDetails);

			} finally {
				br.close();
			}
		}
	}
	
	/**
	 * Main method for this class
	 * @param args
	 * @throws Exception
	 */
	public static void main(String args[]) throws Exception {

		FileFilter fileFilter = new FileFilter();
		fileFilter.getVehicleDataForFiles(fileFilter);
	}

}
