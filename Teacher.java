import java.util.ArrayList;
import java.util.Scanner;

class Teacher extends Person{

	Scanner in = new Scanner(System.in);
	
  	//data fields
    private StringBuilder field, rank;
    private ArrayList<StringBuilder> courses_teaching = new ArrayList<>();

	/*
	Type -> no-arguments constructor
	Input -> none
	Output -> none
	Purpose -> to initialise variables
	*/
	public Teacher()
	{
		field = new StringBuilder("");
		rank =new StringBuilder("");
	}

   	/*
	Type -> accessor
	Input -> none
	Output -> field: StringBuilder
	Purpose -> to get the field
	*/
   	public StringBuilder get_field()
	{
		return field;
	}

	/*
	Type -> accessor
	Input -> none
	Output -> rank: StringBuilder
	Purpose -> to get the rank
	*/
   	public StringBuilder get_rank()
	{
		return rank;
	}

   	/*
	Type -> accessor
	Input -> none
	Output -> courses_teaching: ArrayList<StringBuilder>
	Purpose -> to get the courses_teaching
	*/
	public ArrayList<StringBuilder> get_courses_teaching()
	{
		return courses_teaching;
	}

	/*
	Type -> mutators
	Input -> field: StringBuilder
	Output -> none 
	Purpose -> to change the field
	*/
   	public void set_field(StringBuilder nField)
	{
		field = nField;
	}

	/*
	Type -> mutators
	Input -> nClass: StringBuilder
	Output -> none 
	Purpose -> to change the classification
	*/
   	public void set_rank(StringBuilder nRank)
	{
		rank = nRank;
	}

	/*
	Type -> mutators
	Input -> nClass: StringBuilder
	Output -> none 
	Purpose -> to change the classification
	*/
   	public void add_to_course_teaching(StringBuilder course_name)
	{
		add_course(courses_teaching, course_name);
	}

	/*
	Type -> mutators
	Input -> nClass: StringBuilder
	Output -> none 
	Purpose -> to change the classification
	*/
   	public void erase_from_course_teaching(StringBuilder course_name)
	{
		remove_course(courses_teaching, course_name);
	}

   	/*
	Type -> enter
	Input -> none
	Output -> none 
	Purpose -> to enter the details of a teacher
	*/
	public void enterTeacher()
	{
		enter();
		String x;
		System.out.print("Enter Field: ");
		x = in.nextLine();
		field.append(x);

		System.out.print("Enter Rank: ");
		x = in.nextLine();
		rank.append(x);
	}

	/*
	Type -> print
	Input -> none
	Output -> none 
	Purpose -> to print the details of a teacher
	*/
	public void printTeacher()
	{
		print();
		System.out.println("Field: " + field);
		System.out.println("Rank: " + rank);
	}
}
