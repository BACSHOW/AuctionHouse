package com.dream.game.auctionengine.managers;

import com.dream.L2DatabaseFactory;
import com.dream.game.auctionengine.managers.holder.HouseItem;
import com.dream.game.model.actor.instance.L2PcInstance;
import com.dream.util.StatsSet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Logger;

public final class AuctionHouseGenerator
{
	L2PcInstance activeChar;
	private static final Logger _log = Logger.getLogger(AuctionHouseGenerator.class.getName());
	public static final boolean NORMAL_WEAPON = false;
	public static final boolean MAGICAL_WEAPON = true;
	
	public AuctionHouseGenerator()
	{
		_log.info("Initializing Auction House Htmls.");
	}
	
	public final static List<HouseItem> loadItems()
	{
		ArrayList<HouseItem> items = new ArrayList<>();
		
		try (Connection con = L2DatabaseFactory.getInstance().getConnection())
		{
			PreparedStatement statement = con.prepareStatement("SELECT * FROM auction_house WHERE itemId > 0");
			ResultSet rset = statement.executeQuery();
			while (rset.next())
			{
				int itemId;
				int ownerId;
				int count;
				long salePrice;
				long expirationTime;
				
				itemId = rset.getInt("itemId");
				ownerId = rset.getInt("ownerId");
				count = rset.getInt("count");
				salePrice = rset.getLong("sale_price");
				expirationTime = rset.getLong("expiration_time");
				
				items.add(new HouseItem(ownerId, itemId, count, salePrice, expirationTime));
			}
		}
		catch (Exception e)
		{
			_log.warning(AuctionHouseGenerator.class.getName() + ": An error was generated while loading auction items on sale from DB: " + e);
		}
		
		return items;
	}
	
	public final static StatsSet processBypass(String command)
	{
		if (command.startsWith("_bbsgetfav;server;"))
		{
			StringTokenizer st = new StringTokenizer(command, ";");
			String token;
			StatsSet set = new StatsSet();
			while (st.hasMoreTokens())
			{
				if ((token = st.nextToken()).startsWith("page"))
				{
					set.set("page", token.substring(4));
				}
				else if (token.startsWith("rank"))
				{
					set.set("rank", token.substring(5).trim());
				}
				else if (token.startsWith("category"))
				{
					set.set("category", token.substring(8).trim());
				}
				else if (token.startsWith("search "))
				{
					set.set("search", token.substring(7).trim().toLowerCase());
				}
				else if (token.startsWith("selectedItemId"))
				{
					set.set("selectedItemId", token.substring(14));
				}
				else if (token.startsWith("order"))
				{
					set.set("order", token.substring(5));
				}
			}
			return set;
		}
		if (command.startsWith("_bbsgetfav;my"))
		{
			StringTokenizer st = new StringTokenizer(command, ";");
			String token;
			StatsSet set = new StatsSet();
			while (st.hasMoreTokens())
			{
				if ((token = st.nextToken()).startsWith("itemId"))
				{
					set.set("itemId", token.substring(6));
				}
				else if (token.startsWith("page"))
				{
					set.set("page", token.substring(4));
				}
				else if (token.startsWith("selectedAuction"))
				{
					set.set("selectedAuction", token.substring(15));
				}
				else if (token.startsWith("apply"))
				{
					set.set("apply", true);
				}
				else if (token.startsWith("create"))
				{
					set.set("create", true);
				}
				else if (token.startsWith("cancelConfirm"))
				{
					set.set("cancelConfirm", token.substring(13));
				}
				else if (token.startsWith("cancel"))
				{
					set.set("cancel", token.substring(6));
				}
				else if (token.startsWith("quantity"))
				{
					long temp = Integer.parseInt(token.substring(9).trim());
					set.set("quantity", temp);
					if (temp > 1000000)
					{
						set.set("quantity", 1000000);
					}
					else if (temp < 1L)
					{
						set.set("quantity", 1);
					}
				}
				else if (token.startsWith("saleprice"))
				{
					long temp = Long.parseLong(token.substring(10).trim());
					set.set("saleprice", temp);
					if (temp > 10000000000L)
					{
						set.set("saleprice", 10000000000L);
					}
					else if (temp < 1)
					{
						set.set("saleprice", 1);
					}
				}
				else if (token.startsWith("duration"))
				{
					long temp = Integer.parseInt(token.substring(9, 10).trim());
					set.set("duration", Integer.parseInt(token.substring(9, 10).trim()));
					if (temp < 1)
					{
						set.set("duration", 1);
					}
					else if (temp > 9L)
					{
						set.set("duration", 9);
					}
				}
			}
			return set;
		}
		if (command.startsWith("_bbsgetfav;purchase;"))
		{
			StringTokenizer st = new StringTokenizer(command, ";");
			String token;
			StatsSet set = new StatsSet();
			while (st.hasMoreTokens())
			{
				if ((token = st.nextToken()).startsWith("page"))
				{
					set.set("page", token.substring(4));
				}
				else if (token.startsWith("rank"))
				{
					set.set("rank", token.substring(5).trim());
				}
				else if (token.startsWith("category"))
				{
					set.set("category", token.substring(8).trim());
				}
				else if (token.startsWith("search "))
				{
					set.set("search", token.substring(7).trim().toLowerCase());
				}
				else if (token.startsWith("selectedItemId"))
				{
					set.set("selectedItemId", token.substring(14));
				}
				else if (token.startsWith("order"))
				{
					set.set("order", token.substring(5));
				}
				else if (token.startsWith("apply"))
				{
					set.set("apply", true);
				}
				else if (token.startsWith("confirm"))
				{
					set.set("confirm", true);
				}
				else if (token.startsWith("purchaseCount"))
				{
					try
					{
						set.set("purchaseCount", token.substring(14).trim());
					}
					catch (Exception _ex)
					{
						set.set("purchaseCount", 1);
					}
				}
			}
			return set;
		}
		
		return null;
	}
	
	public final static void addNewAuctionToDB(int itemId, int charId, int count, long price, long endTime)
	{
		try (Connection con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement statement = con.prepareStatement("INSERT INTO auction_house VALUES (?,?,?,?,?)");)
		{
			statement.setInt(1, itemId);
			statement.setInt(2, charId);
			statement.setInt(3, count);
			statement.setLong(4, price);
			statement.setLong(5, endTime);
			statement.execute();
		}
		catch (Exception e)
		{
			_log.warning(AuctionHouseGenerator.class.getName() + ": The auction couldnt be deleted from the DB: " + e);
		}
	}
	
	public final static void updateItemCountToDB(int itemId, int itemCount)
	{
		try (Connection con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement statement = con.prepareStatement("UPDATE auction_house SET count=? WHERE itemId=?"))
		{
			statement.setInt(1, itemCount);
			statement.setInt(2, itemId);
			statement.executeUpdate();
		}
		catch (Exception e)
		{
			_log.warning(AuctionHouseGenerator.class.getName() + ": The Auction item couldnt be updated to the DB: " + e);
		}
	}
	
	public final static void deleteItemFromDB(int itemId)
	{
		try (Connection con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement statement = con.prepareStatement("DELETE FROM auction_house WHERE itemId=?"))
		{
			statement.setInt(1, itemId);
			statement.execute();
		}
		catch (Exception e)
		{
			_log.warning(AuctionHouseGenerator.class.getName() + ": The auction couldnt be deleted from the DB: " + e);
		}
	}
	
	private static class SingletonHolder
	{
		protected static final AuctionHouseGenerator _instance = new AuctionHouseGenerator();
	}
	
	public static AuctionHouseGenerator getInstance()
	{
		return SingletonHolder._instance;
	}
}
