package statkevich.scooters.entity.entities;

import statkevich.scooters.entity.abstractEntities.EntityWithTitle;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "roles"
        ,uniqueConstraints = {@UniqueConstraint(columnNames = "title",name = "roles_uniq")}
)
public class Roles extends EntityWithTitle {

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private List<Users> users;


    public Roles() {
    }

    public Roles(String title) {
        super(title);
        users = new ArrayList<>();
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Role: " + super.toString();
    }
}
