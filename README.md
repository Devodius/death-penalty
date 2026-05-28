<p align="center">
    <img src="/assets/Icon.png" alt="Plugin Icon"/>
</p>
<h1 align="center">death-penalty</h1>

This plugin is here to add consequences for death without being banned like in hardcore.
If enabled, on death all items will be deleted. There is also a scoreboard keeping track of the number of death.

This plugin also add custom totem with craft that allow the inventory to be dropped or to keep the inventory.  
To balance with the original totem, the loot from Evoker was disabled and a craft is added.  
**The custom totem do not need to be in hand to work, they can be anywhere in your inventory.**

[//]: # (TODO)
A resource pack is given to add the custom totem models. It is planned to use paper to add the resource pack
automatically.

## Totems

### Totem of drop inventory

This totem will allow the inventory to drop on death instead of being deleted. It will be deleted on use.  
Default craft using coal block, redstone block, gold ingot, diamond, iron ingot and emerald:  
![The default craft for the drop inventory totem](/assets/craft-totem-of-drop-inventory.png)

### Totem of keep inventory

This totem will allow the player to keep its inventory on death. It will be deleted on use.  
Default craft using quartz block, golden apple, dragon breath, wither skeleton head, netherite upgrade smithing template
and netherite ingot:  
![The default craft for the keep inventory totem](/assets/craft-totem-of-keep-inventory.png)

## Totem of undying

This is the default minecraft totem.  
It's loot is removed from the Evoker and this craft is added:  
(resin block, turtle helmet, honey block, totem of drop inventory, beacon, totem of keep inventory, ochre froglight,
pitcher plant, yellow shulker box)  
![The added craft for the totem of undying](/assets/craft-totem-of-undying.png)

## Config

```yaml
# What happens to inventory on death, possible values:
# DEFAULT : Minecraft default
# TOTEM : CLEAR + totem
# CLEAR : Clear inventory on death
# KEEP : Keep inventory on death
inventoryOnDeath: DEFAULT

# Should the craft for custom totem be enabled.
# Passing this option to false will also allow evoker to drop totems of undying again.
# You can replace the craft using a datapack or a command. cf plugin readme
enableTotemCraft: true

# Should the death scoreboard be enabled
enableDeathCount: true

```

## Commands

Root command: `deathpenaltysettings`  
Alias: `dpsettings`
Permission: `deathpenalty.settings`

- `deathpenaltysettings inventoryOnDeath get` : Get the value of the inventoryOnDeath setting.
- `deathpenaltysettings inventoryOnDeath set <value>` : Set the value of the inventoryOnDeath setting.

## Changing the recipe / model

The plugin is looking for an item with the given custom_data:  
`"minecraft:custom_data": {PublicBukkitValues: {"death_penalty_plugin:custom_totem": "totem_of_drop_inventory"}}`  
This mean that you can create a custom totem using whatever item, model or recipe you want as long as the custom_data is
set.  
If you want to replace the recipe / model, disable the totem craft in the config and add a datapack or plugin to make a
new craft or model.

You can also use a command to give item with the custom data. Exemple:  
`/give @s minecraft:glass_bottle[minecraft:custom_data={PublicBukkitValues:{"death_penalty_plugin:custom_totem":"totem_of_drop_inventory"}}]`  
This will give a glass bottle that will have the same effect as the totem of drop inventory.

Value to use for custom_totem:

- `totem_of_drop_inventory` for the totem of drop inventory
- `totem_of_keep_inventory` for the totem of keep inventory

[!WARNING]
If the given item isn't a minecraft:totem_of_undying, the animation on death will show a default totem.
