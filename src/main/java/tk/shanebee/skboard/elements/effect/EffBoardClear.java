package tk.shanebee.skboard.elements.effect;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;
import tk.shanebee.skboard.objects.Board;

public class EffBoardClear extends Effect {

    static {
        Skript.registerEffect(EffBoardClear.class,
                "clear %players%'[s] [score]board[s]",
                "clear [score]board[s] of %players%['s]");
    }

    private Expression<Player> players;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        players = (Expression<Player>) exprs[0];
        return true;
    }

    @Override
    protected void execute(Event event) {
        Player[] players = this.players.getArray(event);
        for (Player player : players) {
            Board board = Board.getBoard(player);
            if (board == null) continue;
            board.clearBoard();
        }
    }

    @Override
    public String toString(@Nullable Event e, boolean d) {
        return "clear scoreboard of " + this.players.toString(e, d);
    }

}
