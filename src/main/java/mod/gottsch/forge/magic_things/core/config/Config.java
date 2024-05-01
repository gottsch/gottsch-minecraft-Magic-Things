
package mod.gottsch.forge.magic_things.core.config;

import mod.gottsch.forge.gottschcore.config.AbstractConfig;
import net.minecraftforge.common.ForgeConfigSpec;

/**
 * 
 * @author Mark Gottschling May 3, 2023
 *
 */
public class Config extends AbstractConfig {
	public static final String CATEGORY_DIV = "##############################";
	public static final String UNDERLINE_DIV = "------------------------------";

	// TODO change to the new Echelons style of config setup
	protected static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
	protected static final ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
	
	public static ForgeConfigSpec COMMON_CONFIG;
	public static ForgeConfigSpec SERVER_CONFIG;
	
	public static final Logging LOGGING;
	public static final ServerConfig SERVER;
	public static Config instance = new Config();
	
	static {
		LOGGING = new Logging(COMMON_BUILDER);
		COMMON_CONFIG = COMMON_BUILDER.build();
		
		SERVER = new ServerConfig(SERVER_BUILDER);
		SERVER_CONFIG = SERVER_BUILDER.build();
	}
	
	/*
	 * 
	 */
	public static class ServerConfig {
		
		public ServerConfig(ForgeConfigSpec.Builder builder) {

			builder.comment(CATEGORY_DIV, "Section Properties", CATEGORY_DIV)
			.push("section name");
			

			builder.pop();
		}
	}
	
	@Override
	public String getLogsFolder() {
		return Config.LOGGING.folder.get();
	}
	
	public void setLogsFolder(String folder) {
		Config.LOGGING.folder.set(folder);
	}
	
	@Override
	public String getLoggingLevel() {
		return Config.LOGGING.level.get();
	}
}
