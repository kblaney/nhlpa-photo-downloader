package com.kblaney.nhlpaphotodownloader;

import org.apache.commons.io.Charsets;
import com.google.common.base.Function;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

public final class EntryPoint
{
  private static final String INPUT_FILE_SPEC = "C:/data/hockeypool/players-2013-Playoffs.csv.txt";
  private static final String OUTPUT_FOLDER_SPEC = "C:/data/hockeypool/nhlpa-photos";
  private static final PlayerPhotoDownloader PLAYER_PHOTO_DOWNLOADER = new PlayerPhotoDownloaderImpl(OUTPUT_FOLDER_SPEC);
  private static final Function<Player, File> PLAYER_TO_OUTPUT_FILE_FUNCTION = new PlayerToOutputFileFunction(
        OUTPUT_FOLDER_SPEC);

  private EntryPoint()
  {
  }

  public static void main(final String[] args) throws Exception
  {
    final Reader inputReader = new InputStreamReader(new FileInputStream(INPUT_FILE_SPEC), Charsets.UTF_8);
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
      System.out.println("Photo already downloaded for " + player.getFullName());
    }
    else
    {
      download(player);
    }
  }

  private static void download(final Player player)
  {
    try
    {
      PLAYER_PHOTO_DOWNLOADER.download(player);
      System.out.println("Photo downloaded for " + player.getFullName());
    }
    catch (final NoProfileException e)
    {
      System.out.println("No profile for " + player.getFullName());
    }
    catch (final NoPhotoException e)
    {
      System.out.println("No photo for " + player.getFullName());
    }
  }
}
