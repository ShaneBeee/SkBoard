package tk.shanebee.skboard;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import tk.shanebee.skboard.listener.PlayerListener;
import tk.shanebee.skboard.objects.Board;

import java.io.IOException;

@SuppressWarnings("unused")
public class SkBoard extends JavaPlugin {

    private static SkBoard instance;
    @Override
    public void onEnable() {
        instance = this;
        PluginDescriptionFile desc = getDescription();

        if ((Bukkit.getPluginManager().getPlugin("Skript") != null) && (Skript.isAcceptRegistrations())) {
            SkriptAddon addon = Skript.registerAddon(this);
            if (!Skript.isRunningMinecraft(1, 13)) {
                log("&cThis addon is not supported on your version.");
                log("&7SkBoard only supports 1.13+");
                Bukkit.getPluginManager().disablePlugin(this);
                return;
            }
            try {
                addon.loadClasses("tk.shanebee.skboard", "elements");
            } catch (IOException e) {
                e.printStackTrace();
                Bukkit.getPluginManager().disablePlugin(this);
                return;
            }
            log("&aSuccessfully enabled v" + desc.getVersion());
            if (desc.getVersion().contains("Beta")) {
                log("&eThis is a BETA build, things may not work as expected, please report any bugs on GitHub");
                log("&ehttps://github.com/ShaneBeee/SkBoard/issues");
            }
            Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
        } else {
            log("&cDependency Skript was not found, plugin disabling");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        for (Player player : Bukkit.getOnlinePlayers()) { // if reload, load player boards
            Board.loadBoard(player);
        }
    }

    @Override
    public void onDisable() {
        Board.clearBoards();
    }

    public static SkBoard getInstance() {
        return instance;
    }

    public void log(String message) {
        String pre = "&7[&bSkBoard&7] &r";
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', pre + message));
    }

}
