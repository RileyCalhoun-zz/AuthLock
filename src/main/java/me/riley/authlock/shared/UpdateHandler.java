package me.riley.authlock.shared;

import me.riley.authlock.bukkit.AuthLock;
import org.apache.commons.io.IOUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class UpdateHandler {

    private static boolean upToDate = false;
    private static String latest = "";
    private AuthLock authLock;

    public UpdateHandler(boolean checkForUpdates, String current, AuthLock authLock) {
        this.authLock = authLock;
        authLock.getLogger().info("Checking for plugin updates...");
        InputStream in = null;
        try {
            in = new URL("https://raw.githubusercontent.com/RileyCalhounDEV/AuthLock/master/version.txt").openStream();
        } catch (IOException e) {
            authLock.getLogger().info("Unable to check for updates!");
            e.printStackTrace();
        }

        try {
            latest = IOUtils.readLines(in).get(0);
        } catch (IOException e) {
            authLock.getLogger().info("Unable to check for updates!");
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(in);
        }

        authLock.getLogger().info("Latest version is " + latest + ".");
        authLock.getLogger().info("Current version is " + current);
        upToDate = current.equals(latest);
        if(upToDate) {
            authLock.getLogger().info("You're using the latest version!");
        } else {
            authLock.getLogger().info("You're using an out-of-date version! Please update.");
        }
    }

    public String getLatestVersion() {
        return latest;
    }

    public boolean isUpToDate() {
        return upToDate;
    }

}
