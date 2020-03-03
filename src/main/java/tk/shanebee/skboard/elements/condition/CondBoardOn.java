package tk.shanebee.skboard.elements.condition;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;
import tk.shanebee.skboard.objects.Board;

@Name("Board - Is on")
@Description("Check if a player's scoreboard is currently toggled on or off")
@Examples({"if scoreboard of player is on:",
        "\ttoggle scoreboard of player off"})
@Since("1.0.0")
public class CondBoardOn extends Condition {

    static {
        Skript.registerCondition(CondBoardOn.class,
                "scoreboard of %player% is on",
                "scoreboard of %player% is(n't| not) on");
    }

    private Expression<Player> player;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        player = (Expression<Player>) exprs[0];
        return true;
    }

    @Override
    public boolean check(Event event) {
        Player player = this.player.getSingle(event);
        if (player != null) {
            Board board = Board.getBoard(player);
            return board.isOn();
        }
        return false;
    }

    @Override
    public String toString(@Nullable Event e, boolean d) {
        return "scoreboard of " + this.player.toString(e, d) + " is on";
    }

}
