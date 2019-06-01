class Main {
	
	public static Scanner in = new Scanner(System.in);
        

	//main method
	public static void main(String[] args) 
	{
		School sch = new School();
		sch.enterSchool();
		int x;

		x = display();
		
		do
		{
			if(x >=1 && x <=14)
				action(sch, x);
			else if(x == 0)
				System.exit(0);
			x = display();
		}while(x != 0 || x == -1); 
	
	}

	/*
	Name: display(): int
	Input -> none
	Output -> choice: int
	Purpose -> to display a menu and return the user's choice
	*/
	public static int display()
	{
		int choice = 0;
		String p;

		System.out.println("");
		System.out.println("1. Admit a Student to the School");
		System.out.println("2. Expel a Student from the School");
		System.out.println("3. Hire a teacher");
		System.out.println("4. Fire a teacher" );
		System.out.println("5. Start a new Course");
		System.out.println("6. Stop a Course" );
		System.out.println("7. Add a student to a class");
		System.out.println("8. Drop a student from a class" );
		System.out.println("9. Assign a teacher to teach a class" );
		System.out.println("10. Stop a teacher from teaching a class" );
		System.out.println("11. View the details of a particular student");
		System.out.println("12. View the details of a particular teacher" );
		System.out.println("13. View the details of a particular course" );
		System.out.println("14. Print School Details." );
		System.out.println("15. Quit program!!!" );

		System.out.println("");
		System.out.print("Enter Choice of action: ");
		p = in.next();

		if(p.length() == 2)
		{
			choice = (int)p.charAt(0) - (int)'0';
			choice *= 10;
			choice += (int)p.charAt(1) - (int)'0';
		}
		else if (p.length() == 1)
		{
			choice = (int)p.charAt(0) - (int)'0';
		}
		//(Character.isLetter(p.charAt(0)) || Character.isLetter(p.charAt(1)))
		//return
		if((p.length() == 1) && (Character.isLetter(p.charAt(0))))
		{
			System.out.println("Invalid Input!!!");
			return 16;
		}
		else if ((p.length() > 1) && ((Character.isLetter(p.charAt(0)) || Character.isLetter(p.charAt(1)))) )
		{
			System.out.println("Invalid Input!!!");
			return 16;
		}
		else if(choice == 15)
		{
			String sure;
			System.out.println("Are you sure you want to quit (Y or anything else): ");
			sure = in.next();

			if(sure.equals("Y") || sure.equals("y"))
			{
				System.out.println("Bye :) :) ");
				return 0;
			}
			else
			{
				System.out.println("Continue.....");
				return -1;
			}
		}
		else if(choice < 1 || choice > 15)
		{
			System.out.println("Invalid Input!!!");
			return 16;
		}
		return choice;
	}	

	/*
	Name: action(): void
	Input -> sch: School&, x: int& 
	Output -> none
	Purpose -> to implement the user's choice
	*/
	public static void action(School sch, int x)
	{
		System.out.println("");
		System.out.println("");
		System.out.println("********************************************" );

		switch(x)
		{
			case 1: System.out.println( "Student Admission" ); sch.admit_student(); break;
			case 2: System.out.println( "Student Expulsion" ); sch.expel_student(); break;
			case 3: System.out.println("Teacher Employment" ); sch.hire_teacher(); break;
			case 4: System.out.println( "Teacher Employment Termination"); sch.fire_teacher(); break;
			case 5: System.out.println( "Course Commencement" );  sch.start_course(); break;
			case 6: System.out.println("Course Termination" ); sch.stop_course(); break;
			case 7: System.out.println( "Adding Student to Course" ); sch.add_student_to_class(); break;
			case 8: System.out.println( "Dropping Student from Course" );sch.remove_student_from_class(); break;
			case 9: System.out.println( "Assigning Teacher to Course" ); sch.assign_teacher_to_class(); break;
			case 10: System.out.println("Removing Teacher from Course"); 				sch.remove_teacher_from_class(); break;
			case 11: System.out.println("Student Details"); sch.view_student_details(); break;
			case 12: System.out.println("Teacher Details"); sch.view_teacher_details(); break;
			case 13: System.out.println("Course Details"); sch.view_course_details(); break;
			case 14: System.out.println("School Details"); sch.print(); break;
		}

		System.out.println("********************************************" );
	}
   
	
}
