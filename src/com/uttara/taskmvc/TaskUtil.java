package com.uttara.taskmvc;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import java.util.Scanner;

public class TaskUtil {

	static TaskModel model = TaskModel.getInstance();
	
	public static void log( String data ) {
		
		Logger.getInstance().log( data );
		
	}
	
	public static int validateIntInput( Scanner s ) {
		
		int val = 0;
		
		while( !s.hasNextInt() ){
			
			TaskUtil.printWhiteSpace( 1 );
			System.out.println( "Enter only int value" );
			
			TaskUtil.printWhiteSpace( 1 );
			s.next();
			
		}
		
		val = s.nextInt();
		return val;
		
	}
	
	public static void printWhiteSpace( int num ) {
		
		for( int i = 1 ; i <= num; i++ ) {
		
			System.out.println("");
		}
		
	}

	public static void outOfRange() {
		
		System.out.println( "Entered choice is out of range , please select range in the following menu" );
		TaskUtil.printWhiteSpace( 1 );
		
	}
	
	public static void enterChoice() {
		
		TaskUtil.printWhiteSpace( 1 );
		System.out.println("Enter choice = ");
		
	}

	public static void caseImplementation(String categoryName) throws ParseException, IOException {
		
		int ch1 = 0;
		String taskName    = null;
		String description = null;
		String status      = null;
		int priority       = 0;
		Date createdDate   = null;
		String stringDate;
		Date dueDate       = null; 
		String tags        = null;
		
		Scanner scanInt = new Scanner( System.in );
		Scanner scanWord = new Scanner( System.in );
		
		SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy" );
		
		while( ch1 != 6 ) {
			
			TaskUtil.printWhiteSpace( 1 );
			
			System.out.println( "Press 1 to Add a Task" );
			System.out.println( "Press 2 to Edit a Task" );
			System.out.println( "Press 3 to Remove a Task" );
			System.out.println( "Press 4 to List the Tasks" );
			System.out.println( "Press 5 to Search" );
			System.out.println( "Press 6 to Go back" );
			
			TaskUtil.enterChoice();
			
			ch1 = TaskUtil.validateIntInput( scanInt );
			
			switch ( ch1 ) {
			
			case 1: {
				
				TaskUtil.log("adding task...");
				
				taskName    = TaskUtil.taskNameMethod ( scanWord , categoryName );
				description = TaskUtil.descriptionMethod ( scanWord );
				status      = TaskUtil.statusMethod ( scanInt );
				priority    = TaskUtil.priorityMethod ( scanInt );
				stringDate  = sdf.format ( new Date() );					
				createdDate = sdf.parse  ( stringDate );
				dueDate     = TaskUtil.dueDateMethod ( scanWord );
				tags        = TaskUtil.tagsMethod ( scanWord );
				
				TaskBean bean = new TaskBean( taskName , description , status , priority , createdDate , dueDate , tags );
				
				model.addTask( bean , categoryName );
				TaskUtil.log( "Task with name "+taskName+" added in category "+categoryName );
			
				break;
				
			}
			case 2: {
				
				TaskUtil.log( "editing task......." );			
				TaskUtil.editTask( scanWord , scanInt , categoryName );
				
				TaskUtil.log( "Task with name "+taskName+" in category "+categoryName+" edited" );
				break;
				
			}
			case 3: {
				
				TaskUtil.log( "removing task......" ); 
				
				TaskUtil.removeTask( scanWord , scanInt , categoryName );
				break;
				
			}
			case 4: {
				
				int ch = 0;
				
				TaskUtil.log( "listing tasks..." );
				
				while( ch != 5 ) {
					
					TaskUtil.printWhiteSpace( 1 );
					
					System.out.println( "Press 1 to list tasks by alphabetical listing by name" );
					System.out.println( "Press 2 to list tasks by due date" );
					System.out.println( "Press 3 to list tasks by created date" );
					System.out.println( "Press 4 to list tasks by longest time" );
					System.out.println( "Press 5 to go back" );
					
					TaskUtil.enterChoice();
					ch = TaskUtil.validateIntInput( scanInt );
					
					ArrayList< TaskBean > beanLists = model.getTasks ( categoryName );
					
					switch( ch ) {
					
					case 1 : {
						
						if( beanLists.size() < 0 ) {
							
							System.out.println( "Category "+categoryName+" does not contain any tasks. Add task before listing" );
							TaskUtil.printWhiteSpace( 1 );
						
						} else {
							
							System.out.println( "------------Task sorted by alphabetical order--------------" );
							
							TaskUtil.printWhiteSpace( 1 );
							
							Comparator< TaskBean > comp = model.getComparator( 1 );
							Collections.sort( beanLists ,  comp );
							
							for( TaskBean tb : beanLists ) {
								
								System.out.println( tb );
								
							}
						}
						
						break;
						
					}
					case 2 : {
						
						if( beanLists.size() < 0 ) {
							
							System.out.println( "Category "+categoryName+" does not contain any tasks. Add task before listing" );
							TaskUtil.printWhiteSpace( 1 );
						
						} else {
							
							System.out.println( "------------Task sorted by due date --------------" );
							
							TaskUtil.printWhiteSpace( 1 );
							
							Comparator< TaskBean > comp = model.getComparator( 2 );
							Collections.sort( beanLists ,  comp );
							
							for( TaskBean tb : beanLists ) {
								
								System.out.println( tb );
							
							}
						}
						
						break;
						
					}
					case 3 : {
						
						if( beanLists.size() < 0 ) {
							
							System.out.println( "Category "+categoryName+" does not contain any tasks. Add task before listing" );
							TaskUtil.printWhiteSpace( 1 );
						
						} else {
							
							System.out.println( "------------Task sorted by created date --------------" );
							
							TaskUtil.printWhiteSpace( 1 );
							
							Comparator< TaskBean > comp = model.getComparator( 3 );
							Collections.sort( beanLists ,  comp );
							
							for( TaskBean tb : beanLists ) {
								
								System.out.println( tb );
								
							}
						}
	
						break;
	
					}
					case 4 : {
	
						if( beanLists.size() < 0 ) {
							
							System.out.println( "Category "+categoryName+" does not contain any tasks. Add task before listing" );
							TaskUtil.printWhiteSpace( 1 );
						
						} else {
							
							System.out.println( "------------Task sorted by Longest time to complete task--------------" );
							
							TaskUtil.printWhiteSpace( 1 );
							Comparator< TaskBean > comp = model.getComparator( 4 );
							Collections.sort( beanLists ,  comp );
							
							for( TaskBean tb : beanLists ) {
								
								System.out.println( tb ); 
								
							}
						}
						
						break;
	
					}
					case 5 : {
						
						System.out.println( "Going back.." );
	
						break;
	
					}

				}
					
			}
				break;
			}
			case 5: {
				
				String searchString = null;
				int taskNameCount   = 0;
				int descCount       = 0;
				int tagsCount       = 0;
				int total           = 0;
				
				ArrayList< TaskBean > beanLists = model.getTasks( categoryName );
				ArrayList< String > taskNameOccurences = new ArrayList< String >();
				ArrayList< String > descOccurences = new ArrayList< String >();
				ArrayList< String > tagsOccurences = new ArrayList< String >();
				
				TaskUtil.log( "searching tasks...." );	
				
				System.out.println( "Enter String to search" );
				searchString = scanWord.nextLine();
				
				
				if( beanLists.size() < 0 ) {
					
					System.out.println( "Category "+categoryName+" does not contain any tasks. Add task before searching" );
					TaskUtil.printWhiteSpace( 1 );
				
				} else {
					
					for( TaskBean tb : beanLists ) {
						
						if( tb.getTaskname().contains( searchString ) ) {
							
							taskNameOccurences.add( tb.getTaskname() );
							taskNameCount++;
							
						}
						
						if( tb.getDescription().contains( searchString ) ) {
							
							descOccurences.add( tb.getTaskname() + " - " + tb.getDescription() );
							descCount++;
							
						}
						
						if( tb.getTaskname().contains( searchString ) ) { 
							
							tagsOccurences.add( tb.getTaskname() );
							tagsCount++;
							
						}
					}
					
					total = taskNameCount + descCount + tagsCount ;
					
					System.out.println( "Total number of occurences : " +total );
					TaskUtil.printWhiteSpace( 1 );
					
					System.out.println(" Number of occurances in desc : "+descCount );
					System.out.println( "Matches found" );
					TaskUtil.printWhiteSpace( 1);
					
					for( String s : descOccurences ) {
						
						System.out.println( s );
						
					}
					
					TaskUtil.printWhiteSpace( 1 );
					
					System.out.println( "Number of occurances in name : "+taskNameCount );
					System.out.println( "Matches found" );
					TaskUtil.printWhiteSpace( 1 );
					
					for( String s : taskNameOccurences ) {
						
						System.out.println( s );
						
					}
					
					TaskUtil.printWhiteSpace( 1 );
					
					System.out.println( "Number of occurances in tags : "+tagsCount );
					System.out.println( "Matches found" );
					TaskUtil.printWhiteSpace( 1 );
					
					for( String s : tagsOccurences ) {
						
						System.out.println( s );
						
					}
					
				}
				
				break;
			}
			case 6: {
				
				TaskUtil.printWhiteSpace( 1 );
				System.out.println( "going back...." );
				
				TaskUtil.printWhiteSpace( 1 );
				break;
				
			}
			default:{
				
				TaskUtil.printWhiteSpace( 1 );
				
				TaskUtil.outOfRange();
				break;
				
			}
			
			}
		}
	}

	private static void removeTask( Scanner scanWord, Scanner scanInt, String categoryName ) throws IOException {
		
		ArrayList< TaskBean > beanList = model.getTasks ( categoryName );
		int listSize = beanList.size();
		
		if( beanList.size() == 0 ) {
			
			TaskUtil.printWhiteSpace( 1 );
			System.out.println( "Category " +categoryName+ " does not contain any tasks. Add task before removing" );
			
		}
		
		if( beanList.size() > 0 ) {
			
			TaskUtil.printWhiteSpace( 1 );
			int k = 0;
			
			for( int j = 0 ; j < listSize ; j++ ){
				
				k = j;
				System.out.println( k+1 +".) "+ beanList.get(j) );
				
			}
			
			TaskUtil.printWhiteSpace( 1 );
			System.out.println( "Enter the task number from the above list to remove." );
			
			TaskUtil.enterChoice();
			
			int taskNumber = 0 ;
			
			while ( true ) {
				
				taskNumber = TaskUtil.validateIntInput( scanInt );
				
				if ( ( taskNumber < 1 ) || ( taskNumber > listSize ) ) {
					
					TaskUtil.printWhiteSpace( 1 );
					System.out.println( "Entered value is out of range. Enter the value in the range " +1+ " to "+listSize );
					continue;
					
				} else {
					
					break ;
				
				}
			}
			
			beanList.remove( taskNumber - 1 );
			TaskModel.saveEditedTasks( categoryName, beanList );
			
			TaskUtil.log( "Task removed successfully from the category " +categoryName );
			
		}
		
	}

	private static void editTask( Scanner scanWord, Scanner scanInt, String categoryName ) throws ParseException, IOException {
		
		
		ArrayList< TaskBean > beanList = model.getTasks ( categoryName );
		int listSize = beanList.size();
		
		if( beanList.size() == 0 ) {
			
			TaskUtil.printWhiteSpace( 1 );
			System.out.println( "Category " +categoryName+ " does not contain any tasks. Add task before editing" );
			
		}
		
		if( beanList.size() > 0 ) {
			
			TaskUtil.printWhiteSpace( 1 );
			int k = 0;
			
			for( int j = 0 ; j < listSize ; j++ ) {
				
				k = j;
				System.out.println( k+1 +".) "+ beanList.get( j ) );
				
			}
			
			TaskUtil.printWhiteSpace( 1 );
			System.out.println( "Enter the task number from the above list to edit." );
			TaskUtil.enterChoice();
			
			int taskNumber = 0 ;
			
			while ( true ) {
				
				taskNumber = TaskUtil.validateIntInput( scanInt );
				
				if ( ( taskNumber < 1 ) || ( taskNumber > listSize ) ) {
					
					TaskUtil.printWhiteSpace( 1 );
					System.out.println( "Entered value is out of range. Enter the value in the range " +1+ " to "+listSize );
					continue;
					
				} else {
					
					break ;
				
				}
			}
			
			TaskBean beanToEdit = beanList.get( taskNumber-1 );
			
			
			int toEditNumber = 0;
			
			while( toEditNumber != 8) {
				
				TaskUtil.printWhiteSpace( 1 );
				
				System.out.println( "Enter 1 to edit task name" );
				System.out.println( "Enter 2 to edit task Description" );
				System.out.println( "Enter 3 to edit task status" );
				System.out.println( "Enter 4 to edit task priority" );
				System.out.println( "Enter 5 to edit task due date" );
				System.out.println( "Enter 6 to edit task tags" );
				System.out.println( "Enter 7 to save changes" );
				System.out.println( "Enter 8 to go back" );
				
				TaskUtil.printWhiteSpace( 1 );
				
							
				while( true ) {
					
					toEditNumber = TaskUtil.validateIntInput( scanInt );
					
					if( ( toEditNumber < 1 ) || ( toEditNumber > 8 ) ) {
						
						TaskUtil.printWhiteSpace( 1 );
						System.out.println( "Entered value out of range. Please enter a value betweenn 1 - 8" );
						continue;
						
					} else {
						
						break;
					
					}
				}
				
				switch( toEditNumber ) {
				
					case 1: {

						String taskName = TaskUtil.taskNameMethod( scanWord, categoryName );
						beanToEdit.setTaskname( taskName );
						
						break;
					}
					
					case 2: {
						
						String taskDescription = TaskUtil.descriptionMethod( scanWord );
						beanToEdit.setDescription( taskDescription );
						
						break;
					}
					
					case 3:	{
						
						String taskStatus = TaskUtil.statusMethod( scanInt );
						beanToEdit.setStatus( taskStatus );
						
						break;
					}

					case 4: {
						
						int taskPriority = TaskUtil.priorityMethod( scanInt );
						beanToEdit.setPriority( taskPriority );
						
						break;
					}
					
					case 5:
					{
						Date dueDate = TaskUtil.dueDateMethod( scanWord );
						beanToEdit.setDueDate( dueDate );
						
						break;
					}

					case 6:
					{
						String taskTags = TaskUtil.tagsMethod( scanWord );
						beanToEdit.setTags( taskTags );
						
						break;
					}
					
					case 7:
					{
						beanList.remove( taskNumber-1 );
						beanList.add( taskNumber-1, beanToEdit );
						
						TaskModel.saveEditedTasks( categoryName, beanList );
						
						TaskUtil.printWhiteSpace( 1 );
						System.out.println( "Task addition completed" );
						TaskUtil.printWhiteSpace( 1 );
						
						break;
					
					}
					
					case 8: {
						
						System.out.println( "Going back to previous menu" );
						TaskUtil.printWhiteSpace( 1 );
						
						break;
						
					}
					
					default: {
						
						System.out.println( "Entered value out of range. Enter any value from below list" );
						TaskUtil.printWhiteSpace( 1 );
						
						break;
					
					}
					
				}								
					
			}
		}
		
	}

	private static boolean validateTaskName( String taskName ) {
		
		if( taskName == null )
			return false;
		
		if( taskName.trim().equals(" ") )
			return false;
		
		if( !Character.isLetter( taskName.charAt(0) ) )
			return false;
		
		for( int i = 1 ; i < taskName.length(); i++ ) {
			
			char c = taskName.charAt( i );
			
			if( !( Character.isLetter( c ) || Character.isDigit( c ) || Character.isWhitespace( c ) ) ) {
				
				return false;
				
			}
		}
		
		return true;
	}

	public static String createCategory( Scanner scanWord ) throws IOException {
		
			String categoryName;
			
			System.out.println( "Enter the name of a category" );
			categoryName = scanWord.nextLine();
			
			while( !TaskUtil.validateCategoryName( categoryName ) ) {
				
				TaskUtil.printWhiteSpace( 1 );
				
				System.out.println( "Category name starts with letter , no special character , not null , alphanumeric one....Enter another category name" );
				categoryName = scanWord.nextLine();
				
			}
			
			while( model.validateCategoryNameExists( categoryName ) ) {
				
				TaskUtil.printWhiteSpace( 1 );
				
				System.out.println( "Category name already exists. Enter another category name" );
				categoryName = scanWord.nextLine();
				
			}
			
			model.createCategory( categoryName );
			return categoryName;
		
	}

	public static boolean validateCategoryName( String categoryName ) {
		
		if( categoryName == null )
			return false;
		
		if( categoryName.trim().equals(" ") )
			return false;
		
		if( categoryName.split(" ").length > 1 )
			return false;
		
		if( !Character.isLetter( categoryName.charAt( 0 ) ) )
			return false;
		
		for( int i = 1 ; i < categoryName.length(); i++ ) {
			
			char c = categoryName.charAt( i );
			
			if( !(Character.isLetter( c ) || Character.isDigit( c ) ) ) {
				
				return false;
			}
		}
		
		return true;
		
	}
	
	static String taskNameMethod( Scanner scanWord , String categoryName ) {
		
		String taskName;
		
		TaskUtil.printWhiteSpace( 1 );
		
		System.out.println( "Enter task name" );
		taskName = scanWord.nextLine();
		
		while( !TaskUtil.validateTaskName( taskName ) ) {
			
			System.out.println( "Enter a valid task name. Task name should not be null , not empty , start with letter...." );
			taskName = scanWord.nextLine();
			
			TaskUtil.printWhiteSpace( 1 );
			
		}
		
		while( model.taskNameAlreadyExists( taskName , categoryName ) ) {
			
			System.out.println( "Task name already exists. Enter unique task name" );
			taskName = scanWord.nextLine();
			
			TaskUtil.printWhiteSpace( 1 );
			
		}
		
		return taskName;

	}
	

	private static String descriptionMethod( Scanner scanWord ) {
		 
		String desc;
		
		System.out.println( "Enter task description" );
		desc = scanWord.nextLine();
		
		return desc;
		
	}
	
	private static String statusMethod( Scanner scanInt ) {
		 
		int val = 0;
		
		System.out.println( "Press 1 to choose Pending status" );
		System.out.println( "Press 2 to choose Processing status" );
		System.out.println( "Press 3 to choose Completed status" );
		
		val = scanInt.nextInt();
		
		while( !( val >= 1 && val <= 3 ) ) {
			
			TaskUtil.printWhiteSpace( 1 );
			
			System.out.println( "Enter numbers from 1 to 3 only" );
			val = scanInt.nextInt();
		}

		if( val == 1 ) {
			
			return Constants.PENDING;
		
		}
		if( val == 2 ) {
		
			return Constants.PROCESSING;
		
		}
			return Constants.COMPLETED;
			
	}


	private static int priorityMethod( Scanner scanInt ) {
		
		int priority;
		
		System.out.println( "Enter priority from 1(low) to 10(high)" );
		priority = scanInt.nextInt();
		
		while( !( priority >= 1 && priority <= 10 ) ) {
			
			TaskUtil.printWhiteSpace( 1 );
			
			System.out.println( "Enter priority in the range of 1 to 10" );
			priority = scanInt.nextInt();
			
		}
		
		return priority;
	
	}
	

	private static Date dueDateMethod( Scanner scanWord ) throws ParseException {
		
		String sDueDate;
		Date dueDate = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy" );
		
		System.out.println( "Enter due date in dd/MM/yyyy format" );
		
		while( true ) {
			
			sDueDate = scanWord.nextLine();
			
			if( Constants.WRONG_FORMAT == TaskUtil.dateValidation( sDueDate ) ) {
				
				System.out.println( "Entered date format is wrong. Enter date in dd/MM/yyyy format" );
				
				TaskUtil.printWhiteSpace( 1 );
				
				continue;
				
			} else if( Constants.PREVIOUS == TaskUtil.dateValidation( sDueDate ) ) {
				
				System.out.println( "Entered previous date. Enter date after " +sdf.format( new Date() ) );
				
				TaskUtil.printWhiteSpace( 1 );
				
				continue;
				
			} else {
				
				dueDate = sdf.parse( sDueDate );
				break;
				
			}
			
		}
		
		return dueDate;
	
	}


	private static String dateValidation( String sDueDate ) {
		 
		Date dueDate;
		
		try {
			
			dueDate = new SimpleDateFormat( "dd/MM/yyyy" ).parse( sDueDate );
			
		} catch (Exception e) {
			
			return Constants.WRONG_FORMAT;
		}
		
		if( dueDate.before( new Date() ) ) {
			
			return Constants.PREVIOUS;
		}
		
		return Constants.SUCCESS;

	}

	private static String tagsMethod( Scanner scanWord ) {
		
		String tags;
		
		System.out.println( "Enter tags(comma separated)" );
		tags = scanWord.nextLine();
		
		TaskUtil.printWhiteSpace( 1 );
		return tags;
		
	}

}

