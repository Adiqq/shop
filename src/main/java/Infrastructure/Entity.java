package Infrastructure;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Adiq on 16.10.2016.
 */
public abstract class Entity implements Serializable {
    private UUID id;

    public String getId() {
        return id == null ? "0" : id.toString();
    }

    public void setId(String id) {
        this.id = UUID.fromString(id);
    }
}
