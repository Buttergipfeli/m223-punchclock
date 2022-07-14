package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.Motto;
import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.domain.submodels.MottoPurchase;
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

   public List<Motto> getAllMottosByUser(User user) {
      return mottoRepository.findAllByOwnerfk(user).orElse(null);
   }

   public Motto createMotto(Motto motto) {
      return mottoRepository.save(motto);
   }

   public Motto findById(Long id) {
      return mottoRepository.findById(id).orElse(null);
   }

   public MottoPurchase purchaseMotto(int wallet, Motto motto) {
      MottoPurchase mottoPurchase = new MottoPurchase();
      wallet = wallet - motto.getPrice();
      mottoPurchase.setWallet(wallet);
      mottoPurchase.setMotto(motto);
      return mottoPurchase;
   }

   public Motto updatePurchasedMotto(Motto motto, User owner) {
      motto.setOwnerfk(owner);
      return mottoRepository.save(motto);
   }

   public void deleteAllCertainMottos(List<Motto> mottos) {
      mottoRepository.deleteAll(mottos);
   }

}
