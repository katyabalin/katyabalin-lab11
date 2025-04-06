import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.ListIterator;

import static org.junit.Assert.assertEquals;


public class Lab11_Tests {
    /*
        Complete the test case below that checks to see that threads A and B have both contributed 100 entries respectively
        to the shared ArrayList when they have both finished running.
    */
    @Test

public void test1() {
    Lab11_Thread threadA = new Lab11_Thread("A1", 100);
    Lab11_Thread threadB = new Lab11_Thread("B1", 100);

    threadA.start();
    threadB.start();

    try {
        threadA.join();
        threadB.join();
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

    ArrayList<String> lst = threadA.getData();
    int fromA = 0;
    int fromB = 0;

    for (String s : lst) {
        if (s.startsWith("A1")) fromA++;
        else if (s.startsWith("B1")) fromB++;
    }

    String expected = "There are 100 entries from thread A and 100 entries from thread B";
    String result = "There are " + fromA + " entries from thread A and " + fromB + " entries from thread B";

    lst.clear(); // reset shared state
    assertEquals(expected, result);
}


    /*
        Complete the test case below that checks to see if the shared ArrayList has at least 10 entries after 500ms of system time
    */
    @Test
    public void test2() {

        Lab11_Thread threadA = new Lab11_Thread("A2", 500);
        Lab11_Thread threadB = new Lab11_Thread("B2", 500);
        // addition

        // end addition

        threadA.start();
        threadB.start();

        try {
            Thread.sleep(500); 
        } catch (Exception e){
            e.printStackTrace();
        }

        int num = threadA.getData().size();

        // test continues//
         try {
            threadA.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            threadB.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        threadA.getData().clear();

        String result = "error";
        String expected = "Shared ArrayList has at least 10 entries";
        // ArrayList<String> lst = threadA.getData();
        // int num = lst.size();

        if(num >= 10)
            result = expected;
        else
            result = "Shared ArrayList has " + num + " entries";



        assertEquals(expected, result);
    }

    /*
        Complete the test case below that checks to see if thread A finishes adding its 10 entries before thread B was allowed to 
        add anything to the shared ArrayList
    */
    @Test
    public void test3() {
        Lab11_Thread threadA = new Lab11_Thread("A3", 10);
        Lab11_Thread threadB = new Lab11_Thread("B3", 10);

        threadA.start();
        
        try {
            threadA.join();
        } catch (Exception e){
            e.printStackTrace();
        }
        
        threadB.start();

        try {
            threadB.join();
        } catch (Exception e){
            e.printStackTrace();
        }
        String expected = "There are 10 entries of thread A before entries of thread B";
        ArrayList<String> lst = threadA.getData();
        int entriesA = 0;
        ListIterator<String> it = lst.listIterator();
        while(it.hasNext()) {
            String s = it.next();
            if(s.charAt(0) == 'B')
                break;
            else
                entriesA++;

        }
        String result = "There are " + entriesA +  " entries of thread A before entries of thread B";
        assertEquals(expected, result);
    }
}
