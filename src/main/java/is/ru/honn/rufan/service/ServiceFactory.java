package is.ru.honn.rufan.service;

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
     */
    public PlayerService getPlayerService(String serviceType)
    {
        PlayerService service;
        ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:service.xml");
        service = (PlayerService) ctx.getBean(serviceType);
        System.out.println(service.toString());
        return service;
    }

    public TeamService getTeamService(String serviceType)
    {
        TeamService service;
        ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:service.xml");
        service = (TeamService) ctx.getBean(serviceType);
        System.out.println(service.toString());
        return service;
    }
}
