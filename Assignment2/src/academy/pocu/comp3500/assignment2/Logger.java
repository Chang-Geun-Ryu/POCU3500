package academy.pocu.comp3500.assignment2;

import academy.pocu.comp3500.assignment2.datastructure.Stack;

import java.io.BufferedWriter;

public final class Logger {
    private static Indent rootIndent = new Indent();
    private static Stack<Indent> stackIndents = new Stack<>();

    public static void log(final String text) {
        if (stackIndents.getSize() <= 0) {
            stackIndents.push(rootIndent);
        }

        stackIndents.peek().addLog(new Node(text));
    }

    public static void printTo(final BufferedWriter writer) {
        rootIndent.printLog(writer, 0, null);
    }

    public static void printTo(final BufferedWriter writer, final String filter) {
        rootIndent.printLog(writer, 0, filter);
    }

    public static void clear() {
        rootIndent = new Indent();
        while (stackIndents.getSize() > 0) {
            stackIndents.pop();
        }
    }

    public static Indent indent() {
        if (stackIndents.getSize() <= 0) {
            stackIndents.push(rootIndent);
        }

        Indent indent = new Indent();
        stackIndents.peek().addIndent(indent);
        stackIndents.push(indent);
        return indent;
    }

    public static void unindent() {
        if (stackIndents.getSize() <= 0) {
            stackIndents.push(rootIndent);
        }

        stackIndents.pop();
    }
}