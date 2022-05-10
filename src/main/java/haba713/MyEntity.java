package haba713;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

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
