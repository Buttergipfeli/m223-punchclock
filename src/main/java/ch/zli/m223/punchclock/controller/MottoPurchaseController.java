package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.Motto;
import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.domain.submodels.MottoPurchase;
import ch.zli.m223.punchclock.domain.submodels.MottoPurchaseBody;
import ch.zli.m223.punchclock.service.MottoService;
import ch.zli.m223.punchclock.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.security.Principal;

/**
 * @author Andre Kocher
 * @project punchclock
 * @package ch.zli.m223.punchclock.controller
 * @date 14.07.2022
 */

@RestController
@RequestMapping("/mottopurchases")
public class MottoPurchaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private MottoService mottoService;

    @PostMapping
    @Transactional
    public ResponseEntity<?> purchaseMotto(@Valid @RequestBody MottoPurchaseBody mottoPurchaseBody, Principal principal) {
        User buyer = userService.findByUsername(principal.getName());
        Motto motto = mottoService.findById(mottoPurchaseBody.getMottoId());
        if (motto == null) {
            return new ResponseEntity("Motto not found", HttpStatus.NOT_FOUND);
        }
        if (buyer.getWallet() < 1 || buyer.getWallet() < motto.getPrice()) {
            return new ResponseEntity("Motto is more expensive as your amount in your wallet", HttpStatus.PRECONDITION_FAILED);
        }
        User seller = motto.getOwnerfk();
        seller.setWallet(seller.getWallet() + motto.getPrice());
        userService.updateUser(seller);
        MottoPurchase mottoPurchase = mottoService.purchaseMotto(buyer.getWallet(), motto);
        buyer.setWallet(mottoPurchase.getWallet());
        User updatedUser = userService.updateUser(buyer);
        mottoPurchase.setMotto(mottoService.updatePurchasedMotto(motto, updatedUser));
        return new ResponseEntity(mottoPurchase, HttpStatus.OK);
    }

}
