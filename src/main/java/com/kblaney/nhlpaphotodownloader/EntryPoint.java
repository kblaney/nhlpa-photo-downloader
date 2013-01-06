package com.kblaney.nhlpaphotodownloader;

import java.io.FileReader;
import java.io.Reader;
import java.util.List;

public final class EntryPoint
{
  private static final String INPUT_FILE_SPEC = "C:/location/of/player-list.csv.txt";
  private static final PlayerPhotoDownloader PLAYER_PHOTO_DOWNLOADER = new PlayerPhotoDownloaderImpl(
        "C:/location/of/output/folder/in/which/to/put/photos");

  public static void main(final String[] args) throws Exception
  {
    final Reader inputReader = new FileReader(INPUT_FILE_SPEC);
    try
    {
      final List<Player> players = new PlayersSupplier(inputReader).get();
      for (final Player p : players)
      {
        downloadPlayerPhoto(p);
      }
    }
    finally
    {
      inputReader.close();
    }
  }

  private static void downloadPlayerPhoto(final Player player)
  {
    PLAYER_PHOTO_DOWNLOADER.download(player);
  }
}
