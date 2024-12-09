import java.util.LinkedList;
import java.util.Queue;

class CounterEmptyException extends Exception {
    public CounterEmptyException(String message) {
        super(message);
    }
}

class CoffeeCounter {
    private final Queue<String> counter = new LinkedList<>();
    private final int capacity = 3;

    public synchronized void addCoffee(String coffee) throws InterruptedException {
        while (counter.size() == capacity) {
            System.out.println("Counter is full. Barista is waiting.");
            wait();
        }
        counter.add(coffee);
        System.out.println(coffee + " added. Counter: " + counter.size());
        notifyAll();
    }

    public synchronized String takeCoffee() throws InterruptedException, CounterEmptyException {
        while (counter.isEmpty()) {
            System.out.println("Counter is empty. Waiting for coffee.");
            wait();
        }
        String coffee = counter.poll();
        System.out.println(coffee + " picked up. Counter: " + counter.size());
        notifyAll();
        return coffee;
    }
}

class Barista implements Runnable {
    private final CoffeeCounter counter;
    private final int coffeesToPrepare;
    private final String name;

    public Barista(CoffeeCounter counter, int coffeesToPrepare, String name) {
        this.counter = counter;
        this.coffeesToPrepare = coffeesToPrepare;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < coffeesToPrepare; i++) {
                counter.addCoffee(name + " prepared coffee");
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Customer implements Runnable {
    private final CoffeeCounter counter;
    private final int coffeesToPick;
    private final String name;

    public Customer(CoffeeCounter counter, int coffeesToPick, String name) {
        this.counter = counter;
        this.coffeesToPick = coffeesToPick;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < coffeesToPick; i++) {
                counter.takeCoffee();
                Thread.sleep(300);
            }
        } catch (InterruptedException | CounterEmptyException e) {
            System.out.println(name + " encountered an issue: " + e.getMessage());
        }
    }
}

class CoffeeReviewer implements Runnable {
    private final CoffeeCounter counter;

    public CoffeeReviewer(CoffeeCounter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        try {
            String coffee = counter.takeCoffee();
            System.out.println("Reviewer sampled: " + coffee);
        } catch (InterruptedException | CounterEmptyException e) {
            System.out.println("Reviewer encountered an issue: " + e.getMessage());
        }
    }
}

public class CoffeeShopSimulation {
    public static void main(String[] args) {
        CoffeeCounter counter = new CoffeeCounter();

        Thread barista1 = new Thread(new Barista(counter, 2, "Barista 1"));
        Thread barista2 = new Thread(new Barista(counter, 3, "Barista 2"));
        
        Thread customer1 = new Thread(new Customer(counter, 1, "Customer 1"));
        Thread customer2 = new Thread(new Customer(counter, 2, "Customer 2"));
        Thread customer3 = new Thread(new Customer(counter, 1, "Customer 3"));

        Thread reviewer = new Thread(new CoffeeReviewer(counter));

        barista1.start();
        barista2.start();

        customer1.start();
        customer2.start();
        customer3.start();

        reviewer.start();
    }
}
