package unsafe;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class B {
    public int grade;

    public Address pAdd;

    public B(int grade, Address pAdd) {
        this.grade = grade;
        this.pAdd = pAdd;
    }
}
