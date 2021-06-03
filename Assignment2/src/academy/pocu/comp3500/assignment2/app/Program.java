package academy.pocu.comp3500.assignment2.app;

import academy.pocu.comp3500.assignment2.Indent;
import academy.pocu.comp3500.assignment2.Logger;
import academy.pocu.comp3500.assignment2.datastructure.Stack;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static academy.pocu.comp3500.assignment2.Logger.clear;
import static academy.pocu.comp3500.assignment2.Logger.log;

public class Program {

    public static void main(String[] args) throws IOException {
        sort();
    }

    public static void sort() throws  IOException {
        final BufferedWriter writer1 = new BufferedWriter(new FileWriter("quicksort1.log"));

        int[] nums = new int[]{30, 10, 80, 90, 50, 70, 40};
        Sort.quickSort(nums);
        Logger.printTo(writer1);
    }

    public static void testIndent() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("mylog1.log"));

        log("this is not indented");

        Logger.indent();
        {
            log("but this is");
            log("so is this");
        }
        Logger.unindent();

        log("but not this");
        Logger.printTo(writer);

    }

    public static void testDiscard() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("mylog2.log"));

        int x = 10;

        log("first level 1");

        Indent indent = Logger.indent();
        {
            log("second level 1");
            log("second level 2");

            if (x % 2 == 0) {
                indent.discard();
            }
        }
        Logger.unindent();

        log("first level 2");
        Logger.printTo(writer);
    }
}