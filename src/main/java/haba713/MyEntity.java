package haba713;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "my_entity")
public class MyEntity {

    private Long id;

    @Id
    @SequenceGenerator(name = "id_sequence", sequenceName = "my_id_sequence")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "id_sequence")
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }

}
