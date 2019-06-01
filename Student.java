import java.util.ArrayList;
import java.util.Scanner;

class Student extends Person{
	
	Scanner in = new Scanner(System.in);
    
	//data fields
    private StringBuilder major, classification;
    private ArrayList<StringBuilder> courses_taking  = new ArrayList<>();

  	/*
	Type -> no-arguments constructor
	Input -> none
	Output -> none
	Purpose -> to initialise variables
	*/
	public Student()
	{
		major = new StringBuilder("");
		classification = new StringBuilder("");
	}

    
	/*
	Type -> accessor
	Input -> none
	Output -> classification: StringBuilder
	Purpose -> to get the classification
	*/
    public StringBuilder get_classification()
	{
		return classification;
	}
    
	/*
	Type -> accessor
	Input -> none
	Output -> major: StringBuilder
	Purpose -> to get the major
	*/
	public StringBuilder get_major()
	{
		return major;
	}

	/*
	Type -> accessor
	Input -> none
	Output -> courses_taking: ArrayList<StringBuilder>
	Purpose -> to get the courses_taking
	*/
    public ArrayList<StringBuilder> get_courses_taking()
	{
		return courses_taking;
	}

	/*
	Type -> mutators
	Input -> nClass: StringBuilder
	Output -> none 
	Purpose -> to change the classification
	*/
    public void set_classification(StringBuilder nClass)
	{
		classification = nClass;
	}

	/*
	Type -> mutators
	Input -> major: StringBuilder
	Output -> none 
	Purpose -> to change the major
	*/
    public void set_major(StringBuilder nMajor)
	{
		major = nMajor;
	}

	/*
	Type -> mutators
	Input -> course_name: StringBuilder
	Output -> none 
	Purpose -> to add course_name to the courses_taking
	*/
    public void add_to_course_taking(StringBuilder course_name)
	{
		add_course(courses_taking, course_name);
	}
	
	/*
	Type -> mutators
	Input -> course_name: StringBuilder
	Output -> none 
	Purpose -> to remove course_name from the courses_taking
	*/
    public void erase_from_course_taking(StringBuilder course_name)
	{
		remove_course(courses_taking, course_name);
	}

	/*
	Type -> enter
	Input -> none
	Output -> none 
	Purpose -> to enter the details of a student
	*/
	public void enterStudent()
	{
		enter();
		String x;
		System.out.print("Enter Major: ");
		x = in.nextLine();
		major.append(x);

		System.out.print("Enter Classification: ");
		x = in.nextLine();
		classification.append(x);
	}

	/*
	Type -> print
	Input -> none
	Output -> none 
	Purpose -> to print the details of a student
	*/
	public void printStudent()
	{
		print();
		System.out.println("Major: " + major);
		System.out.println("Classification: " + major);
	}

}
