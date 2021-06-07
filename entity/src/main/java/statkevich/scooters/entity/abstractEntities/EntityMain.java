package statkevich.scooters.entity.abstractEntities;

import javax.persistence.*;

@MappedSuperclass
public abstract class EntityMain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public EntityMain() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "id= " + id;
    }
}
