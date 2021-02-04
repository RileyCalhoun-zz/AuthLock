package me.riley.authlock.bungee;

import net.md_5.bungee.api.plugin.Plugin;

public class AuthLock extends Plugin {

    public void onEnable() {
        getProxy().getLogger().severe("MC2FA does NOT yet support BungeeCord, sorry :(");
    }

}
