package Domain.Infrastructure;

import java.util.UUID;

public abstract class Entity implements Validatable {
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
