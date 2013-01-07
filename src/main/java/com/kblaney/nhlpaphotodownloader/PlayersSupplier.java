package com.kblaney.nhlpaphotodownloader;

import com.google.common.base.Supplier;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import org.apache.commons.io.IOUtils;

class PlayersSupplier implements Supplier<List<Player>>
{
  private final Reader inputReader;
  private final PlayerListInputLineParser inputLineParser = new PlayerListInputLineParserImpl();

  public PlayersSupplier(final Reader inputReader)
  {
    this.inputReader = inputReader;
  }

  @Override
  public List<Player> get()
  {
    try
    {
      final List<String> inputLines = IOUtils.readLines(inputReader);
      final List<Player> players = Lists.newArrayList();
      for (final String line : inputLines)
      {
        final Player player = parseLine(line);
        players.add(player);
      }
      return players;
    }
    catch (final IOException e)
    {
      throw new IllegalStateException("Can't read players", e);
    }
  }

  private Player parseLine(final String inputLine)
  {
    return inputLineParser.parse(inputLine);
  }
}
