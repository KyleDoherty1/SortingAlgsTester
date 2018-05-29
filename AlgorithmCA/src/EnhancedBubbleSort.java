
public class EnhancedBubbleSort {
	static int swaps=0;
    static int comps=0;
    static long startTime, endTime, timeTaken;
	
	public static void sort(int[] array) {
		
		swaps=0;
	 	comps=0;
	 	startTime = System.nanoTime();
	    
	    
	    int n = array.length;
	    int pass = 1;
	    boolean sorted = false;

	    while ((!sorted) && (pass < n))
	      {
	         sorted = true;
	         for(int i = 0; i < n - pass; i++)
	         {
	            comps++;
	            if (array[i] > array[i+1])
	            {
	               swaps++;
	               int temp = array[i];
	               array[i] = array[i+1];
	               array[i+1] = temp;
	               sorted = false;
	            }
	         }
	         pass++;
	      }
	    endTime = System.nanoTime();
        timeTaken = (endTime - startTime);
        return;
	}

}