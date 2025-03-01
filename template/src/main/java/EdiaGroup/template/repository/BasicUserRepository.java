package EdiaGroup.template.repository;

import EdiaGroup.template.model.BasicUser;
import EdiaGroup.template.model.TestGroup;
import jakarta.persistence.Basic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasicUserRepository extends JpaRepository<BasicUser, String> {
    // You don't need to add any custom methods here for basic CRUD operations.
    // JpaRepository provides methods like save(), findById(), existsById(), etc.
    BasicUser findByUsername(String username);

}
