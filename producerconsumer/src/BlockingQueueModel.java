

import consumer.AbstractConsumer;
import producer.AbstractProducer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lichao 2018/6/4 - 下午3:40.
 */
public class BlockingQueueModel implements Model{

    private final BlockingQueue<Task> queue;
    private final AtomicInteger atomicInteger = new AtomicInteger();

    public BlockingQueueModel(int cap) {
        this.queue = new LinkedBlockingDeque<Task>(cap);
    }

    @Override
    public Runnable runnableConsumer() {
        return new ConsumerImpl();
    }

    @Override
    public Runnable runnableProducer() {
        return new ProducerImpl();
    }

    private class ConsumerImpl extends AbstractConsumer{
        @Override
        public void consume() throws InterruptedException {
            Task task = queue.take();
            Thread.sleep(500 + (long)(Math.random()*500));
            System.out.println("consume: " + task.number);
        }
    }

    private class ProducerImpl extends AbstractProducer{

        @Override
        public void produce() throws InterruptedException {
            Thread.sleep((long)(Math.random() * 1000));
            Task task = new Task(atomicInteger.getAndIncrement());
            queue.put(task);
            System.out.println("produce: " + task.number);
        }
    }

    public static void main(String[] args) {
        Model model = new BlockingQueueModel(10);
        for (int i = 0; i < 2; i++) {
            new Thread(model.runnableConsumer()).start();
        }
        for (int i =0; i< 2; i++) {
            new Thread(model.runnableProducer()).start();
        }
    }

}
