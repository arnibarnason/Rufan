package is.ru.honn.rufan.reader;

import is.ru.honn.rufan.domain.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.util.*;

public class PlayerReader
{
  public Object parse(String content)
  {
    // Root object
    JSONObject jsonObject = (JSONObject) JSONValue.parse(content);

    // Get apiResults, this is an array so get the first (and only) item
    JSONArray apiResults = (JSONArray) jsonObject.get("apiResults");
    JSONObject jTmp = (JSONObject) apiResults.get(0);

    // League
    League league = new League();
    JSONObject jLeague = (JSONObject) jTmp.get("league");

    // Get Players
    JSONArray jPlayers = (JSONArray) jLeague.get("players");
    JSONObject jPlayer;
    List<Player> players = new ArrayList<Player>();
    Player player;
    for (int i = 0; i < jPlayers.size(); i++)
    {
      player = new Player();
      jPlayer = (JSONObject) jPlayers.get(i);
      player.setPlayerId(getInt(jPlayer, "playerId"));
      player.setFirstName((String) jPlayer.get("firstName"));
      player.setLastName((String) jPlayer.get("lastName"));
      jTmp = (JSONObject) jPlayer.get("height");
      player.setHeight(getInt(jTmp, "centimeters"));
      jTmp = (JSONObject) jPlayer.get("weight");
      player.setWeight(getInt(jTmp, "kilograms"));
      jTmp = (JSONObject) jPlayer.get("birth");
      jTmp = (JSONObject) jTmp.get("birthDate");
      player.setBirthDate(newDate(getInt(jTmp, "year"), getInt(jTmp, "month"), getInt(jTmp, "date")));

      jTmp = (JSONObject) jPlayer.get("team");
      player.setTeamId(getInt(jTmp, "teamId"));

      jTmp = (JSONObject) jPlayer.get("nationality");
      Country country = new Country();
      country.setCountryId(getInt(jTmp, "countryId"));
      country.setName((String) jTmp.get("name"));
      country.setAbbreviation((String) jTmp.get("abbreviation"));
      player.setNationality(country);

      Position position;
      JSONArray jPositions = (JSONArray) jPlayer.get("positions");
      Iterator it = jPositions.iterator();
      while(it.hasNext())
      {
        jTmp = (JSONObject)it.next();
        position = new Position(getInt(jTmp, "positionId"),
        (String)jTmp.get("name"),
        (String)jTmp.get("abbreviation"),
            getInt(jTmp, "sequence"));
        player.addPosition(position);
      }

      players.add(player);
      readHandler.read(i, player);
    }

    return players;
  }

  protected int getInt(JSONObject jParent, String name)
  {
    if(jParent == null)
      return 0;
    Long value = (Long)jParent.get(name);
    if(value == null)
      return 0;
    return value.intValue();
  }

  protected Date newDate(int year, int month, int date)
  {
    Calendar cal = new GregorianCalendar();
    cal.set(year, month, date);
    return cal.getTime();
  }



}
