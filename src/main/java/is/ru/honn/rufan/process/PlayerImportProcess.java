package is.ru.honn.rufan.process;

import is.ru.honn.rufan.domain.Player;
import is.ru.honn.rufan.observers.Observer;
import is.ru.honn.rufan.observers.ObserverFactory;
import is.ru.honn.rufan.reader.ReadHandler;
import is.ru.honn.rufan.reader.Reader;
import is.ru.honn.rufan.reader.ReaderException;
import is.ru.honn.rufan.reader.ReaderFactory;
import is.ru.honn.rufan.service.PlayerService;
import is.ru.honn.rufan.service.ServiceException;
import is.ru.honn.rufan.service.ServiceFactory;
import is.ruframework.process.RuAbstractProcess;
import org.springframework.context.MessageSource;

import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Logger;

/**
 * PlayerImportProcess extends RuAbstractProcess which contains the
 * main function. Class implements ReadHandler's read function which
 * accepts objects created by a reader in it's parse function.
 */
public class PlayerImportProcess extends RuAbstractProcess implements ReadHandler
{
    Reader reader;
    Logger log = Logger.getLogger(PlayerImportProcess.class.getName());
    private PlayerService playerService;
    private ArrayList<Observer> observerList = new ArrayList<Observer>();
    MessageSource msg;
    Locale languageTag = Locale.forLanguageTag("is");

    /**
     * Called before process starts and sets up data for process
     */
    @Override
    public void beforeProcess()
    {
        ObserverFactory observerFactory = new ObserverFactory();
        ServiceFactory serviceFactory = new ServiceFactory();
        ReaderFactory readerFactory = new ReaderFactory();

        // Get type of service to use
        try {
            playerService = serviceFactory.getPlayerService("PlayerStub");
        } catch (ReaderException e) {
            log.info(e.getMessage());
        }

        // Get the observer to add to observer list
        Observer observer = null;
        try {
            observer = observerFactory.getObserver("playerObserver");
        } catch (ReaderException e) {
            log.info(e.getMessage());
        }

        observerList.add(observer);

        try {
            reader = readerFactory.getReader("PlayerReader");
        } catch (ReaderException e) {
            log.info(e.getMessage());
        }
        reader.setURI(getProcessContext().getImportURL());
        try {
            msg = readerFactory.getMessageSource("messageSource");
        } catch (ReaderException e) {
            log.info(e.getMessage());
        }

        reader.setReadHandler(this);

        log.info(msg.getMessage("processbefore", new Object[]{this.getClass().getName()}, languageTag));
    }

    /**
     * Called after process has finished
     */
    @Override
    public void afterProcess()
    {
        log.info(msg.getMessage("processstartdone", new Object[] {this.getClass().getName()}, languageTag));
    }

    /**
     * Starts the process
     */
    @Override
    public void startProcess()
    {
        try
        {
            log.info("start process");
            reader.read();
        }
        catch (ReaderException e)
        {
            log.severe("process read error");
            log.severe(e.getMessage());
        }
    }

    /**
     * Implemented from ReadHandler to manage
     * objects created in parse function of reader.
     * Notifies observers of each object added.
     * @param count Lines read
     * @param object The object to add
     */
    public void read(int count, Object object)
    {
        try
        {
            playerService.addPlayer((Player) object);
            notifyObservers(object);
        }
        catch (ServiceException e)
        {
            log.info("Error adding player" + e.getMessage());
        }
    }

    /**
     * Send notification to all observers in the observer list
     * @param object Object created
     */
    public void notifyObservers(Object object)
    {
        for(Observer o : observerList)
        {
            o.update(object);
        }
    }

    /**
     * Helper for testing if the reader reads the file correctly,
     * only uset for unit testing.
     * @param service The service to set.
     */
    public void setService(PlayerService service)
    {
        this.playerService = service;
    }

    /**
     * Adds an observer to the list of observers
     * @param o The observer to be added to the list
     */
    public void addObserver(Observer o)
    {
        observerList.add(o);
    }

    /**
     * Removes an observer from the list of observers.
     * @param o The observer to be removed
     */
    public void removeObserver(Object o)
    {
        observerList.remove(o);
    }
}
