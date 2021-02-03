# AuthLock
AuthLock is a **free** two-factor authentication plugin for Bukkit, Spigot & BungeeCord (coming soon). Forked from ConnorLinfoot.

## Features
- Designed to work out of the box. Just copy the plugin into your plugins folder and restart your server.
- Customizable messages and prefix.
- In-game QR display using maps.
- Support flat file (YAML), SQLite, or MySQL for key storage. (SQLite and MySQL coming soon)
- Ability to require 2FA for all players. (Or just staff)
- Disables tasks before authentication. (Tasks include: player movement, block breaking, chat, etc.)

## Planned
- BungeeCord & Multi-Bungee support
- MySQL support
- Fallback key, allow players to be given a backup key in the case that they lose their 2FA device
- Admin commands, allow staff to view players with 2FA and disable if needed
- Auto-allow if on the same IP within a certain time (option)
- Support for 1.8+

## Requirements
- Spigot 1.16.5
- Java 8+
