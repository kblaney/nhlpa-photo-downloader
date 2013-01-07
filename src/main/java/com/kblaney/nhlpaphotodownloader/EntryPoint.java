package com.kblaney.nhlpaphotodownloader;

import com.google.common.base.Function;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;

public final class EntryPoint
{
  private static final String INPUT_FILE_SPEC = "C:/Temp/nhlpa-photos-download-list/players.csv.txt";
  private static final String OUTPUT_FOLDER_SPEC = "C:/temp/nhlpa-photos";
  private static final PlayerPhotoDownloader PLAYER_PHOTO_DOWNLOADER = new PlayerPhotoDownloaderImpl(OUTPUT_FOLDER_SPEC);
  private static final Function<Player, File> PLAYER_TO_OUTPUT_FILE_FUNCTION = new PlayerToOutputFileFunction(
        OUTPUT_FOLDER_SPEC);

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
      System.out.println("Done downloading");
    }
    finally
    {
      inputReader.close();
    }
  }

  private static void downloadPlayerPhoto(final Player player)
  {
    final File outputFile = PLAYER_TO_OUTPUT_FILE_FUNCTION.apply(player);
    if (outputFile.exists())
    {
      System.out.println("Skipping download for " + player.getFullName());
    }
    else
    {
      System.out.println("Downloading photo for " + player.getFullName());
      PLAYER_PHOTO_DOWNLOADER.download(player);
    }
  }
}
