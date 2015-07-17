package net.ryanhecht.CMDEdit;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;


public class Main extends JavaPlugin implements Listener {
	
	@Override
	public void onEnable() {
		
		getLogger().info("CMDEdit enabled.");
	}
	public static WorldEditPlugin getWE() {
		return (WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
	}
	@Override
	public void onDisable() {
		
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		return Commands.onCommand(sender, cmd, label, args);
	}
}