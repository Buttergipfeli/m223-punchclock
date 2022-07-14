package ch.zli.m223.punchclock.domain.submodels;

import ch.zli.m223.punchclock.domain.Motto;

/**
 * @author Andre Kocher
 * @project punchclock
 * @package ch.zli.m223.punchclock.domain.submodels
 * @date 14.07.2022
 */
public class MottoPurchase {

    private int wallet;
    private Motto motto;

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public Motto getMotto() {
        return motto;
    }

    public void setMotto(Motto motto) {
        this.motto = motto;
    }

    @Override
    public String toString() {
        return "MottoPurchase{" +
                "wallet=" + wallet +
                ", motto=" + motto +
                '}';
    }
}
