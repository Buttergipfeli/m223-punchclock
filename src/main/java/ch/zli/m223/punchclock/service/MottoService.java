package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.Motto;
import ch.zli.m223.punchclock.repository.MottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Andre Kocher
 * @project punchclock
 * @package ch.zli.m223.punchclock.service
 * @date 13.07.2022
 */

@Service
public class MottoService {

   @Autowired
   private MottoRepository mottoRepository;

   public List<Motto> getAllMottos() {
      return mottoRepository.findAll();
   }

   public Motto createMotto(Motto motto) {
      return mottoRepository.save(motto);
   }

}
