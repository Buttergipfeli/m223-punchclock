package ch.zli.m223.punchclock.domain.submodels;

/**
 * @author Andre Kocher
 * @project punchclock
 * @package ch.zli.m223.punchclock.domain.submodels
 * @date 14.07.2022
 */
public class MottoPurchaseBody {

    private Long mottoId;

    public Long getMottoId() {
        return mottoId;
    }

    public void setMottoId(Long mottoId) {
        this.mottoId = mottoId;
    }

    @Override
    public String toString() {
        return "MottoPurchaseBody{" +
                "mottoId=" + mottoId +
                '}';
    }
}
