package com.uttara.taskmvc;

import java.io.File;
import java.util.Scanner;

public class StartApp {

	public static void main(String[] args) {
		
		try {
			
			Scanner scanInt = new Scanner( System.in );
			Scanner scanWord = new Scanner( System.in );
			
			int choice1 = 0;
			String categoryName = null;
			
			TaskModel model = TaskModel.getInstance();
			
			TaskUtil.log( "Starting task manager......." );
			
			while( choice1 != 5 ) {
				
				TaskUtil.printWhiteSpace( 1 );
				
				System.out.println( "Press 1 to Create Category" );
				System.out.println( "Press 2 to Load Category" );
				System.out.println( "Press 3 to Search" );
				System.out.println( "Press 4 to List" );
				System.out.println( "Press 5 to Exit" );
				
				TaskUtil.enterChoice();
				
				choice1 = TaskUtil.validateIntInput( scanInt );
				
				switch ( choice1 ) {
				
				case 1: {
					
					TaskUtil.printWhiteSpace( 1 );
					
					TaskUtil.log( "Creating category.." );
					categoryName = TaskUtil.createCategory( scanWord );
					TaskUtil.log( "Category with name "+categoryName+" created" );
					
					TaskUtil.printWhiteSpace( 1 );
					TaskUtil.caseImplementation( categoryName );
					
					break;
				}
				
				case 2: {
					
					int count = 1;
					int selectedNumber = 0;
					String selectedName;
					
					TaskUtil.log( "load category......" );
					
					File[] fa = model.listFilles();
					
					if( fa.length == 0 ) {
						
						TaskUtil.printWhiteSpace( 1 );
						System.out.println( "Any categories dosen't exist. Create category before loading categories." );
						categoryName = TaskUtil.createCategory(scanWord);
						
					}
					
					if( fa.length > 0 ) {
						
						System.out.println( "Existing categories" );
						System.out.println( "-------------------" );
						
						for( File file : fa ) {
							
							if( file.exists() && file.getName().contains( ".todo" )) {
								
								System.out.println( count + ".)" + file.getName() );
								count++;
								
							}
						}
						
						TaskUtil.printWhiteSpace( 1 );
						
						System.out.println( "Enter a number from the above list to load category" );
						selectedNumber = TaskUtil.validateIntInput( scanInt );
						
						while( !( selectedNumber >= 1 && selectedNumber < count ) ) {
							
							System.out.println( "Enter numbers from 1 to " +( count - 1 ) );
							selectedNumber = TaskUtil.validateIntInput( scanInt );
							
							TaskUtil.printWhiteSpace( 1 );
							
						}
						
						selectedName = fa[ selectedNumber - 1 ].getName();
						selectedName = selectedName.substring(0 , selectedName.length() - 5);
						categoryName = selectedName;
						
						
						TaskUtil.caseImplementation( categoryName );
					}
					
					break;
				}
				case 3: {
					
					while ( true ) {
						
						TaskUtil.printWhiteSpace( 1 );
						System.out.print( "Enter a string to search = " );
						String stringToSearch = null;
						
						stringToSearch = scanWord.nextLine();
						
						File[] fa = model.listFilles();
						
						int length = fa.length;
						
						if( length == 0 ) {
							
							TaskUtil.printWhiteSpace(1);
							System.out.println( "Any category dosen't exists. Please create category before searching." );
							
							TaskUtil.printWhiteSpace( 1 );
							break;
							
						}
						
						if( length > 0 ) {
							
							int count = 0;
							for( File f : fa ) {
								
								if( f.getName().contains( stringToSearch ) ) {
									
									count++;
								}
							}
							
							if( count > 0 ) {
								
								TaskUtil.printWhiteSpace( 1 );
								System.out.println( "Categories which matched the string." );
								
								TaskUtil.printWhiteSpace( 1 );
								
								int x = 1;
								
								for( File f : fa ) {
									
									String name = f.getName();
									
									if( name.contains( stringToSearch ) ) {
										
										System.out.println( x+ ". " +name );
										x++;
										
									}
								}
								TaskUtil.printWhiteSpace( 1 );
							}
							
							if( count == 0 ) {
								
								TaskUtil.printWhiteSpace( 1 );
								System.out.println( "Any category did not match match the string. Please input another string to search." );
								TaskUtil.printWhiteSpace( 1 );
								continue;
							}
							
							break;
						}
						else
							break;
					}
					break;
						
					}
					
				case 4: {
					
					File[] fa = model.listFilles();
					int length = fa.length;
					
					if( length == 0 ) {
						
						TaskUtil.printWhiteSpace( 1 );
						System.out.println( "Any category dosen't exists. Please create category before searching." );
						TaskUtil.printWhiteSpace( 1 );
						break;
					
					}
					
					if( length > 0 ) {
						
						int x = 0;

						TaskUtil.printWhiteSpace( 1 );
						System.out.println( "Categories present are listed below." );
						
						for( File f : fa ) {
							
							if( f.getName().contains( ".todo" )) {
								
								System.out.println( ++x+ ".) " +f.getName() );

							}
						}
						
						TaskUtil.printWhiteSpace( 1 );
					}
					
					break;
				}
				
				case 5: {
					
					TaskUtil.printWhiteSpace( 1 );
					System.out.println( "Bye bye!" );
					
					System.exit( 0 );
					break;
					
				}
				
				default: {
					
					TaskUtil.outOfRange();
					break;
					
				}
			}
		}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
}


