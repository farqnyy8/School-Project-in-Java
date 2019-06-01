import java.util.ArrayList;
import java.util.Scanner;

class Course{
	
	Scanner in = new Scanner(System.in);

  	//data fields
    private StringBuilder name, instructor;
    private ArrayList<StringBuilder> course_takers = new ArrayList<>();
    private int max; 

    
	/*
	Type -> Special private function
	Input -> type: ArrayList<StringBuilder>, name: StringBuilder 
	Output -> i: int
	Purpose -> to search for name in type and return -1 if it is not found
	*/
    private int search(ArrayList<StringBuilder> type, StringBuilder name)
	{
		for(int i = 0 ; i < type.size(); i++)
		{
    		if(type.get(i) == name)
      			return i;
  		}
  		return -1;
	}

  	/*
	Type -> no-arguments constructor
	Input -> none
	Output -> none
	Purpose -> to initialise variables
	*/
    public Course()
	{
		max = 10;
		name = new StringBuilder("");
		instructor = new StringBuilder("");
	}
	
	/*
	Type -> accessor
	Input -> none
	Output -> name: StringBuilder
	Purpose -> to get the name
	*/
    public StringBuilder get_name()
	{
		return name;
	}

	/*
	Type -> accessor
	Input -> none
	Output -> initialise: StringBuilder
	Purpose -> to get the instructor
	*/
    public StringBuilder get_instructor()
	{
		return instructor;
	}  
    
    /*
	Type -> mutator
	Input -> student: Student
	Output -> none
	Purpose -> to add a student to the course
	*/
    public void add_student(Student student)
	{
  		int find = search(course_takers, student.get_name());

  		if(find == -1 && course_takers.size() < max)
  		{
    		course_takers.add(student.get_name());
			System.out.println(student.get_name() + " has been added to " + name); 
			//add the course's name to the student's list of courses
    		student.add_to_course_taking(name);
  		}
 		 else if(find == -1 && course_takers.size() == max)
		  	System.out.print("Course capacity is already exhausted." );
 		else
		 	System.out.print(name + " is already taking this class");
	}
			
	/*
	Type -> mutator
	Input -> student: Student
	Output -> none
	Purpose -> to drop a student from the course
	*/
    public void drop_student(Student student)
	{
  		int find = search(course_takers, student.get_name());

  		if(find == -1)
    		System.out.println(student.get_name() + " was not taking this class.");
  		else
  		{
    		course_takers.remove(find);
			System.out.println(student.get_name() + "has been dropped from " + name );
			//remove the course's name from the student's list of courses
    		student.erase_from_course_taking(name);
  		}
	}

	/*
	Type -> mutators
	Input -> teacher: Teacher
	Output -> none
	Purpose -> to assign a teacher to the course
	*/
    public void assign_teacher(Teacher teacher)
	{
		if(instructor.equals("empty"))
  		{
	  		instructor = teacher.get_name();
			System.out.println(teacher.get_name() + " has been assigned to " + name);
  			//add the course's name to the teacher's list of courses
  			teacher.add_to_course_teaching(name);
  		}
  		else 
		{
			System.out.println("There is already a teacher for this class. \nTo assign another, follow due protocol by removing the former one first before assigning a new one.");
		}
	}

	/*
	Type -> mutators
	Input -> teacher: Teacher
	Output -> none
	Purpose -> to remove a teacher from the course
	*/
    public void remove_teacher(Teacher teacher)
	{
		//cout << teacher.get_name() << " has been removed from "<< name << endl;
		//remove the course's name from the teacher's list of courses
		instructor = new StringBuilder("empty");
		teacher.erase_from_course_teaching(name);
	}


   	/*
	Type -> enter
	Input -> none
	Output -> none 
	Purpose -> to enter the details of a teacher
	*/
   	public void enterCourse()
	{
		//char xy = in.next().charAt(0);
  		System.out.print("Enter Course Name: ");
  		String x;
		x = in.nextLine();
  		name.append(x);
	}

	/*
	Type -> print
	Input -> none
	Output -> none 
	Purpose -> to print the details of a teacher
	*/
	public void printCourse()
	{
		System.out.println("Course Name: " + name);
		System.out.println("Course Instructor Name: " + instructor);
		
		if(course_takers.isEmpty() == true)
			System.out.println("There is no student " + name + " yet." );
		else
		{
			for(int i = 0; i < course_takers.size(); i++)
				System.out.println("\tStudent " + i + 1 + ": " + course_takers.get(i));
		}
				
	}
}
