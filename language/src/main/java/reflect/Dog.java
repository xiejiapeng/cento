package reflect;

public class Dog extends Animal{
    int lastWord;
    public Dog(String name) {
        super(name);
    }

    @Override
    public void bark() {
        System.out.println("wow");
    }

    public void barkAll(Integer word, Integer empty) {
        lastWord = word;
    }
}
