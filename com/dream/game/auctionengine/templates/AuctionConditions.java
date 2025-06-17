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

import com.dream.game.model.actor.instance.L2ItemInstance;
import com.dream.game.templates.item.L2ArmorType;
import com.dream.game.templates.item.L2EtcItem;
import com.dream.game.templates.item.L2EtcItemType;
import com.dream.game.templates.item.L2WeaponType;

import java.util.ArrayList;
import java.util.List;

/**
 * List of conditions that can be used to filter what kind of items include each category
 * @author GodFather
 */
public class AuctionConditions
{
	public static abstract class AuctionCondition
	{
		/**
		 * @param item
		 * @param entrance
		 * @return Returns true if the item passes all the checks invoqued by the conditions of that entry
		 */
		public abstract boolean checkCondition(L2ItemInstance item, AuctionHouse entrance);
	}
	
	public static class AuctionConditionEtcType extends AuctionCondition
	{
		private final List<L2EtcItemType> _types = new ArrayList<>();
		
		public AuctionConditionEtcType(L2EtcItemType... types)
		{
			for (L2EtcItemType type : types)
			{
				_types.add(type);
			}
		}
		
		@Override
		public boolean checkCondition(L2ItemInstance item, AuctionHouse entrance)
		{
			if (!(item.getItem() instanceof L2EtcItem))
			{
				return false;
			}
			
			return _types.contains(item.getItem().getItemType());
		}
	}
	
	public static class AuctionConditionWeaponType extends AuctionCondition
	{
		private final boolean _isMagicWeapon;
		private final List<L2WeaponType> _types = new ArrayList<>();
		
		public AuctionConditionWeaponType(boolean isMagicWeapon, L2WeaponType... types)
		{
			_isMagicWeapon = isMagicWeapon;
			for (L2WeaponType type : types)
			{
				_types.add(type);
			}
		}
		
		@Override
		public boolean checkCondition(L2ItemInstance item, AuctionHouse entrance)
		{
			if (!item.isWeapon())
			{
				return false;
			}
			
			if (_isMagicWeapon != item.isMagicWeapon())
			{
				return false;
			}
			
			return _types.contains(item.getItem().getItemType());
		}
	}
	
	public static class AuctionConditionArmorType extends AuctionCondition
	{
		private final List<L2ArmorType> _types = new ArrayList<>();
		
		public AuctionConditionArmorType(L2ArmorType... types)
		{
			for (L2ArmorType type : types)
			{
				_types.add(type);
			}
		}
		
		@Override
		public boolean checkCondition(L2ItemInstance item, AuctionHouse entrance)
		{
			if (!item.getItem().isArmorOrShield())
			{
				return false;
			}
			
			return _types.contains(item.getItem().getItemType());
		}
	}
	
	public static class AuctionConditionSkill extends AuctionCondition
	{
		private final List<Integer> _skills = new ArrayList<>();
		
		public AuctionConditionSkill(int... skills)
		{
			for (int skill : skills)
			{
				_skills.add(skill);
			}
		}

		@Override
		public boolean checkCondition(L2ItemInstance item, AuctionHouse entrance)
		{
			// TODO Auto-generated method stub
			return false;
		}
		
		
	}
	
	public static class AuctionConditionSlot extends AuctionCondition
	{
		private final List<Integer> _slots = new ArrayList<>();
		
		public AuctionConditionSlot(int... slots)
		{
			for (int slot : slots)
			{
				_slots.add(slot);
			}
		}
		
		@Override
		public boolean checkCondition(L2ItemInstance item, AuctionHouse entrance)
		{
			return _slots.contains(item.getItem().getBodyPart());
		}
	}
	
	public static class AuctionConditionIcon extends AuctionCondition
	{
		private final List<String> _icons = new ArrayList<>();
		
		public AuctionConditionIcon(String... icons)
		{
			for (String icon : icons)
			{
				_icons.add(icon);
			}
		}

		@Override
		public boolean checkCondition(L2ItemInstance item, AuctionHouse entrance)
		{
			// TODO Auto-generated method stub
			return false;
		}
		
		
	}
	
	public static class AuctionConditionName extends AuctionCondition
	{
		private final List<String> _names = new ArrayList<>();
		
		public AuctionConditionName(String... names)
		{
			for (String name : names)
			{
				_names.add(name);
			}
		}
		
		@Override
		public boolean checkCondition(L2ItemInstance item, AuctionHouse entrance)
		{
			for (String name : _names)
			{
				if (item.getItem().getName().startsWith(name))
				{
					return true;
				}
			}
			
			return false;
		}
	}
	
	public static class AuctionConditionHandler extends AuctionCondition
	{
		private final List<String> _handlers = new ArrayList<>();
		
		public AuctionConditionHandler(String... handlers)
		{
			for (String handler : handlers)
			{
				_handlers.add(handler);
			}
		}
		
		@Override
		public boolean checkCondition(L2ItemInstance item, AuctionHouse entrance)
		{
			if (!(item.getItem() instanceof L2EtcItem))
			{
				return false;
			}
			
			if (item.getEtcItem().getName() == null)
			{
				return false;
			}
			
			for (String handler : _handlers)
			{
				if (item.getEtcItem().getName().equalsIgnoreCase(handler))
				{
					return true;
				}
			}
			
			return false;
		}
	}
	
	public static class AuctionConditionForPet extends AuctionCondition
	{
		private final boolean _isForPet;
		
		public AuctionConditionForPet(boolean isForPet)
		{
			_isForPet = isForPet;
		}
		
		@Override
		public boolean checkCondition(L2ItemInstance item, AuctionHouse entrance)
		{
			return _isForPet == item.getItem().isPetItem();
		}
	}
}
