package academy.pocu.comp3500.lab6.app;

import academy.pocu.comp3500.lab6.League;
import academy.pocu.comp3500.lab6.leagueofpocu.Player;

import java.util.HashMap;
import java.util.Stack;

public class Program {

    public static void main(String[] args) {
//        {
        // write your code here
        Player player111111 = new Player(1, "player1", 9);
        HashMap<Integer, Player> hashMap = new HashMap<>();
        hashMap.put(player111111.getId(), player111111);

        Player p = hashMap.remove(player111111.getId());

        Player[] players = new Player[1000];

        for (int i = 10; i < players.length ; ++i) {
            players[i] = new Player(i, String.format("p %d", i), i * 2);
        }

        for (int i = 0; i < 10 ; ++i) {
            players[i] = new Player(i, String.format("p %d", i), 5);
        }
        League leagueTest = new League(players, true);

        {
            Player[] a = leagueTest.getBottom(5);

            for (int i = 0; i < a.length; ++i) {
                System.out.println(a[i].toString());
            }
        }

//        Player player3Match1 = leagueTest.findMatchOrNull(players[555]);
//        }
        {
            Player player1 = new Player(1, "player1", 9);
            Player player2 = new Player(2, "player2", 10);
            Player player3 = new Player(3, "player3", 14);
            Player player4 = new Player(4, "player4", 14);
            Player player5 = new Player(5, "player5", 14);
            Player player6 = new Player(6, "player6", 14);
            Player player7 = new Player(7, "player7", 15);
            Player player8 = new Player(8, "player8", 16);
            Player player9 = new Player(9, "player9", 16);
            Player player10 = new Player(10, "player10", 20);
            Player player101 = new Player(10000, "player10", 20);
//            Player[] players = new Player[100000];
//
//            for (int i = 0; i < players.length; ++i) {
//                players[i] = new Player(i, String.format("p %d", i), i * 2);
//            }
//            League league = new League(players, true);
//
//            Player player3Match = league.findMatchOrNull(player101);

            League league1 = new League(new Player[]{player1, player2, player3, player4, player5, player6, player7, player8, player9, player10}, true);
            League league2 = new League(new Player[]{player4, player1, player3, player2}, false);
            League league11 = new League(new Player[]{player4}, false);
            Player player3Match = league11.findMatchOrNull(player4);

        }
        {
            Player player1 = new Player(1, "player1", 9);
            Player player2 = new Player(2, "player2", 10);
            Player player3 = new Player(3, "player3", 14);
            Player player4 = new Player(4, "player4", 14);

            League league = new League(new Player[]{player1, player2, player3, player4}, true);

            Player player3Match = league.findMatchOrNull(player3); // player4
            Player player2Match = league.findMatchOrNull(player2); // player1

            assert (player3Match.getId() == player4.getId());
            assert (player2Match.getId() == player1.getId());
        }
        {
            Player player1 = new Player(1, "player1", 12);
            Player player2 = new Player(2, "player2", 17);
            Player player3 = new Player(3, "player3", 12);
            Player player4 = new Player(4, "player4", 18);
            Player player5 = new Player(5, "player5", 10);

            League league = new League(new Player[]{player1, player2, player3, player4, player5}, false);

            Player[] topPlayers = league.getTop(3); // player4, player2, player1 or player4, player2, player3

            assert (topPlayers.length == 3);

            Player[] top = leagueTest.getTop(10);

            assert (top.length == 10);
        }

        {
            Player player1 = new Player(1, "player1", 12);
            Player player2 = new Player(2, "player2", 17);
            Player player3 = new Player(3, "player3", 12);
            Player player4 = new Player(4, "player4", 18);
            Player player5 = new Player(5, "player5", 10);

            League league = new League(new Player[]{player1, player2, player3, player4, player5}, false);

            Player[] bottomPlayers = league.getBottom(3); // player5, player1, player3 or player5, player3, player1

            assert (bottomPlayers.length == 3);

            Player[] bottom = leagueTest.getBottom(10);

            assert (bottom.length == 10);
        }
        {
            Player player1 = new Player(1, "player1", 12);
            Player player2 = new Player(2, "player2", 15);

            League league = new League(new Player[]{player1, player2}, true);

            Player newPlayer = new Player(3, "player3", 13);

            boolean success = league.join(newPlayer); // true
            assert (success == true);
            success = league.join(newPlayer); // false
            assert (success == false);
            success = league.join(player2); // false
            assert (success == false);
        }
        {
            League emptyLeague = new League();

            Player[] emptyLeaguePlayers = emptyLeague.getTop(10);

            assert (emptyLeaguePlayers.length == 0);

            Player player1 = new Player(1, "player1", 4);
            Player player2 = new Player(2, "player2", 6);
            Player player3 = new Player(3, "player3", 6);
            Player player4 = new Player(4, "player4", 7);
            Player player5 = new Player(5, "player5", 10);
            Player player6 = new Player(6, "player6", 12);

            League league1 = new League(new Player[]{player1, player2, player3, player4, player5, player6}, true);
            League league2 = new League(new Player[]{player6, player4, player1, player2, player5, player3}, false);

            // findMatchOrNull()
            Player match = league1.findMatchOrNull(player2);
            assert (match.getId() == player3.getId());

            match = league1.findMatchOrNull(player4);
            assert (match.getId() == player2.getId() || match.getId() == player3.getId());

            match = league1.findMatchOrNull(player5);
            assert (match.getId() == player6.getId());

            // getTop(), getBottom()
            Player[] topPlayers = league2.getTop(3);

            assert (topPlayers[0].getId() == player6.getId());
            assert (topPlayers[1].getId() == player5.getId());
            assert (topPlayers[2].getId() == player4.getId());

            Player[] bottomPlayers = league2.getBottom(3);

            assert (bottomPlayers[0].getId() == player1.getId());
            assert ((bottomPlayers[1].getId() == player2.getId() && bottomPlayers[2].getId() == player3.getId())
                    || (bottomPlayers[1].getId() == player3.getId() && bottomPlayers[2].getId() == player2.getId()));

            // join()
            boolean joinSuccess = league1.join(new Player(7, "player7", 9));
            assert (joinSuccess);

            joinSuccess = league1.join(new Player(1, "player1", 4));
            assert (!joinSuccess);

            // leave()
//            boolean leaveSuccess = league1.leave(new Player(5, "player5", 10));
//            assert (leaveSuccess);
//
//            leaveSuccess = league1.leave(new Player(5, "player5", 10));
//            assert (!leaveSuccess);

            boolean leaveSuccess = league1.leave(player6);
            assert (leaveSuccess);
            leaveSuccess = league1.leave(player5);
            assert (leaveSuccess);
        }

        {
            {
                Player player001 = new Player(1, "player1", 4);
                Player player002 = new Player(2, "player2", 4);
                Player player003 = new Player(3, "player3", 5);
                Player player004 = new Player(4, "player4", 6);
                Player player005 = new Player(5, "player5", 6);
                Player player006 = new Player(6, "player6", 5);
                League league01 = new League(new Player[]{
                        player001, player002, player003, player004, player005,
                        player006
                }, false);
                Player m = league01.findMatchOrNull(player006);
                assert (m.getId() == player003.getId());
            }
        }

        {
            {
                Player player1 = new Player(1, "player1", 10);
                Player player2 = new Player(2, "player2", 7);
                Player player3 = new Player(3, "player3", 9);
                Player player4 = new Player(4, "player4", 8);

                League league1 = new League();

                assert (league1.join(player1) == true);
                assert (league1.join(player2) == true);
                assert (league1.join(player3) == true);
                assert (league1.join(player4) == true);

                assert (league1.leave(player2) == true);
                assert (league1.leave(player3) == true);
                assert (league1.leave(player4) == true);
                assert (league1.leave(player1) == true);

                assert (league1.join(player1) == true);
                assert (league1.join(player2) == true);
                assert (league1.join(player3) == true);
                assert (league1.join(player4) == true);

                assert (league1.leave(player3) == true);
                assert (league1.leave(player1) == true);
                assert (league1.leave(player2) == true);
                assert (league1.leave(player4) == true);

                int n = 0;
            }

// leave same rating
            {
                Player player1 = new Player(1, "player1", 77);
                Player player2 = new Player(2, "player2", 77);
                Player player3 = new Player(3, "player3", 77);
                Player player4 = new Player(4, "player4", 77);
                Player player5 = new Player(5, "player5", 77);
                Player player6 = new Player(6, "player6", 77);
                League league1 = new League(new Player[]{player1, player2, player3, player4, player5, player6}, true);
                League league2 = new League(new Player[]{player1, player2, player3, player4, player5, player6}, false);
                League league3 = new League(new Player[]{player6, player4, player1, player2, player5, player3}, false);

                //assert (league1.leave(player1) == true);
                //assert (league1.leave(player1) == false);
                //assert (league1.leave(player2) == true);
                //assert (league1.leave(player2) == false);
                //assert (league1.leave(player3) == true);
                //assert (league1.leave(player3) == false);
                //assert (league1.leave(player4) == true);
                //assert (league1.leave(player4) == false);
                assert (league1.leave(player5) == true);
                assert (league1.leave(player5) == false);
                assert (league1.leave(player6) == true);
                assert (league1.leave(player6) == false);

                assert (league2.leave(player1) == true);
                assert (league2.leave(player1) == false);
                assert (league2.leave(player2) == true);
                assert (league2.leave(player2) == false);
                assert (league2.leave(player3) == true);
                assert (league2.leave(player3) == false);
                assert (league2.leave(player4) == true);
                assert (league2.leave(player4) == false);
                assert (league2.leave(player5) == true);
                assert (league2.leave(player5) == false);
                assert (league2.leave(player6) == true);
                assert (league2.leave(player6) == false);

                assert (league3.leave(player1) == true);
                assert (league3.leave(player1) == false);
                assert (league3.leave(player2) == true);
                assert (league3.leave(player2) == false);
                assert (league3.leave(player3) == true);
                assert (league3.leave(player3) == false);
                assert (league3.leave(player4) == true);
                assert (league3.leave(player4) == false);
                assert (league3.leave(player5) == true);
                assert (league3.leave(player5) == false);
                assert (league3.leave(player6) == true);
                assert (league3.leave(player6) == false);
            }
        }

        {
            {
                Player player100 = new Player(100, "player100", 6);
                Player player101 = new Player(101, "player101", 6);
                Player player102 = new Player(102, "player102", 6);
                Player player103 = new Player(103, "player103", 6);
                Player player104 = new Player(104, "player104", 6);
                League league = new League(new Player[]{
                        player100, player101, player102, player103, player104
                }, true);
                System.out.println("G03");
                boolean leaveSuccess = league.leave(player100);
                assert (leaveSuccess);
                leaveSuccess = league.leave(player100);
                assert (!leaveSuccess);
                leaveSuccess = league.leave(player101);
                assert (leaveSuccess);
                leaveSuccess = league.leave(player101);
                assert (!leaveSuccess);
                leaveSuccess = league.leave(player102);
                assert (leaveSuccess);
                leaveSuccess = league.leave(player102);
                assert (!leaveSuccess);
                leaveSuccess = league.leave(player103);
                assert (leaveSuccess);
                leaveSuccess = league.leave(player103);
                assert (!leaveSuccess);
                leaveSuccess = league.leave(player104);
                assert (leaveSuccess);
                leaveSuccess = league.leave(player104);
                assert (!leaveSuccess);
                leaveSuccess = league.leave(player104);
                assert (!leaveSuccess);
                Player m = league.findMatchOrNull(player111111);
                assert (m == null);
            }
            {
                {
                    Player player001 = new Player(1, "player1", 4);
                    Player player002 = new Player(2, "player2", 4);
                    Player player003 = new Player(3, "player3", 5);
                    Player player004 = new Player(4, "player4", 6);
                    Player player005 = new Player(5, "player5", 6);
                    Player player006 = new Player(6, "player6", 5);
                    League league01 = new League(new Player[]{
                            player001, player002, player003, player004, player005,
                            player006
                    }, false);
                    assert (league01.leave(player006));
                    assert (!league01.join(player003));
                    assert (!league01.leave(player006));
                    Player m = league01.findMatchOrNull(player003);
                    assert (m != null && (m.getId() == player004.getId() || m.getId() == player005.getId()));
                }
                {
                    Player player001 = new Player(1, "player1", 4);
                    Player player002 = new Player(2, "player2", 4);
                    Player player003 = new Player(3, "player3", 5);
                    Player player004 = new Player(4, "player4", 6);
                    Player player005 = new Player(5, "player5", 6);
                    Player player006 = new Player(6, "player6", 5);
                    League league01 = new League(new Player[]{
                            player001, player002, player003, player004, player005,
                            player006
                    }, false);
                    assert (league01.leave(player003));
                    assert (!league01.join(player006));
                    assert (!league01.leave(player003));
                    Player m = league01.findMatchOrNull(player006);
                    assert (m != null && (m.getId() == player004.getId() || m.getId() == player005.getId()));
                }
                {
                    Player player001 = new Player(1, "player1", 4);
                    Player player002 = new Player(2, "player2", 4);
                    Player player003 = new Player(3, "player3", 5);
                    Player player004 = new Player(4, "player4", 6);
                    Player player005 = new Player(5, "player5", 6);
                    Player player006 = new Player(6, "player6", 5);
                    League league01 = new League(new Player[]{
                            player001, player002, player003, player004, player005,
                            player006
                    }, false);
                    assert (league01.leave(player001)); assert (!league01.leave(player001));
                    Player m = league01.findMatchOrNull(player002);
                    assert (m != null && (m.getId() == player003.getId() || m.getId() == player006.getId()));
                    assert (league01.leave(player004)); assert (!league01.leave(player004));
                    m = league01.findMatchOrNull(player005);
                    assert (m != null && (m.getId() == player003.getId() || m.getId() == player006.getId()));
                    assert (league01.leave(player003)); assert (!league01.leave(player003));
                    m = league01.findMatchOrNull(player006);
                    assert (m != null && (m.getId() == player005.getId()) || m.getId() == player006.getId());
                    assert (league01.leave(player002)); assert (!league01.leave(player002));
                    m = league01.findMatchOrNull(player005);
                    assert (m != null && (m.getId() == player006.getId()));
                    assert (league01.leave(player005)); assert (!league01.leave(player005));
                    m = league01.findMatchOrNull(player006);
                    assert (m == null);
                }
                {
                    Player player001 = new Player(1, "player1", 4); //
                    Player player002 = new Player(2, "player2", 4); //
                    Player player003 = new Player(3, "player3", 5);
                    Player player004 = new Player(4, "player4", 6); //
                    Player player005 = new Player(5, "player5", 6); //
                    Player player006 = new Player(6, "player6", 5);
                    League league01 = new League(new Player[]{
                            player001, player002, player003, player004, player005,
                            player006
                    }, false);
                    assert (league01.leave(player004)); assert (!league01.leave(player004));
                    Player m = league01.findMatchOrNull(player005);
                    assert (m != null && (m.getId() == player003.getId() || m.getId() == player006.getId()));
                    assert (league01.leave(player001)); assert (!league01.leave(player001));
                    m = league01.findMatchOrNull(player003);
                    assert (m != null && (m.getId() == player006.getId()));
                    assert (league01.leave(player005)); assert (!league01.leave(player005));
                    m = league01.findMatchOrNull(player003);
                    assert (m != null && (m.getId() == player006.getId()));
                    m = league01.findMatchOrNull(player006);
                    assert (m != null && (m.getId() == player003.getId()));
                    assert (league01.leave(player002)); assert (!league01.leave(player002));
                    m = league01.findMatchOrNull(player003);
                    assert (m != null && (m.getId() == player006.getId()));
                    m = league01.findMatchOrNull(player006);
                    assert (m != null && (m.getId() == player003.getId()));
                    assert (league01.leave(player003)); assert (!league01.leave(player003));
                    m = league01.findMatchOrNull(player006);
                    assert (m == null);
                }
            }
        }
    }
}
