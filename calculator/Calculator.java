//Designed to be a terminal based calculator
public class Calculator
{
	//main method
	public static void main(String[] args)
	{
		//run tests to make sure everything works
		runTests();

		//run application
		run();
	}

	//run tests
	private static void runTests()
	{
	}

	//run application
	private static void run()
	{
	}
}

//designed to parse console input
public class Parser
{
	private String ivnalidCharactersRegex = "[]";
	public static void parseInput(String input)
	{
		if(validate(input))
		{
			
		}
		else
		{
			System.out.println("Invalid Input");
		}
	}

	//use a regex to parse for valid characters
	private static boolean validate(String input)
	{
		return true;
	}
}
