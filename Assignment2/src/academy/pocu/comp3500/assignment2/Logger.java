package academy.pocu.comp3500.assignment2;

import academy.pocu.comp3500.assignment2.datastructure.ArrayList;
import academy.pocu.comp3500.assignment2.datastructure.LinkedList;
import academy.pocu.comp3500.assignment2.datastructure.Queue;
import academy.pocu.comp3500.assignment2.datastructure.Stack;

import java.io.BufferedWriter;
import java.io.IOException;

public final class Logger {
    private static ArrayList<String> logArray = new ArrayList<>();
    private static Queue<Indent> qIndents = new Queue<>();
    private static Stack<Indent> sIndents = new Stack<>();

    public static void log(final String text) {
        logArray.add(text);
    }

    public static void printTo(final BufferedWriter writer) {
        try {
            Stack<Indent> temp = new Stack<>();
            int indentsCount = 0;
            boolean isShow = true;
            for (int i = 0; i < logArray.getSize(); ++i) {
                if (qIndents.getSize() > 0 && qIndents.peek().getStartIndex() == i) {
                    temp.push(qIndents.dequeue());
                    System.out.println("intentCount: " + temp.getSize() + ", Start: " + temp.peek().getStartIndex() + ", End: " + temp.peek().getEndIndex());
                    isShow = temp.peek().isShowLog();
                }
                if (temp.getSize() > 0 && temp.peek().getEndIndex() == i) {
//                    isShow = !temp.peek().isShowLog();
                    qIndents.enqueue(temp.pop());
//                    indentsCount = temp.getSize() * 2;
                }
                indentsCount = temp.getSize() * 2;
//                System.out.println("indentsCount: " + indentsCount + ", temp.getSize(): " + temp.getSize());
                if (isShow) {
                    if (indentsCount > 0) {
                        String str = String.format("%" + (indentsCount + logArray.get(i).length()) + "s", logArray.get(i));
                        System.out.println(str);
                        writer.write(str + System.lineSeparator());
                    } else {
                        String str = logArray.get(i);
                        System.out.println(str);
                        writer.write(str + System.lineSeparator());
                    }
                }

            }

            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("error IOException: " + e.getLocalizedMessage());
        }

    }

    public static void printTo(final BufferedWriter writer, final String filter) {

    }

    public static void clear() {
        logArray.clear();
    }

    public static Indent indent() {
        Indent indent = new Indent(logArray.getSize());
        qIndents.enqueue(indent);
        sIndents.push(indent);
        return indent;
    }

    public static void unindent() {
//        qIndents.peek().unindent(logArray.getSize());
        sIndents.peek().unindent(logArray.getSize());
        System.out.println("input==> intentCount: " + sIndents.getSize() + ", Start: " + sIndents.peek().getStartIndex() + ", End: " + sIndents.peek().getEndIndex());

    }
}