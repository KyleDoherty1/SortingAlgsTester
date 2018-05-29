
public class StanderdBubbleSort {
	
	static int swaps=0;
    static int comps=0;
    static long startTime, endTime, timeTaken;
	
	public static void sort(int[] array) {
		
		swaps=0;
	 	comps=0;
	 	startTime = System.nanoTime();
	 	
	    int n = array.length;
	    int temp = 0;

	    for (int i = 0; i < n; i++) {
	        for (int j = 1; j < (n - i); j++) {
	        	
	        	comps++;
	        	
	            if (array[j - 1] > array[j]) {
	                temp = array[j - 1];
	                array[j - 1] = array[j];
	                array[j] = temp;
	                swaps++;
	            }

	        }
	    }
	    
	    endTime = System.nanoTime();
        timeTaken = (endTime - startTime);
        return;
	}

}
