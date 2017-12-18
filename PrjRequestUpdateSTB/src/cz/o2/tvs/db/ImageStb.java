package cz.o2.tvs.db;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({ @NamedQuery(name = "ImageStb.findAll", query = "select o from ImageStb o order by o.id") })
@Table(name = "\"image_stb\"")
public class ImageStb implements Serializable {
    private static final long serialVersionUID = 478926846541823818L;
    @Id
    @Column(nullable = false)
    private int id;
    private String name;

    public ImageStb() {
    }

    public ImageStb(int id, String name) {
        this.id = id;
        this.name = name;
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
