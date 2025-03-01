package EdiaGroup.template.repository;

import EdiaGroup.template.model.BasicUser;
import EdiaGroup.template.model.TestGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestGroupRepository extends JpaRepository<TestGroup, Long> {
    List<TestGroup> findByBasicUser(BasicUser basicUser);
}