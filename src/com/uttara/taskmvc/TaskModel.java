package com.uttara.taskmvc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;


public class TaskModel {

	private static TaskModel obj = null;
	
	private TaskModel() {
	
	}
	
	static {
		
		File file = new File( "projectFolder" );
		
		if( !file.exists() ) {
			
			file.mkdir();
			
		}
		
		File catFolder = new File( "projectFolder/todoCategoriesFolder ");
		
		if( !catFolder.exists() ) {
			
			catFolder.mkdir();
		
		}
		
		File logFolder = new File( "projectFolder/loggerFolder" );
		
		if( !logFolder.exists() ) {
			
			logFolder.mkdir();
		
		}
		
	}
	
	public static TaskModel getInstance() {
		
		if( obj == null ) {
			
			obj = new TaskModel();
		
		}
		
		return obj;
	
	}

	public void createCategory( String categoryName ) throws IOException {
		
		File f = new File( Constants.CATEGORY_PATH + categoryName+ ".todo" );
		f.createNewFile();
		
	}
	
	public boolean validateCategoryNameExists( String categoryName ) {
		
		File f = new File( Constants.CATEGORY_PATH + categoryName + ".todo" );
		return f.exists();
		
	}
	
	public File[] listFilles() {
		
		File file = new File( Constants.CATEGORY_PATH );
		
		File[] fileArray = file.listFiles();
		
		return fileArray;
		
	}
	
	public boolean taskNameAlreadyExists( String taskName , String categoryName ) {
		
		File f = new File( Constants.CATEGORY_PATH + categoryName + ".todo" );
		
		if( f.exists() ) {
			
			BufferedReader br    = null;

			try {
				
				br = new BufferedReader( new FileReader( f) );
				String line;                          
				
				while(( line = br.readLine() ) != null ) {
					
					String tname = line.split( ":" )[ 0 ];
					
					if( tname.equals( taskName ) ) {
						
						return true;
						
					}
					
				}
				
			} catch ( Exception e ) {
				
				e.printStackTrace();
				
			}
			
			finally {
				
				if( br != null ) {
					
					try {
						
						br.close();
						
					} catch ( Exception e2 ) {
						
						e2.printStackTrace();
						
					}
				}
			}
			
			return false;
		
		} else {
		
			return false;
		
		}	
	}

	public void addTask( TaskBean bean, String categoryName ) {
		 
		BufferedWriter bw = null;
		
		try {
			
			bw = new BufferedWriter( new FileWriter( Constants.CATEGORY_PATH + categoryName+ ".todo" , true ));
			
			bw.write( bean.toString() );
			bw.newLine();
			
		} catch ( Exception e ) {
			
			e.printStackTrace();
		
		}
		
		finally {
			
			if( bw != null ) {
				
				try {
				
					bw.close();
				
				} catch ( Exception e2 ) {
					
					e2.printStackTrace();
				
				}
			}
		}
		
	}
	
	public ArrayList< TaskBean > getTasks ( String categoryName ) throws IOException
	{
		ArrayList< TaskBean > beanList   = new ArrayList< TaskBean >() ;
		TaskBean bean        = null ;
		String line          = null ;
		BufferedReader br    = null ;
		String[] arr         = null ;
		SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy" );
		File f               = new File( Constants.CATEGORY_PATH + categoryName+ ".todo" );
		
		if( f.exists() ) {
			
			try
			{
				br = new BufferedReader( new FileReader( f ) );
					
					while( ( line = br.readLine() ) != null ) {
						
						arr  = line.split( ":" );
						
						bean = new TaskBean( arr[0], arr[1], arr[2], Integer.parseInt(arr[3]), sdf.parse(arr[4]), sdf.parse(arr[5]), arr[6] );
						beanList.add( bean );
						
					}

			}
			catch ( Exception e ) {
				
				e.printStackTrace();
			
			}
			
			finally	{
				
				if ( br != null )
				
					br.close();
			
			}
		}
		
		return beanList;
	}

public static void saveEditedTasks( String categoryName, ArrayList< TaskBean > beanList ) {
		
		BufferedWriter bw = null ;
		
		try
		{
			bw = new BufferedWriter( new FileWriter( Constants.CATEGORY_PATH+ categoryName +".todo") );
			
			for( TaskBean bean : beanList )	{
				
				bw.write( bean.toString() );
				bw.newLine();
				
			}
			
		}
		
		catch( IOException i ) {
			
			i.printStackTrace();
		
		}
		
		finally	{
			
				if( bw != null )
				try	{
					
					bw.close();
				
				}
				
				catch (IOException e) {
					
					e.printStackTrace();
				
				}
		}
		
	}

	public Comparator< TaskBean > getComparator( int ch ) {
		
	if( ch == 1 )
		return new NameComparator();
	
	if( ch == 2 )
		return new DueDateComparator();
	
	if( ch == 3 )
		return new CreationDateComparator();
	
	else		
		return new LongestTimeComparator();
		
	}


}

