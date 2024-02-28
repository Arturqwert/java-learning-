package db.model.db;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "company", schema = "\"x-clients\"", catalog = "postgres")
public class HCompanyEntity {
    public HCompanyEntity() {

    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "\"isActive\"", nullable = false)
    private boolean isActive;
    @Basic
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Basic
    @Column(name = "description", length = 300)
    private String description;
    @Basic
    @Column(name = "\"deletedAt\"")
    private boolean deletedAt;
    @Basic
    @Column(name = "\"createDateTime\"", nullable = false)
    private Timestamp createDateTime;
    @Basic
    @Column(name = "\"lastChangedDateTime\"", nullable = false)
    private Timestamp lastChangedDateTime;

    public HCompanyEntity(int id, boolean isActive, String name, String description, boolean deletedAt, Timestamp createDateTime, Timestamp lastChangedDateTime) {
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