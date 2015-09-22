package is.ru.honn.rufan.observers;

import is.ru.honn.rufan.reader.ReaderException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
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
     * @param observerType String containing name of observer type to get
     * @return type of Observer
     */
    public Observer getObserver(String observerType) throws ReaderException {
        Observer observer;
        ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:observer.xml");
        try
        {
            observer = (Observer) ctx.getBean(observerType);
            return observer;
        } catch(NoSuchBeanDefinitionException e)
        {
            throw new ReaderException();
        }
    }
}
