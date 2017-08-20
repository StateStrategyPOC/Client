package client_store_actions;

import common.Sector;
import common.StoreAction;

public class ClientDisableRescueSector extends StoreAction {

    private final Sector sectorToDisable;

    public ClientDisableRescueSector(Sector sectorToDisable) {
        super("@CLIENT_DISABLE_RESCUE_SECTOR", "@CLIENT_GROUP");
        this.sectorToDisable = sectorToDisable;
    }

    public Sector getSectorToDisable() {
        return sectorToDisable;
    }

    @Override
    public String toString() {
        return "ClientDisableRescueSector{" +
                "sectorToDisable=" + sectorToDisable +
                ", actionIdentifier='" + actionIdentifier + '\'' +
                ", actionGroupIdentifier='" + actionGroupIdentifier + '\'' +
                '}';
    }
}
