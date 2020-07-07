package me.scoltbr.terrenos;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import me.scoltbr.terrenos.Cuboid.CuboidDirection;

public class TerrenosEvents implements Listener {

	public static void comprarTerreno(Player p, Types tipo) {
		if (tipo == Types.MINI) {
			Cuboid cb = new Cuboid(p.getWorld(), (int) p.getLocation().getX() - 5, (int) p.getLocation().getY(),
					(int) p.getLocation().getZ() - 5, (int) p.getLocation().getX() + 5, (int) p.getLocation().getY(),
					(int) p.getLocation().getZ() + 5);
			cb.getFace(CuboidDirection.East).forEach(a -> a.setType(Material.OAK_FENCE));
			cb.getFace(CuboidDirection.West).forEach(a -> a.setType(Material.OAK_FENCE));
			cb.getFace(CuboidDirection.North).forEach(a -> a.setType(Material.OAK_FENCE));
			cb.getFace(CuboidDirection.South).forEach(a -> a.setType(Material.OAK_FENCE));
			Location pos1 = new Location(p.getWorld(), p.getLocation().getX() - 5, 150, p.getLocation().getZ() - 5);
			Location pos2 = new Location(p.getWorld(), p.getLocation().getX() + 5, 0, p.getLocation().getZ() + 5);
			Terrenos t = new Terrenos(p.getName(), tipo, pos1, pos2, p.getLocation(), null, false);
			Main.instance.TerrenosCache.put(p.getName(), t);
			Main.instance.DataFile.saveConfig();
			return;
		}

		if (tipo == Types.PEQUENO) {
			Cuboid cb = new Cuboid(p.getWorld(), (int) p.getLocation().getX() - 7, (int) p.getLocation().getY(),
					(int) p.getLocation().getZ() - 7, (int) p.getLocation().getX() + 7, (int) p.getLocation().getY(),
					(int) p.getLocation().getZ() + 7);
			cb.getFace(CuboidDirection.East).forEach(a -> a.setType(Material.OAK_FENCE));
			cb.getFace(CuboidDirection.West).forEach(a -> a.setType(Material.OAK_FENCE));
			cb.getFace(CuboidDirection.North).forEach(a -> a.setType(Material.OAK_FENCE));
			cb.getFace(CuboidDirection.South).forEach(a -> a.setType(Material.OAK_FENCE));
			Location pos1 = new Location(p.getWorld(), p.getLocation().getX() - 7, 150, p.getLocation().getZ() - 7);
			Location pos2 = new Location(p.getWorld(), p.getLocation().getX() + 7, 0, p.getLocation().getZ() + 7);
			Terrenos t = new Terrenos(p.getName(), tipo, pos1, pos2, p.getLocation(), null, false);
			Main.instance.TerrenosCache.put(p.getName(), t);
			Main.instance.DataFile.saveConfig();
			return;
		}

		if (tipo == Types.MEDIO) {
			Cuboid cb = new Cuboid(p.getWorld(), (int) p.getLocation().getX() - 10, (int) p.getLocation().getY(),
					(int) p.getLocation().getZ() - 10, (int) p.getLocation().getX() + 10, (int) p.getLocation().getY(),
					(int) p.getLocation().getZ() + 10);
			cb.getFace(CuboidDirection.East).forEach(a -> a.setType(Material.OAK_FENCE));
			cb.getFace(CuboidDirection.West).forEach(a -> a.setType(Material.OAK_FENCE));
			cb.getFace(CuboidDirection.North).forEach(a -> a.setType(Material.OAK_FENCE));
			cb.getFace(CuboidDirection.South).forEach(a -> a.setType(Material.OAK_FENCE));
			Location pos1 = new Location(p.getWorld(), p.getLocation().getX() - 10, 150, p.getLocation().getZ() - 10);
			Location pos2 = new Location(p.getWorld(), p.getLocation().getX() + 10, 0, p.getLocation().getZ() + 10);
			Terrenos t = new Terrenos(p.getName(), tipo, pos1, pos2, p.getLocation(), null, false);
			Main.instance.TerrenosCache.put(p.getName(), t);
			Main.instance.DataFile.saveConfig();
			return;
		}
		if (tipo == Types.GRANDE) {
			Cuboid cb = new Cuboid(p.getWorld(), (int) p.getLocation().getX() - 12, (int) p.getLocation().getY(),
					(int) p.getLocation().getZ() - 12, (int) p.getLocation().getX() + 12, (int) p.getLocation().getY(),
					(int) p.getLocation().getZ() + 12);
			cb.getFace(CuboidDirection.East).forEach(a -> a.setType(Material.OAK_FENCE));
			cb.getFace(CuboidDirection.West).forEach(a -> a.setType(Material.OAK_FENCE));
			cb.getFace(CuboidDirection.North).forEach(a -> a.setType(Material.OAK_FENCE));
			cb.getFace(CuboidDirection.South).forEach(a -> a.setType(Material.OAK_FENCE));
			Location pos1 = new Location(p.getWorld(), p.getLocation().getX() - 12, 150, p.getLocation().getZ() - 12);
			Location pos2 = new Location(p.getWorld(), p.getLocation().getX() + 12, 0, p.getLocation().getZ() + 12);
			Terrenos t = new Terrenos(p.getName(), tipo, pos1, pos2, p.getLocation(), null, false);
			Main.instance.TerrenosCache.put(p.getName(), t);
			Main.instance.DataFile.saveConfig();
			return;
		}

		if (tipo == Types.GIGANTE) {
			Cuboid cb = new Cuboid(p.getWorld(), (int) p.getLocation().getX() - 15, (int) p.getLocation().getY(),
					(int) p.getLocation().getZ() - 15, (int) p.getLocation().getX() + 15, (int) p.getLocation().getY(),
					(int) p.getLocation().getZ() + 15);
			cb.getFace(CuboidDirection.East).forEach(a -> a.setType(Material.OAK_FENCE));
			cb.getFace(CuboidDirection.West).forEach(a -> a.setType(Material.OAK_FENCE));
			cb.getFace(CuboidDirection.North).forEach(a -> a.setType(Material.OAK_FENCE));
			cb.getFace(CuboidDirection.South).forEach(a -> a.setType(Material.OAK_FENCE));
			Location pos1 = new Location(p.getWorld(), p.getLocation().getX() - 15, 150, p.getLocation().getZ() - 15);
			Location pos2 = new Location(p.getWorld(), p.getLocation().getX() + 15, 0, p.getLocation().getZ() + 15);
			Terrenos t = new Terrenos(p.getName(), tipo, pos1, pos2, p.getLocation(), null, false);
			Main.instance.TerrenosCache.put(p.getName(), t);
			Main.instance.DataFile.saveConfig();
			return;
		}
	}

	@EventHandler
	public void invBuy(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (e.getView().getTitle().equalsIgnoreCase("Adquira seu terreno")) {
			e.setCancelled(true);
			if (e.getCurrentItem() == null)
				return;
			if (!e.getCurrentItem().hasItemMeta())
				return;
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§3Terreno mini")) {
				if (Terrenos.terrenosNaLoc(p.getLocation()) != null) {
					p.sendMessage("§cVocê se encontra dentro de um terreno.");
					p.closeInventory();
					return;
				}
				if (Terrenos.terrenosPorPerto(p.getLocation(), 12).size() >= 1) {
					p.sendMessage("§cHá algum terreno por perto, afaste-se.");
					p.closeInventory();
					return;
				}
				if (Main.econ.getBalance((p.getName())) < 10000) {
					p.sendMessage("§cVocê não tem coins suficientes para comprar este terreno.");
					return;
				}
				Main.econ.withdrawPlayer(p.getName(), 10000);
				comprarTerreno(p, Types.MINI);
				p.closeInventory();
				p.sendMessage("§3Terreno §fMINI §3adquirido com sucesso!");
				p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
				return;
			}

			if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§3Terreno pequeno")) {
				if (Terrenos.terrenosNaLoc(p.getLocation()) != null) {
					p.sendMessage("§cVocê se encontra dentro de um terreno.");
					p.closeInventory();
					return;
				}
				if (Terrenos.terrenosPorPerto(p.getLocation(), 17).size() >= 2) {
					p.sendMessage("§cHá algum terreno por perto, afaste-se.");
					p.closeInventory();
					return;
				}
				if (Main.econ.getBalance(p.getName()) < 25000) {
					p.sendMessage("§cVocê não tem coins suficientes para comprar este terreno.");
					return;
				}
				Main.econ.withdrawPlayer(p.getName(), 25000);
				comprarTerreno(p, Types.PEQUENO);
				p.closeInventory();
				p.sendMessage("§3Terreno §fPEQUENO §3adquirido com sucesso!");
				p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
				return;
			}

			if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§3Terreno médio")) {
				if (Terrenos.terrenosNaLoc(p.getLocation()) != null) {
					p.sendMessage("§cVocê se encontra dentro de um terreno.");
					p.closeInventory();
					return;
				}
				if (Terrenos.terrenosPorPerto(p.getLocation(), 22).size() >= 1) {
					p.sendMessage("§cHá algum terreno por perto, afaste-se.");
					p.closeInventory();
					return;
				}
				if (Main.econ.getBalance(p.getName()) < 50000) {
					p.sendMessage("§cVocê não tem coins suficientes para comprar este terreno.");
					return;
				}
				Main.econ.withdrawPlayer(p.getName(), 50000);
				comprarTerreno(p, Types.MEDIO);
				p.closeInventory();
				p.sendMessage("§3Terreno §fMEDIO §3adquirido com sucesso!");
				p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
				return;
			}

			if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§3Terreno grande")) {
				if (Terrenos.terrenosNaLoc(p.getLocation()) != null) {
					p.sendMessage("§cVocê se encontra dentro de um terreno.");
					p.closeInventory();
					return;
				}
				if (Terrenos.terrenosPorPerto(p.getLocation(), 27).size() >= 1) {
					p.sendMessage("§cHá algum terreno por perto, afaste-se.");
					p.closeInventory();
					return;
				}
				if (Main.econ.getBalance(p.getName()) < 75000) {
					p.sendMessage("§cVocê não tem coins suficientes para comprar este terreno.");
					return;
				}
				Main.econ.withdrawPlayer(p.getName(), 75000);
				comprarTerreno(p, Types.GRANDE);
				p.closeInventory();
				p.sendMessage("§3Terreno §fGRANDE §3adquirido com sucesso!");
				p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
				return;
			}

			if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§3Terreno gigante")) {
				if (Terrenos.terrenosNaLoc(p.getLocation()) != null) {
					p.sendMessage("§cVocê se encontra dentro de um terreno.");
					p.closeInventory();
					return;
				}
				if (Terrenos.terrenosPorPerto(p.getLocation(), 32).size() >= 1) {
					p.sendMessage("§cHá algum terreno por perto, afaste-se.");
					p.closeInventory();
					return;
				}
				if (Main.econ.getBalance(p.getName()) < 100000) {
					p.sendMessage("§cVocê não tem coins suficientes para comprar este terreno.");
					return;
				}
				Main.econ.withdrawPlayer(p.getName(), 100000);
				comprarTerreno(p, Types.GIGANTE);
				p.closeInventory();
				p.sendMessage("§3Terreno §fGIGANTE §3adquirido com sucesso!");
				p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
				return;
			}
		}
	}

	@EventHandler
	public void invTeleport(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (e.getView().getTitle().equalsIgnoreCase("Terrenos que você tem acesso")) {
			e.setCancelled(true);
			if (e.getCurrentItem() == null)
				return;
			if (!e.getCurrentItem().hasItemMeta())
				return;
			if (e.getCurrentItem().getDurability() == 13) {
				Terrenos t = Main.instance.TerrenosCache.get(p.getName());
				if (t.getSpawn() == null) {
					p.teleport(t.getPos1());
					p.sendMessage("§3Teleportado para o seu terreno.");
				} else {
					p.teleport(t.getSpawn());
					p.sendMessage("§3Teleportado para o seu terreno.");
				}
			} else if (e.getCurrentItem().getDurability() == 4) {
				String[] lore1 = e.getCurrentItem().getItemMeta().getLore().get(0).split(" ");
				String jogador = lore1[2];
				Terrenos t = Main.instance.TerrenosCache.get(jogador);
				p.sendMessage("§3Teleportado para o terreno de seu amigo §f" + jogador + "§3.");
				p.teleport(t.getSpawn());
			}
			return;
		}
	}

	@EventHandler
	public void putBlock(BlockPlaceEvent e) {
		Block b = e.getBlock();
		if (b.getLocation().getY() >= 150) {
			e.setCancelled(true);
			e.getPlayer().sendMessage("§cVocê pode colocar blocos até a altura de §f150 §cblocos.");
			return;
		}
	}

	@EventHandler
	public void chuva(WeatherChangeEvent e) {
		e.setCancelled(true);
	}

	// eventos

	@EventHandler
	public void place(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		if (p.getWorld().getName().equals("Terrenos")) {
			Terrenos t = Terrenos.terrenosNaLoc(p.getLocation());
			if (t != null) {
				if (t.getJogador().equals(p.getName())) {
					e.setCancelled(false);
					return;
				}
				if (t.getAmigos() != null) {
					if (t.getAmigos().contains(p.getName())) {
						e.setCancelled(false);
						return;
					}
				}
			}
			e.setCancelled(true);
			p.sendMessage("§cVocê não pode colocar blocos aqui.");
		}
	}

	@EventHandler
	public void break2(BlockBreakEvent e) {
		Player p = e.getPlayer();
		if (p.getWorld().getName().equals("Terrenos")) {
			Terrenos t = Terrenos.terrenosNaLoc(p.getLocation());
			if (t != null) {
				if (t.getJogador().equals(p.getName())) {
					e.setCancelled(false);
					return;
				}
				if (t.getAmigos() != null) {
					if (t.getAmigos().contains(p.getName())) {
						e.setCancelled(false);
						return;
					}
				}
			}
			e.setCancelled(true);
			p.sendMessage("§cVocê não pode quebrar blocos aqui.");
		}
	}

	@EventHandler
	public void dano(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player) {
			if (e.getDamager() instanceof Player) {
				Player p = (Player) e.getEntity();
				if (p.getWorld().getName().equals("Terrenos")) {
					Terrenos t = Terrenos.terrenosNaLoc(p.getLocation());
					if (t != null) {
						if (t.isPvp()) {
							e.setCancelled(false);
						} else {
							e.setCancelled(true);
						}
						return;
					}
					e.setCancelled(true);
					p.sendMessage("§cVocê não pode hitar jogadores aqui.");
				}

			}
		}
	}

	@EventHandler
	public void interagir(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (p.getWorld().getName().equals("Terrenos")) {
			Terrenos t = Terrenos.terrenosNaLoc(p.getLocation());
			if (t != null) {
				if (t.getJogador().equals(p.getName())) {
					e.setCancelled(false);
					return;
				}
				if (t.getAmigos() != null) {
					if (t.getAmigos().contains(p.getName())) {
						e.setCancelled(false);
						return;
					}
				}
			}
			e.setCancelled(true);
			p.sendMessage("§cVocê não pode interagir com coisas aqui.");
		}
	}

}
