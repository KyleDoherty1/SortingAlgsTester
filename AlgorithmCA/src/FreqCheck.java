import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.io.File;
import java.io.FileNotFoundException;

/**
   This program checks which words in a file are not present in a dictionary.
*/
public class FreqCheck
{
   public static void main(String[] args) throws FileNotFoundException
   {	  
	   // I am using a Map to hold all the words and the number of occurrences
	   // they have. When I call readWords I am returning a treeMap as it will
	   // sort my words alphabetically.
	   
	   Map<String, Integer> wordCounts = readWords("src/WarAndPeace.txt");
		  
	   for (String s: wordCounts.keySet()) 
   	   {
		   System.out.println(s + " " +  wordCounts.get(s));
   	   }
   }

   public static Map<String, Integer> readWords(String filename) throws FileNotFoundException
   {
      Map<String, Integer> words = new TreeMap<>();
      Scanner in = new Scanner(new File(filename));
      in.useDelimiter("[^a-zA-Z]+");
      
      while (in.hasNext()) //While the book has a next word
      {
    	  String word = in.next().toLowerCase(); //Let word equal to the scanners string
    	  
    	  if (!words.containsKey(word))//If the word doesnt exist, add it to the treemap
    		    words.put(word, 1);
    	  else							//If it does, increment the number of occurances
		    words.put(word, words.get(word) + 1);    
      }
      
      return words;
      
   }
}