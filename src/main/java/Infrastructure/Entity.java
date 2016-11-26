package Infrastructure;

import java.io.Serializable;
import java.util.UUID;

public abstract class Entity implements Serializable {
    private UUID id;

    public Entity() {
        id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
