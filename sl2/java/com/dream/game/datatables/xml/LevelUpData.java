/*
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.dream.game.datatables.xml;

import com.dream.Config;
import com.dream.game.model.L2LvlupData;
import com.dream.game.model.base.ClassId;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class LevelUpData
{
	private final static Logger _log = Logger.getLogger(LevelUpData.class.getName());

	private static final String CLASS_LVL = "class_lvl", CLASS_ID = "classid";
	private static final String MP_MOD = "mpmod", MP_ADD = "mpadd", MP_BASE = "mpbase";
	private static final String HP_MOD = "hpmod", HP_ADD = "hpadd", HP_BASE = "hpbase";
	private static final String CP_MOD = "cpmod", CP_ADD = "cpadd", CP_BASE = "cpbase";

	private static LevelUpData _instance;

	public static LevelUpData getInstance()
	{
		if (_instance == null)
		{
			_instance = new LevelUpData();
		}

		return _instance;
	}

	private Map<Integer, L2LvlupData> _lvlTable;

	@SuppressWarnings("deprecation")
	private LevelUpData()
	{
		_lvlTable = new HashMap<>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(false);
		factory.setIgnoringComments(true);
		File f = new File(Config.DATAPACK_ROOT + "/data/xml/player/lvl_up_data.xml");
		if (!f.exists())
		{
			_log.warn("lvl_up_data.xml could not be loaded: file not found");
			return;
		}
		try
		{
			InputSource in = new InputSource(new InputStreamReader(new FileInputStream(f), "UTF-8"));
			in.setEncoding("UTF-8");
			Document doc = factory.newDocumentBuilder().parse(in);
			L2LvlupData lvlDat;
			for (Node n = doc.getFirstChild(); n != null; n = n.getNextSibling())
				if (n.getNodeName().equalsIgnoreCase("list"))
				{
					for (Node d = n.getFirstChild(); d != null; d = d.getNextSibling())
						if (d.getNodeName().equalsIgnoreCase("lvlup"))
						{
							lvlDat = new L2LvlupData();
							int CLASS1_ID = Integer.valueOf(d.getAttributes().getNamedItem(CLASS_ID).getNodeValue());
							int CLASS1_LVL = Integer.valueOf(d.getAttributes().getNamedItem(CLASS_LVL).getNodeValue());
							float HP_BASE1 = Float.valueOf(d.getAttributes().getNamedItem(HP_BASE).getNodeValue());
							float HP_ADD1 = Float.valueOf(d.getAttributes().getNamedItem(HP_ADD).getNodeValue());
							float HP_MOD1 = Float.valueOf(d.getAttributes().getNamedItem(HP_MOD).getNodeValue());
							float CP_BASE1 = Float.valueOf(d.getAttributes().getNamedItem(CP_BASE).getNodeValue());
							float CP_ADD1 = Float.valueOf(d.getAttributes().getNamedItem(CP_ADD).getNodeValue());
							float CP_MOD1 = Float.valueOf(d.getAttributes().getNamedItem(CP_MOD).getNodeValue());
							float MP_BASE1 = Float.valueOf(d.getAttributes().getNamedItem(MP_BASE).getNodeValue());
							float MP_ADD1 = Float.valueOf(d.getAttributes().getNamedItem(MP_ADD).getNodeValue());
							float MP_MOD1 = Float.valueOf(d.getAttributes().getNamedItem(MP_MOD).getNodeValue());

							lvlDat.setClassid(CLASS1_ID);
							lvlDat.setClassLvl(CLASS1_LVL);
							lvlDat.setClassHpBase(HP_BASE1);
							lvlDat.setClassHpAdd(HP_ADD1);
							lvlDat.setClassHpModifier(HP_MOD1);
							lvlDat.setClassCpBase(CP_BASE1);
							lvlDat.setClassCpAdd(CP_ADD1);
							lvlDat.setClassCpModifier(CP_MOD1);
							lvlDat.setClassMpBase(MP_BASE1);
							lvlDat.setClassMpAdd(MP_ADD1);
							lvlDat.setClassMpModifier(MP_MOD1);

							_lvlTable.put(new Integer(lvlDat.getClassid()), lvlDat);
						}
				}
			lvlDat = null;

			_log.info("LevelUp Data: Loaded " + _lvlTable.size() + " character level up templates.");
		}
		catch (SAXException e)
		{
			_log.error("Error while creating table", e);
		}
		catch (IOException e)
		{
			_log.error("Error while creating table", e);
		}
		catch (ParserConfigurationException e)
		{
			_log.error("Error while creating table", e);
		}
	}

	public L2LvlupData getTemplate(ClassId classId)
	{
		return _lvlTable.get(classId.getId());
	}

	public L2LvlupData getTemplate(int classId)
	{
		return _lvlTable.get(classId);
	}

}