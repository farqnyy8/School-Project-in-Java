import java.util.Scanner;
import java.util.ArrayList;

class School{

	Scanner in = new Scanner(System.in);

	//data fields
    private StringBuilder name;
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Teacher> teachers = new ArrayList<>();
    private ArrayList<Course> courses = new ArrayList<>();


	

	/*
	Type -> No-arguments constructor
	Input -> none
	Output -> none
	Purpose -> to initialise variables
	*/
	public School()
	{
		name = new StringBuilder("");
	}

	/*
	Type -> non member function
	Input -> courses: ArrayList<Course>, student: Student 
	Output -> none
	Purpose -> to remove student from all the classes
	*/
	void remove_student_from_all_courses(ArrayList<Course> courses, Student student)
	{
		//get the courses the student is taking
		ArrayList<StringBuilder> temp = student.get_courses_taking();

		for(int i = 0; i < courses.size(); i++)
		{
			for(int j = 0; i < temp.size(); i++)
    		{
      			if(courses.get(i).get_name().toString().equals(temp.get(j).toString()))
	  			//drop student from course
        			courses.get(i).drop_student(student);
    		}
  		}
	}

	/*
	Type -> private member function 
	Input -> courses: ArrayList<Course>&, teacher: Teacher& 
	Output -> none
	Purpose -> to remove teacher from all the classes
	*/
	void remove_teacher_from_all_courses(ArrayList<Course> courses, Teacher teacher)
	{
		//get the courses the teacher is teaching
		ArrayList<StringBuilder> temp = teacher.get_courses_teaching();

		for(int j = 0; j < temp.size(); j++)
    	{
			//remove teacher from all courses
			if(courses.get(j).get_name().toString().equals(temp.get(j).toString()))	
				courses.get(j).remove_teacher(teacher);

			//block of code to ask user to assign new teacher	
			{
				String assign;
				System.out.print("Do you want to assign a new teacher now (Yes or anything else): ");
				assign = in.nextLine();

				if(assign.equals("Yes") || assign.equals("yes"))
				{
					System.out.println("Continue by assign a teacher to this class......" );
					assign_teacher_to_class();
				}
				else
					System.out.println("Don't forget to assign a teacher later -> the students would be missing out.");
			}	
		}

	}

	/*
	Type -> non-member function 
	Input -> temp: ArrayList<string>&, name: string& 
	Output -> temp: boolean
	Purpose -> to search for name in temp
	*/
	boolean check_student_course(ArrayList<StringBuilder> temp, StringBuilder name)
	{
		for(int i = 0; i < temp.size(); i++)
		{
   		 	if(temp.get(i).toString().equals(name.toString()))
    		  	return true;
  		}
  		return false;
	}


	/*
	Type -> non-member function 
	Input -> students: ArrayList<Student>, teachers: ArrayList<Teacher>, course: Course 
	Output -> none
	Purpose -> to remove course from all teachers and students
	*/
	void remove_course_from_students_teachers(ArrayList<Student> students, ArrayList<Teacher> teachers, Course course)
	{
		//get course name
  		StringBuilder s = course.get_name();

		//to prevent re-writing code, use the higher number between teachers.size() and students.size()
  		for(int i = 0; i < teachers.size() && i < students.size(); i++)
  		{
    		//teachers
    		if(i < teachers.size())
			{
      			if(course.get_instructor().toString().equals(teachers.get(i).get_name().toString()))
      			{
		  			//erase course from teacher
        			teachers.get(i).erase_from_course_teaching(s);
      			}
    		}
    		//students
    		if(i < students.size())
    		{
				//get the student's list of courses
      			ArrayList<StringBuilder> temp = students.get(i).get_courses_taking();
      			boolean check = check_student_course(temp, s);
	  			//erase course from student
      			if(check == true)
        			students.get(i).erase_from_course_taking(s);
    		}
  		}

	}

	/*
	Type -> non-member function 
	Input -> students: ArrayList<Student>, name: StringBuilder 
	Output -> i: int
	Purpose -> to search for name in students
	*/
	int search_student(ArrayList<Student> students, StringBuilder name)
	{
  		for(int i = 0; i < students.size(); i++)
  		{
    		if(students.get(i).get_name().toString().equals(name.toString()))
      			return i;
  		}
  		return -1;
	}

	/*
	Type -> non-member function 
	Input -> teachers: ArrayList<Teacher>, name: StringBuilder 
	Output -> i: int
	Purpose -> to search for name in teachers
	*/
	int search_teacher(ArrayList<Teacher> teachers, StringBuilder name)
	{
  		for(int i = 0; i < teachers.size(); i++)
  		{
    		if(teachers.get(i).get_name().toString().equals(name.toString()))
     			return i;
  		}
  		return -1;
	}

	/*
	Type -> non-member function 
	Input -> courses: ArrayList<Course>, name: StringBuilder 
	Output -> i: int
	Purpose -> to search for name in courses
	*/
	int search_course(ArrayList<Course> courses, StringBuilder name)
	{
  		for(int i = 0; i < courses.size(); i++)
  		{
    		if(courses.get(i).get_name().toString().equals(name.toString()))
      			return i;
  		}
  		return -1;
	}

	/*
	Type -> mutator
	Input -> none
	Output -> none
	Purpose -> to add a Student to students
	*/
	public void admit_student()
	{
  		if(students.size() == 0)
  		{
    		Student temp = new Student();
    		temp.enterStudent();
    		students.add(temp);
			System.out.println(temp.get_name() + " has been admitted to " + name );
  		}
  		else
  		{
    		String temp_name;
			System.out.print("Enter Student Name to be admitted: " );
			//char xy = in.next().charAt(0);
    		temp_name = in.nextLine();

    		int find = search_student(students, new StringBuilder(temp_name));

    		if(find == -1)
    		{
				System.out.println("Press Enter to Continue....");
      			Student temp = new Student();
      			temp.enterStudent();
      			students.add(temp);
				System.out.println(temp.get_name() + "has been admitted to " + name );
    		}
    		else
				System.out.println(temp_name + " has already been admitted.");
  		}
	}

	/*
	Type -> mutator
	Input -> none
	Output -> none
	Purpose -> to remove a Student from students
	*/
	void expel_student()
	{
  		if(students.size() == 0)
  		{
			System.out.println("There is no student in the School yet.");
 		}
  		else
  		{
    		//char xy = in.next().charAt(0);
    		String temp_name;
			print_all_students();
    		System.out.print("Enter Student name to be expelled: ");
    		temp_name = in.nextLine();

    		int find = search_student(students, new StringBuilder(temp_name));

    		if(find == -1)
    		{
     			System.out.println( temp_name + " does not exist.");
    		}
    		else
    		{
				//removing student from all courses
      			remove_student_from_all_courses(courses, students.get(find));

      			students.remove(find);
	  			System.out.println(temp_name + " has been expelled.");
    		}
  		}
	}

	/*
	Type -> behaviour
	Input -> none
	Output -> none
	Purpose -> to add a Teacher to teachers
	*/
	void hire_teacher()
	{
  		if(teachers.size() == 0)
  		{
    		Teacher temp = new Teacher();
    		temp.enterTeacher();
    		teachers.add(temp);
			System.out.println(temp.get_name() + " has been hired to work at " + name );
 		}
  		else
  		{
    		//char xy = in.next().charAt(0);
    		String temp_name;
    		System.out.println("Enter Teacher Name to be hired: ");
    		temp_name = in.nextLine();

    		int find = search_teacher(teachers, new StringBuilder(temp_name));
    		if(find == -1)
    		{
				System.out.println("Press Enter to Continue....");
      			Teacher temp = new Teacher();
      			temp.enterTeacher();
      			teachers.add(temp);
	  			System.out.println(temp.get_name() + " has been hired to work at " + name );
    		}
    		else
      		System.out.println(temp_name + " has already been hired." );
  		}
	}

	/*
	Type -> mutator
	Input -> none
	Output -> none
	Purpose -> to remove a Teacher from teachers
	*/
	void fire_teacher()
	{
  		if(teachers.size() == 0)
  		{
    		System.out.println( "There is no teacher in the School yet." );
  		}
  		else
  		{
    		//char xy = in.next().charAt(0);
    		String temp_name;
			print_all_teachers();
    		System.out.print("Enter Teacher name to be fired: ");
    		temp_name = in.nextLine();

    		int find = search_teacher(teachers, new StringBuilder(temp_name));
    
    		if(find == -1)
    		{
      			System.out.println(temp_name + " does not exist.");
   			}
    		else
    		{
				//removing teacher from all courses
      			remove_teacher_from_all_courses(courses, teachers.get(find));

      			System.out.println(temp_name + " as been fired.");
      			teachers.remove(find);
    		}
  		}
	}

	/*
	Type -> mutator
	Input -> none
	Output -> none
	Purpose -> to add Course to courses
	*/
	void start_course()
	{
  		if(courses.size() == 0)
  		{
    		Course temp = new Course();
    		temp.enterCourse();
    		courses.add(temp);
			System.out.println(temp.get_name() + " has been started in " + name);
  		}
  		else
  		{
    		String temp_name;
   			System.out.println("Enter Course Name to be started: ");
    		temp_name = in.nextLine();

    		int find = search_course(courses, new StringBuilder(temp_name));
    		if(find == -1)
    		{
				System.out.println( "Press Enter to Continue....");
      			Course temp = new Course();
      			temp.enterCourse();
      			courses.add(temp);
	  			System.out.println(temp_name + " has been started in " + name);
    		}
    		else
      			System.out.println(temp_name + " has already been started.");
  		}
	}

	/*
	Type -> mutator
	Input -> none
	Output -> none
	Purpose -> to remove a Course from courses
	*/
	void stop_course()
	{
  		if(courses.size() == 0)
  		{
    		System.out.println("No course has been started in the School yet.");
 		}
  		else
  		{
			//char xy = in.next().charAt(0);
    		String temp_name;
			print_all_courses();
    		System.out.print("Enter Course name to be stopped: ");
    		temp_name = in.nextLine();

    		int find = search_course(courses, new StringBuilder(temp_name));

    		if(find == -1)
    		{
     			System.out.println(temp_name + " does not exist.");
    		}
    		else
    		{
				//remove course from all students and teachers
      			remove_course_from_students_teachers(students, teachers, courses.get(find));

      			System.out.println(temp_name + " has been stopped in " + name);
      			courses.remove(find);
    		}
  		}
	}

	/*
	Type -> mutator
	Input -> none
	Output -> none
	Purpose -> to display school details
	*/
	void print()
	{
  		System.out.println("School Name: " + name);
  		System.out.println("Number of Students: " + students.size());
  		System.out.println("Number of Teachers: " + teachers.size());
  		System.out.println("Number of Courses: " + courses.size());
	}

	/*
	Type -> mutator
	Input -> none
	Output -> none
	Purpose -> to add a student to a course
	*/
	void add_student_to_class()
	{
		if(students.size() == 0 || courses.size() == 0)
		{
			System.out.println("There are no Classes or Students in " + name + " yet. Add some." );
		}
		else
		{
  			String student_name, class_name;
  			int find_s, find_c;

			print_all_students();
			//getting student

			System.out.print("Enter the name of the student to be added or Q to quit action: ");
			//char xy = in.next().charAt(0);
    		student_name = in.nextLine();
    		find_s = search_student(students, new StringBuilder(student_name));
  			while(find_s == -1 && !(student_name.toString().equals("Q") || student_name.toString().equals("q")))
  			{
				System.out.println("Student not found");
    			System.out.print("Enter the name of the student to be added or Q to quit action: ");
    			student_name = in.nextLine();
    			find_s = search_student(students, new StringBuilder(student_name));
  			}

			if(!(student_name.toString().equals("Q")) || student_name.toString().equals("q"))
			{
				print_all_courses();
				//getting course
				System.out.print("Enter the name of the class  or Q to quit action: ");
    			class_name = in.nextLine();
    			find_c = search_course(courses, new StringBuilder(student_name));

				while(find_c == -1 && !(class_name.toString().equals("Q") || class_name.toString().equals("q")))
				{
					System.out.println("Class not found");
					System.out.print("Enter the name of the class  or Q to quit action: ");
    				class_name = in.nextLine();
    				find_c = search_course(courses, new StringBuilder(student_name));
				}
    
  				//add the student to the course
  				courses.get(find_c).add_student(students.get(find_s));
			}
		}
	}

	/*
	Type -> mutator
	Input -> none
	Output -> none
	Purpose -> to remove a student from a course
	*/
	void remove_student_from_class()
	{
		if(students.size() == 0 || courses.size() == 0)
		{
			System.out.println("There are no Classes or Students in " + name + " yet. Add some.");
		}
		else
		{
  			String student_name, class_name;
  			int find_s, find_c;

			//getting student
			print_all_students();
			System.out.print( "Enter the name of the student to be dropped or Q to quit action: ");
    		student_name = in.nextLine();
    		find_s = search_student(students, new StringBuilder(student_name));

  			while(find_s == -1 && !(student_name.equals("Q") || student_name.equals("q")))
  			{
				System.out.println("Student not found");
    			System.out.print("Enter the name of the student to be dropped or Q to quit action: ");
    			student_name = in.nextLine();
    			find_s = search_student(students, new StringBuilder(student_name));
  			}	

			if(!(student_name.equals("Q") || student_name.equals("q")))
			{
				print_all_courses();
				//getting class/course
				System.out.print("Enter the name of the class  or Q to quit action: ");
    			class_name = in.nextLine();
    			find_c = search_course(courses, new StringBuilder(class_name));

				while(find_c == -1 && !(class_name.equals("Q") || class_name.equals("q")))
				{
					System.out.println("Class not found");
					System.out.print("Enter the name of the class  or Q to quit action: ");
    				class_name = in.nextLine();
    			find_c = search_course(courses, new StringBuilder(class_name));
			}
    		//drop the student from the course
  			courses.get(find_c).drop_student(students.get(find_s));
			}
		}
	}


	/*
	Type -> mutator
	Input -> none
	Output -> none
	Purpose -> to assign a teacher to a course
	*/
	void assign_teacher_to_class()
	{

		if(teachers.size() == 0 || courses.size() == 0)
		{
			System.out.println("There are no Classes or Teachers in " + name + " yet. Add some." );
		}
		else
		{
			String teacher_name, class_name;
  			int find_t, find_c;
			
			print_all_courses();
			//getting class
			System.out.print("Enter the class you want to assign a teacher to or Q to quit action: ");
			//char xy = in.next().charAt(0);
			class_name = in.nextLine();
			find_c = search_course(courses, new StringBuilder(class_name));

			while(find_c == -1 && !(class_name.equals("Q") || class_name.equals("q")))
			{
				System.out.print("Enter the class you want to assign a teacher to or Q to quit action: ");
				class_name = in.nextLine();
				find_c = search_course(courses, new StringBuilder(class_name));
			}

			if(!(class_name.equals("Q") || class_name.equals("q")))
			{
				print_all_teachers();
				//getting teacher
				System.out.print("Enter the teacher you want to assign or Q to quit action: ");
				teacher_name = in.nextLine();
				find_t = search_teacher(teachers, new StringBuilder(teacher_name));
	
				while(find_t == -1 && !(teacher_name.equals("Q") || teacher_name.equals("q")))
				{
					System.out.print("Enter the teacher you want to assign or Q to quit action: ");
					teacher_name = in.nextLine();
					find_t = search_teacher(teachers, new StringBuilder(teacher_name));
				}

				//assign the teacher to the class
				courses.get(find_c).assign_teacher(teachers.get(find_t));
			}
		}
	}

	/*
	Type -> mutator
	Input -> none
	Output -> none
	Purpose -> to remove a teacher from a course
	*/
	void remove_teacher_from_class()
	{
		if(teachers.size() == 0 || courses.size() == 0)
		{
			System.out.println("There are no Classes or Teachers in " + name + " yet. Add some.");
		}
		else
		{
			String class_name;
  			int find_t, find_c;
	  		print_all_courses();
			//getting class
			System.out.print("Enter the class you want to remove a teacher from or Q to quit action: ");
			class_name = in.nextLine();
			find_c = search_course(courses, new StringBuilder(class_name));

			while(find_c == -1 && !(class_name.equals("Q") || class_name.equals("q")))
			{
				System.out.print("Enter the class you want to remove a teacher from or Q to quit action: ");
				class_name = in.nextLine();
				find_c = search_course(courses, new StringBuilder(class_name));
			}
			if(courses.get(find_c).get_instructor().toString().equals(""))
			{
				System.out.println("There is no teacher teaching this class yet. ");
			}
			else if(!(class_name.equals("Q") || class_name.equals("q")))
			{
				print_all_teachers();
				//getting teacher
				find_t = search_teacher(teachers, new StringBuilder(courses.get(find_c).get_instructor()));

				//removing teacher form course
				courses.get(find_c).remove_teacher(teachers.get(find_t));

				//choice to assign a new teacher
				String assign;
				System.out.print("Do you want to assign a new teacher now (Y or anything else): ");
				assign = in.nextLine();

				if(assign.equals("Y") || assign.equals("y"))
				{
					System.out.println("Continue by assign a teacher to this class......");
					assign_teacher_to_class();
				}
				else
					System.out.println("Don't forget to assign a teacher later -> the students would be missing out.");
			}
		}
	}

	/*
	Type -> viewer
	Input -> none
	Output -> none
	Purpose -> to print a course
	*/
	void view_course_details()
	{
		if(courses.size() == 0)
			System.out.println("There are no courses yet.");
		else if(courses.size() > 0)
		{
			String name;

			//char xy = in.next().charAt(0);
			print_all_courses();
			System.out.print("Enter the name of the Course you want to display: ");
			name = in.nextLine();

			int find = search_course(courses, new StringBuilder(name));

			if(find == -1)
				System.out.println(name + " does not exist.");
			else
				(courses.get(find)).printCourse();
		}
	}


	/*
	Type -> viewer
	Input -> none
	Output -> none
	Purpose -> to print a student
	*/
	void view_student_details()
	{
		if(students.size() == 0)
			System.out.println("There are no students yet.");
		else if(students.size() > 0)
		{
			String name;

			//char xy = in.next().charAt(0);
			print_all_students();
			System.out.print("Enter the name of the Student you want to display: ");
			name = in.nextLine();

			int find = search_student(students, new StringBuilder(name));

			if(find == -1)
				System.out.println(name + " does not exist.");
			else
				(students.get(find)).printStudent();
		}
	}

	/*
	Type -> viewer
	Input -> none
	Output -> none
	Purpose -> to print a teacher
	*/
	void view_teacher_details()
	{
		if(teachers.size() == 0)
			System.out.println("There are no teachers yet.");
		else if(teachers.size() > 0)
		{
			String name;

			//char xy = in.next().charAt(0);
			print_all_teachers();
			System.out.print("Enter the name of the Teacher you want to display: ");
			name = in.nextLine();

			int find = search_teacher(teachers, new StringBuilder(name));

			if(find == -1)
				System.out.println(name + " does not exist.");
			else
				(teachers.get(find)).printTeacher();
		}
	}

	/*
	Type -> viewer
	Input -> none
	Output -> none
	Purpose -> to print all students
	*/
	void print_all_students()
	{
		if(students.size() == 0)
		{
			System.out.println("There are no students yet. Add some.");
		}
		else
		{
			System.out.println("");
			System.out.println("List of Students.");
			for(int i = 0; i < students.size(); i++)
				System.out.println("\tStudent " + (i + 1) + ": " +  students.get(i).get_name());
			System.out.println("");
		}
	}

	/*
	Type -> viewer
	Input -> none
	Output -> none
	Purpose -> to print all teachers
	*/
	void print_all_teachers()
	{
		if(teachers.size() == 0)
		{
			System.out.println("There are no teachers yet. Add some.");
		}
		else
		{
			System.out.println("");
			System.out.println("List of Teachers.");
			for(int i = 0; i < teachers.size(); i++)
				System.out.println("\tTeacher " + (i + 1) + ": " + teachers.get(i).get_name());
			System.out.println("");
		}
	}

	/*
	Type -> viewer
	Input -> none
	Output -> none
	Purpose -> to print all courses
	*/
	void print_all_courses()
	{
		if(courses.size() == 0)
		{
			System.out.println("There are no students yet. Add some.");
		}
		else
		{
			System.out.println("");
			System.out.println("List of Courses.");
			for(int i = 0; i < courses.size(); i++)
				System.out.println("\tCourse " + (i + 1) + ": " + courses.get(i).get_name());
			System.out.println("");
		}
	}

	/*
	Type -> operator overloading
	Input -> inn: istream&, school: School&
	Output -> inn: istream& 
	Purpose -> to allow the use of cin
	*/
	public void enterSchool()
	{
  		System.out.print("Enter School Name: ");
  		String x = in.nextLine();
		name = new StringBuilder(x);
	}

}
