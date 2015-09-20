package is.ru.honn.rufan.domain;


import java.util.ArrayList;

public class Season
{
  private int season;
  private String name;
  private boolean isActive;
  private ArrayList<Team> teams = new ArrayList<Team>();

  public int getSeason()
  {
    return season;
  }

  public void setSeason(int season)
  {
    this.season = season;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public boolean isActive()
  {
    return isActive;
  }

  public void setIsActive(boolean isActive)
  {
    this.isActive = isActive;
  }

  public ArrayList<Team> getTeams()
  {
    return teams;
  }

  public void addTeam(Team team)
  {
    teams.add(team);
  }

}
