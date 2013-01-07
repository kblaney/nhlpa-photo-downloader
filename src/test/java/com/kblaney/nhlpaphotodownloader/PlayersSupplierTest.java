package com.kblaney.nhlpaphotodownloader;

import static org.junit.Assert.*;
import com.google.common.base.Supplier;
import com.google.common.collect.Lists;
import java.io.StringReader;
import java.util.List;
import org.junit.Test;

public final class PlayersSupplierTest
{
  private Supplier<List<Player>> playersSupplier;

  @Test
  public void get_emptyInput()
  {
    playersSupplier = new PlayersSupplier(new StringReader(""));
    assertTrue(playersSupplier.get().isEmpty());
  }

  @Test
  public void get_oneLineOfInput()
  {
    playersSupplier = new PlayersSupplier(new StringReader("Wayne,Gretzky"));
    assertEquals(Lists.newArrayList(new Player("Wayne", "Gretzky")), playersSupplier.get());
  }

  @Test
  public void get_twoLinesOfInput()
  {
    playersSupplier = new PlayersSupplier(new StringReader("Wayne,Gretzky\nJari,Kurri"));
    assertEquals(Lists.newArrayList(new Player("Wayne", "Gretzky"), new Player("Jari", "Kurri")), playersSupplier.get());
  }

  @Test
  public void get_threeLinesOfInput()
  {
    playersSupplier = new PlayersSupplier(new StringReader("Wayne,Gretzky\nJari,Kurri\nMarty,McSorley"));
    assertEquals(Lists.newArrayList(new Player("Wayne", "Gretzky"), new Player("Jari", "Kurri"), new Player("Marty", "McSorley")), playersSupplier.get());
  }
}
