package com.ancientrealms.config;
import com.ancientrealms.AncientRealms;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
public final class ModConfig {
 private static final Gson GSON=new GsonBuilder().setPrettyPrinting().create();
 private static final Path FILE=FabricLoader.getInstance().getConfigDir().resolve("ancientrealms.json");
 public double dragonGrowthSpeed=1.0, weaponXpMultiplier=1.0;
 public int maxParticlesPerEffect=128, worldEventFrequency=3;
 private static ModConfig current=new ModConfig();
 private ModConfig(){}
 public static ModConfig get(){return current;}
 public static void load(){try{
  Files.createDirectories(FILE.getParent());
  if(Files.exists(FILE))try(Reader r=Files.newBufferedReader(FILE)){ModConfig v=GSON.fromJson(r,ModConfig.class);if(v!=null)current=v;}
  current.sanitize(); save(); AncientRealms.LOGGER.info("Loaded config from {}",FILE);
 }catch(Exception e){AncientRealms.LOGGER.error("Config load failed; defaults retained.",e);current=new ModConfig();}}
 private void sanitize(){dragonGrowthSpeed=clamp(dragonGrowthSpeed,.1,10);weaponXpMultiplier=clamp(weaponXpMultiplier,.1,10);maxParticlesPerEffect=Math.max(0,Math.min(4096,maxParticlesPerEffect));worldEventFrequency=Math.max(0,Math.min(24,worldEventFrequency));}
 private static double clamp(double v,double min,double max){return Double.isFinite(v)?Math.max(min,Math.min(max,v)):1.0;}
 private static void save(){try(Writer w=Files.newBufferedWriter(FILE)){GSON.toJson(current,w);}catch(Exception e){AncientRealms.LOGGER.error("Config save failed.",e);}}
}
