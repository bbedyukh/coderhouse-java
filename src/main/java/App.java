public class App {
    public static void main(String[] arg) {
        float pi = 3.1416f;
        int wholeNumber = (int) pi;
        String string = String.format("Número sin decimales <%s>", wholeNumber);
        System.out.println(string);
    }
}
