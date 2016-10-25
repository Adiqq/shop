package Infrastructure;

import java.util.UUID;

/**
 * Created by Adiq on 16.10.2016.
 */
public abstract class Entity {
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
