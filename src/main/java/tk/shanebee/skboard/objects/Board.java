package tk.shanebee.skboard.objects;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import tk.shanebee.skboard.SkBoard;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("WeakerAccess")
public class Board {

    // STATIC STUFF
    private static Map<Player, Board> boardMap = new HashMap<>();

    public static Board getBoard(Player player) {
        return boardMap.get(player);
    }

    public static void createBoard(Player player) {
        Board board = new Board(player);
        boardMap.put(player, board);
    }

    // OBJECT STUFF
    private SkBoard plugin = SkBoard.getInstance();
    private Player player;
    private Scoreboard scoreboard;
    private Objective board;
    private Team line1, line2, line3, line4, line5, line6, line7, line8, line9, line10, line11, line12, line13, line14, line15;

    @SuppressWarnings("ConstantConditions")
    public Board(Player player) {
        this.player = player;
        scoreboard = plugin.getServer().getScoreboardManager().getNewScoreboard();
        player.setScoreboard(scoreboard);
        board = scoreboard.registerNewObjective("Board", "dummy", "Board");
        board.setDisplaySlot(DisplaySlot.SIDEBAR);
        board.setDisplayName("");
        line1 = scoreboard.registerNewTeam("line1");
        line2 = scoreboard.registerNewTeam("line2");
        line3 = scoreboard.registerNewTeam("line3");
        line4 = scoreboard.registerNewTeam("line4");
        line5 = scoreboard.registerNewTeam("line5");
        line6 = scoreboard.registerNewTeam("line6");
        line7 = scoreboard.registerNewTeam("line7");
        line8 = scoreboard.registerNewTeam("line8");
        line9 = scoreboard.registerNewTeam("line9");
        line10 = scoreboard.registerNewTeam("line10");
        line11 = scoreboard.registerNewTeam("line11");
        line12 = scoreboard.registerNewTeam("line12");
        line13 = scoreboard.registerNewTeam("line13");
        line14 = scoreboard.registerNewTeam("line14");
        line15 = scoreboard.registerNewTeam("line15");

        line1.addEntry(getColString("&1"));
        line2.addEntry(getColString("&2"));
        line3.addEntry(getColString("&3"));
        line4.addEntry(getColString("&4"));
        line5.addEntry(getColString("&5"));
        line6.addEntry(getColString("&6"));
        line7.addEntry(getColString("&7"));
        line8.addEntry(getColString("&8"));
        line9.addEntry(getColString("&9"));
        line10.addEntry(getColString("&0"));
        line11.addEntry(getColString("&a"));
        line12.addEntry(getColString("&b"));
        line13.addEntry(getColString("&c"));
        line14.addEntry(getColString("&d"));
        line15.addEntry(getColString("&e"));

    }

    public void setTitle(String title) {
        board.setDisplayName(getColString(title));
    }

    public void setLine(int line, String text) {
        Team t = getLine(line);
        t.setPrefix(getColString(text));
        board.getScore(getEntry(line)).setScore(line);
    }

    public void deleteLine(int line) {
        Team t = getLine(line);
        t.setPrefix(""); // TODO need to figure this one out
        board.getScore(getEntry(line)).setScore(line);
    }

    private Team getLine(int line) {
        switch (line) {
            case 2:
                return line2;
            case 3:
                return line3;
            case 4:
                return line4;
            case 5:
                return line5;
            case 6:
                return line6;
            case 7:
                return line7;
            case 8:
                return line8;
            case 9:
                return line9;
            case 10:
                return line10;
            case 11:
                return line11;
            case 12:
                return line12;
            case 13:
                return line13;
            case 14:
                return line14;
            case 15:
                return line15;
            default:
                return line1;
        }
    }

    private String getEntry(int line) {
        switch (line) {
            case 2:
                return getColString("&2");
            case 3:
                return getColString("&3");
            case 4:
                return getColString("&4");
            case 5:
                return getColString("&5");
            case 6:
                return getColString("&6");
            case 7:
                return getColString("&7");
            case 8:
                return getColString("&8");
            case 9:
                return getColString("&9");
            case 10:
                return getColString("&0");
            case 11:
                return getColString("&a");
            case 12:
                return getColString("&b");
            case 13:
                return getColString("&c");
            case 14:
                return getColString("&d");
            case 15:
                return getColString("&e");
            default:
                return getColString("&1");
        }
    }

    private String getColString(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

}
