package com.kblaney.nhlpaphotodownloader;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;
import org.apache.commons.io.IOUtils;

public final class EntryPoint
{
  public static void main(final String[] args) throws Exception
  {
    final List<String> lines =
          IOUtils.readLines(new FileReader("C:/Data/nhl/nhlpaPlayers2.txt"));
    for (final String line : lines)
    {
      final String[] fields = line.split(",");
      final String playerId = fields[0];
      final String playerFirstName = fields[1];
      final String playerLastName = fields[2];
      System.out.println("Getting photo for " + playerFirstName + " " +
            playerLastName);
      getPhoto(playerFirstName, playerLastName, playerId);
    }
  }

  private static void getPhoto(final String playerFirstName,
        final String playerLastName, final String playerId) throws IOException
  {
    final InputStream inputStream = new URL("http://www.nhlpa.com/" +
          "PlayerImageHandler.ashx?Size=L&PlayerOID=" + playerId).openStream();
    OutputStream outputStream = null;
    try
    {
      outputStream = new FileOutputStream("C:/Data/nhl/nhlpa-photos/" +
            playerFirstName + "_" + playerLastName + ".jpg");
      IOUtils.copy(inputStream, outputStream);
    }
    finally
    {
      IOUtils.closeQuietly(inputStream);
      IOUtils.closeQuietly(outputStream);
    }
  }
}
