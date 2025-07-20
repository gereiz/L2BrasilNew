package com.dream.game.manager;

import com.dream.L2DatabaseFactory;
import com.dream.game.model.actor.instance.L2PcInstance;
import com.dream.lang.L2Integer;
import com.dream.util.SingletonSet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

public final class BlockListManager
{
	private static final Logger _log = Logger.getLogger(BlockListManager.class);

	private static final String SELECT_QUERY = "SELECT charId, name FROM character_blocks";

	private static final String INSERT_QUERY = "INSERT INTO character_blocks (charId, name) VALUES (?,?)";

	private static final String DELETE_QUERY = "DELETE FROM character_blocks WHERE charId=? AND name=?";
	private static BlockListManager _instance;

	public static BlockListManager getInstance()
	{
		if (_instance == null)
		{
			_instance = new BlockListManager();
		}
		return _instance;
	}

	
	public static void insert(L2PcInstance listOwner, L2PcInstance blocked)
	{
		Connection con = null;
		try
		{
			con = L2DatabaseFactory.getInstance().getConnection();

			PreparedStatement statement = con.prepareStatement(INSERT_QUERY);
			statement.setInt(1, listOwner.getObjectId());
			statement.setString(2, blocked.getName());
			statement.execute();
			statement.close();
		}
		catch (SQLException e)
		{
		}
		finally
		{
			try
			{
				if (con != null)
				{
					con.close();
				}
			}
			catch (Exception e)
			{
			}
		}
	}

	
	public static void remove(L2PcInstance listOwner, String name)
	{
		Connection con = null;
		try
		{
			con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement statement = con.prepareStatement(DELETE_QUERY);
			statement.setInt(1, listOwner.getObjectId());
			statement.setString(2, name);
			statement.execute();
			statement.close();
		}
		catch (SQLException e)
		{
		}
		finally
		{
			L2DatabaseFactory.close(con);
		}
	}

	private final Map<Integer, Set<String>> _blocks = new HashMap<>();

	
	private BlockListManager()
	{
		Connection con = null;
		try
		{
			con = L2DatabaseFactory.getInstance().getConnection();

			PreparedStatement statement = con.prepareStatement(SELECT_QUERY);
			ResultSet rset = statement.executeQuery();

			while (rset.next())
			{
				Integer objectId = L2Integer.valueOf(rset.getInt("charId"));
				String name = rset.getString("name");

				getBlockList(objectId).add(name);
			}
			rset.close();
			statement.close();
		}
		catch (SQLException e)
		{
		}
		finally
		{
			L2DatabaseFactory.close(con);
		}

		int size = 0;
		for (Set<String> set : _blocks.values())
		{
			size += set.size();
		}
		_log.info("Block List: Loaded " + size + " character block(s).");
	}

	public Set<String> getBlockList(Integer objectId)
	{
		Set<String> set = _blocks.get(objectId);

		if (set == null)
		{
			_blocks.put(objectId, set = new SingletonSet<>());
		}
		return set;
	}
}