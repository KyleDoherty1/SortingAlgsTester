
public class SelectionSort {
	static int swaps=0;
    static int comps=0;
    static long startTime, endTime, timeTaken;
    
	 public static void sort(int arr[])
	    {
		 
		 	swaps=0;
		 	comps=0;
	        int n = arr.length;
	        startTime = System.nanoTime();
	        
	        
	        // One by one move boundary of unsorted subarray
	        for (int i = 0; i < n-1; i++)
	        {
	            // Find the minimum element in unsorted array
	            int min_idx = i;
	            
	            for (int j = i+1; j < n; j++)
	            {

                    comps++;
	                if (arr[j] < arr[min_idx])
	                {
	                    min_idx = j;
	                }
	            }
	 
	            // Swap the found minimum element with the first
	            // element
	            int temp = arr[min_idx];
	            arr[min_idx] = arr[i];
	            arr[i] = temp;
	            
	            swaps++;
	        }
	        
	        endTime = System.nanoTime();
	        timeTaken = (endTime - startTime);
	        return;
	    }

}
