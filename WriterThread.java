package testing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;

public class WriterThread implements Runnable{

  protected BlockingQueue<String> blockingQueue = null;

  public WriterThread(BlockingQueue<String> blockingQueue){
    this.blockingQueue = blockingQueue;     
  }

  @Override
  public void run() {
    PrintWriter writer = null;

    try {
        writer = new PrintWriter(new File("outputFile.txt"));

        while(true){
            String buffer = blockingQueue.take();
            //Check whether end of file has been reached
            if(buffer.equals("EOF")){ 
                break;
            }
            writer.println(buffer);
        }               


    } catch (FileNotFoundException e) {

        e.printStackTrace();
    } catch(InterruptedException e){

    }finally{
        writer.close();
    } 

  }

}