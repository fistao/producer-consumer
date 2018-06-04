
package producer;

/**
 * @author lichao 2018/6/4 - 下午3:19.
 */
public abstract class AbstractProducer implements Producer,Runnable{

    @Override
    public void run() {
        while (true) {
            try {
                produce();
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
