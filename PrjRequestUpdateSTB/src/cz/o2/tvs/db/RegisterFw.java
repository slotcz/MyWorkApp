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
@NamedQueries({ @NamedQuery(name = "RegisterFw.findAll", query = "select o from RegisterFw o"),
                // select *, MAX(DATE_FW) from ng_stb_boot.register_fw  group by CATEGORY;
                    @NamedQuery(name = "RegisterFw.findByKey", query = "select o from RegisterFw o"),
                @NamedQuery(name = "RegisterFw.findLastVersion", query = "select o, MAX(r.dateFw)  from RegisterFw o, RegisterFw r  group by r.category")})
@Table(name = "\"register_fw\"")
public class RegisterFw implements Serializable {
    private static final long serialVersionUID = 2947882263802952609L;
    @Column(name = "CATEGORY")
    private int category;
    @Column(name = "CODE_PATCH")
    private String codePatch;
    @Column(name = "DATE_FW")
    private Timestamp dateFw;
    @Id
    @Column(nullable = false)
    private int id;
    @Column(name = "IMG_TYPE")
    private int imgType;
    @Column(name = "MANUAL_PATH")
    private String manualPath;
    private int model;
    private String version;
    
    private transient Timestamp dateFwMax;

    public RegisterFw() {
    }

    public RegisterFw(int category, String codePatch, Timestamp dateFw, int id, int imgType, String manualPath, int model, String version) {
        this.category = category;
        this.codePatch = codePatch;
        this.dateFw = dateFw;
        this.id = id;
        this.imgType = imgType;
        this.manualPath = manualPath;
        this.model = model;
        this.version = version;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getCodePatch() {
        return codePatch;
    }

    public void setCodePatch(String codePatch) {
        this.codePatch = codePatch;
    }

    public Timestamp getDateFw() {
        return dateFw;
    }

    public void setDateFw(Timestamp dateFw) {
        this.dateFw = dateFw;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImgType() {
        return imgType;
    }

    public void setImgType(int imgType) {
        this.imgType = imgType;
    }

    public String getManualPath() {
        return manualPath;
    }

    public void setManualPath(String manualPath) {
        this.manualPath = manualPath;
    }

    public int getModel() {
        return model;
    }

    public void setModel(int model) {
        this.model = model;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setDateFwMax(Timestamp dateFwMax) {
        this.dateFwMax = dateFwMax;
    }

    public Timestamp getDateFwMax() {
        return dateFwMax;
    }

    @Override
    public String toString() {      
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getName() + "@" + Integer.toHexString(hashCode()));
        buffer.append('[');
        buffer.append("id=");
        buffer.append(getId());
        buffer.append(',');
        buffer.append("codePatch=");
        buffer.append(getCodePatch());
        buffer.append(',');
        buffer.append("Category=");
        buffer.append(getCategory());
        buffer.append(',');
        buffer.append("Image=");
        buffer.append(getImgType());
        buffer.append(',');
        buffer.append("Version=");
        buffer.append(getVersion());
        buffer.append(',');
        buffer.append("dateFw=");
        buffer.append(getDateFw());
        buffer.append(',');
        buffer.append(']');
        return buffer.toString();
    }

}
