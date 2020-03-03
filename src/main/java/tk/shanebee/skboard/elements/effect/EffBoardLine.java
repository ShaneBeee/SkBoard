package tk.shanebee.skboard.elements.effect;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import tk.shanebee.skboard.objects.Board;

@Name("Board - Line")
public class EffBoardLine extends Effect {

    static {
        Skript.registerEffect(EffBoardLine.class,
                "set line %number% of %players%'s [score]board[s] to %string%",
                "delete line %number% of %players%'s [score]board[s]");
    }

    private Expression<Number> line;
    private Expression<Player> players;
    private Expression<String> text;
    private boolean set;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        line = (Expression<Number>) exprs[0];
        players = (Expression<Player>) exprs[1];
        set = matchedPattern == 0;
        if (set) {
            text = (Expression<String>) exprs[2];
        }
        return true;
    }

    @Override
    protected void execute(Event event) {
        Player[] players = this.players.getArray(event);
        int line = this.line.getSingle(event).intValue();
        if (line > 15 || line < 1) return; // Only set lines 1-15

        String text = this.text.getSingle(event);

        for (Player player : players) {
            Board board = Board.getBoard(player);
            if (set)
                board.setLine(line, text);
            else
                board.deleteLine(line);
        }

    }

    @Override
    public String toString(Event e, boolean d) {
        String set = this.set ? "set" : "delete";
        String string = this.set ? " to " + text.toString(e, d) : "";
        return set + " line " + line.toString(e, d) + " of " + players.toString(e,d) + " scoreboard" + string;
    }

}
