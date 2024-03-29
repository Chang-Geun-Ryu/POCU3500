package academy.pocu.comp3500.lab9.app;

import academy.pocu.comp3500.lab9.CodingMan;
import academy.pocu.comp3500.lab9.ProfitCalculator;
import academy.pocu.comp3500.lab9.PyramidBuilder;
import academy.pocu.comp3500.lab9.data.Task;
import academy.pocu.comp3500.lab9.data.VideoClip;

public class Program {

    public static void main(String[] args) {
        // write your code here
        {
            int pyramidHeight = PyramidBuilder.findMaxHeight(new int[]{5}, 10); // 0
            pyramidHeight = PyramidBuilder.findMaxHeight(new int[]{6, 8}, 10); // 1
            pyramidHeight = PyramidBuilder.findMaxHeight(new int[]{3, 3, 4, 4, 30, 12, 10, 10, 6}, 5); // 3
        }
        {
            // PyramidBuilder
            int pyramidHeight = PyramidBuilder.findMaxHeight(new int[]{3}, 2);

            assert (pyramidHeight == 0);

            pyramidHeight = PyramidBuilder.findMaxHeight(new int[]{5, 5}, 10);

            assert (pyramidHeight == 0);

            pyramidHeight = PyramidBuilder.findMaxHeight(new int[]{5, 5}, 9);

            assert (pyramidHeight == 1);

            pyramidHeight = PyramidBuilder.findMaxHeight(new int[]{5, 4, 6}, 8);

            assert (pyramidHeight == 1);

            pyramidHeight = PyramidBuilder.findMaxHeight(new int[]{5, 6, 8, 10, 12, 16, 16}, 17);

            assert (pyramidHeight == 2);

            pyramidHeight = PyramidBuilder.findMaxHeight(new int[]{60, 40, 20, 16, 16, 12, 10, 8, 6, 5}, 10);

            assert (pyramidHeight == 3);
        }
        {
            Task[] tasks = new Task[]{
                    new Task(20, 30),
                    new Task(30, 40),
                    new Task(10, 35)
            };

            int profit = ProfitCalculator.findMaxProfit(tasks, new int[]{10}); // 35

            assert (profit == 35);
            profit = ProfitCalculator.findMaxProfit(tasks, new int[]{20, 25}); // 70
            assert (profit == 70);
            profit = ProfitCalculator.findMaxProfit(tasks, new int[]{40, 15, 5}); // 75
            assert (profit == 75);
        }
        {
            // ProfitCalculator
            Task[] tasks = new Task[]{
                    new Task(20, 30),
            };
            int[] skillLevels = new int[]{20};

            int profit = ProfitCalculator.findMaxProfit(tasks, skillLevels);

            assert (profit == 30);

            tasks = new Task[]{
                    new Task(20, 30),
            };
            skillLevels = new int[]{10};

            profit = ProfitCalculator.findMaxProfit(tasks, skillLevels);

            assert (profit == 0);

            tasks = new Task[]{
                    new Task(20, 50),
                    new Task(20, 40)
            };
            skillLevels = new int[]{25};

            profit = ProfitCalculator.findMaxProfit(tasks, skillLevels);

            assert (profit == 50);

            tasks = new Task[]{
                    new Task(20, 40),
                    new Task(30, 40),
                    new Task(50, 25),
                    new Task(60, 45)
            };
            skillLevels = new int[]{10, 20, 35, 70, 45};

            profit = ProfitCalculator.findMaxProfit(tasks, skillLevels);

            assert (profit == 165);
        }

        {
            VideoClip[] clips = new VideoClip[]{
                    new VideoClip(0, 15),
                    new VideoClip(10, 20),
                    new VideoClip(30, 35)
            };

            int count = CodingMan.findMinClipsCount(clips, 10); // 1
            assert (count == 1);
            count = CodingMan.findMinClipsCount(clips, 20); // 2
            assert (count == 2);
            count = CodingMan.findMinClipsCount(clips, 25); // -1
            assert (count == -1);
            count = CodingMan.findMinClipsCount(clips, 35); // -1
            assert (count == -1);
        }
        {
            // CodingMan
            VideoClip[] clips = new VideoClip[]{
                    new VideoClip(0, 10),
            };
            int airTime = 10;

            int count = CodingMan.findMinClipsCount(clips, airTime);

            assert (count == 1);

            clips = new VideoClip[]{
                    new VideoClip(30, 60),
                    new VideoClip(0, 20)
            };
            airTime = 60;

            count = CodingMan.findMinClipsCount(clips, airTime);

            assert (count == -1);

            clips = new VideoClip[]{
                    new VideoClip(0, 5),
                    new VideoClip(0, 20),
                    new VideoClip(5, 30),
                    new VideoClip(25, 35),
                    new VideoClip(35, 70),
                    new VideoClip(50, 75)
            };
            airTime = 60;

            count = CodingMan.findMinClipsCount(clips, airTime);

            assert (count == 4);
        }
        {
            VideoClip[] clips = new VideoClip[]{
                    new VideoClip(0, 14),
                    new VideoClip(0, 10),
                    new VideoClip(1, 3),
                    new VideoClip(2, 10),
                    new VideoClip(0, 15),
                    new VideoClip(0, 17),
                    new VideoClip(5, 20),
            };

            int count = CodingMan.findMinClipsCount(clips, 20);

            assert (count == 2);

            clips = new VideoClip[]{
                    new VideoClip(1, 2),
                    new VideoClip(1, 4),
                    new VideoClip(4, 10),
                    new VideoClip(3, 11),
                    new VideoClip(2, 14),
                    new VideoClip(1, 15),
            };

            count = CodingMan.findMinClipsCount(clips, 15);

            assert (count == -1);
        }

        {
            {
                VideoClip[] clips = new VideoClip[]{
                        new VideoClip(0, 3),
                        new VideoClip(1, 4),
                        new VideoClip(0, 5),
                        new VideoClip(3, 6),
                        new VideoClip(4, 7),
                        new VideoClip(3, 9),
                        new VideoClip(4, 10),
                        new VideoClip(5, 11),
                        new VideoClip(8, 11),
                };
                int count = CodingMan.findMinClipsCount(clips, 11);
                assert (2 == count);
            }
        }
    }
}
