package whg;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class MyListener implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent e) {
        System.out.println("MyListener onApplicationEvent");
    }
}
