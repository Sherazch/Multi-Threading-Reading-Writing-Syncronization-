package testing;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
 
public class MultithreadingWriteToFile {
    
    public static void main(String[] args) {
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        File dir = new File("D:\\java\\sample files");
        File destination = new File("D:\\java\\sample files\\DestinationMultiThread.txt");
        File[] files = dir.listFiles();
        for (File file : files) {
        	readingThread r1=new readingThread(file);
            Writer w1 = new Writer(destination);
            Thread r=new Thread(r1);
            Thread t = new Thread(w1);
            r.setPriority(Thread.MAX_PRIORITY);
            t.setPriority(Thread.MIN_PRIORITY);
            r.start();
            t.start();
            
        }
        
    
        
    }
 
}
 
class Writer implements Runnable{
//File source;
File destination;
    public Writer(File destination) {
//this.source = source;
this.destination = destination;
    }
    @Override
    public void run() {
        System.out.println("Thread 2 Started");
        writeToFile(destination,readingThread.content);  
        
    }
    
    private static void writeToFile(File file,String content) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
            writer.write(content);
            writer.write("file content written");
            writer.flush();
            System.out.println("writing thread");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Successfully copied...");
    }
 
    
 
    
}


class readingThread implements Runnable{
	File source;
	static String content;
	
	public readingThread(File source) {
		this.source = source;
    }
	
	@Override
	public void run() {
		System.out.println("Thead 1 Started");
		this.content =   readFromFile(source.getAbsolutePath());
		
	}
	static String readFromFile(String filename){
        StringBuffer content = new StringBuffer();
        try {
            String text;
            BufferedReader reader = new BufferedReader(new FileReader(filename));
                while((text = reader.readLine())!=null){
                    content.append(text);
                    content.append("\n");
                    
                }
             System.out.println("reading thread");   
             
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    return content.toString();  
    }
	
}
