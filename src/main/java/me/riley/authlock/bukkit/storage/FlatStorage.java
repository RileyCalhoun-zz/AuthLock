package me.riley.authlock.bukkit.storage;

import me.riley.authlock.shared.storage.StorageHandler;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FlatStorage extends StorageHandler {
    private File file;
    private YamlConfiguration configuration;

    public FlatStorage(File file) {
        this.file = file;
        try {
            file.createNewFile();
            this.configuration = YamlConfiguration.loadConfiguration(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getKey(UUID uuid) {
        if (configuration.isSet(uuid.toString() + ".key")) {
            return configuration.getString(uuid.toString() + ".key");
        }

        return null;
    }

    public void setKey(UUID uuid, String key) {
        configuration.set(uuid.toString() + ".key", key);
        save();
    }

    public void removeKey(UUID uuid) {
        configuration.set(uuid.toString() + ".key", null);
        save();
    }

    @Override
    public String getBackupKey(UUID uuid) {
        if (configuration.isSet(uuid.toString() + ".backup-key")) {
            return configuration.getString(uuid.toString() + ".backup-key");
        }

        return null;
    }

    @Override
    public void setBackupKey(UUID uuid, String backupKey) {
        configuration.set(uuid.toString() + ".backup-key", backupKey);
        save();
    }

    @Override
    public void removeBackupKey(UUID uuid) {
        configuration.set(uuid.toString() + ".backup-key", null);
        save();
    }

    private void save() {
        try {
            configuration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
