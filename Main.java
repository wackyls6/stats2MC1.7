import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {

	public static void main(String[] args)
	{
		String[] directory = new File(".").list();
		for(int i = 0; i < directory.length; i++)
		{
			if(directory[i].startsWith("stats_") && directory[i].endsWith("_unsent.dat")) convert(directory[i]);
		}
	}

	private static void convert(String stats)
	{
		try {
			BufferedReader br = new BufferedReader(new FileReader(stats));
			br.readLine();
			br.readLine();

			String userID = br.readLine().split("\"")[3];
			PrintWriter pw = new PrintWriter(new FileWriter(userID + ".json"));
			pw.print("{");

			br.readLine();
			br.readLine();
			br.readLine();

			String s = "";
			while(!(s = br.readLine()).matches("  ],"))
			{
				int id = Integer.parseInt(s.split("\"")[1]);
				String amount = s.split(":")[1].split("}")[0];

				     if(id >= 16777216 && id < 16842752) pw.print("\"stat.mineBlock." + id % 65536 + "\":" + amount + ",");
				else if(id >= 16842752 && id < 16908288) pw.print("\"stat.craftItem." + id % 65536 + "\":" + amount + ",");
				else if(id >= 16908288 && id < 16973824) pw.print("\"stat.useItem."   + id % 65536 + "\":" + amount + ",");
				else if(id >= 16973824 && id < 17039360) pw.print("\"stat.breakItem." + id % 65536 + "\":" + amount + ",");

				else
				{
					switch(id){
					case 1004: pw.print("\"stat.leaveGame\":"     + amount + ","); break;
					case 1100: pw.print("\"stat.playOneMinute\":" + amount + ","); break;
					case 2000: pw.print("\"stat.walkOneCm\":"     + amount + ","); break;
					case 2001: pw.print("\"stat.swimOneCm\":"     + amount + ","); break;
					case 2002: pw.print("\"stat.fallOneCm\":"     + amount + ","); break;
					case 2003: pw.print("\"stat.climbOneCm\":"    + amount + ","); break;
					case 2004: pw.print("\"stat.flyOneCm\":"      + amount + ","); break;
					case 2005: pw.print("\"stat.diveOneCm\":"     + amount + ","); break;
					case 2006: pw.print("\"stat.minecartOneCm\":" + amount + ","); break;
					case 2007: pw.print("\"stat.boatOneCm\":"     + amount + ","); break;
					case 2008: pw.print("\"stat.pigOneCm\":"      + amount + ","); break;
					case 2010: pw.print("\"stat.jump\":"          + amount + ","); break;
					case 2011: pw.print("\"stat.drop\":"          + amount + ","); break;
					case 2020: pw.print("\"stat.damageDealt\":"   + amount + ","); break;
					case 2021: pw.print("\"stat.damageTaken\":"   + amount + ","); break;
					case 2022: pw.print("\"stat.deaths\":"        + amount + ","); break;
					case 2023: pw.print("\"stat.mobKills\":"      + amount + ","); break;
					case 2024: pw.print("\"stat.playerKills\":"   + amount + ","); break;
					case 2025: pw.print("\"stat.fishCaught\":"    + amount + ","); break;
					case 5242880: pw.print("\"achievement.openInventory\":"      + amount + ","); break;
					case 5242881: pw.print("\"achievement.mineWood\":"           + amount + ","); break;
					case 5242882: pw.print("\"achievement.buildWorkBench\":"     + amount + ","); break;
					case 5242883: pw.print("\"achievement.buildPickaxe\":"       + amount + ","); break;
					case 5242884: pw.print("\"achievement.buildFurnace\":"       + amount + ","); break;
					case 5242885: pw.print("\"achievement.acquireIron\":"        + amount + ","); break;
					case 5242886: pw.print("\"achievement.buildHoe\":"           + amount + ","); break;
					case 5242887: pw.print("\"achievement.makeBread\":"          + amount + ","); break;
					case 5242888: pw.print("\"achievement.bakeCake\":"           + amount + ","); break;
					case 5242889: pw.print("\"achievement.buildBetterPickaxe\":" + amount + ","); break;
					case 5242890: pw.print("\"achievement.cookFish\":"           + amount + ","); break;
					case 5242891: pw.print("\"achievement.onARail\":"            + amount + ","); break;
					case 5242892: pw.print("\"achievement.buildSword\":"         + amount + ","); break;
					case 5242893: pw.print("\"achievement.killEnemy\":"          + amount + ","); break;
					case 5242894: pw.print("\"achievement.killCow\":"            + amount + ","); break;
					case 5242895: pw.print("\"achievement.flyPig\":"             + amount + ","); break;
					case 5242896: pw.print("\"achievement.snipeSkeleton\":"      + amount + ","); break;
					case 5242897: pw.print("\"achievement.diamonds\":"           + amount + ","); break;
					case 5242898: pw.print("\"achievement.portal\":"             + amount + ","); break;
					case 5242899: pw.print("\"achievement.ghast\":"              + amount + ","); break;
					case 5242900: pw.print("\"achievement.blazeRod\":"           + amount + ","); break;
					case 5242901: pw.print("\"achievement.potion\":"             + amount + ","); break;
					case 5242902: pw.print("\"achievement.theEnd\":"             + amount + ","); break;
					case 5242903: pw.print("\"achievement.theEnd2\":"            + amount + ","); break;
					case 5242904: pw.print("\"achievement.enchantments\":"       + amount + ","); break;
					case 5242905: pw.print("\"achievement.overkill\":"           + amount + ","); break;
					case 5242906: pw.print("\"achievement.bookcase\":"           + amount + ","); break;
					}
				}
			}

			pw.print("\"stat.dummy\":0}");
			pw.close();
			br.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
