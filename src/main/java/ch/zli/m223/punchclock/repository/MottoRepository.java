package ch.zli.m223.punchclock.repository;

import ch.zli.m223.punchclock.domain.Motto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Andre Kocher
 * @project punchclock
 * @package ch.zli.m223.punchclock.repository
 * @date 13.07.2022
 */

@Repository
public interface MottoRepository extends JpaRepository<Motto, Long> {
}
