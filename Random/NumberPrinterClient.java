package DPBootcamp.Random;

public class NumberPrinterClient {
    public static void main(String[] args){
        for(int i = 1; i <= 100; i++){
            NumberPrinter np = new NumberPrinter(i);
            Thread t = new Thread(np);
            t.start();
        }
    }
}
