package ozturk.assignment.assignment.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ozturk.assignment.assignment.model.EnumRole;
import ozturk.assignment.assignment.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(EnumRole name);
}