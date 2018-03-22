package com.github.maxopoly.MemeMana.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.github.maxopoly.MemeMana.MemeManaOwnerManager;
import com.github.maxopoly.MemeMana.MemeManaPlugin;
import com.github.maxopoly.MemeMana.model.ManaGainStat;

import vg.civcraft.mc.prisonpearl.PrisonPearlPlugin;
import vg.civcraft.mc.prisonpearl.events.PrisonPearlEvent;

public class PearlListener implements Listener {

	@EventHandler
	public void playerPearled(PrisonPearlEvent e) {
		int oid = MemeManaOwnerManager.fromUUID(e.getPrisonPearl().getImprisonedId());
		ManaGainStat mgs = MemeManaPlugin.getInstance().getActivityManager().getForPlayer(oid);
		mgs.reset();
		MemeManaPlugin.getInstance().getDAO().updateManaStat(oid,mgs);
	}
	
	@EventHandler
	public void playerJoin(PlayerJoinEvent e) {
		if(PrisonPearlPlugin.getPrisonPearlManager().isImprisoned(e.getPlayer())) {
			int oid = MemeManaOwnerManager.fromUUID(e.getPlayer().getUniqueId());
			ManaGainStat mgs = MemeManaPlugin.getInstance().getActivityManager().getForPlayer(oid);
			mgs.reset();
			MemeManaPlugin.getInstance().getDAO().updateManaStat(oid, mgs);
		}
	}

}
