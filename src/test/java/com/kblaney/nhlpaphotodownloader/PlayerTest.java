package com.kblaney.nhlpaphotodownloader;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public final class PlayerTest
{
  private String firstName;
  private String lastName;
  private Player player;

  @Before
  public void setUp()
  {
    firstName = "Wayne";
    lastName = "Gretzky";
    player = new Player(firstName, lastName);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructor_nullFirstName()
  {
    new Player(null, lastName);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructor_nullLastName()
  {
    new Player(firstName, null);
  }

  @Test
  public void getFirstName()
  {
    assertEquals(firstName, player.getFirstName());
  }

  @Test
  public void getLastName()
  {
    assertEquals(lastName, player.getLastName());
  }

  @Test
  public void getFullName()
  {
    assertEquals(firstName + " " + lastName, player.getFullName());
  }

  @Test
  public void equals_null()
  {
    assertNotEquals(player, null);
  }

  @Test
  public void equals_wrongType()
  {
    assertNotEquals(player, "This string is definitely not a Player object");
  }

  @Test
  public void equals_differentFirstName()
  {
    final String differentFirstName = firstName + "A";
    final Player unequalPlayer = new Player(differentFirstName, lastName);
    assertNotEquals(player, unequalPlayer);
  }

  @Test
  public void equals_differentLastName()
  {
    final String differentLastName = lastName + "A";
    final Player unequalPlayer = new Player(firstName, differentLastName);
    assertNotEquals(player, unequalPlayer);
  }

  @Test
  public void equals_equalInstance()
  {
    assertEquals(player, new Player(firstName, lastName));
  }

  @Test
  public void hashCode_equalInstance()
  {
    final Player equalInstance = new Player(firstName, lastName);
    assertEquals(player.hashCode(), equalInstance.hashCode());
  }

  @Test
  public void hashCode_repeatedInvocationSameInstance()
  {
    assertEquals(player.hashCode(), player.hashCode());
  }
}
