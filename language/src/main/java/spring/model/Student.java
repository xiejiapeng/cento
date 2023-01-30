package spring.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "students", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class Student {
    public Student(String name, int age){
        this.name = name;
        this.age = age;
    }

    public Student(){}


    @Id
    @SequenceGenerator(name = "pet_seq",
            sequenceName = "pet_sequence",
            initialValue = 1, allocationSize = 20)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pet_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;
}
