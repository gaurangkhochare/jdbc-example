package main;

import java.awt.EventQueue;
import com.gaurang.view.StudentManagerGUI;
/**
 * This assignment is taking too long...
 * @author Gaurang Khochare
 */
public class StudentManagementApp {
    
    /**
     * Behold, the iconic main method of Java, with all of its incredible keywords: public, static and void.
     * @param args The command line arguments. Absolutely not required here.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new StudentManagerGUI());
    }
}
