
package mod.gottsch.forge.magic_things.datagen;

import mod.gottsch.forge.magic_things.MagicThings;
import mod.gottsch.forge.magic_things.core.item.MagicThingsItems;
import mod.gottsch.forge.magic_things.core.registry.JewelryRegistry;
import mod.gottsch.forge.treasure2.core.block.TreasureBlocks;
import mod.gottsch.forge.treasure2.core.item.TreasureItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

/**
 * 
 * @author Mark Gottschling on May 4, 2024
 *
 */
public class Recipes extends RecipeProvider {
		private static String CRITERIA = "criteria";

		public Recipes(DataGenerator generator) {
			super(generator);
		}

		@Override
		protected void buildCraftingRecipes(Consumer<FinishedRecipe> recipe) {
			MagicThings.LOGGER.debug("build recipes is running...");

			// recipe scrolls
			ShapelessRecipeBuilder.shapeless(MagicThingsItems.STANDARD_JEWELRY.stream().filter(o -> o.getId().getPath().equalsIgnoreCase("iron_ring")).findFirst().get().get())
					.requires(MagicThingsItems.RING_RECIPE.get())
					.requires(Items.IRON_INGOT, 4)
					.unlockedBy(CRITERIA, InventoryChangeTrigger.TriggerInstance.hasItems(MagicThingsItems.RING_RECIPE.get()))
					.save(recipe);

			ShapelessRecipeBuilder.shapeless(MagicThingsItems.STANDARD_JEWELRY.stream().filter(o -> o.getId().getPath().equalsIgnoreCase("copper_ring")).findFirst().get().get())
					.requires(MagicThingsItems.RING_RECIPE.get())
					.requires(Items.COPPER_INGOT, 4)
					.unlockedBy(CRITERIA, InventoryChangeTrigger.TriggerInstance.hasItems(MagicThingsItems.RING_RECIPE.get()))
					.save(recipe);

			ShapelessRecipeBuilder.shapeless(MagicThingsItems.STANDARD_JEWELRY.stream().filter(o -> o.getId().getPath().equalsIgnoreCase("silver_ring")).findFirst().get().get())
					.requires(MagicThingsItems.RING_RECIPE.get())
					.requires(MagicThingsItems.SILVER_INGOT.get(), 4)
					.unlockedBy(CRITERIA, InventoryChangeTrigger.TriggerInstance.hasItems(MagicThingsItems.RING_RECIPE.get()))
					.save(recipe);

			ShapelessRecipeBuilder.shapeless(MagicThingsItems.STANDARD_JEWELRY.stream().filter(o -> o.getId().getPath().equalsIgnoreCase("gold_ring")).findFirst().get().get())
					.requires(MagicThingsItems.RING_RECIPE.get())
					.requires(Items.GOLD_INGOT, 4)
					.unlockedBy(CRITERIA, InventoryChangeTrigger.TriggerInstance.hasItems(MagicThingsItems.RING_RECIPE.get()))
					.save(recipe);

//			ShapelessRecipeBuilder.shapeless(MagicThingsItems.STANDARD_JEWELRY.stream().filter(o -> o.getId().getPath().equalsIgnoreCase("bone_ring")).findFirst().get().get())
//					.requires(MagicThingsItems.RING_RECIPE.get())
//					.requires(Items.BONE, 4)
//					.unlockedBy(CRITERIA, InventoryChangeTrigger.TriggerInstance.hasItems(MagicThingsItems.RING_RECIPE.get()))
//					.save(recipe);

			ShapelessRecipeBuilder.shapeless(MagicThingsItems.STANDARD_JEWELRY.stream().filter(o -> o.getId().getPath().equalsIgnoreCase("iron_necklace")).findFirst().get().get())
					.requires(MagicThingsItems.NECKLACE_RECIPE.get())
					.requires(Items.IRON_INGOT, 4)
					.unlockedBy(CRITERIA, InventoryChangeTrigger.TriggerInstance.hasItems(MagicThingsItems.NECKLACE_RECIPE.get()))
					.save(recipe);

			ShapelessRecipeBuilder.shapeless(MagicThingsItems.STANDARD_JEWELRY.stream().filter(o -> o.getId().getPath().equalsIgnoreCase("copper_necklace")).findFirst().get().get())
					.requires(MagicThingsItems.NECKLACE_RECIPE.get())
					.requires(Items.COPPER_INGOT, 4)
					.unlockedBy(CRITERIA, InventoryChangeTrigger.TriggerInstance.hasItems(MagicThingsItems.NECKLACE_RECIPE.get()))
					.save(recipe);

			ShapelessRecipeBuilder.shapeless(MagicThingsItems.STANDARD_JEWELRY.stream().filter(o -> o.getId().getPath().equalsIgnoreCase("silver_necklace")).findFirst().get().get())
					.requires(MagicThingsItems.NECKLACE_RECIPE.get())
					.requires(MagicThingsItems.SILVER_INGOT.get(), 4)
					.unlockedBy(CRITERIA, InventoryChangeTrigger.TriggerInstance.hasItems(MagicThingsItems.NECKLACE_RECIPE.get()))
					.save(recipe);

			ShapelessRecipeBuilder.shapeless(MagicThingsItems.STANDARD_JEWELRY.stream().filter(o -> o.getId().getPath().equalsIgnoreCase("gold_necklace")).findFirst().get().get())
					.requires(MagicThingsItems.NECKLACE_RECIPE.get())
					.requires(Items.GOLD_INGOT, 4)
					.unlockedBy(CRITERIA, InventoryChangeTrigger.TriggerInstance.hasItems(MagicThingsItems.NECKLACE_RECIPE.get()))
					.save(recipe);

//			ShapelessRecipeBuilder.shapeless(MagicThingsItems.STANDARD_JEWELRY.stream().filter(o -> o.getId().getPath().equalsIgnoreCase("bone_necklace")).findFirst().get().get())
//					.requires(MagicThingsItems.NECKLACE_RECIPE.get())
//					.requires(Items.BONE, 4)
//					.unlockedBy(CRITERIA, InventoryChangeTrigger.TriggerInstance.hasItems(MagicThingsItems.NECKLACE_RECIPE.get()))
//					.save(recipe);

			ShapelessRecipeBuilder.shapeless(MagicThingsItems.STANDARD_JEWELRY.stream().filter(o -> o.getId().getPath().equalsIgnoreCase("iron_bracelet")).findFirst().get().get())
					.requires(MagicThingsItems.BRACELET_RECIPE.get())
					.requires(Items.IRON_INGOT, 4)
					.unlockedBy(CRITERIA, InventoryChangeTrigger.TriggerInstance.hasItems(MagicThingsItems.BRACELET_RECIPE.get()))
					.save(recipe);

			ShapelessRecipeBuilder.shapeless(MagicThingsItems.STANDARD_JEWELRY.stream().filter(o -> o.getId().getPath().equalsIgnoreCase("copper_bracelet")).findFirst().get().get())
					.requires(MagicThingsItems.BRACELET_RECIPE.get())
					.requires(Items.COPPER_INGOT, 4)
					.unlockedBy(CRITERIA, InventoryChangeTrigger.TriggerInstance.hasItems(MagicThingsItems.BRACELET_RECIPE.get()))
					.save(recipe);

			ShapelessRecipeBuilder.shapeless(MagicThingsItems.STANDARD_JEWELRY.stream().filter(o -> o.getId().getPath().equalsIgnoreCase("silver_bracelet")).findFirst().get().get())
					.requires(MagicThingsItems.BRACELET_RECIPE.get())
					.requires(MagicThingsItems.SILVER_INGOT.get(), 4)
					.unlockedBy(CRITERIA, InventoryChangeTrigger.TriggerInstance.hasItems(MagicThingsItems.BRACELET_RECIPE.get()))
					.save(recipe);

			ShapelessRecipeBuilder.shapeless(MagicThingsItems.STANDARD_JEWELRY.stream().filter(o -> o.getId().getPath().equalsIgnoreCase("gold_bracelet")).findFirst().get().get())
					.requires(MagicThingsItems.BRACELET_RECIPE.get())
					.requires(Items.GOLD_INGOT, 4)
					.unlockedBy(CRITERIA, InventoryChangeTrigger.TriggerInstance.hasItems(MagicThingsItems.BRACELET_RECIPE.get()))
					.save(recipe);

//			ShapelessRecipeBuilder.shapeless(MagicThingsItems.STANDARD_JEWELRY.stream().filter(o -> o.getId().getPath().equalsIgnoreCase("bone_bracelet")).findFirst().get().get())
//					.requires(MagicThingsItems.BRACELET_RECIPE.get())
//					.requires(Items.BONE, 4)
//					.unlockedBy(CRITERIA, InventoryChangeTrigger.TriggerInstance.hasItems(MagicThingsItems.BRACELET_RECIPE.get()))
//					.save(recipe);


//	        // keyring
//	        ShapedRecipeBuilder.shaped(TreasureItems.KEY_RING.get())
//	        .pattern("kx ")
//	        .pattern("x x")
//	        .pattern(" x ")
//	        .define('x', Items.IRON_INGOT)
//	        .define('k', Ingredient.of(TreasureItems.WOOD_KEY.get(),
//	        		TreasureItems.STONE_KEY.get(),
//	        		TreasureItems.IRON_KEY.get()))
//	        .unlockedBy("has_iron", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT))
//	        .save(recipe);
//
//	        // pouch
//	        ShapedRecipeBuilder.shaped(TreasureItems.POUCH.get())
//	        .pattern(" ct")
//	        .pattern(" x ")
//	        .define('t', TreasureItems.TREASURE_TOOL.get())
//	        .define('c', Ingredient.of(
//	        		TreasureItems.COPPER_COIN.get(),
//	        		TreasureItems.SILVER_COIN.get(),
//	        		TreasureItems.GOLD_COIN.get()))
//	        .define('x', Items.LEATHER)
//	        .unlockedBy("has_tool", InventoryChangeTrigger.TriggerInstance.hasItems(TreasureItems.TREASURE_TOOL.get()))
//	        .save(recipe);
		}
}
