package ch.zli.m223.punchclock.repository;

import ch.zli.m223.punchclock.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Andre Kocher
 * @project punchclock
 * @package ch.zli.m223.punchclock.repository
 * @date 13.07.2022
 */

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRole(String role);

}
