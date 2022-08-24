import implementations.*;
import org.apache.commons.lang.NullArgumentException;

import java.io.*;
public class Main {
    static Stack<String> stack = new Stack<>();
    static Queue<String> queue = new Queue<>();
    static String messagesFile = "./src/messages.txt";
    static String[] strings;

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        transferMessages();
        processMessages();
        printMessages();
        long endTime = System.currentTimeMillis();
        long elaspedTime = endTime - startTime;
        System.out.println("The system took " + elaspedTime + "ms to run...");
    }
    public static void transferMessages(){
        try{
            FileReader fr = new FileReader(messagesFile);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine())!=null){
                try {
                        long startTime1 = System.currentTimeMillis();
                        if (!line.equals("finish")){
                            strings = line.split("\\.");
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
                        long endTime1 = System.currentTimeMillis();
                        System.out.println("Time" + (endTime1 - startTime1));
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
