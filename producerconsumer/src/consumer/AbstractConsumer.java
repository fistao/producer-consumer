
package consumer;

/**
 * @author lichao 2018/6/4 - 下午3:18.
 */
public abstract class AbstractConsumer implements Consumer, Runnable{

    @Override
    public void run() {
        while (true) {
            try {
                consume();
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
