package spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.model.Student;
import spring.repo.StudentRepository;

import javax.annotation.PostConstruct;
import java.sql.Struct;
import java.util.List;

/*
https://dzone.com/articles/the-ultimate-guide-on-db-generated-ids-in-jpa-enti
 */

@Component
public class SayService {
    @Autowired
    private StudentRepository repo;

    private void init(){
        Student a = new Student("alice", 20);
        repo.save(a);
        System.out.println("save a!");

        Student b = new Student("bob", 18);
        repo.save(b);
        System.out.println("save b!");
    }


    @PostConstruct
    public void say(){
        List<Student> students = repo.findAllByName("alice");
        System.out.println(students);

        Student alice = students.get(0);
        alice.setAge(65);
        repo.save(alice);

        students = repo.findAllByName("alice");
        System.out.println(students);

    }
}
