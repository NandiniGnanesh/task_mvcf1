package com.uttara.taskmvc;

import java.util.Comparator;

public class ComparatorFactory {

}

class NameComparator implements Comparator< TaskBean > {

	@Override
	public int compare( TaskBean arg0, TaskBean arg1 ) {
		
		return arg0.getTaskname().compareTo( arg1.getTaskname() );
		
	}
	
}

class DueDateComparator implements Comparator< TaskBean > {

	@Override
	public int compare( TaskBean arg0, TaskBean arg1 ) {
		
		return arg0.getDueDate().toString().compareTo( arg1.getDueDate().toString() );
	}
	
}

class CreationDateComparator implements Comparator< TaskBean > {

	@Override
	public int compare( TaskBean arg0, TaskBean arg1 ) {
		
		return arg0.getCreatedDate().compareTo( arg1.getCreatedDate() );
	}
	
}

class LongestTimeComparator implements Comparator< TaskBean > {
	
	@Override
	public int compare( TaskBean arg0, TaskBean arg1 ) {
		
		long l = ( arg0.getDueDate().getTime() - arg0.getCreatedDate().getTime() ) - ( arg1.getDueDate().getTime() - arg1.getCreatedDate().getTime() );
		
		if( l > 0 )
			return -1;
		
		if( l < 0 ) 
			return 1;
		
		else 
			return 0;
		
	}
}