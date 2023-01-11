public class HelloWorld {
    public static class Person {
        String name;
        String joke;
        String info;
        int ans1;
        String ans2;
    }
    public static void main(String[] args) {
        Person Winson = new Person();
        Winson.name = "Winson";
        Winson.joke = "How does the ocean say hello? It waves.";
        Winson.info = "I am a first year student in NUS. I like to run and eat.";
        Winson.ans1 = 3125;
        Winson.ans2 = "the output is argA to the power of argB";

        System.out.println("My name is " + Winson.name + ".\n" + "My favourite joke is " + Winson.joke +
        ".\n" + "A fact about me is that " + Winson.info + ".\n" + "My answer to question 1a is " + Winson.ans1
        + ".\n" + "My answer to question 1b is that " + Winson.ans2 + "."
        );
    }
}
