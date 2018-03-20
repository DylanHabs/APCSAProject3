import java.io.*;
import java.util.*;
import java.io.File;
import java.util.Collections;


public class ProcessStatistics {

	public static void main(String[] args) {
    	for(int i = 0; i < args.length; i++)
        {
            bookInfo(args[i]);
        }
	}
	public static void bookInfo(String pass)
	{
    	File xd =new File(pass);
    	if(xd.exists() && !xd.isDirectory())
    	{
        	System.out.println("Statistics for " + pass);
        	System.out.println("==========================================================");
        	System.out.println(lineCount(bookCreator(pass)) + " lines");
        	System.out.println(wordCount(bookCreator(pass)) + " words");
        	System.out.println(new File(pass).length() + " characters");
        	System.out.println("------------------------------");
        	letterCounter(bookCreator(pass));
        	System.out.println("------------------------------");
        	wordLength(bookCreator(pass), (double)wordCount(bookCreator(pass)));
    	}else System.out.println("Invalid file path: " + pass);
	}
	public static void wordLength(String[] c, Double d)
	{
    	double avgWordLegth = 0;
    	ArrayList<Integer> counter = new ArrayList<Integer>();
    	for(int i = 0; i < c.length; i++)
    	{
        	c[i] = c[i].replaceAll("[^a-zA-Z0-9]", " ");
        	c[i] = c[i].replaceAll("\\d", "");
        	c[i] = c[i].replaceAll("\\s+", " ");
        	c[i] = c[i].replaceAll("(?m)^[ \t]*\r?\n", " ");
        	c[i] = c[i].trim();
        	//System.out.println(c[i]);
        	String[] ar = c[i].split(" ");
        	for(int j = 0; j < ar.length; j++)
        	{
            	counter.add(ar[j].length());
        	}
    	}
    	System.out.println(" length  frequency");
    	System.out.println(" ------  ---------");
    	for(int i = 1; i <= Collections.max(counter); i ++)
    	{
        	System.out.print(" 	");
        	if(String.valueOf(i).length() < 2) System.out.print(" ");
        	System.out.print(i + "     	");
        	if(String.valueOf(Collections.frequency(counter, i)).length() < 2) System.out.print(" ");
        	System.out.println(Collections.frequency(counter, i));
        	avgWordLegth += (i * (Collections.frequency(counter, i)));
    	}
    	System.out.println(" ");
    	double x = avgWordLegth / d;
        System.out.println("Average word length = " + Math.round(x * 100.0) / 100.0);
    	System.out.println("==========================================================");
    	System.out.println(" ");
	}
    
	public static void letterCounter(String[] c)
	{
    	String a = "";
    	for(int i = 0; i < c.length; i++) a += c[i];
    	String b = a.toLowerCase();
    	for( int i = 'a'; i <= 'm'; i++ )
    	{
        	int num = b.length() - b.replaceAll(String.valueOf((char)i),"").length();
        	int num2 = b.length() - b.replaceAll(String.valueOf((char)(i + 13)),"").length();
        	System.out.print((char)i + " = " + num);
        	for(int j = 0; j < 11 - String.valueOf(num).length(); j++)System.out.print(" ");
        	System.out.print((char)(i + 13)+ " = " + num2);
        	System.out.println();
    	}
	}
	public static int lineCount(String[] c)
	{
    	return (c.length);
	}
	public static int wordCount(String[] c)
	{
    	int tempC = 0;    
    	int xd = 0;
    	for(int i = 0; i < c.length; i++)
    	{
        	if(c[i].isEmpty()) xd++;
        	c[i] = c[i].replaceAll("[^a-zA-Z0-9]", " ");
        	c[i] = c[i].replaceAll("\\d", "");
        	c[i] = c[i].replaceAll("\\s+", " ");
        	c[i] = c[i].replaceAll("(?m)^[ \t]*\r?\n", " ");
        	//System.out.println(c[i]);
        	c[i] = c[i].trim();
        	tempC += c[i].split(" ").length;
    	}
    	//System.out.println(xd);
    	return (tempC - xd);
	}
	public static String[] bookCreator (String f)
	{
    	Scanner s;
    	List<String> lines = new ArrayList<String>();
    	try{
        	s = new Scanner(new File(f));
    	}
    	catch (IOException e) {
        	s = null;
        	System.out.println("Invalid file path: " + f);}
    	while(s.hasNextLine())
    	{
        	lines.add(s.nextLine());
    	}
    	String[] book = lines.toArray(new String[0]);
    	return(book);
	}
}
