package cz.o2.tvs.db;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({ @NamedQuery(name = "History.findAll", query = "select o from History o") })
@Table(name = "\"history\"")
public class History implements Serializable {
    private static final long serialVersionUID = -3342617958941248583L;
    @Column(name = "DATE_UPDATE")
    private Timestamp dateUpdate;
    @Id
    @Column(nullable = false)
    private int id;
    @Column(nullable = false)
    private int stb;
    @Column(name = "VERSION_FW", nullable = false)
    private int versionFw;

    public History() {
    }

    public History(Timestamp dateUpdate, int id, int stb, int versionFw) {
        this.dateUpdate = dateUpdate;
        this.id = id;
        this.stb = stb;
        this.versionFw = versionFw;
    }

    public Timestamp getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Timestamp dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStb() {
        return stb;
    }

    public void setStb(int stb) {
        this.stb = stb;
    }

    public int getVersionFw() {
        return versionFw;
    }

    public void setVersionFw(int versionFw) {
        this.versionFw = versionFw;
    }
}
