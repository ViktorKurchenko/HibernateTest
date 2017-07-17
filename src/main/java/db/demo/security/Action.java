package db.demo.security;

import javax.persistence.*;

@Entity
@Table(name = "ACTIONS")
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ACTIONS")
    @SequenceGenerator(name = "SEQ_ACTIONS", sequenceName = "SEQ_ACTIONS", allocationSize = 1)
    private Long id;

    @Column(nullable = false, length = 20, unique = true)
    private String name;

    @Column(length = 50)
    private String description;


    public Action(String name, String description) {
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
