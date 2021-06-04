package academy.pocu.comp3500.assignment2;

import academy.pocu.comp3500.assignment2.datastructure.ArrayList;

import java.io.BufferedWriter;
import java.io.IOException;

public final class Indent {
    private boolean isShowLog = true;
    private ArrayList<Node> nodes = new ArrayList<>();

    public Indent() {
        nodes.add(new Node(null));
    }

    public void discard() {
        this.isShowLog = false;
    }

    public boolean isShowLog() {
        return isShowLog;
    }

    public void addLog(Node node) {
        nodes.add(node);
    }

    public void addIndent(Indent indent) {
        nodes.get(nodes.getSize() - 1).setIndent(indent);
    }

    public void printLog(final BufferedWriter writer, int indentCount, String filter) {
        for (int i = 0; i < nodes.getSize(); ++i) {
            String log = nodes.get(i).getLog();
            ArrayList<Indent> indents = nodes.get(i).getIndents();

            if (log != null) {
                if (filter != null) {
                    if (log.contains(filter)) {
                        write(writer, log, indentCount);
                    }
                } else {
                    write(writer, log, indentCount);
                }
            }

            for (int j = 0; j < indents.getSize(); ++j) {
                if (indents.get(j).isShowLog) {
                    indents.get(j).printLog(writer, indentCount + 2, filter);
                }
            }
        }
    }

    private void write(final BufferedWriter writer, String log, int indentCount) {
        try {
            String str;
            if (indentCount > 0) {
                str = String.format("%" + (indentCount + log.length()) + "s", log);
            } else {
                str = log;
            }
            writer.write(str + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("error IOException: " + e.getLocalizedMessage());
        }
    }
}