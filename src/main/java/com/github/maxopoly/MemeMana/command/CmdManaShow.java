package com.github.maxopoly.MemeMana.command;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.maxopoly.MemeMana.MemeManaOwnerManager;
import com.github.maxopoly.MemeMana.MemeManaPlugin;
import com.github.maxopoly.MemeMana.model.ManaGainStat;
import com.github.maxopoly.MemeMana.model.MemeManaPouch;

import net.md_5.bungee.api.ChatColor;
import vg.civcraft.mc.civmodcore.command.PlayerCommand;

public class CmdManaShow extends PlayerCommand {
	public CmdManaShow(String name) {
		super(name);
		setIdentifier("manashow");
		setDescription("Show your own mana");
		setUsage("/manashow");
		setArguments(0,0);
	}

	public boolean execute(CommandSender sender, String [] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Can't show your own mana from console.");
			return true;
		}
		int owner = MemeManaOwnerManager.fromPlayer((Player)sender);
		int manaAvailable = MemeManaPouch.getPouch(owner).getManaContent();
		sender.sendMessage(ChatColor.YELLOW + "You have " + ChatColor.GOLD + manaAvailable + ChatColor.YELLOW + " mana");
		ManaGainStat stat = MemeManaPlugin.getInstance().getActivityManager().getForPlayer(owner);
		if(stat.getPayout() != 0) {
			sender.sendMessage(ChatColor.YELLOW + "Your mana streak is " + ChatColor.LIGHT_PURPLE + stat.getPayout());
		}
		return true;
	}

	public List <String> tabComplete(CommandSender sender, String [] args) {
		return new LinkedList <String> (); //empty list
	}
}
