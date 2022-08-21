import implementations.*;
import org.apache.commons.lang.NullArgumentException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    static Stack<String> stack = new Stack<>();
    static Queue<String> queue = new Queue<>();
    static String[] strings;
    public static void main(String[] args) throws IOException{
        enterMessage();
        long startTime = System.currentTimeMillis();
        transferMessages();
        processMessages();
        printMessages();
        long stopTime = System.currentTimeMillis();
        long elaspedTime = stopTime - startTime;
        System.out.println("The system took " + elaspedTime + "ms to run...");
    }

    public static void enterMessage() throws IOException {
        System.out.println("Enter your message");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        strings = br.readLine().split("\\.");
    }
    public static void transferMessages(){
        try{
            for (int i = 0; i < strings.length; i++) {
                if (strings[i].length() > 250){
                    throw new IndexOutOfBoundsException("The message characters must be less than 250 character!!! Please retype");
                }else if(strings[i].equals("")){
                    throw new NullArgumentException("You haven't typed anything yet!!! Please retype");
                }else {
                    queue.offer(strings[i] + ".");
                    System.out.println("Message has been sent successfully!!!");
                }
            }
            while (!queue.isEmpty()){
                String message = queue.poll();
                stack.push(message);
            }
        }catch (IndexOutOfBoundsException ioobe){
            System.out.println(ioobe.getMessage());
        }catch (NullArgumentException nae){
            System.out.println(nae.getMessage());
        }
    }
    public static void processMessages(){
        while (!stack.isEmpty()){
            queue.offer(stack.pop());
        }
        while (!queue.isEmpty()){
            String message = queue.poll();
            stack.push(message);
        }
    }
    public static void printMessages(){
        while (!stack.isEmpty()){
            System.out.println("New messages: " + stack.pop());
        }
    }
}
