package unsafe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class A extends B{
    public String name;
    private int age;
    public Address address;

    public A(String name, int age, Address address, int grade, Address pAdd){
        super(grade, pAdd);
        this.name = name;
        this.age = age;
        this.address = address;
    }
}
