package cz.o2.tvs.db;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({ @NamedQuery(name = "Model.findAll", query = "select o from Model o") })
@Table(name = "\"model\"")
public class Model implements Serializable {
    private static final long serialVersionUID = -4171983913777466806L;
    private String fabricate;
    @Id
    @Column(nullable = false)
    private int id;
    private String name;

    public Model() {
    }

    public Model(String fabricate, int id, String name) {
        this.fabricate = fabricate;
        this.id = id;
        this.name = name;
    }

    public String getFabricate() {
        return fabricate;
    }

    public void setFabricate(String fabricate) {
        this.fabricate = fabricate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
