import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.*;

public class ClassOrganizer {
	
	public static void main(String[] args) throws IOException{
		Scanner scan = new Scanner(System.in);
		
		ClassOrganizer co = new ClassOrganizer();
		String answer = "";
		List<String> FilePaths = new ArrayList<String>();
		List<String> FileNames = new ArrayList<String>();
		System.out.println("Please select your first file");
		do {
			List<String> fileNameAndPath = co.getFileInfo();
			FilePaths.add(fileNameAndPath.get(1));
			FileNames.add(fileNameAndPath.get(0));
			System.out.println("Do you want to select another file? (Yes or No)");
			answer = scan.nextLine();
			answer = answer.toLowerCase();
		} while (answer.equals("yes"));
		
		
			
		System.out.println("Enter the number of classes you have: ");
		int numClasses = scan.nextInt();
		int count;
		scan.nextLine();
		List<String> Classes = new ArrayList<String>();
		for (count = 1; count <= numClasses; count++) {
			System.out.println("Enter the name of your class " + count);
			String tempClass = scan.nextLine();
			tempClass = tempClass.toLowerCase();
			Classes.add(tempClass);
		}		
		
		
		
		System.out.println("Enter your computer username:");
		String user = scan.nextLine();
		
		String path = "";
		boolean exists = false;
		boolean confirm = false;
		boolean userCheck = false;
		do {
			for (int b = 0; b < numClasses; b++) {
				path = "/Users/" + user + "/documents/" + Classes.get(b);
				File f1 = new File(path);
				confirm = f1.mkdir();
				exists = f1.exists();
				if (confirm == true) {
					System.out.println("The directory has been created");
				}
				if (confirm == false) {
					if (exists == true) {
						System.out.println("This directory already exists.");
						confirm = true;
					} else {
						userCheck = true;
					}
				}
				
			}
			if (userCheck == true) {
				System.out.println("Please enter the right username:");
				user = scan.nextLine();
				userCheck = false;
			}
			
		} while (confirm == false);
				
		String original = "";
		String destination = "";
		String fileName = "";
		for (int c = 0; c < FileNames.size(); c++) {
			original = FilePaths.get(c) + "/" + FileNames.get(c);
			fileName = FileNames.get(c);
			for (int d = 0; d < Classes.size(); d++) {
				if (fileName.contains(Classes.get(d))) {
					destination = "/Users/" + user + "/documents/" + Classes.get(d) + "/" + fileName;
					Path status = Files.move(Paths.get(original), Paths.get(destination));
					if (status != null) {
						System.out.println("File moved successfully");
					} else {
						System.out.println("Error moving file.");
					}
					
				}
			}
		}
		
		
	}
	public List getFileInfo() {
		List<String> tempInfo = new ArrayList<String>();
		JFileChooser ourFile = new JFileChooser();
	    ourFile.showDialog(null,"Please Select the File");
	    ourFile.setVisible(true);
	    File filePath = ourFile.getCurrentDirectory();
	    File fileName = ourFile.getSelectedFile();
	    tempInfo.add(fileName.getName());
	    tempInfo.add(filePath.getAbsolutePath());
	    return tempInfo;
	}
}
