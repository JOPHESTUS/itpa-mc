package us.jophest.isthispersonawesome;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.libs.org.ibex.nestedvm.util.Seekable.InputStream;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class isthispersonawesome extends JavaPlugin {
    Logger log = this.getLogger();
	PluginDescriptionFile pdfFile;
    String scoreRaw;



    public void SetupConfig()
    {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
    }
    public void itpa(String name){
        String addr = getConfig().getString("itpa-link");
      try{
        URL yahoo = new URL(addr + "/awesomestate/" + name + ".awesome");
        URLConnection yc = yahoo.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        yc.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null){
            scoreRaw = inputLine;

        }
            in.close();
      } catch (Exception e) {
          // TODO Auto-generated catch block
         scoreRaw = "0";
      }
    }
    public void response(String player, int score, CommandSender sender){
        if (getConfig().getString("form").equalsIgnoreCase("broadcast")){
            if(score <= 33){
                Bukkit.broadcastMessage("Is " + player + " awesome?" + ChatColor.RED + " No." + ChatColor.WHITE + " How awesome exactly? " + ChatColor.BOLD + score + "%" );
            }  else if (score > 33 && score <= 66){
                Bukkit.broadcastMessage("Is " + player + " awesome?" + ChatColor.GOLD + " Sorta." + ChatColor.WHITE + " How awesome exactly? " + ChatColor.BOLD + score + "%" );

            } else if (score > 66){
                Bukkit.broadcastMessage("Is " + player + " awesome?" + ChatColor.DARK_GREEN + " Yes." + ChatColor.WHITE + " How awesome exactly? " + ChatColor.BOLD + score + "%" );

            }
        }else if (getConfig().getString("form").equalsIgnoreCase("msg")){
            if(score <= 33){
                sender.sendMessage("Is " + player + " awesome?" + ChatColor.RED + " No." + ChatColor.WHITE + " How awesome exactly? " + ChatColor.BOLD + score + "%" );
            }  else if (score > 33 && score <= 66){
                sender.sendMessage("Is " + player + " awesome?" + ChatColor.GOLD + " Sorta." + ChatColor.WHITE + " How awesome exactly? " + ChatColor.BOLD + score + "%" );

            } else if (score > 66){
                sender.sendMessage("Is " + player + " awesome?" + ChatColor.DARK_GREEN + " Yes." + ChatColor.WHITE + " How awesome exactly? " + ChatColor.BOLD + score + "%" );

            }
        }
    }
	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {	
		// TODO Auto-generated method stub
		  		
		if (command.getName().equalsIgnoreCase("itpa")) {


		     if(sender.hasPermission("itpa.check")){
                 if (args.length == 0){

                     try {
                        itpa("console");
                         int score = Integer.valueOf(scoreRaw);
                         String plr = "console";
                         response(plr, score, sender );
                         scoreRaw = null;
                     } catch (Exception e) {
                         // TODO Auto-generated catch block
                         e.printStackTrace();
                     }
                 } else if (args.length == 1){
                     try {

                         String plr = args[0];
                         plr = plr.toLowerCase();
                         itpa(plr);

                         int score = Integer.valueOf(scoreRaw);


                         response(plr, score, sender);
                          scoreRaw = null;
                     } catch (Exception e) {
                         // TODO Auto-generated catch block
                         e.printStackTrace();
                     }
                 }    else {
                     sender.sendMessage(ChatColor.GREEN + "[ITPA] " + ChatColor.YELLOW + "Too many args");
                 }

             }
			

		}
			
		return super.onCommand(sender, command, label, args);

	}
    public void onEnable()
    {
        this.pdfFile = getDescription();
        PluginManager pm = getServer().getPluginManager();

        if (!new File(getDataFolder(), "config.yml").exists())  {
            SetupConfig();
        }
        try {
            Metrics metrics = new Metrics(this);
            metrics.start();
        } catch (IOException e) {
            // Failed to submit the stats :-(
        }
    }



    public void HelloMrBukkitDevStaffIDontKnowWhoYouAreButImSureWhoeverYouAreYouSmellQuiteBadAtThisPresentMomentInTimePerhapsYouShouldTakeAShower(){

    }
    public void lol768_ily(){

    }
    public void dsh105_ily(){

    }
    public void husky_ily(){

    }
    public void mbaxter_ily(){

    }
    public void yer_yer_yer_soz_not_soz(){

    }
    public void YOLO(){

    }
    public void omg_I_cant_believe_I_just_said_that(){

    }
}