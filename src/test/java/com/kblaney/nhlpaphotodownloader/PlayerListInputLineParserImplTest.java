package com.kblaney.nhlpaphotodownloader;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public final class PlayerListInputLineParserImplTest
{
  private PlayerListInputLineParser parser;

  @Before
  public void setUp()
  {
    parser = new PlayerListInputLineParserImpl();
  }

  @Test(expected = NullPointerException.class)
  public void parse_nullLine()
  {
    parser.parse(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void parse_emptyLine()
  {
    parser.parse("");
  }

  @Test(expected = IllegalArgumentException.class)
  public void parse_tooFewFields()
  {
    parser.parse("Wayne");
  }

  @Test
  public void parse_tooManyFields()
  {
    assertEquals(new Player("Wayne", "Gretzky"), parser.parse("Wayne,Gretzky,EDM,F"));
  }

  @Test
  public void parse_exactlyRightNumFields()
  {
    assertEquals(new Player("Wayne", "Gretzky"), parser.parse("Wayne,Gretzky"));
  }
}
