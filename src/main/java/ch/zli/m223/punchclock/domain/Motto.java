package ch.zli.m223.punchclock.domain;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author Andre Kocher
 * @project punchclock
 * @package ch.zli.m223.punchclock.domain
 * @date 13.07.2022
 */

@Entity
public class Motto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "categoryfk")
    private Category categoryfk;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "ownerfk")
    private User ownerfk;

    @Column(nullable = false)
    private String motto;

    @Column(nullable = false)
    @Min(1)
    private int price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategoryfk() {
        return categoryfk;
    }

    public void setCategoryfk(Category categoryfk) {
        this.categoryfk = categoryfk;
    }

    public User getOwnerfk() {
        return ownerfk;
    }

    public void setOwnerfk(User ownerfk) {
        this.ownerfk = ownerfk;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Motto{" +
                "id=" + id +
                ", categoryfk=" + categoryfk +
                ", ownerfk=" + ownerfk +
                ", motto='" + motto + '\'' +
                ", price=" + price +
                '}';
    }
}
