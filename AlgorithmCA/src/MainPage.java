import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.awt.Font;
import java.awt.Image;
import java.awt.Choice;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class MainPage extends JFrame implements ItemListener{

	private JPanel contentPane;
	ArrayList<Object> algorithms;
	private JTable table;
	DefaultTableModel dtm;
	private JLabel lblImageHolder;
	 int arraySizeInt;
	 int[] sortedArray;
     int[] invSortedArray;
     int[] randomArray;
     Choice choiceArraySize;

	public MainPage() {
		setTitle("Kyle Doherty - Algorithm CA");
	
		//GUI CODE *********************************************************************************
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 692, 349);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Right Panel
		JPanel panelRight = new JPanel();
		panelRight.setBackground(new Color(95, 158, 160));
		panelRight.setBounds(190, 71, 496, 249);
		contentPane.add(panelRight);
		panelRight.setLayout(null);
		
		//Left Panel
		JPanel panelLeft = new JPanel();
		panelLeft.setBackground(Color.WHITE);
		panelLeft.setBounds(10, 0, 342, 317);
		contentPane.add(panelLeft);
		panelLeft.setLayout(null);
		
		//Adding label to gui
		JLabel lblPrevResults = new JLabel("Previous Results");
		lblPrevResults.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblPrevResults.setBackground(Color.WHITE);
		lblPrevResults.setBounds(10, 11, 136, 25);
		panelRight.add(lblPrevResults);
		
		Label lblArrayType = new Label("Choose type of array: ");
		lblArrayType.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblArrayType.setBounds(180, 10, 152, 24);
		panelLeft.add(lblArrayType);
		
		Label lblArraySize = new Label("Choose size of array: ");
		lblArraySize.setBounds(10, 10, 152, 24);
		panelLeft.add(lblArraySize);
		lblArraySize.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		
		Label lblSortMethod = new Label("Choose sorting method:");
		lblSortMethod.setBounds(6, 66, 156, 29);
		panelLeft.add(lblSortMethod);
		lblSortMethod.setFont(new Font("Colonna MT", Font.PLAIN, 14));
		
		JLabel lblAlgorithmTesterProgram = new JLabel("Algorithm Tester Program by Kyle Doherty");
	    lblAlgorithmTesterProgram.setBackground(new Color(95, 158, 160));
	    lblAlgorithmTesterProgram.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
	    lblAlgorithmTesterProgram.setHorizontalAlignment(SwingConstants.CENTER);
	    lblAlgorithmTesterProgram.setBounds(362, 0, 312, 41);
	    contentPane.add(lblAlgorithmTesterProgram);
		
		//Adding choice menus to GUI
		choiceArraySize = new Choice();
		choiceArraySize.setBounds(10, 40, 124, 20);
		panelLeft.add(choiceArraySize);

		Choice choiceAlgorithm = new Choice();
		choiceAlgorithm.setBounds(10, 95, 124, 20);
		panelLeft.add(choiceAlgorithm);
		
		Choice choiceArrayType = new Choice();
		choiceArrayType.setBounds(180, 40, 124, 20);
		panelLeft.add(choiceArrayType);
		
		//Adding option to choice menus
		choiceAlgorithm.add("S. Bubble");
		choiceAlgorithm.add("E. Bubble");
		choiceAlgorithm.add("Selection");
		choiceAlgorithm.add("Insertion");
		choiceAlgorithm.addItemListener(this);
		choiceArraySize.add("1000");  
		choiceArraySize.add("10000");  
		choiceArraySize.add("100000");
		choiceArraySize.addItemListener(this);
		choiceArrayType.add("Sorted");  
		choiceArrayType.add("Inv Sorted");  
		choiceArrayType.add("Random");
		choiceArraySize.addItemListener(this);
		
		
		//Adding single button to gui
		JButton btnCalc = new JButton("Calculate Performance");
		btnCalc.setBounds(0, 129, 174, 36);
		panelLeft.add(btnCalc);
		btnCalc.setBackground(new Color(153, 204, 204));
		
		//IMage Icon
		lblImageHolder = new JLabel("");
		lblImageHolder.setBounds(10, 176, 164, 141);
		Image img = new ImageIcon(this.getClass().getResource("/img.png")).getImage();
		lblImageHolder.setIcon(new ImageIcon(img));
		panelLeft.add(lblImageHolder);
		
		//JTable Code for gui
		dtm = new DefaultTableModel(0, 0);
		table= new JTable();

		String header[] = new String[] { "Array Size", "Array Type", "Algorithm",
                "Time", "Comparisons", "Swaps" };
		dtm.setColumnIdentifiers(header);

		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 47, 486, 202);
		 scrollPane.setViewportView(table);
		 table.setModel(dtm);
		panelRight.add(scrollPane);
		
		//END OF GUI CODE ************************************************************************************
		
		//Get array size by comparing the index
		updateArraySize(); // this is also called when the size of array is changed when running
        

        //Fill the sorted array
        sortedArray = Arrays.copyOf(randomArray, arraySizeInt);
        Arrays.sort(sortedArray);
        
        //Fill the inversely sorted array
        invSortedArray = Arrays.copyOf(sortedArray, arraySizeInt);
        reverseArray(invSortedArray);
        
		//CALCULATE BUTTON ACTION CODE
		btnCalc.addActionListener(new ActionListener() 
		{ 		
            public void actionPerformed(ActionEvent e) 
            {   
            	
            	//A lot of code below is getting data from the choice boxes to then make decisions
            	//Get size of array(as string) and sorting method(as string and id of selected choice)
		         String arrayTypeString = ""+ choiceArrayType.getItem(choiceArrayType.getSelectedIndex()); 
		         String algorithmChoosen = ""+ choiceAlgorithm.getItem(choiceAlgorithm.getSelectedIndex());
		         
		         //Get the choosen Array ( sorted, random etc...)
		         int [] arrayChoosen;
		         if(choiceArrayType.getSelectedIndex() == 0) arrayChoosen = Arrays.copyOf(sortedArray, arraySizeInt);
		         else if(choiceArrayType.getSelectedIndex() == 1) arrayChoosen = Arrays.copyOf(invSortedArray, arraySizeInt);
		         else  arrayChoosen = Arrays.copyOf(randomArray, arraySizeInt);
		         
		         //By getting the ID of the selected option in the choice box, I will 
		         //execute the choosen algorithm sort method. I call each algortihm in a static way and print
		         //the current results. I then make a temp object to add it to the JTable.
		         int algorithmID = choiceAlgorithm.getSelectedIndex();
		         
		         System.out.println("\n\nArray Pre-Sorted: ");
		         printArray(arrayChoosen);
		         
		         switch(algorithmID)
		         {
			         case(0): //Standard Bubble
			         {
			        	 StanderdBubbleSort.sort(arrayChoosen);
			        	 addRow(new Object[] {arraySizeInt, arrayTypeString, algorithmChoosen, computeTimeUnit(StanderdBubbleSort.timeTaken), StanderdBubbleSort.comps,StanderdBubbleSort.swaps  });
				         writeCSV(algorithmChoosen, StanderdBubbleSort.timeTaken, arrayTypeString, arraySizeInt);
			        	 
			         }
			         break;
			         
			         case(1): //Enhanced Bubble Sort
			         {
			        	 EnhancedBubbleSort.sort(arrayChoosen);
			        	 addRow(new Object[] {arraySizeInt, arrayTypeString, algorithmChoosen, computeTimeUnit(EnhancedBubbleSort.timeTaken), EnhancedBubbleSort.comps,EnhancedBubbleSort.swaps  });
				         writeCSV(algorithmChoosen, EnhancedBubbleSort.timeTaken, arrayTypeString, arraySizeInt);
			         }
			         break;
			         
			         case(2): //Selection Sort
			         {
			        	 SelectionSort.sort(arrayChoosen);
				         addRow(new Object[] {arraySizeInt, arrayTypeString, algorithmChoosen, computeTimeUnit(SelectionSort.timeTaken), SelectionSort.comps,SelectionSort.swaps  });
				         writeCSV(algorithmChoosen, SelectionSort.timeTaken, arrayTypeString, arraySizeInt);
			         }
			         break;
			         
			         case(3): //Insertion Sort
			         {			        	 
			        	 InsertionSort.sort(arrayChoosen);
				         addRow(new Object[] {arraySizeInt, arrayTypeString, algorithmChoosen, computeTimeUnit(InsertionSort.timeTaken), InsertionSort.comps,InsertionSort.swaps  });
				         writeCSV(algorithmChoosen, InsertionSort.timeTaken, arrayTypeString, arraySizeInt);   
			         }
			         
			         break;
			         
			         
		         }  
		         System.out.println("Array Sorted: ");
		         printArray(arrayChoosen);
		         
            }	  
        });  
	}
	
	private void updateArraySize()
	{
		//Get array size by comparing the index
		// -------- For every time the array size is the same, the arrays are the same
        // -------- to allow for imperical analysis
	    System.out.println("UPDATING ARRAY SIZE, ARRAY VAUES CHANGING");
        if(choiceArraySize.getSelectedIndex() == 0) arraySizeInt = 1000;
        else if(choiceArraySize.getSelectedIndex() == 1) arraySizeInt = 10000;
        else if(choiceArraySize.getSelectedIndex() == 2) arraySizeInt = 100000;
        
        //Create 3 empty arrays for testing. I find this easier to put here to keep the code tidy.
        //I know that sorted and inv sorted will be the same all the time but its neater to keep here
        sortedArray = new int[arraySizeInt];
        invSortedArray = new int[arraySizeInt];
        randomArray = new int[arraySizeInt];
        
        //Fill the random array
        for(int i =0; i < randomArray.length; i++)
        {
        	randomArray[i] = (int)(Math.random() * randomArray.length) + 1;
        }
        

        //Fill the sorted array
        sortedArray = Arrays.copyOf(randomArray, arraySizeInt);
        Arrays.sort(sortedArray);
        
        //Fill the inversely sorted array
        invSortedArray = Arrays.copyOf(sortedArray, arraySizeInt);
        reverseArray(invSortedArray);
	}

	
	private void reverseArray(int[] array) { //Bubble sort reversed to fill the inv array
		 int n = array.length;
		    int temp = 0;

		    for (int i = 0; i < n; i++) {
		        for (int j = 1; j < (n - i); j++) {
		        	
		            if (array[j - 1] < array[j]) {
		                temp = array[j - 1];
		                array[j - 1] = array[j];
		                array[j] = temp;
		            }

		        }
		    }
		    return;
	}

	//Method to display the time in MS or NS
	private String computeTimeUnit(long timeTaken) {
		String timeTakenString = "";
		if((timeTaken / 1000000) > 0)
			timeTakenString = "" + (timeTaken/ 1000000) + "ms";
	         else
	        	timeTakenString = "" + timeTaken +  "ns";
		return timeTakenString;
	}
	
	//Method to print array
	public void printArray(int[] array)
	{
		System.out.println(Arrays.toString(array));
	}
	
	//Method to add result to JTable
	public void addRow(Object[] objectsString)
	{
		dtm.addRow(objectsString);
	}
	 
	//Method to write result to csv file
	private void writeCSV(String algorithm, long time, String arrayType, int arraySize ) {
		File file = new File("results.csv");
		if(!file.exists()) {
			try {
				file.createNewFile();
				FileWriter writer = new FileWriter(file, true);
				writer.append("Algorithm");
				writer.append(",");
				writer.append("Time");
				writer.append(",");
				writer.append("Array Type");
				writer.append(",");
				writer.append("Array Size");
				writer.append('\n');
				writer.close();
			} catch (IOException e) {
				System.out.println("Failed to create file");
			}
		}
		
		try {
			FileWriter writer = new FileWriter(file, true);
			writer.append(algorithm + "," + time + "," + arrayType + "," + arraySize);
			writer.append('\n');
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		updateArraySize();	
	}
	
	//Run the program
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage frame = new MainPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

