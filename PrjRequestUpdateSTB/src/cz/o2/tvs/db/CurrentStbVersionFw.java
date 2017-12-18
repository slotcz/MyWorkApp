package cz.o2.tvs.db;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({ @NamedQuery(name = "CurrentStbVersionFw.findAll", query = "select o from CurrentStbVersionFw o") })
@Table(name = "\"current_stb_version_fw\"")
public class CurrentStbVersionFw implements Serializable {
    private static final long serialVersionUID = 1465094391100213503L;
    @Id
    @Column(nullable = false)
    private int id;
    private int category;
    private int image;
    private String mac;
    private String version;
    private int id_version;

    public CurrentStbVersionFw() {
    }

    public CurrentStbVersionFw(int category, int id, int image, String mac, String version) {
        this.category = category;
        this.id = id;
        this.image = image;
        this.mac = mac;
        this.version = version;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setId_version(int id_version) {
        this.id_version = id_version;
    }

    public int getId_version() {
        return id_version;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getName() + "@" + Integer.toHexString(hashCode()));
        buffer.append('[');
        buffer.append("id=");
        buffer.append(getId());
        buffer.append(',');
        buffer.append("mac=");
        buffer.append(getMac());
        buffer.append(',');
        buffer.append("Category=");
        buffer.append(getCategory());
        buffer.append(',');
        buffer.append("Image=");
        buffer.append(getImage());
        buffer.append(',');
        buffer.append("Version=");
        buffer.append(getVersion());
        buffer.append(',');
        buffer.append(']');
        return buffer.toString();
    }


}
