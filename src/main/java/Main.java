import implementations.*;
import org.apache.commons.lang.NullArgumentException;

import java.sql.SQLOutput;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    static Stack<String> stack = new Stack<>();
    static Queue<String> queue = new Queue<>();
    public static void main(String[] args) {
        String choice = "";
        while (!"0".equals(choice)){
            Scanner sc = new Scanner(System.in);

            System.out.println("1. Send Messages");
            System.out.println("2. Show All Messages");
            System.out.println("0. Exit");

            System.out.println("====Please input choice====");
            choice = sc.nextLine();
            switch (choice){
                case "1":
                    transfer();
                    break;
                case "2":
                    process();
                    break;
                case "0":
                    System.exit(0);
            }
        }
    }
    public static void transfer(){
        System.out.println("Enter your message");
        String messages;
        Scanner sc = new Scanner(System.in);
        do {
            messages = sc.nextLine();
            if (!messages.equals("finish")){
                queue.offer(messages);
            }
        }while (!messages.equals("finish"));
        while (!queue.isEmpty()){
            String message = queue.poll();
            stack.push(message);
        }
    }
    public static void process(){
        System.out.println("Processing...");
        while (!stack.isEmpty()){
            queue.offer(stack.pop());
        }
        while (!queue.isEmpty()){
            String message = queue.poll();
            stack.push(message);
        }
        while (!stack.isEmpty()){
            System.out.println("Messages: " + stack.pop());
        }
    }
}
