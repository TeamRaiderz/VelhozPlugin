package me.tr.survival.util.files;

import java.io.File;
import java.util.List;
import java.util.Set;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.tr.survival.Main;

public class FileManager
{
  public File file;
  public FileConfiguration fileConfiguration;
  private static FileManager config = new FileManager("config");
  private static FileManager data = new FileManager("data");

  private FileManager(String fileName)
  {
    if (!Main.getInstance().getDataFolder().exists()) {
      Main.getInstance().getDataFolder().mkdir();
    }

    File f = new File(Main.getInstance().getDataFolder(), "/userdata");
    if (!f.exists())
      f.mkdirs();
    this.file = new File(Main.getInstance().getDataFolder(), fileName + ".yml");

    this.fileConfiguration = YamlConfiguration.loadConfiguration(this.file);
  }

  public static FileManager getConfigFile() {
    return config;
  }

  public static FileManager getPlayerDataFile(OfflinePlayer p) {
    return new FileManager("/userdata/" + p.getName());
  }

  public static FileManager getDataFile() {
	return  data;
  }
  
  public void set(String path, Object value) {
    createfile();
    this.fileConfiguration.set(path, value);
    try {
      this.fileConfiguration.save(this.file);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void addDefault(String path, Object value) {
    createfile();
    if (!this.fileConfiguration.contains(path))
      set(path, value);
  }

  public boolean contains(String value)
  {
    createfile();
    return this.fileConfiguration.contains(value);
  }

  public <T> Object get(String path)
  {
    createfile();
    return this.fileConfiguration.get(path);
  }

  public Object get(String path, Object value) {
    createfile();
    return this.fileConfiguration.get(path, value);
  }

  public FileConfiguration getFile() {
    createfile();
    return this.fileConfiguration;
  }

  public String getString(String path) {
    createfile();
    return this.fileConfiguration.getString(path);
  }

  public List<String> getStringList(String path) {
    createfile();
    return this.fileConfiguration.getStringList(path);
  }

  public int getInt(String path) {
    createfile();
    return this.fileConfiguration.getInt(path);
  }

  public boolean getBoolean(String path) {
    createfile();
    return this.fileConfiguration.getBoolean(path);
  }

  public double getDouble(String path) {
    createfile();
    return this.fileConfiguration.getDouble(path);
  }

  public Set<String> getKeys(boolean arg0) {
    createfile();
    return this.fileConfiguration.getKeys(arg0);
  }

  public ConfigurationSection getConfigurationSection(String arg0) {
    createfile();
    return this.fileConfiguration.getConfigurationSection(arg0);
  }

  public boolean isExists() {
    return this.file.exists();
  }

  public ConfigurationSection createSection(String arg0) {
    createfile();
    ConfigurationSection section = this.fileConfiguration.createSection(arg0);
    save();
    return section;
  }

  public void save() {
    try {
      createfile();
      this.fileConfiguration.save(this.file);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void reload() {
    try {
      createfile();
      this.fileConfiguration = YamlConfiguration.loadConfiguration(this.file);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void createfile() {
    if (!this.file.exists())
      try {
        this.file.createNewFile();
      } catch (Exception e) {
        e.printStackTrace();
      }
  }
}
