package is.ru.honn.rufan.reader;


import is.ru.honn.rufan.domain.League;
import is.ru.honn.rufan.domain.Season;
import is.ru.honn.rufan.domain.Team;
import is.ru.honn.rufan.domain.Venue;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TeamReader
{
  public Object parse(String content)
  {
    String tmp;

    // Root object
    JSONObject jsonObject = (JSONObject) JSONValue.parse(content);

    // Get apiResults, this is an array so get the first (and only) item
    JSONArray apiResults = (JSONArray)jsonObject.get("apiResults");
    JSONObject jtmp = (JSONObject)apiResults.get(0);

    // League
    League league = new League();
    JSONObject jleague = (JSONObject)jtmp.get("league");
    league.setLeagueId(((Long)jleague.get("leagueId")).intValue());
    league.setName((String) jleague.get("name"));
    league.setAbbreviation((String) jleague.get("abbreviation"));
    league.setDisplayName((String) jleague.get("displayName"));

    // Season
    Season season = new Season();
    JSONObject jseason = (JSONObject)jleague.get("season");
    season.setSeason(((Long) jleague.get("season")).intValue());
    season.setName((String) jseason.get("name"));
    season.setIsActive(((Boolean)jseason.get("isActive")).booleanValue());

    league.setSeason(season);

    // Seasons have conferenes, which is array, so get first item
    JSONArray conferances = (JSONArray)jseason.get("conferences");
    jtmp = (JSONObject)conferances.get(0);

    // Conferences have divisions, which is array, so get first item
    JSONArray divisions = (JSONArray)jtmp.get("divisions");
    jtmp= (JSONObject)divisions.get(0);

    // Teams
    JSONArray teams = (JSONArray)jtmp.get("teams");

    // Iterate through the array, and display teams and venue
    JSONObject jteam, jvenue;

    Team team;
    Venue venue;
    for (int i=0; i < teams.size(); i++)
    {
      team = new Team();
      jteam = (JSONObject)teams.get(i);
      team.setTeamId(((Long) jteam.get("teamId")).intValue());
      team.setLocation((String) jteam.get("location"));
      team.setAbbreviation((String) jteam.get("abbreviation"));
      team.setDisplayName((String) jteam.get("displayName"));
      season.addTeam(team);

      venue = new Venue();
      jvenue = (JSONObject)jteam.get("venue");
      venue.setVenueId(((Long) jvenue.get("venueId")).intValue());
      venue.setName(((String) jvenue.get("name")));
      venue.setCity(((String) jvenue.get("city")));

      team.setVenue(venue);
    }

    return league;
  }


  // Format example 2015-08-21T16:19:30.6967613Z
  protected Date convertDate(String strDate)
  {
    DateFormat format = new SimpleDateFormat("yyyy.MM.dd'T'HH:mm:ss", Locale.ENGLISH);
    Date date = null;

    try
    {
      date = format.parse(strDate);
    }
    catch (ParseException e)
    {
      System.out.println("FAIL");
    }
    return date;
  }

}
