package me.scoltbr.terrenos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;

public class Terrenos {

	private String jogador;
	private Types tipo;
	private Location pos1;
	private Location pos2;
	private Location spawn;
	private List<String> amigos;
	private boolean pvp;

	public Terrenos(String jogador, Types tipo, Location pos1, Location pos2, Location spawn, List<String> amigos,
			boolean pvp) {
		this.jogador = jogador;
		this.tipo = tipo;
		this.pos1 = pos1;
		this.pos2 = pos2;
		this.spawn = spawn;
		this.amigos = amigos;
		this.pvp = pvp;
	}

	public String getJogador() {
		return jogador;
	}

	public void setJogador(String jogador) {
		this.jogador = jogador;
	}

	public Types getTipo() {
		return tipo;
	}

	public void setTipo(Types tipo) {
		this.tipo = tipo;
	}

	public Location getPos1() {
		return pos1;
	}

	public void setPos1(Location pos1) {
		this.pos1 = pos1;
	}

	public Location getPos2() {
		return pos2;
	}

	public void setPos2(Location pos2) {
		this.pos2 = pos2;
	}

	public Location getSpawn() {
		return spawn;
	}

	public void setSpawn(Location spawn) {
		this.spawn = spawn;
	}

	public List<String> getAmigos() {
		return amigos;
	}

	public void setAmigos(List<String> amigos) {
		this.amigos = amigos;
	}

	public boolean isPvp() {
		return pvp;
	}

	public void setPvp(boolean pvp) {
		this.pvp = pvp;
	}

	public static List<Terrenos> getAll() {
		return Main.instance.TerrenosCache.values().stream().collect(Collectors.toList());
	}

	public static String serializeLoc(Location loc) {
		return loc.getX() + "#" + loc.getY() + "#" + loc.getZ() + "#" + loc.getWorld().getName();
	}
	public static Location desarializeLoc(String loc) {
		String[] split = loc.split("#");
		Location loc2 = new Location(Bukkit.getWorld(split[3]), Double.parseDouble(split[0]), Double.parseDouble(split[1]), Double.parseDouble(split[2]));
		return loc2;
	}
	
	public void save() {
		Main.getInstance().DataFile.set("Terrenos." + getJogador() + ".Tipo", getTipo().toString());
		Main.getInstance().DataFile.set("Terrenos." + getJogador() + ".Pos1", serializeLoc(getPos1()));
		Main.getInstance().DataFile.set("Terrenos." + getJogador() + ".Pos2", serializeLoc(getPos2()));
		Main.getInstance().DataFile.set("Terrenos." + getJogador() + ".Spawn", serializeLoc(getSpawn()));
		Main.getInstance().DataFile.set("Terrenos." + getJogador() + ".Amigos", getAmigos());
		Main.getInstance().DataFile.set("Terrenos." + getJogador() + ".Pvp", isPvp());
	}

	public static List<Terrenos> terrenosPorPerto(Location location, int radius) {
		ArrayList<Terrenos> terrenos = new ArrayList<>();

		ArrayList<Block> blocos = new ArrayList<>();
		for (int x = location.getBlockX() - radius; x <= location.getBlockX() + radius; x++) {
			for (int y = location.getBlockY() - radius; y <= location.getBlockY() + radius; y++) {
				for (int z = location.getBlockZ() - radius; z <= location.getBlockZ() + radius; z++) {
					blocos.add(location.getWorld().getBlockAt(x, y, z));
				}
			}
		}
		
		for(Terrenos all : getAll()) {
			Cuboid cb = new Cuboid(all.getPos1(), all.getPos2());
			for(Block bs : blocos) {
				if(cb.contains(bs)) {
					terrenos.add(all);
				}
			}
			
		}

		return terrenos;
	}

	public static Terrenos terrenosNaLoc(Location loc) {
		Block b = Bukkit.getWorld(loc.getWorld().getName()).getBlockAt(loc);
		for (Terrenos all : getAll()) {
			Location pos1 = all.getPos1();
			Location pos2 = all.getPos2();
			Cuboid cb = new Cuboid(pos1, pos2);
			if (cb.getBlocks().contains(b)) {
				return all;
			}
		}
		return null;
	}

}
