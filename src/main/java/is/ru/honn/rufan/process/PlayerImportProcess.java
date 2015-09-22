package is.ru.honn.rufan.process;

import is.ru.honn.rufan.domain.Player;
import is.ru.honn.rufan.observers.Observer;
import is.ru.honn.rufan.observers.ObserverFactory;
import is.ru.honn.rufan.reader.ReadHandler;
import is.ru.honn.rufan.reader.Reader;
import is.ru.honn.rufan.reader.ReaderException;
import is.ru.honn.rufan.reader.ReaderFactory;
import is.ru.honn.rufan.service.PlayerServiceStub;
import is.ru.honn.rufan.service.ServiceException;
import is.ruframework.process.RuAbstractProcess;
import org.springframework.context.MessageSource;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created by arnib on 20/09/15.
 */
public class PlayerImportProcess extends RuAbstractProcess implements ReadHandler
{
    Reader reader;
    Logger log = Logger.getLogger(PlayerImportProcess.class.getName());
    private PlayerServiceStub playerService = new PlayerServiceStub();
    private ObserverFactory observerFactory = new ObserverFactory();
    private ArrayList<Observer> observerList = new ArrayList<Observer>();
    MessageSource msg;

    @Override
    public void beforeProcess()
    {
        Observer observer = observerFactory.getObserver("playerObserver");
        //Observer observer1 = observerFactory.getObserver("teamObserver");
        // /PlayerObserver observer = new PlayerObserver();
        //playerService.addObserver(observer);
        observerList.add(observer);
        ReaderFactory factory = new ReaderFactory();
        reader = factory.getReader("PlayerReader");
        msg = factory.getMessageSource("messageSource");
        reader.setReadHandler(this);
        log.info("start process");
    }

    @Override
    public void afterProcess()
    {
        super.afterProcess();
    }

    @Override
    public void startProcess()
    {
        try
        {
            reader.read();
        }
        catch (ReaderException e)
        {
            log.severe("process read error");
            log.severe(e.getMessage());
        }
    }

    public void read(int count, Object object)
    {
        try
        {
            playerService.addPlayer((Player) object);
            notifyObservers(object);
        }
        catch (ServiceException e)
        {
            e.printStackTrace();
        }
    }

    public void notifyObservers(Object object)
    {
        for(Observer o : observerList)
        {
            o.update(object);
        }
    }
}
