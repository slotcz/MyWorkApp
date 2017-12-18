package cz.o2.tvs.db;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({ @NamedQuery(name = "Category.findAll", query = "select o from Category o") })
@Table(name = "\"category\"")
public class Category implements Serializable {
    private static final long serialVersionUID = 8752506755431239460L;
    @Id
    @Column(nullable = false)
    private int id;
    private String type;

    public Category() {
    }

    public Category(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
