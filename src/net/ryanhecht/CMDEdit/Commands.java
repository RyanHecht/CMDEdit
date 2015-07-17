package net.ryanhecht.CMDEdit;

import java.util.ArrayList;

import org.bukkit.ChatColor;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CommandBlock;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.Selection;

public class Commands {
	public static boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		WorldEditPlugin WE = Main.getWE();
		if(sender instanceof Player) {
			Player player = (Player) sender;
			Selection sel = WE.getSelection(player);
			if(sel==null) {
				player.sendMessage("Empty selection!");
				return false;
			}
			if(cmd.getName().equals("cmdedit")) {
				
				if(args[0].equalsIgnoreCase("findreplace")) {
					int occ=0;
					for(CommandBlock cb : getCMDBlocks(sel)) {
						String cbcmd = cb.getCommand();
						if(cbcmd.indexOf(args[1]) > -1) {
							occ++;
							String newcmd = cbcmd.replace(args[1], args[2]);
							cb.setCommand(newcmd);
							cb.update();
						}
					}
					player.sendMessage(ChatColor.AQUA + "" + occ + " occurances of " + ChatColor.LIGHT_PURPLE + args[1] + ChatColor.AQUA + " changed to " + ChatColor.LIGHT_PURPLE + args[2]);
				}
				
				if(args[0].equalsIgnoreCase("set")) {
					int occ=0;
					for(CommandBlock cb : getCMDBlocks(sel)) {
						occ++;
						cb.setCommand(concatArgs(args));
						cb.update();
					}
					player.sendMessage(ChatColor.LIGHT_PURPLE + "" + occ + ChatColor.AQUA + " command blocks set to " + ChatColor.LIGHT_PURPLE + concatArgs(args));
				}
			}
			
			
			
			
			
			
			
			
			
			
			
		}
		return true;
	}
	public static ArrayList<CommandBlock> getCMDBlocks(Selection sel) {
		ArrayList<CommandBlock> cmds = new ArrayList<CommandBlock>();
         Vector min = sel.getNativeMinimumPoint();
         Vector max = sel.getNativeMaximumPoint();
         for(int x = min.getBlockX();x <= max.getBlockX(); x++){
             for(int y = min.getBlockY();y <= max.getBlockY(); y++){
                 for(int z = min.getBlockZ();z <= max.getBlockZ(); z++){
                 	Block b = sel.getWorld().getBlockAt(x,y,z);
                 	if(b.getType().equals(Material.COMMAND)) {
                 		cmds.add((CommandBlock) b.getState());
                 	}
                 }
             }
         }
         return cmds;
	}
	public static String concatArgs(String args[]) {
		String result="";
		for(int i=1; i<args.length; i++) {
			result+=args[i];
			if(!(i==args.length-1)) result += " ";
		}
		return result;
	}
}
