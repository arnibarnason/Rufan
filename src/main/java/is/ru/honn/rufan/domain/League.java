package is.ru.honn.rufan.domain;

public class League
{
  private int leagueId;
  private String name;
  private String abbreviation;
  private String displayName;
  protected Season season;

  public int getLeagueId()
  {
    return leagueId;
  }

  public void setLeagueId(int leagueId)
  {
    this.leagueId = leagueId;
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

  public String getDisplayName()
  {
    return displayName;
  }

  public void setDisplayName(String displayName)
  {
    this.displayName = displayName;
  }

  public Season getSeason()
  {
    return season;
  }

  public void setSeason(Season season)
  {
    this.season = season;
  }
}
