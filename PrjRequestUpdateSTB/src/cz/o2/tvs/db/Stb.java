package cz.o2.tvs.db;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({ @NamedQuery(name = "Stb.findAll", query = "select o from Stb o") })
@Table(name = "\"stb\"")
public class Stb implements Serializable {
    private static final long serialVersionUID = -605233339189948216L;
    private int category;
    @Id
    @Column(nullable = false)
    private int id;
    private int image;
    private String mac;
    @Column(name = "VERSION_FW")
    private int versionFw;

    public Stb() {
    }

    public Stb(int category, int id, int image, String mac, int versionFw) {
        this.category = category;
        this.id = id;
        this.image = image;
        this.mac = mac;
        this.versionFw = versionFw;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public int getVersionFw() {
        return versionFw;
    }

    public void setVersionFw(int versionFw) {
        this.versionFw = versionFw;
    }
}
