package me.scoltbr.terrenos;

import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin {


	public static Location locFromString(String path) {
		String[] split = path.split(";");
		double x = Double.valueOf(split[0]);
		double y = Double.valueOf(split[1]);
		double z = Double.valueOf(split[2]);
		World world = Bukkit.getWorld(split[3]);
		return new Location(world, x, y, z);
	}

	public static Main instance;

	public static Main getInstance() {
		return instance;
	}
	
	public static Economy econ = null;


	private boolean setupEconomy() {
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return false;
		}
		econ = rsp.getProvider();
		return econ != null;
	}

	// cache

	public HashMap<String, Terrenos> TerrenosCache = new HashMap<>();

	public PrimeConfig DataFile = new PrimeConfig(this, "armazenamento.yml");

	@Override
	public void onEnable() {
		instance = this;
		DataFile.saveDefaultConfig();
		setupEconomy();

		// register
		getCommand("terrenos").setExecutor(new TerrenosCommand());
		Bukkit.getServer().getPluginManager().registerEvents(new TerrenosEvents(), this);

		DataFile.getConfig().getConfigurationSection("Terrenos").getKeys(false).forEach(a -> {
			Types tipo = Types.valueOf(DataFile.getString("Terrenos." + a + ".Tipo"));
			Location pos1 = Terrenos.desarializeLoc(DataFile.getString("Terrenos." + a + ".Pos1"));
			Location pos2 = Terrenos.desarializeLoc(DataFile.getString("Terrenos." + a + ".Pos2"));
			Location spawn = Terrenos.desarializeLoc(DataFile.getString("Terrenos." + a + ".Spawn"));
			List<String> amigos = DataFile.getConfig().getStringList("Terrenos." + a + ".Amigos");
			boolean pvp = DataFile.getBoolean("Terrenos." + a + ".Pvp");

			TerrenosCache.put(a, new Terrenos(a, tipo, pos1, pos2, spawn, amigos, pvp));
		});

	}

	@Override
	public void onDisable() {
		// save
		Terrenos.getAll().forEach(Terrenos::save);
		DataFile.saveConfig();
	}

}
