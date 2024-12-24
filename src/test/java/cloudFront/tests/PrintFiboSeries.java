package cloudFront.tests;

public class PrintFiboSeries {
	
	public static void main(String[]args)
	{
		
//		Fibonacci Series upto 20
//		0 1 1 2 3 5 8 13 21 34 55 89 144 233 377 610 987 1597 2584 4181
		 
		int count =20;
		
		long num1 = 0, num2 = 1;
		
		System.out.println("Fibonacci series up to :" + count + "numbers");
		
		        for(int i =2; i< count; i++)
		        {
		        	long nextNum = num1+ num2;
		        	
		        	System.out.println(","  +  nextNum);
		        	
		        	num1= num2;
		        	num2= nextNum;
		        }
		
	}

}
