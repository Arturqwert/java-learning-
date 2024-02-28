package db.model.db;

import java.sql.Timestamp;
import java.util.Objects;

public class CompanyEntity {
    private int id;
    private boolean isActive;
    private String name;
    private String description;
    private boolean deletedAt;
    private Timestamp createDateTime;
    private Timestamp lastChangedDateTime;

    public CompanyEntity(int id, boolean isActive, String name, String description, boolean deletedAt, Timestamp createDateTime, Timestamp lastChangedDateTime) {
        this.id = id;
        this.isActive = isActive;
        this.name = name;
        this.description = description;
        this.deletedAt = deletedAt;
        this.createDateTime = createDateTime;
        this.lastChangedDateTime = lastChangedDateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Timestamp getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Timestamp createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Timestamp getLastChangedDateTime() {
        return lastChangedDateTime;
    }

    public void setLastChangedDateTime(Timestamp lastChangedDateTime) {
        this.lastChangedDateTime = lastChangedDateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(boolean deletedAt) {
        this.deletedAt = deletedAt;
    }

    @Override
    public String toString() {
        return "CompanyEntity{" +
                "id=" + id +
                ", isActive=" + isActive +
                ", createDateTime=" + createDateTime +
                ", lastChangedDateTime=" + lastChangedDateTime +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", deletedAt=" + deletedAt +
                '}';
    }
}