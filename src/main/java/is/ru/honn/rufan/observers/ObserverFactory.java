package is.ru.honn.rufan.observers;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Factory that gets observer class from observer.xml
 * for adding it to observer list in observable class PlayerImportProcess
 */
public class ObserverFactory
{
    /**
     * Empty constructor
     */
    public ObserverFactory()
    {
    }

    /**
     * Get the type of observer
     * @param observerType String contining name of observer type to get
     * @return type of Observer
     */
    public Observer getObserver(String observerType)
    {
        Observer observer;
        ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:observer.xml");
        observer = (Observer) ctx.getBean(observerType);
        System.out.println(observer.toString());
        return observer;
    }
}
