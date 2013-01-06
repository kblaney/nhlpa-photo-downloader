package com.kblaney.nhlpaphotodownloader;

import static org.junit.Assert.*;
import com.google.common.base.Function;
import java.net.URL;
import org.junit.Before;
import org.junit.Test;

public final class PlayerToProfileUrlFunctionTest
{
  private Function<Player, URL> function;

  @Before
  public void setUp()
  {
    function = new PlayerToProfileUrlFunction();
  }

  @Test
  public void apply_regularName() throws Exception
  {
    assertEquals(new URL("http://www.nhlpa.com/the-players/profile/matt-beleskey"),
          function.apply(new Player("Matt", "Beleskey")));
  }

  @Test
  public void apply_initialsForFirstName() throws Exception
  {
    assertEquals(new URL("http://www.nhlpa.com/the-players/profile/t.j.-oshie"),
          function.apply(new Player("T.J.", "Oshie")));
  }

  @Test
  public void apply_dashInFirstName() throws Exception
  {
    assertEquals(new URL("http://www.nhlpa.com/the-players/profile/jean-sebastien-giguere"),
          function.apply(new Player("Jean-Sebastien", "Giguere")));
  }

  @Test
  public void apply_spaceInLastName() throws Exception
  {
    assertEquals(new URL("http://www.nhlpa.com/the-players/profile/michael-del-zotto"),
          function.apply(new Player("Michael", "Del Zotto")));
  }
}
