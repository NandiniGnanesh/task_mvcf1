package com.uttara.taskmvc;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Logger {

	private static Logger obj = null;
	TaskModel m = TaskModel.getInstance();
	private Logger() {
		
	}
	
	public static Logger getInstance() {
		
		if(obj == null) {
			obj = new Logger();
		}
		return obj;
	}
	
	public void log(String data) {
		
		new Thread(new Runnable() {
			
			public void run() {
				
				BufferedWriter bw = null;
				try {
					
					bw = new BufferedWriter(new FileWriter(Constants.LOGGER_FILE_PATH , true));
					Date dt = new Date();
					String msg = dt + ":" +data;
					bw.write(msg);
					bw.newLine();
					
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				finally {
					
					if(bw!=null)
						try {
							bw.close();
						} catch (IOException e) {
					
							e.printStackTrace();
						}
				}
				}
		}).start();
	}
}
