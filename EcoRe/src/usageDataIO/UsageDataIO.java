package usageDataIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

public class UsageDataIO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private FileOutputStream fop;
	private FileInputStream fip;
	private File file;
	
	public UsageDataIO(){ 
		open();
	}
	
	public void open(){
		try {
			file = new File("usage.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			
			fop = new FileOutputStream(file, true);
			fip = new FileInputStream(file);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void close(){
		try {
			fop.flush();
			fop.close();
			
			fip.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void write(String data){
		byte[] bytes = data.getBytes();
		try {
			fop.write(bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String read(){
    	StringBuffer fileContent = new StringBuffer("");
	    try {
	    	byte[] buffer = new byte[1024];

	    	while (fip.read(buffer) != -1) {
	    	    fileContent.append(new String(buffer));
	    	}
	    }catch (IOException e){
	    	e.printStackTrace();
	    }
    	return fileContent.toString();
	}

}
