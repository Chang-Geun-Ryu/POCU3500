package academy.pocu.comp3500.assignment2.app;

import academy.pocu.comp3500.assignment2.Indent;
import academy.pocu.comp3500.assignment2.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static academy.pocu.comp3500.assignment2.Logger.log;

public class Program {

    public static void main(String[] args) throws IOException {

        {
            BufferedWriter writer = new BufferedWriter(new FileWriter("yourLog4.log"));
            Logger.indent();
            Logger.log("LOG");
            Logger.unindent();
            Logger.log("LOG2");
            Logger.printTo(writer);
            writer.close();
        }
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter("yourLog3.log"));
            Indent indent1;
            Indent indent2;
            Indent indent3;
            Indent indent4;
            log("this");
            Logger.indent();
            {
                log("will");
                indent1 = Logger.indent();
                {
                    log("(invisible1)");
                    log("(invisible2)");
                    log("(invisible3)");
                    Logger.indent();
                    {
                        log("be");
                        Logger.indent();
                        {
                            log("enough");
                            Logger.indent();
                            {
                                log("to");
                                indent2 = Logger.indent();
                                {
                                    log("(invisible1)");
                                    log("(invisible2)");
                                    log("(invisible3)");
                                }
                                Logger.unindent();
                                log("deepLogs");
                                Logger.indent();
                                {
                                    log("case");
                                    indent3 = Logger.indent();
                                    {
                                        log("(invisible1)");
                                        log("(invisible2)");
                                        log("(invisible3)");
                                    }
                                    Logger.unindent();
                                    log("am I");
                                }
                                Logger.unindent();
                                log("right?");
                            }
                            Logger.unindent();
                            log("12345678");
                            indent4 = Logger.indent();
                            {
                                Logger.indent();
                                {
                                    Logger.indent();
                                    {
                                        log("XX");
                                    }
                                }
                            }
                        }
                    }
                }
            }
            indent1.discard();
            indent2.discard();
            indent3.discard();
            indent4.discard();
            Logger.printTo(writer);
            writer.close();
        }
        {
            {
                BufferedWriter writer = new BufferedWriter(new FileWriter("yourLog2.log"));
                log("out 1");
                Indent indent = Logger.indent();
                {
                    log("in 1-in");
                    Logger.indent();
                    {
                        log("in 2-in");
                        Logger.indent();
                        {
                            log("in 3");
                        }
                        Logger.unindent();
                        log("in 2-out");
                    }
                    Logger.unindent();
                    log("in 1-out1");
                    indent.discard();
                    log("in 1-out2");
                }
                Logger.unindent();
                log("out 2");
                Logger.printTo(writer);

                writer.close();
            }
        }
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter("yourLog.log"));
            log("first level 1");
            Indent indent1 =Logger.indent();
            {
                log("second level 1");
                Indent indent2 =Logger.indent();
                {
                    log("third level 1");
                    Indent indent3 =Logger.indent();
                    {
                        log("fourth level 1");
                    }
                    Logger.unindent();
                }
                Logger.unindent();
            }
            Logger.unindent();
            indent1.discard();
            log("first level 2");
            Logger.printTo(writer);
            writer.close();
        }
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter("1.log"));

            log("hello");
            log("world");

            Logger.printTo(writer);
            writer.close();
            Logger.clear();
        sort();
            Logger.clear();
        sortFilter();
            Logger.clear();
        testIndent();
            Logger.clear();
        testDiscard();
            Logger.clear();
        testDiscard2();
            Logger.clear();
        }
        {
            test();
        }
    }

    private static void doMagic() {
        Indent indent = Logger.indent();
        {
            log("you can also nest an indent");
            log("like this!");
        }
        Logger.unindent();
    }

    public static void test() throws IOException {
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter("mylog1.log"));

            log("hello");
            log("world");
            log("this is logging at the top level");

            Logger.indent();
            {
                log("using indent, you can indent to organize your logs");
                log("call unindent() to decrease the indentation level");
            }
            Logger.unindent();

            Indent indent = Logger.indent();
            {
                log("whatever I say here");
                log("is discarded!");
                log("too bad!");

                indent.discard();
            }
            Logger.unindent();

            Logger.indent();
            {
                log("this won't be discarded");
                log("it's true!");

                doMagic();
            }
            Logger.unindent();

            log("back to the top level!");
            log("and let's print the logs");

            Logger.printTo(writer);

            Logger.clear();

            log("log was just cleared");
            log("so you start logging from the top level again");

            Logger.printTo(writer);

            writer.close();
        }

        Logger.clear();

        {
            final BufferedWriter writer1 = new BufferedWriter(new FileWriter("quicksort1.log"));
            final BufferedWriter writer2 = new BufferedWriter(new FileWriter("quicksort2.log"));

            int[] nums = new int[]{30, 10, 80, 90, 50, 70, 40};

            Sort.quickSort(nums);

            Logger.printTo(writer1);

            Logger.printTo(writer2, "90");

            writer1.close();
            writer2.close();
        }
    }


    public static void sort() throws  IOException {
        final BufferedWriter writer1 = new BufferedWriter(new FileWriter("sort.log"));

        final BufferedWriter writer2 = new BufferedWriter(new FileWriter("sort2.log"));

        int[] nums = new int[]{30, 10, 80, 90, 50, 70, 40};
        Sort.quickSort(nums);
        Logger.printTo(writer1);
        writer1.close();
        Logger.printTo(writer2);
        writer2.close();
    }

    public static void sortFilter() throws IOException {
        final BufferedWriter writer2 = new BufferedWriter(new FileWriter("sortfilter.log"));

        int[] nums = new int[]{30, 10, 80, 90, 50, 70, 40};

        Sort.quickSort(nums);

        Logger.printTo(writer2, "90");
        writer2.close();
    }

    public static void testIndent() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("testIndent.log"));

        log("this is not indented");

        Logger.indent();
        {
            log("but this is");
            log("so is this");
        }
        Logger.unindent();

        log("but not this");
        Logger.printTo(writer);

        writer.close();
    }

    public static void testDiscard() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("testDiscard.log"));

        int x = 10;
        Logger.indent();
        Logger.indent();
        log("first level 1");
        Indent indent = Logger.indent();
        {
            log("second level 1");
            log("second level 2");
            Logger.indent();
            {
                log("third level 1");
            }
            Logger.unindent();
            if (x % 2 == 0) {
                indent.discard();
            }
        }
        Logger.unindent();
        log("first level 2");
        Logger.printTo(writer);

        writer.close();
    }

    public static void testDiscard2() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("testDiscard2.log"));
        log("first level 1");
        Indent indent1 = Logger.indent();
        {
            log("second level 1");
            Indent indent2 = Logger.indent();
            {
                log("third level 1");
                Indent indent3 = Logger.indent();
                {
                    log("fourth level 1");
                }
                Logger.unindent();
            }
            Logger.unindent();
        }
        Logger.unindent();
        indent1.discard();
        log("first level 2");
        Logger.printTo(writer);
        writer.close();

        BufferedWriter writer2 = new BufferedWriter(new FileWriter("mylog11.log"));
        Logger.printTo(writer2);
        writer2.close();
    }
}