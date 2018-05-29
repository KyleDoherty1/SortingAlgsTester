
public class InsertionSort {
	static int swaps=0;
    static int comps=0;
    static long startTime, endTime, timeTaken;
    
    public static void sort(int array[])
    {
    	swaps=0;
	 	comps=0;
	 	startTime = System.nanoTime();
	 	int n = array.length;
	 	 for(int k = 1; k < n; k++)
	      {
	         int x = array[k];
	         int i = k - 1;
	         boolean found = false;
	         
	         while ((!found) && (i >= 0))
	         {
	            comps++;
	            if (array[i] > x)
	            {
	               swaps++;
	               int temp = array[i];
	               array[i] = array[i+1];
	               array[i+1] = temp;
	               i--;
	            }
	            else
	               found = true;
	         }
	      }
    	
    	endTime = System.nanoTime();
        timeTaken = (endTime - startTime);
        return;

    }

}
