# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.2.0] - 2024-09-02

### Changed
- Silver Ore blocks now drop Raw Silver items.
- Raw Silver can be smelted into Silver Ingots.
- Use datagen for blockstate, block model and block loot tables.

### Added
- Raw Silver Item
- Raw Silver to the Forge tags (aka ore dictionary)
- Config options to control each Loot Modifier

## [1.1.0] - 2024-07-15

### Changed
- Renamed Magic Treasures Manual to Guide.
- Added Magic Treasures Guide (Patchouli Guide) to the Magic Treasures tab.
- Updated Guide book to version 1.2
- Added jewelry entries to Guide book.
- Fixed Patchouli guide not adding on first use (naming issue).
- Fixed mods.toml to point to the correct update.json file.
- Blood jewelry material has -10% spell cost.
- Fixed LootModifiers to use the correct chance (the chance always calculated to 0 and thus was not working).
- Update global_loot_modifiers files with the zombie and wither_skeleton modifier.
- Update loot_modifiers loot tables to remove killed_by_player, use random_chance, and rebalance values.

### Added
- Jewelry-Rarity tag mappings!

## [1.0.0] - 2024-07-11

- Initial port from mc1.19.2.

### Changed
- Moved loot function registrations to the common setup.
- Removed Integration config options. If Curios is present, it is enabled.
- Updated Patchouli book to version 1.1
- Cleaned up extra textures
- Fixed naming of some jewelery items during deferred construction.

### Added
