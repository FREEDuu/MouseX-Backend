package EdiaGroup.template.repository;

import EdiaGroup.template.model.BasicUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicUserRepository extends JpaRepository<BasicUser, String> {
    // You don't need to add any custom methods here for basic CRUD operations.
    // JpaRepository provides methods like save(), findById(), existsById(), etc.
}
