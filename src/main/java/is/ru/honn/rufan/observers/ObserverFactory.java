package is.ru.honn.rufan.observers;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by arnib on 22/09/15.
 */
public class ObserverFactory
{
    public ObserverFactory()
    {
    }

    public Observer getObserver(String observerType)
    {
        Observer observer;
        ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:observer.xml");
        observer = (Observer) ctx.getBean(observerType);
        System.out.println(observer.toString());
        return observer;
    }
}
