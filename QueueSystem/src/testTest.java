
public class testTest {

	public static void main(String[] args) 
	{
		Test t = new Test();
		
		if (t.tQueue())
			System.out.println("queue WORKING\n");
		else
			System.out.println("queue NOT working\n");
		
		if (t.tList())
			System.out.println("list WORKING\n");
		else
			System.out.println("list NOT working\n");
	}

}
