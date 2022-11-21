package reflect;

public class Student<S extends Animal> {
    private String name;
    private int age;

    private S pet;

    public S getPet(){
        return pet;
    }

    public void setPet(S s){
        pet = s;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String say(String word) {
        return "hello " + word;
    }

    public <T> T find(T t) {
        return t;
    }
}
