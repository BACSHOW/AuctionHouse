/*
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.dream.game.auctionengine.templates;

import com.dream.game.auctionengine.managers.AuctionHouseGenerator;
import com.dream.game.auctionengine.managers.AuctionHouseManager;
import com.dream.game.auctionengine.managers.AuctionHouseManager.AuctionHouseEntrance;
import com.dream.game.auctionengine.templates.AuctionConditions.AuctionCondition;
import com.dream.game.auctionengine.templates.AuctionConditions.AuctionConditionEtcType;
import com.dream.game.auctionengine.templates.AuctionConditions.AuctionConditionForPet;
import com.dream.game.auctionengine.templates.AuctionConditions.AuctionConditionHandler;
import com.dream.game.auctionengine.templates.AuctionConditions.AuctionConditionIcon;
import com.dream.game.auctionengine.templates.AuctionConditions.AuctionConditionName;
import com.dream.game.auctionengine.templates.AuctionConditions.AuctionConditionSkill;
import com.dream.game.auctionengine.templates.AuctionConditions.AuctionConditionSlot;
import com.dream.game.auctionengine.templates.AuctionConditions.AuctionConditionWeaponType;
import com.dream.game.model.actor.instance.L2ItemInstance;
import com.dream.game.templates.item.L2EtcItemType;
import com.dream.game.templates.item.L2Item;
import com.dream.game.templates.item.L2WeaponType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * List of available categories for the Auction House This is not static, can be modified as you wish. Edit the current ones, delete, add, etc
 * @author GodFather
 */
public enum AuctionHouse
{
	// Structure: Name, Category, Conditions
	ITEM_PET_WEAPON("Pet Weapon", "Pet Goods", new AuctionConditionSlot(L2Item.SLOT_R_HAND), new AuctionConditionForPet(true)),
	ITEM_PET_ARMOR("Pet Armor", "Pet Goods", new AuctionConditionSlot(L2Item.SLOT_CHEST), new AuctionConditionForPet(true)),
	ITEM_PET_JEWEL("Pet Jewel", "Pet Goods", new AuctionConditionSlot(L2Item.SLOT_NECK), new AuctionConditionForPet(true)),
	ITEM_PET_COLLAR("Pet Collar", "Pet Goods", new AuctionConditionEtcType(L2EtcItemType.PET_COLLAR)),
	ITEM_PET_SUPPLIES("Pet Supplies", "Pet Goods", new AuctionConditionForPet(true)),
	
	ITEM_WEAPON_ONE_HANDED_SWORD("1-H Sword", "Weapon", new AuctionConditionWeaponType(AuctionHouseGenerator.NORMAL_WEAPON, L2WeaponType.SWORD)),
	ITEM_WEAPON_ONE_HANDED_MAGIC_SWORD("1-H Magic Sword", "Weapon", new AuctionConditionWeaponType(AuctionHouseGenerator.MAGICAL_WEAPON, L2WeaponType.SWORD)),
	ITEM_WEAPON_DAGGER("Dagger", "Weapon", new AuctionConditionWeaponType(AuctionHouseGenerator.NORMAL_WEAPON, L2WeaponType.DAGGER)),
	//ITEM_WEAPON_RAPIER("Rapier", "Weapon", new AuctionConditionWeaponType(AuctionHouseGenerator.NORMAL_WEAPON, WeaponType.RAPIER)),
	//ITEM_WEAPON_ANCIENT_SWORD("Ancient Sword", "Weapon", new AuctionConditionWeaponType(AuctionHouseGenerator.NORMAL_WEAPON, WeaponType.ANCIENTSWORD)),
	ITEM_WEAPON_DUAL_SWORD("Dual Sword", "Weapon", new AuctionConditionWeaponType(AuctionHouseGenerator.NORMAL_WEAPON, L2WeaponType.DUAL)),
	//ITEM_WEAPON_DUAL_DAGGER("Dual Dagger", "Weapon", new AuctionConditionWeaponType(AuctionHouseGenerator.NORMAL_WEAPON, WeaponType.DUALDAGGER)),
	ITEM_WEAPON_ONE_HANDED_BLUNT("Blunt", "Weapon", new AuctionConditionWeaponType(AuctionHouseGenerator.NORMAL_WEAPON, L2WeaponType.BLUNT)),
	ITEM_WEAPON_ONE_HANDED_MAGIC_BLUNT("Magic Blunt", "Weapon", new AuctionConditionWeaponType(AuctionHouseGenerator.MAGICAL_WEAPON, L2WeaponType.BLUNT)),
	ITEM_WEAPON_BOW("Bow", "Weapon", new AuctionConditionWeaponType(AuctionHouseGenerator.NORMAL_WEAPON, L2WeaponType.BOW)),
	//ITEM_WEAPON_CROSSBOW("Crossbow", "Weapon", new AuctionConditionWeaponType(AuctionHouseGenerator.NORMAL_WEAPON, WeaponType.CROSSBOW)),
	ITEM_WEAPON_POLE("Pole", "Weapon", new AuctionConditionWeaponType(AuctionHouseGenerator.NORMAL_WEAPON, L2WeaponType.POLE)),
	ITEM_WEAPON_FIST("Fists", "Weapon", new AuctionConditionWeaponType(AuctionHouseManager.NORMAL_WEAPON, L2WeaponType.DUALFIST)),
	ITEM_WEAPON_OTHER("Other", "Weapon", new AuctionConditionWeaponType(AuctionHouseGenerator.NORMAL_WEAPON, L2WeaponType.ETC)),
	
	ITEM_ARMOR_HELMET("Helmet", "Armor", new AuctionConditionSlot(L2Item.SLOT_HEAD)),
	ITEM_ARMOR_UPPER_PIECE("Upper Piece", "Armor", new AuctionConditionSlot(L2Item.SLOT_CHEST)),
	ITEM_ARMOR_LOWER_PIECE("Lower Piece", "Armor", new AuctionConditionSlot(L2Item.SLOT_LEGS)),
	ITEM_ARMOR_FULL_BODY("Full Body", "Armor", new AuctionConditionSlot(L2Item.SLOT_FULL_ARMOR)),
	ITEM_ARMOR_GLOVES("Gloves", "Armor", new AuctionConditionSlot(L2Item.SLOT_GLOVES)),
	ITEM_ARMOR_BOOTS("Boots", "Armor", new AuctionConditionSlot(L2Item.SLOT_FEET)),
	//ITEM_ARMOR_SHIELD("Shield", "Armor", new AuctionConditionSlot(L2Item.SLOT_L_HAND), new AuctionConditionArmorType(L2ArmorType.SHIELD)),
	//ITEM_ARMOR_SIGIL("Sigil", "Armor", new AuctionConditionSlot(Item.SLOT_L_HAND), new AuctionConditionArmorType(ArmorType.SIGIL)),
	ITEM_ARMOR_UNDERWEAR("Underwear", "Armor", new AuctionConditionSlot(L2Item.SLOT_UNDERWEAR)),
	ITEM_ARMOR_CLOAK("Cloak", "Armor", new AuctionConditionSlot(L2Item.SLOT_BACK)),
	
	ITEM_ACCESORY_RING("Ring", "Accesory", new AuctionConditionSlot(L2Item.SLOT_LR_FINGER)),
	ITEM_ACCESORY_EARRING("Earring", "Accesory", new AuctionConditionSlot(L2Item.SLOT_LR_EAR)),
	ITEM_ACCESORY_NECKLACE("Necklace", "Accesory", new AuctionConditionSlot(L2Item.SLOT_NECK)),
	//ITEM_ACCESORY_BELT("Belt", "Accesory", new AuctionConditionSlot(Item.SLOT_BELT)),
	ITEM_ACCESORY_BRACELET("Bracelet", "Accesory", new AuctionConditionSlot(L2Item.SLOT_LR_HAND)),
	ITEM_ACCESORY_HAIR("Hair Accesory", "Accesory", new AuctionConditionSlot(L2Item.SLOT_HAIR, L2Item.SLOT_HAIRALL)),
	
	ITEM_SUPPLY_POTION("Potion", "Supplies", new AuctionConditionEtcType(L2EtcItemType.POTION)),
	//ITEM_SUPPLY_SCROLL_ENCHANT_WEAPON("Scroll: Enchant Weapon", "Supplies", new AuctionConditionEtcType(L2EtcItemType.SCRL_ENCHANT_WP, L2EtcItemType.BLESS_SCRL_ENCHANT_WP)),
	//ITEM_SUPPLY_SCROLL_ENCHANT_ARMOR("Scroll: Enchant Armor", "Supplies", new AuctionConditionEtcType(L2EtcItemType.SCRL_ENCHANT_AM, L2EtcItemType.BLESS_SCRL_ENCHANT_AM)),
	ITEM_SUPPLY_SCROLL_OTHER("Scroll: Other", "Supplies", new AuctionConditionEtcType(L2EtcItemType.SCROLL)),
	ITEM_SUPPLY_SOULSHOT("Soulshot", "Supplies", new AuctionConditionHandler("SoulShots", "BeastSoulShot")),
	ITEM_SUPPLY_SPIRITSHOT("Spiritshot", "Supplies", new AuctionConditionHandler("SpiritShot", "BeastSpiritShot")),
	
	ITEM_ETC_ATTRIBUTE_STONE("Attribute Stone", "Etc", new AuctionConditionHandler("EnchantAttribute")),
	ITEM_ETC_LIFE_STONE("Life Stone", "Etc", new AuctionConditionIcon("icon.etc_mineral")),
	ITEM_ETC_SOUL_CRYSTAL("Soul Crystal", "Etc", new AuctionConditionSkill(2096)),
	ITEM_ETC_SPELLBOOK("Spellbook", "Etc", new AuctionConditionIcon("icon.etc_spell_books_element_")),
	ITEM_ETC_CRYSTAL("Crystal", "Etc", new AuctionConditionName("Crystal:")),
	ITEM_ETC_GEMSTONE("Gemstone", "Etc", new AuctionConditionEtcType(L2EtcItemType.MATERIAL), new AuctionConditionName("Gemstone")),
	ITEM_ETC_POUCH("Magic Pouch", "Etc", new AuctionConditionIcon("icon.pouch_")),
	ITEM_ETC_PIN("Magic Pin", "Etc", new AuctionConditionIcon("icon.icon.pin_")),
	ITEM_ETC_MAGIC_ORNAMENT("Magic Ornament", "Etc", new AuctionConditionIcon("icon.etc_belt_deco_")),
	ITEM_ETC_MAGIC_CLIP("Magic Rune Clip", "Etc", new AuctionConditionIcon("icon.etc_rune_clip_")),
	//ITEM_ETC_DYE("Dyes", "Etc", new AuctionConditionEtcType(L2EtcItemType.DYE)),
	//ITEM_ETC_RECIPE("Recipe", "Etc", new AuctionConditionEtcType(L2EtcItemType.RECIPE)),
	ITEM_ETC_CRAFTING_INGREDIENTS("Crafting Ingredients", "Etc", new AuctionConditionEtcType(L2EtcItemType.MATERIAL)),
	ITEM_ETC_OTHER("Other Items", "Etc");
	
	private final String _name;
	private final String _category;
	private final List<AuctionCondition> _conditions = new ArrayList<>();
	
	private AuctionHouse(String name, String category)
	{
		_name = name;
		_category = category;
	}
	
	private AuctionHouse(String name, String category, AuctionCondition... conditions)
	{
		_name = name;
		_category = category;
		
		for (AuctionCondition cond : conditions)
		{
			_conditions.add(cond);
		}
	}
	
	public int getTopId()
	{
		return ordinal();
	}
	
	public String getTopName()
	{
		return _name;
	}
	
	public String getCategory()
	{
		return _category;
	}
	
	public List<AuctionCondition> getConditions()
	{
		return _conditions;
	}
	
	/**
	 * Generates a list of entries on a map of Class AuctionHouseEntrance
	 * @param rankings
	 */
	public static void generateEntrances(Map<Integer, AuctionHouseEntrance> rankings)
	{
		for (AuctionHouse rank : AuctionHouse.values())
		{
			rankings.put(rank.getTopId(), new AuctionHouseEntrance(rank.getTopId(), rank.getTopName(), rank.getCategory()));
		}
	}
	
	/**
	 * @param item
	 * @return Returns the entry id that the item should be included. For that every condition is checked
	 */
	public static AuctionHouse getEntranceIdFromItem(L2ItemInstance item)
	{
		boolean found;
		for (AuctionHouse rank : AuctionHouse.values())
		{
			found = true;
			for (AuctionCondition cond : rank.getConditions())
			{
				if (!cond.checkCondition(item, rank))
				{
					found = false;
					break;
				}
			}
			
			if (found)
			{
				return rank;
			}
		}
		
		return ITEM_ETC_OTHER;
	}
}
