package com.github.skystardust.accrueaward.Listener;

import com.github.skystardust.accrueaward.SettingManager.ConfigGetter;
import com.github.skystardust.accrueaward.SettingManager.ConfigWriter;
import org.black_ixx.playerpoints.event.PlayerPointsChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayerPointListener implements Listener
{
    @EventHandler
    public void onPlayerPointChange(PlayerPointsChangeEvent event) throws IOException {
        if (event.getChange()<0){
            return;
        }else {
            OfflinePlayer order=Bukkit.getOfflinePlayer(event.getPlayerId());
            try {
                ConfigWriter.getInstance().addPlayerValue(order.getName(),event.getChange());
            } catch (IOException e) {
                e.printStackTrace();
            }
            int now = ConfigGetter.getInstance().getVarintByPlayer(order.getName());
            List<String> award = ConfigGetter.getInstance().getAwardList();
            List<String> outted = new ArrayList<>();
            award.forEach(key -> {
                if (now>Integer.valueOf(key)){
                    List<String> command = ConfigGetter.getInstance().getAccrueCommandList(Integer.valueOf(key));
                    outted.add(key);
                    command.forEach(a -> {
                        if (!ConfigGetter.getInstance().isPlayerGotAward(order.getName(), Integer.parseInt(key))){
                            String b = a.replace("$player$", order.getName());
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),b);
                        }else {
                            return;
                        }
                    });
                }
            });
            outted.forEach(a -> {
                try {
                    ConfigWriter.getInstance().setPlayerAwarded(order.getName(),a,true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
