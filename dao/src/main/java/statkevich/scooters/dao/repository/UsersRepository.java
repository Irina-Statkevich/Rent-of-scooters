package statkevich.scooters.dao.repository;

import statkevich.scooters.entity.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsersRepository extends JpaRepository<Users,Long> {

    //Users findByName(String name);

    @Query("select u from Users u where u.name = :name")
    Users findByName(@Param("name") String name);
}
