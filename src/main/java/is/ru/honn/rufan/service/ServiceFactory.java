package is.ru.honn.rufan.service;

import is.ru.honn.rufan.reader.ReaderException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Factory gets type of service to use from service.xml
 * and returns it.
 */
public class ServiceFactory
{
    /**
     * Empty constructor
     */
    public ServiceFactory()
    {
    }

    /**
     * Gets serviceType from service.xml
     * @param serviceType string containing name of service type
     * @return type of PlayerService
     * @throws ReaderException
     */
    public PlayerService getPlayerService(String serviceType) throws ReaderException
    {
        PlayerService service;
        ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:service.xml");

        try
        {
            service = (PlayerService) ctx.getBean(serviceType);
            return service;
        } catch (NoSuchBeanDefinitionException e)
        {
            throw new ReaderException(e.getMessage());
        }
    }

    /**
     * Gets serviceType from service.xml
     * @param serviceType string containing name of service type
     * @return type of TeamService
     * @throws ReaderException
     */
    public TeamService getTeamService(String serviceType) throws ReaderException
    {
        TeamService service;
        ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:service.xml");
        try
        {
            service = (TeamService) ctx.getBean(serviceType);
            return service;
        } catch (NoSuchBeanDefinitionException e)
        {
            throw new ReaderException(e.getMessage());
        }
    }
}
