package is.ru.honn.rufan.domain;

/**
 * POJO for Venue information, getters and setters for attributes.
 */
public class Venue
{
  private int venueId;
  private String name;
  private String city;

  public int getVenueId()
  {
    return venueId;
  }

  public void setVenueId(int venueId)
  {
    this.venueId = venueId;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getCity()
  {
    return city;
  }

  public void setCity(String city)
  {
    this.city = city;
  }
}
