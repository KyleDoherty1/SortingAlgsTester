import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.io.File;
import java.io.FileNotFoundException;

/**
   This program checks which words in a file are not present in a dictionary.
*/
public class SpellCheck
{
   public static void main(String[] args) 
      throws FileNotFoundException
	   {
	      // Read the dictionary and the document
	      Set<String> dictionaryWords = readWords("src/Dictionary.txt");
	      Set<String> documentWords = readWords("src/Alice_In_Wonderland.txt");
	      
	      //ArrayList to hold words that are not in the dictionary
	      ArrayList<String> excludedWords = new ArrayList<String>();
	      
	      
	      for(String s : documentWords) //Iterate through every word to test if
	      {								//it is in the dictionary. If not add it to ArrayList
	    	  if(!dictionaryWords.contains(s))
	    	  {
	    		  excludedWords.add(s);
	    	  }
	    		  
	      }
	      
	      excludedWords.sort(String::compareToIgnoreCase);
	      
	      //Finally print the words
	      for(String s : excludedWords)
	      {
	    	 System.out.println(s);
	      }
	
	   }

   /**
      Reads all words from a file.
      @param filename the name of the file
      @return a set with all lowercased words in the file. Here, a 
      word is a sequence of upper- and lowercase letters.
   */
   
   public static Set<String> readWords(String filename) throws FileNotFoundException   
   {
      Set<String> words = new HashSet<String>();
      Scanner in = new Scanner(new File(filename));
      // Use any characters other than a-z or A-Z as delimiters
      in.useDelimiter("[^a-zA-Z]+");
      
      while (in.hasNext()) { //While there is a next word oin the book to the Set called documentWords
    	    words.add(in.next().toLowerCase());	    
      }
      
      return words; //Return all words in the 
   }
}