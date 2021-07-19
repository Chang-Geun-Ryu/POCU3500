package academy.pocu.comp3500.lab10.app;

import academy.pocu.comp3500.lab10.Project;
import academy.pocu.comp3500.lab10.project.Task;

import java.util.LinkedList;
import java.util.List;

public class Program {

    public static void main(String[] args) {
        {
            Task[] tasks = createTasks1();

            List<String> schedule = Project.findSchedule(tasks, true);

            assert (schedule.size() == tasks.length);
//            assert (schedule.get(0).equals("A"));
//            assert (schedule.get(1).equals("B"));
//            assert (schedule.get(2).equals("C"));
//            assert (schedule.get(3).equals("D"));
//            assert (schedule.get(2).equals("E"));
//            assert (schedule.get(2).equals("F"));
//            assert (schedule.get(3).equals("G"));

        }
        {
            Task[] tasks = createTasks1();

            List<String> schedule = Project.findSchedule(tasks, false);

            assert (schedule.size() == 4);
            assert (schedule.get(0).equals("A"));
            assert (schedule.get(1).equals("B"));
            assert (schedule.get(2).equals("F"));
            assert (schedule.get(3).equals("G"));

        }

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
}