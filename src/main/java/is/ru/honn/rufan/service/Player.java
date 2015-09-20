package is.ru.honn.rufan.service;

import is.ru.honn.rufan.domain.Country;

import javax.swing.text.Position;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by arnib on 20/09/15.
 */
public class Player
{
    protected int playerId;
    protected String firstName;
    protected String lastName;
    protected int height;
    protected int weight;
    protected Date birthDate;
    protected Country nationality;
    protected int teamId;
    protected List<Position> positions = new ArrayList<Position>();

}
