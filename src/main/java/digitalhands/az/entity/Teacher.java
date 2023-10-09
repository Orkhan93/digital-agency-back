package digitalhands.az.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@NamedQuery(name = "Teacher.getAllTeachers",
        query = "select new digitalhands.az.wrapper.TeacherWrapper" +
                "(t.name,t.surname,t.about,t.imageData)from Teacher t")
@Entity
@Setter
@Getter
@Table(name = "teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    private String about;

    @Column(name = "image_data")
    private String imageData;

    @ManyToOne
    @JoinColumn(name = "experience_id")
    @JsonIgnore
    private Experience experience;

    @Override
    public String toString() {
        return "Teacher{id=%d, name='%s', surname='%s', about='%s', imageData='%s'}"
                .formatted(id, name, surname, about, imageData);
    }

}