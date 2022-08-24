import implementations.*;
import org.apache.commons.lang.NullArgumentException;

import java.io.*;
import java.util.Scanner;
import java.util.stream.Stream;


public class Main {
    static Stack<String> stack = new Stack<>();
    static Queue<String> queue = new Queue<>();
    static String messagesFile = "./src/messages.txt";

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        transferMessages();
        processMessages();
        printMessages();
        long endTime = System.currentTimeMillis();
        long elaspedTime = endTime - startTime;
        System.out.println("The system took " + elaspedTime + "ms to run...");
    }

    public static void enterMessage() throws IOException {
//        System.out.println("Enter your message");
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        strings = br.readLine().split("\\.");

    }
    public static void transferMessages(){
        try{
            FileReader fr = new FileReader(messagesFile);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine())!=null){
                try {
                    if (line.length() > 250) {
                        throw new IndexOutOfBoundsException("The message characters must be less than 250 character!!! Please retype");
                    }else if(line.equals("")){
                        throw new NullArgumentException("You haven't typed anything yet!!! Please retype");
                    }else {
                        long startTime = System.currentTimeMillis();
                        if (!line.equals("finish")){
                            queue.offer(line);
                            System.out.println("Message has been sent successfully!!!");
                        }
                        long endTime1 = System.currentTimeMillis();
                    }
                }catch (IndexOutOfBoundsException iae) {
                    System.out.println(iae.getMessage());
                }catch (NullArgumentException nae){
                    System.out.println(nae.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void processMessages(){
        while (!queue.isEmpty()){
            stack.push(queue.poll());
        }
    }
    public static void printMessages(){
        while (!stack.isEmpty()){
            System.out.println("New messages: " + stack.pop());
        }
    }
}
