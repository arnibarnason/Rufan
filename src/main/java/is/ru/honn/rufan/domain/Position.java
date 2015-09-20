package is.ru.honn.rufan.domain;

/**
 * Created by arnib on 20/09/15.
 */
public class Position
{
    private int positionId;
    private String name;
    private String abbreviation;
    private int sequence;

    public Position(int positionId, String name, String abbreviation, int sequence)
    {
        this.positionId = positionId;
        this.name = name;
        this.abbreviation = abbreviation;
        this.sequence = sequence;
    }

    public int getPositionId()
    {
        return positionId;
    }

    public void setPositionId(int positionId)
    {
        this.positionId = positionId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAbbreviation()
    {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation)
    {
        this.abbreviation = abbreviation;
    }

    public int getSequence()
    {
        return sequence;
    }

    public void setSequence(int sequence)
    {
        this.sequence = sequence;
    }
}
