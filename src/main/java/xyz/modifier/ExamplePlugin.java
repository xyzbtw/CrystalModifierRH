package xyz.modifier;

import org.rusherhack.client.api.RusherHackAPI;
import org.rusherhack.client.api.plugin.Plugin;

/**
 * Example rusherhack plugin
 *
 * @author John200410
 */
public class ExamplePlugin extends Plugin {
	
	public static ExamplePlugin INSTANCE;

	public CrystalModifier crystalModifier = new CrystalModifier();
	
	@Override
	public void onLoad() {
		INSTANCE = this;
		
		this.getLogger().info("Loading CrystalModifier core plugin");
		
		//do stuff like registering modules here
		RusherHackAPI.getModuleManager().registerFeature(crystalModifier);
	}
	
	@Override
	public void onUnload() {
		this.getLogger().info("CrystalModifier plugin unloaded!");
	}
	
}