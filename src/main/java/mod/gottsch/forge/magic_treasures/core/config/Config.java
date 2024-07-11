/*
 * This file is part of  Magic Treasures.
 * Copyright (c) 2023 Mark Gottschling (gottsch)
 *
 * Magic Treasures is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Magic Treasures is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Magic Treasures.  If not, see <http://www.gnu.org/licenses/lgpl>.
 */
package mod.gottsch.forge.magic_treasures.core.config;

import mod.gottsch.forge.gottschcore.config.AbstractConfig;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.Arrays;
import java.util.List;

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

		public Loot loot;
		public Integration integration;

		public ServerConfig(ForgeConfigSpec.Builder builder) {
			loot = new Loot(builder);
			integration = new Integration(builder);
		}
	}

	public static class Loot {
		public ForgeConfigSpec.BooleanValue enableVanillaLootModifiers;

		public Loot(final ForgeConfigSpec.Builder builder) {
			builder.comment(CATEGORY_DIV, " Loot properties", CATEGORY_DIV)
					.push("loot");

			enableVanillaLootModifiers = builder
					.comment(" Enable/Disable global loot modifiers that injects Magic Treasures loot into vanilla loot tables.")
					.define("enableVanillaLootModifiers", true);

			builder.pop();
		}
	}

	/*
	 *
	 */
	public static class Integration {
		public ForgeConfigSpec.ConfigValue<List<? extends String>> dimensionsWhiteList;
		public ForgeConfigSpec.BooleanValue  enableCurios;

		public Integration(final ForgeConfigSpec.Builder builder)	 {
			builder.comment(CATEGORY_DIV, " Integration properties", CATEGORY_DIV)
					.push("integration");

			dimensionsWhiteList = builder
					.comment(" Permitted Dimensions for Magical Treasures execution.",
							" This setting does not use any wildcards (*). You must explicitly set the dimensions that are allowed.",
							" ex. minecraft:overworld")
					.defineList("dimensionsWhiteList", Arrays.asList(new String []{"minecraft:overworld"}), s -> s instanceof String);


			enableCurios = builder
					.comment("Enable/Disable Curios integration.", "Enabled by default, but Curios must be installed as well.")
					.define("Enable Curios Integration:", true);

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
