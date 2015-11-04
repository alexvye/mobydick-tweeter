package vye.md;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.springframework.core.io.ClassPathResource;

public class SourceReader {
	
	public static final int TWEET_SIZE = 140;
	private static StringBuffer buffer;
	public static int CHUNK_SIZE = 80;
	
	public static void readSource(String filename) throws Exception {

		String text = readText(filename);
		String newBuffer = removeWhiteSpace(text);
		
		String positionFile = "pos.txt";
		File f = new File(positionFile);
		
		Integer pos = 0;

		if(f.exists()){
			pos = readState(positionFile);
		} 
		System.out.println("pos is " + pos);
		String nc = getNextChunk(newBuffer.toString(),pos);
		System.out.println(nc);
		Integer newPos = new Integer(pos.intValue()+CHUNK_SIZE-1);
		if(newPos >= newBuffer.length()) {
			newPos = 0;
		}
		writeState(positionFile,newPos);
	}

	
	private static String getNextChunk(String buffer, int pos) {
		if(pos < buffer.length()) {
			String chunk = "";
			if(pos + CHUNK_SIZE-1<buffer.length()) {
				int l = pos + CHUNK_SIZE - 1;
				chunk = buffer.substring(pos,l);
			} else {
				chunk = buffer.substring(pos);
			}
			return chunk;
		} else {
			return null;
		}
	}
	
	private static Integer readState(String fileName) throws Exception {
		FileInputStream fin = new FileInputStream(fileName);
		ObjectInputStream ois = new ObjectInputStream(fin);
		Integer position = (Integer) ois.readObject();
		ois.close();
		return position;
	}
	
	private static void writeState(String fileName, Integer position) throws Exception {
		FileOutputStream fout = new FileOutputStream(fileName);
		ObjectOutputStream oos = new ObjectOutputStream(fout);   
		oos.writeObject(position);
		oos.close();
	}
	
	private static String readText(String fileName) throws Exception {
		ClassPathResource mdFile = new ClassPathResource(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(mdFile.getInputStream()));

		String line = reader.readLine();
		line = reader.readLine(); // toss out first line

		buffer = new StringBuffer("");
		while(line!=null) {
			buffer.append(line);
			line = reader.readLine();
		}
		reader.close();
		return buffer.toString();
	}
	
	private static String removeWhiteSpace(String buffer) throws Exception {
		boolean onBlanks = false;
		StringBuffer newBuffer = new StringBuffer(buffer.length());
		for(int i=0;i<buffer.length();i++) {
			char nc = buffer.charAt(i);
			if(nc != ' ') {
				if(onBlanks) {
					onBlanks = false;
				}
				newBuffer.append(nc);
			} else {
				if(!onBlanks) {
					onBlanks = true;
					newBuffer.append(nc);
				}
			}
		}
		return buffer.toString();
	}
}
