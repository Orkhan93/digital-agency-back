package digitalhands.az.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@NamedQuery(name = "Teacher.getAllTeachers",
        query = "select new digitalhands.az.wrapper.TeacherWrapper" +
                "(t.id,t.nameAndSurname,t.about,t.imageData,t.experienceTeacher.id)from Teacher t")
@Entity
@Setter
@Getter
@Table(name = "teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameAndSurname;
    private String about;

    @Lob
    @Column(name = "image_data")
    private byte[] imageData;

    @ManyToOne
    @JoinColumn(name = "experience_id")
    @JsonIgnore
    private Experience experienceTeacher;

}