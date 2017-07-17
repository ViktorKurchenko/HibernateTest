package db.demo.security;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "GROUPS")
public class Group implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 20, unique = true)
    private String name;

    @Column(length = 100)
    private String description;


    public Group(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}
