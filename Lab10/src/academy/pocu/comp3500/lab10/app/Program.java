package academy.pocu.comp3500.lab10.app;

import academy.pocu.comp3500.lab10.Project;
import academy.pocu.comp3500.lab10.project.Task;

import java.util.LinkedList;
import java.util.List;

public class Program {

    public static void main(String[] args) {
        {
            Task[] tasks = createTasks3();
            List<String> schedule = Project.findSchedule(tasks, true);

            assert (schedule.size() == tasks.length);
//            assert (schedule.get(6).equals("6"));
//            assert (schedule.get(5).equals("1"));
//            assert (schedule.get(4).equals("4"));
//            assert (schedule.get(3).equals("5"));
//            assert (schedule.get(2).equals("0"));
//            assert (schedule.get(1).equals("3"));
//            assert (schedule.get(0).equals("2"));
        }
//        {
//            Task[] tasks = createTasks1();
//
//            List<String> schedule = Project.findSchedule(tasks, true);
//
//            assert (schedule.size() == tasks.length);
////            assert (schedule.get(0).equals("A"));
////            assert (schedule.get(1).equals("B"));
////            assert (schedule.get(2).equals("C"));
////            assert (schedule.get(3).equals("D"));
////            assert (schedule.get(2).equals("E"));
////            assert (schedule.get(2).equals("F"));
////            assert (schedule.get(3).equals("G"));
//
//        }
//        {
//            Task[] tasks = createTasks1();
//
//            List<String> schedule = Project.findSchedule(tasks, false);
//
//            assert (schedule.size() == 4);
//            assert (schedule.get(0).equals("A"));
//            assert (schedule.get(1).equals("B"));
//            assert (schedule.get(2).equals("F"));
//            assert (schedule.get(3).equals("G"));
//        }
//
        {
            Task a = new Task("A", 15);
            Task b = new Task("B", 12);
            Task c = new Task("C", 11);

            c.addPredecessor(b);
            b.addPredecessor(a);

            Task[] tasks = new Task[]{b, c, a};

            List<String> schedule = Project.findSchedule(tasks, false);

            assert (schedule.size() == 3);
            assert (schedule.get(0).equals("A"));
            assert (schedule.get(1).equals("B"));
            assert (schedule.get(2).equals("C"));
        }
        {
            Task[] tasks = createTasks();

            List<String> schedule = Project.findSchedule(tasks, false);

            assert (schedule.size() == 6);
            assert (schedule.get(0).equals("A"));
            assert (schedule.get(1).equals("B"));
            assert (schedule.get(2).equals("C"));
            assert (schedule.get(3).equals("E"));
            assert (schedule.get(4).equals("F"));
            assert (schedule.get(5).equals("I"));
        }
        {
            Task[] tasks = createTasks();

            List<String> schedule = Project.findSchedule(tasks, true);

            assert (schedule.size() == 9);
            assert (schedule.get(0).equals("A"));
            assert (schedule.get(1).equals("B"));
            assert (schedule.get(2).equals("C"));
            assert (schedule.indexOf("C") < schedule.indexOf("E"));
            assert (schedule.indexOf("E") < schedule.indexOf("F"));
            assert (schedule.indexOf("F") < schedule.indexOf("I"));

            assert (schedule.indexOf("C") < schedule.indexOf("D"));
            assert (schedule.indexOf("D") < schedule.indexOf("G"));
            assert (schedule.indexOf("G") < schedule.indexOf("H"));
        }
        {
            Task[] tasks = createTasks2();

            List<String> schedule = Project.findSchedule(tasks, true);

            assert (schedule.size() == 9);
            assert (schedule.get(0).equals("A"));
            assert (schedule.get(1).equals("B"));
            assert (schedule.get(2).equals("C"));
            assert (schedule.indexOf("C") < schedule.indexOf("E"));
            assert (schedule.indexOf("E") < schedule.indexOf("F"));
            assert (schedule.indexOf("F") < schedule.indexOf("I"));

            assert (schedule.indexOf("C") < schedule.indexOf("D"));
            assert (schedule.indexOf("D") < schedule.indexOf("G"));
            assert (schedule.indexOf("G") < schedule.indexOf("H"));
        }
//        {
//            Task a = new Task("A", 15);
//            Task b = new Task("B", 12);
//            Task c = new Task("C", 11);
//
//            c.addPredecessor(b);
//            b.addPredecessor(a);
//            a.addPredecessor(c);
//
//            Task[] tasks = new Task[]{b, c, a};
//
//            List<String> schedule = Project.findSchedule(tasks, false);
//
////            assert (schedule.size() == 0);
//
//            schedule = Project.findSchedule(tasks, true);
//
////            assert (schedule.size() == 3);
//        }
        {
            Task a = new Task("A", 12);
            Task b = new Task("B", 7);
            Task c = new Task("C", 10);
            Task d = new Task("D", 9);
            Task e = new Task("E", 8);
            Task f = new Task("F", 11);

            b.addPredecessor(a, e);
            c.addPredecessor(b);
            d.addPredecessor(c);
            e.addPredecessor(d, f);
            f.addPredecessor(c);

            Task[] tasks = new Task[]{ a, b, c, d, e, f };

            List<String> schedule = Project.findSchedule(tasks, true);
            System.out.println("Test Case by fergcd");
            for (String title : schedule) {
                System.out.println(title);
            }

            assert (schedule.size() == 6);
            assert (schedule.get(0).equals("A"));
            assert (schedule.get(1).equals("B"));
            assert (schedule.get(2).equals("C"));
            assert (schedule.indexOf("C") < schedule.indexOf("D"));
            assert (schedule.indexOf("C") < schedule.indexOf("F"));
            assert (schedule.indexOf("D") < schedule.indexOf("E"));
            assert (schedule.indexOf("F") < schedule.indexOf("E"));
        }
//
    }

    private static Task[] createTasks1() {
        Task a = new Task("A", 12);
        Task b = new Task("B", 7);
        Task c = new Task("C", 10);
        Task d = new Task("D", 9);
        Task e = new Task("E", 8);
        Task f = new Task("F", 11);
        Task g = new Task("G", 14);

        g.addPredecessor(f, b);
        b.addPredecessor(a);
        f.addPredecessor(a);

        c.addPredecessor(b, e);
        d.addPredecessor(c);
        e.addPredecessor(d);

//        a, b, c, d, e, f, g, h, i
        return new Task[]{
                a, b, c, d, e, f, g
        };
    }

    private static Task[] createTasks() {
        Task a = new Task("A", 12);
        Task b = new Task("B", 7);
        Task c = new Task("C", 10);
        Task d = new Task("D", 9);
        Task e = new Task("E", 8);
        Task f = new Task("F", 11);
        Task g = new Task("G", 14);
        Task h = new Task("H", 13);
        Task i = new Task("I", 6);

        i.addPredecessor(f);
        f.addPredecessor(e);
        e.addPredecessor(b, c);
        d.addPredecessor(c, h);
        c.addPredecessor(b);
        b.addPredecessor(a);
        h.addPredecessor(g);
        g.addPredecessor(d);
//        a, b, c, d, e, f, g, h, i
        return new Task[]{
                a, b, c, d, e, f, g, h, i
        };
    }
    private static Task[] createTasks2() {
        Task a = new Task("A", 12);
        Task b = new Task("B", 7);
        Task c = new Task("C", 10);
        Task d = new Task("D", 9);
        Task e = new Task("E", 8);
        Task f = new Task("F", 11);
        Task g = new Task("G", 14);
        Task h = new Task("H", 13);
        Task i = new Task("I", 6);

        i.addPredecessor(f);
        f.addPredecessor(e);
        e.addPredecessor(b, c);
        d.addPredecessor(c, h);
        c.addPredecessor(b);
        b.addPredecessor(a);
        h.addPredecessor(g);
        g.addPredecessor(d);
//        a, b, c, d, e, f, g, h, i
        return new Task[]{
                a,a, b,b, c,c, d,d, e,e, f,f, g,g, h,h, i,i
        };
    }

    private static Task[] createTasks3() {
        Task a = new Task("0", 0);
        Task b = new Task("1", 0);
        Task c = new Task("2", 0);
        Task d = new Task("3", 0);
        Task e = new Task("4", 0);
        Task f = new Task("5", 0);
        Task g = new Task("6", 0);

        a.addPredecessor(c, e);
        b.addPredecessor(a);
        c.addPredecessor(d);
        d.addPredecessor(c);
        e.addPredecessor(f);
        f.addPredecessor(a);
        g.addPredecessor(b);


        return new Task[]{
                c, a, b, d, e, f, g,
        };
    }
}