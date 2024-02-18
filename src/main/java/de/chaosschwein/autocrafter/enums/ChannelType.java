package de.chaosschwein.autocrafter.enums;

import de.chaosschwein.autocrafter.main.AutoMain;
import org.bukkit.Material;

public enum ChannelType {
    Private("sender_policy_type.private", Material.RED_DYE),
    Protected("sender_policy_type.protected", Material.GREEN_DYE),
    Public("sender_policy_type.public", Material.BLUE_DYE);
    private final String translationKey;
    public final Material material;

    ChannelType(String translationKey, Material material) {
        this.translationKey = translationKey;
        this.material = material;
    }

    public static ChannelType fromString(String s) {
        for (ChannelType type : values()) {
            if (type.name().equalsIgnoreCase(s)) {
                return type;
            }
        }
        return null;
    }

    public String getTranslation() {
        return AutoMain.language.fileManager.read(translationKey);
    }
}
