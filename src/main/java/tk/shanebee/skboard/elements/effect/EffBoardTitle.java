package tk.shanebee.skboard.elements.effect;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;
import tk.shanebee.skboard.objects.Board;

@Name("Board - Title")
@Description("Set the title of a player's scoreboard.")
@Examples({"set title of player's scoreboard to \"MyServer\"",
        "set title of all players' scoreboards to \"Our Serberder!\""})
@Since("1.0.0")
public class EffBoardTitle extends Effect {

    static {
        Skript.registerEffect(EffBoardTitle.class,
                "set title of %players%'s [score]board[s] to %string%");
    }

    private Expression<Player> players;
    private Expression<String> title;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        players = (Expression<Player>) exprs[0];
        title = (Expression<String>) exprs[1];
        return true;
    }

    @Override
    protected void execute(Event event) {
        Player[] players = this.players.getArray(event);
        String title = this.title.getSingle(event);
        for (Player player : players) {
            Board board = Board.getBoard(player);
            if (board == null) continue;
            board.setTitle(title != null ? title : "");
        }
    }

    @Override
    public String toString(@Nullable Event e, boolean d) {
        return "set title of " + this.players.toString(e, d) + " scoreboard to " + this.title.toString(e, d);
    }

}
