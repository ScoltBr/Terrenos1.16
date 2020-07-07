package me.scoltbr.terrenos;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class TerrenosCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lb, String[] args) {
		if (s instanceof Player) {
			Player p = (Player) s;
			if (cmd.getName().equalsIgnoreCase("terrenos")) {
				if (args.length == 0) {
					p.sendMessage(" ");
					p.sendMessage("§3          Plugin desenvolvido por ScoltBr                  ");
					p.sendMessage("§3/terreno comprar §fCompre seu terreno.");
					p.sendMessage("§3/terreno info §fInformações de um terreno.");
					p.sendMessage("§3/terreno add (jogador) §fAdicione um amigo em seu terreno.");
					p.sendMessage("§3/terreno remover (jogador) §fRemove um amigo de seu terreno.");
					p.sendMessage("§3/terreno abandonar §fAbandone seu terreno.");
					p.sendMessage("§3/terreno setspawn §fSetar o spawn de um terreno.");
					p.sendMessage("§3/terreno visitar (jogador) §fVisite o terreno de um jogador.");
					p.sendMessage("§3/terreno info §fInformações de um terreno.");
					p.sendMessage("§3/terreno ir §fAbre um menú dos terrenos que você pode acessar.");
					p.sendMessage("§3/terreno kickar (jogador) §fExpulse um jogador de seu terreno.");
					p.sendMessage("§3/terreno pvp (status) §fAltere o pvp em seu terreno.");
					p.sendMessage("§3          Plugin desenvolvido por ScoltBr                  ");
					p.sendMessage(" ");
					return true;
				}
				if (args.length == 1) {
					String subComando1 = args[0];
					if (subComando1.equalsIgnoreCase("comprar")) {
						if (Main.instance.TerrenosCache.containsKey(p.getName())) {
							p.sendMessage("§cVocê já tem um terreno.");
							return true;
						}
						Inventory inv = Bukkit.createInventory(null, 3 * 9, "Adquira seu terreno");

						inv.setItem(11, PrimeCriar.add(Material.BRICK, "§3Terreno mini",
								new String[] { "§f§oTerreno 10 por 10.", " ", "§7Preço§f: 10.000 moedas" }, 0));
						inv.setItem(12, PrimeCriar.add(Material.CLAY, "§3Terreno pequeno",
								new String[] { "§f§oTerreno 15 por 15.", " ", "§7Preço§f: 25.000 moedas" }, 5));
						inv.setItem(13, PrimeCriar.add(Material.IRON_BLOCK, "§3Terreno médio",
								new String[] { "§f§oTerreno 20 por 20.", " ", "§7Preço§f: 50.000 moedas" }, 13));
						inv.setItem(14, PrimeCriar.add(Material.GOLD_BLOCK, "§3Terreno grande",
								new String[] { "§f§oTerreno 25 por 25.", " ", "§7Preço§f: 75.000 moedas" }, 3));
						inv.setItem(15, PrimeCriar.add(Material.DIAMOND_BLOCK, "§3Terreno gigante",
								new String[] { "§f§oTerreno 30 por 30.", " ", "§7Preço§f: 100.000 moedas" }, 11));

						p.openInventory(inv);
					} else if (subComando1.equalsIgnoreCase("info")) {
						if (Terrenos.terrenosNaLoc(p.getLocation()) == null) {
							p.sendMessage("§cNão há nenhum terreno em sua localização.");
						} else {
							Terrenos t = Terrenos.terrenosNaLoc(p.getLocation());
							p.sendMessage(" ");
							p.sendMessage(" §3Terreno em sua localização");
							p.sendMessage(" ");
							p.sendMessage(" §3Dono: §f" + t.getJogador() + "§3.");
							p.sendMessage(" §3Tipo: §f" + t.getTipo().toString() + "§3.");
							if (t.isPvp()) {
								p.sendMessage(" §3PVP: §fAtivado§3.");
							} else {
								p.sendMessage(" §3PVP: §fDesativado§3.");
							}
							if (t.getAmigos() == null || t.getAmigos().isEmpty()) {
								p.sendMessage(" §3Amigos: §fNenhum amigo adicionado§3.");
							} else {
								StringBuilder sb = new StringBuilder();
								for (String add : t.getAmigos()) {
									sb.append("§f" + add);
									sb.append("§3, ");
								}
								p.sendMessage(" §3Amigos: §f" + sb.toString().substring(0, sb.toString().length() - 2)
										+ "§3.");
							}
							p.sendMessage(" ");
						}
					} else if (subComando1.equalsIgnoreCase("ir")) {

						Inventory inv = Bukkit.createInventory(null, 5 * 9, "Terrenos que você tem acesso");
						if (Main.instance.TerrenosCache.containsKey(p.getName())) {
							Terrenos t = Main.instance.TerrenosCache.get(p.getName());
							inv.setItem(0,
									PrimeCriar.add(Material.OAK_FENCE, "§3Seu terreno", new String[] {
											"§f§oSeu terreno " + t.getTipo(), " ", "§aClique para se teleportar." },
											13));
						}
						int i = 8;
						for (Terrenos t : Terrenos.getAll()) {
							if (t.getAmigos() != null) {
								if (t.getAmigos().contains(p.getName())) {
									i++;
									ItemStack item = PrimeCriar.add(Material.DARK_OAK_FENCE, "§3Terreno que você é amigo",
											new String[] { "§f§oTerreno de " + t.getJogador(), " ",
													"§aClique para se teleportar." },
											4);
									inv.setItem(i, item);
								}
							}
						}

						p.openInventory(inv);

					} else if (subComando1.equalsIgnoreCase("setspawn")) {
						if (Terrenos.terrenosNaLoc(p.getLocation()) == null) {
							p.sendMessage("§cNão há nenhum terreno em sua localização.");
						} else {
							Terrenos t = Terrenos.terrenosNaLoc(p.getLocation());
							if (!t.getJogador().equals(p.getName())) {
								p.sendMessage("§cVocê só pode setar o spawn de seu terreno dentro de seu terreno.");
								return true;
							}
							t.setSpawn(p.getLocation());
							p.sendMessage("§3Spawn de seu terreno setado com sucesso.");
						}
					} else if (subComando1.equalsIgnoreCase("kickar")) {
						p.sendMessage("§cUtilize /terreno kickar (jogador).");
					} else if (subComando1.equalsIgnoreCase("add")) {
						p.sendMessage("§cUtilize /terreno add (jogador).");
					} else if (subComando1.equalsIgnoreCase("remover")) {
						p.sendMessage("§cUtilize /terreno remover (jogador).");
					} else if (subComando1.equalsIgnoreCase("abandonar")) {
						if (!Main.instance.TerrenosCache.containsKey(p.getName())) {
							p.sendMessage("§cVocê não tem um terreno.");
							return true;
						}
						p.sendMessage("§3Terreno abandonado com sucesso.");
						Main.instance.TerrenosCache.remove(p.getName());
						Main.instance.DataFile.saveConfig();
					} else if (subComando1.equalsIgnoreCase("pvp")) {
						p.sendMessage("§cUtilize /terreno pvp (ativado/desativado).");
					} else if (subComando1.equalsIgnoreCase("visitar")) {
						p.sendMessage("§cUtilize /terreno visitar (jogador).");
					} else {
						p.sendMessage("§cSub comando não encontrado, digite §f/terreno§c.");
					}
					return true;
				}
				if (args.length == 2) {
					String subComando1 = args[0];
					if (subComando1.equalsIgnoreCase("comprar")) {
						if (Main.instance.TerrenosCache.containsKey(p.getName())) {
							p.sendMessage("§cVocê já tem um terreno.");
							return true;
						}
						Inventory inv = Bukkit.createInventory(null, 3 * 9, "Adquira seu terreno");

						inv.setItem(11, PrimeCriar.add(Material.BRICK, "§3Terreno mini",
								new String[] { "§f§oTerreno 10 por 10.", " ", "§7Preço§f: 10.000 moedas" }, 0));
						inv.setItem(12, PrimeCriar.add(Material.CLAY, "§3Terreno pequeno",
								new String[] { "§f§oTerreno 15 por 15.", " ", "§7Preço§f: 25.000 moedas" }, 5));
						inv.setItem(13, PrimeCriar.add(Material.IRON_BLOCK, "§3Terreno médio",
								new String[] { "§f§oTerreno 20 por 20.", " ", "§7Preço§f: 50.000 moedas" }, 13));
						inv.setItem(14, PrimeCriar.add(Material.GOLD_BLOCK, "§3Terreno grande",
								new String[] { "§f§oTerreno 25 por 25.", " ", "§7Preço§f: 75.000 moedas" }, 3));
						inv.setItem(15, PrimeCriar.add(Material.DIAMOND_BLOCK, "§3Terreno gigante",
								new String[] { "§f§oTerreno 30 por 30.", " ", "§7Preço§f: 100.000 moedas" }, 11));

						p.openInventory(inv);
					} else if (subComando1.equalsIgnoreCase("info")) {
						if (Terrenos.terrenosNaLoc(p.getLocation()) == null) {
							p.sendMessage("§cNão há nenhum terreno em sua localização.");
						} else {
							Terrenos t = Terrenos.terrenosNaLoc(p.getLocation());
							p.sendMessage(" ");
							p.sendMessage(" §3Terreno em sua localização");
							p.sendMessage(" ");
							p.sendMessage(" §3Dono: §f" + t.getJogador() + "§3.");
							p.sendMessage(" §3Tipo: §f" + t.getTipo().toString() + "§3.");
							if (t.isPvp()) {
								p.sendMessage(" §3PVP: §fAtivado§3.");
							} else {
								p.sendMessage(" §3PVP: §fDesativado§3.");
							}
							if (t.getAmigos() == null || t.getAmigos().isEmpty()) {
								p.sendMessage(" §3Amigos: §fNenhum amigo adicionado§3.");
							} else {
								StringBuilder sb = new StringBuilder();
								for (String add : t.getAmigos()) {
									sb.append("§f" + add);
									sb.append("§3, ");
								}
								p.sendMessage(" §3Amigos: §f" + sb.toString().substring(0, sb.toString().length() - 2)
										+ "§3.");
							}
							p.sendMessage(" ");
						}
					} else if (subComando1.equalsIgnoreCase("ir")) {

						Inventory inv = Bukkit.createInventory(null, 5 * 9, "Terrenos que você tem acesso");
						if (Main.instance.TerrenosCache.containsKey(p.getName())) {
							Terrenos t = Main.instance.TerrenosCache.get(p.getName());
							inv.setItem(0,
									PrimeCriar.add(Material.OAK_FENCE, "§3Seu terreno", new String[] {
											"§f§oSeu terreno " + t.getTipo(), " ", "§aClique para se teleportar." },
											13));
						}
						int i = 8;
						for (Terrenos t : Terrenos.getAll()) {
							if (t.getAmigos() != null) {
								if (t.getAmigos().contains(p.getName())) {
									i++;
									ItemStack item = PrimeCriar.add(Material.DARK_OAK_FENCE, "§3Terreno que você é amigo",
											new String[] { "§f§oTerreno de " + t.getJogador(), " ",
													"§aClique para se teleportar." },
											4);
									inv.setItem(i, item);
								}
							}
						}

						p.openInventory(inv);

					} else if (subComando1.equalsIgnoreCase("setspawn")) {
						if (Terrenos.terrenosNaLoc(p.getLocation()) == null) {
							p.sendMessage("§cNão há nenhum terreno em sua localização.");
						} else {
							Terrenos t = Terrenos.terrenosNaLoc(p.getLocation());
							if (!t.getJogador().equals(p.getName())) {
								p.sendMessage("§cVocê só pode setar o spawn de seu terreno dentro de seu terreno.");
								return true;
							}
							t.setSpawn(p.getLocation());
							p.sendMessage("§3Spawn de seu terreno setado com sucesso.");
						}
					} else if (subComando1.equalsIgnoreCase("kickar")) {
						Player alvo = Bukkit.getPlayer(args[1]);
						if (alvo == null) {
							p.sendMessage("§cEste jogador se encontra offline!");
							return true;
						}
						if (Terrenos.terrenosNaLoc(alvo.getLocation()) == null) {
							p.sendMessage("§cEste jogador não se encontra em um terreno.");
							return true;
						}
						if (alvo.getName().equalsIgnoreCase(p.getName())) {
							p.sendMessage("§cEste jogador é você.");
							return true;
						}
						Terrenos t = Terrenos.terrenosNaLoc(alvo.getLocation());
						if (!t.getJogador().equalsIgnoreCase(p.getName())) {
							p.sendMessage("§cEste jogador não se encontra em seu terreno.");
						} else {
							p.sendMessage("§3Jogador expulso de seu terreno.");
							alvo.teleport(t.getPos2());
							alvo.sendMessage("§cVocê foi expulso do terreno de §f" + t.getJogador() + "§c.");
						}
					} else if (subComando1.equalsIgnoreCase("add")) {
						if (!Main.instance.TerrenosCache.containsKey(p.getName())) {
							p.sendMessage("§cVocê não tem um terreno.");
							return true;
						}
						Player alvo = Bukkit.getPlayer(args[1]);
						if (alvo == null) {
							p.sendMessage("§cEste jogador se encontra offline!");
							return true;
						}
						if (alvo.getName().equalsIgnoreCase(p.getName())) {
							p.sendMessage("§cEste jogador é você.");
							return true;
						}
						Terrenos t = Terrenos.terrenosNaLoc(p.getLocation());
						if (!t.getJogador().equals(p.getName())) {
							p.sendMessage("§cVocê deve estar dentro de seu terreno para adicionar amigos no mesmo.");
							return true;
						}
						if (t.getAmigos() == null) {
							List<String> jogadores = new ArrayList<>();
							alvo.sendMessage("§3Você foi adicionado no terreno de §f" + p.getName() + "§3.");
							p.sendMessage("§3Você adicionou §f" + alvo.getName() + "§3 em seu terreno.");
							jogadores.add(alvo.getName());
							t.setAmigos(jogadores);
							return true;
						}
						List<String> jogadores = t.getAmigos();
						if (jogadores.contains(alvo.getName())) {
							p.sendMessage("§cEste jogador já esta adicionado em seu terreno.");
							return true;
						}
						alvo.sendMessage("§3Você foi adicionado no terreno de §f" + p.getName() + "§3.");
						p.sendMessage("§3Você adicionou §f" + alvo.getName() + "§3 em seu terreno.");
						jogadores.add(alvo.getName());
						t.setAmigos(jogadores);
					} else if (subComando1.equalsIgnoreCase("remover")) {
						if (!Main.instance.TerrenosCache.containsKey(p.getName())) {
							p.sendMessage("§cVocê não tem um terreno.");
							return true;
						}
						Player alvo = Bukkit.getPlayer(args[1]);
						if (alvo == null) {
							p.sendMessage("§cEste jogador se encontra offline!");
							return true;
						}
						if (alvo.getName().equalsIgnoreCase(p.getName())) {
							p.sendMessage("§cEste jogador é você.");
							return true;
						}
						Terrenos t = Terrenos.terrenosNaLoc(p.getLocation());
						if (!t.getJogador().equals(p.getName())) {
							p.sendMessage("§cVocê deve estar dentro de seu terreno para remover amigos do mesmo.");
							return true;
						}
						List<String> jogadores = t.getAmigos();
						if (!jogadores.contains(alvo.getName())) {
							p.sendMessage("§cEste jogador não esta adicionado em seu terreno.");
							return true;
						}
						alvo.sendMessage("§3Você foi removido do terreno de §f" + p.getName() + "§3.");
						p.sendMessage("§3Você removeu §f" + alvo.getName() + "§3 de seu terreno.");
						jogadores.remove(alvo.getName());
						t.setAmigos(jogadores);

					} else if (subComando1.equalsIgnoreCase("abandonar")) {
						if (!Main.instance.TerrenosCache.containsKey(p.getName())) {
							p.sendMessage("§cVocê não tem um terreno.");
							return true;
						}
						p.sendMessage("§3Terreno abandonado com sucesso.");
						Main.instance.TerrenosCache.remove(p.getName());
					} else if (subComando1.equalsIgnoreCase("pvp")) {
						if (!Main.instance.TerrenosCache.containsKey(p.getName())) {
							p.sendMessage("§cVocê não tem um terreno.");
							return true;
						}
						Terrenos t = Main.instance.TerrenosCache.get(p.getName());
						if (args[1].equalsIgnoreCase("ativado")) {
							if (t.isPvp()) {
								p.sendMessage("§cO PVP em seu terreno já se encontra ativado.");
								return true;
							}
							t.setPvp(true);
							p.sendMessage("§3PVP em seu terreno ativado.");
						} else if (args[1].equalsIgnoreCase("desativado")) {
							if (!t.isPvp()) {
								p.sendMessage("§cO PVP em seu terreno já se encontra desativado.");
								return true;
							}
							t.setPvp(false);
							p.sendMessage("§3PVP em seu terreno desativado.");
						} else {
							p.sendMessage("§cO status do pvp pode ser ativado e desativado.");
						}
					} else if (subComando1.equalsIgnoreCase("visitar")) {
						String jogador = args[1];
						if (!Main.instance.TerrenosCache.containsKey(jogador)) {
							p.sendMessage("§cEste jogador não tem um terreno.");
							return true;
						}
						Terrenos tJ = Main.instance.TerrenosCache.get(jogador);
						if (tJ.getSpawn() == null) {
							p.sendMessage("§cO proprietário desse terreno não setou o spawn.");
							return true;
						}
						p.sendMessage("§3Teleportado para o terreno de §f" + tJ.getJogador() + "§3.");
						p.teleport(tJ.getSpawn());
					} else {
						p.sendMessage("§cSub comando não encontrado, digite §f/terreno§c.");
					}
					return true;
				}
				if (args.length > 2) {
					p.sendMessage("§cVocê não pode colocar espaços aqui.");
					return true;
				}

			}
		}
		return true;
	}

}
