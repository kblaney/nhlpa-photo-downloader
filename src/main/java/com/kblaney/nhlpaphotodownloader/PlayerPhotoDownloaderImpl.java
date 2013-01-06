package com.kblaney.nhlpaphotodownloader;

import com.google.common.base.Function;
import java.io.File;
import java.net.URL;

final class PlayerPhotoDownloaderImpl implements PlayerPhotoDownloader
{
  private final Function<Player, URL> playerToProfileUrlFunction = new PlayerToProfileUrlFunction();
  private final Function<URL, URL> profileUrlToImageUrlFunction = null;
  private final Function<Player, File> playerToOutputFileSpecFunction;
  private final Downloader downloader = null;

  public PlayerPhotoDownloaderImpl(final String outputFolderSpec)
  {
    playerToOutputFileSpecFunction = new PlayerToOutputFileFunction(outputFolderSpec);
  }

  @Override
  public void download(final Player player)
  {
    final URL profileUrl = getProfileUrl(player);
    final URL imageUrl = getImageUrl(profileUrl);
    final File outputFileSpec = getOutputFile(player);
    download(imageUrl, outputFileSpec);
  }

  private URL getProfileUrl(final Player player)
  {
    return playerToProfileUrlFunction.apply(player);
  }

  private URL getImageUrl(final URL profileUrl)
  {
    return profileUrlToImageUrlFunction.apply(profileUrl);
  }

  private File getOutputFile(final Player player)
  {
    return playerToOutputFileSpecFunction.apply(player);
  }

  private void download(final URL url, final File outputFile)
  {
    downloader.download(url, outputFile);
  }
}
