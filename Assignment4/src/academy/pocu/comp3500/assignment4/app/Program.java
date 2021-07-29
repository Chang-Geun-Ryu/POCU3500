package academy.pocu.comp3500.assignment4.app;

import academy.pocu.comp3500.assignment4.Project;
import academy.pocu.comp3500.assignment4.project.Task;

public class Program {

    public static void main(String[] args) {

        {
            Task[] tasks = E03_ParallelTasks1();
            Project project = new Project(tasks);
            int bonusCount2 = project.findMaxBonusCount("6");
            assert (bonusCount2 == 3);
        }

        {
            Task[] tasks = createTasks1();
            Project project = new Project(tasks);

            int manMonths1 = project.findTotalManMonths("E");
            assert (manMonths1 == 4);

            int manMonths2 = project.findTotalManMonths("E");
            assert (manMonths1 == 4);

            int minDuration1 = project.findMinDuration("E");
            assert (minDuration1 == 4);
        }
        Task[] tasks = createTasks();

        Project project = new Project(tasks);

        int manMonths1 = project.findTotalManMonths("ms1");
        assert (manMonths1 == 17);

        int manMonths2 = project.findTotalManMonths("ms2");
        assert (manMonths2 == 46);

        int minDuration1 = project.findMinDuration("ms1");
        assert (minDuration1 == 14);

        int minDuration2 = project.findMinDuration("ms2");
        assert (minDuration2 == 32);

        int bonusCount3 = project.findMaxBonusCount("A");
        assert (bonusCount3 == 3);
        int bonusCount4 = project.findMaxBonusCount("B");
        assert (bonusCount4 == 5);

        int bonusCount1 = project.findMaxBonusCount("ms1");
        assert (bonusCount1 == 6);

        int bonusCount2 = project.findMaxBonusCount("ms2");
        assert (bonusCount2 == 6);
    }

    private static Task[] createTasks1() {
        Task a = new Task("A", 3);
        Task b = new Task("B", 5);
        Task c = new Task("C", 3);
        Task d = new Task("D", 2);
        Task e = new Task("E", 1);

        e.addPredecessor(d, a);

        d.addPredecessor(c);
        c.addPredecessor(b);
        b.addPredecessor(d);


        Task[] tasks = new Task[]{
                a, b, c, d, e,
        };

        return tasks;
    }

    private static Task[] createTasks() {
        Task a = new Task("A", 3);
        Task b = new Task("B", 5);
        Task c = new Task("C", 3);
        Task d = new Task("D", 2);
        Task e = new Task("E", 1);
        Task f = new Task("F", 2);
        Task g = new Task("G", 6);
        Task h = new Task("H", 8);
        Task i = new Task("I", 2);
        Task j = new Task("J", 4);
        Task k = new Task("K", 2);
        Task l = new Task("L", 8);
        Task m = new Task("M", 7);
        Task n = new Task("N", 1);
        Task o = new Task("O", 1);
        Task p = new Task("P", 6);
        Task ms1 = new Task("ms1", 6);
        Task ms2 = new Task("ms2", 8);

        c.addPredecessor(b);

        ms1.addPredecessor(a, c);

        e.addPredecessor(c);
        f.addPredecessor(g);
        g.addPredecessor(e);

        i.addPredecessor(h);
        j.addPredecessor(ms1);

        k.addPredecessor(j);
        n.addPredecessor(k);
        m.addPredecessor(n);
        l.addPredecessor(m);

        p.addPredecessor(i, j);
        o.addPredecessor(j);

        ms2.addPredecessor(o, p);

        Task[] tasks = new Task[]{
                a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, ms1, ms2
        };

        return tasks;
    }

    private static Task[] E03_ParallelTasks1() {
        Task a = new Task("0", 2);
        Task b = new Task("1", 1);
        Task c = new Task("2", 3);
        Task d = new Task("3", 5);
        Task e = new Task("4", 7);
        Task f = new Task("5", 2);
        Task g = new Task("6", 11);

        b.addPredecessor(a);
        c.addPredecessor(b);
        d.addPredecessor(c);
        f.addPredecessor(b, e);
        g.addPredecessor(d, f);

        Task[] tasks = new Task[]{
                a, b, c, d, e, f, g
        };

        return tasks;
    }
//           a 0    62e717f4-997e-42f6-8e54-226db16b8225           2
//           b 1    b923e897-6c74-49ab-838b-364b9a3e5225           1                       0 a
//           c 2    b809d2a7-fdfe-4ef4-9e07-9adb015a987d           3                       1 b
//           d 3    66ed2dbd-dc4a-4f51-8962-93ad7f3fe6af           5                       2 c
//           e 4    3e8cfaa3-8dd9-4bd0-a389-23ffd74ad1a5           7
//           f 5    88f6f348-080c-4bad-aee9-37103f3898c0           2                    1, 4 b, e
//           g 6    aebcccc5-a9f5-4248-955c-2646b8554758          11                    3, 5 d, f
}