package com.kblaney.nhlpaphotodownloader;

class PlayerListInputLineParserImpl implements PlayerListInputLineParser
{
  @Override
  public Player parse(final String playerListInputLine)
  {
    final String fieldSeparator = ",";
    final String[] fields = playerListInputLine.split(fieldSeparator);
    final int minNumFields = 2;
    if (fields.length < minNumFields)
    {
      throw new IllegalArgumentException("Player list input line contains too few fields: " + playerListInputLine);
    }
    return getPlayer(fields);
  }

  private Player getPlayer(final String[] fields)
  {
    final int firstNameFieldNum = 0;
    final int lastNumFieldNum = 1;
    return new Player(fields[firstNameFieldNum], fields[lastNumFieldNum]);
  }
}
