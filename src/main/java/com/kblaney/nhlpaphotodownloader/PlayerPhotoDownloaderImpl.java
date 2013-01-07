package com.kblaney.nhlpaphotodownloader;

import com.google.common.base.Function;
import java.io.File;
import java.net.URL;

final class PlayerPhotoDownloaderImpl implements PlayerPhotoDownloader
{
  private final Function<Player, URL> playerToProfileUrlFunction;
  private final Function<URL, URL> profileUrlToImageUrlFunction;
  private final Function<Player, File> playerToOutputFileFunction;
  private final Downloader downloader;

  public PlayerPhotoDownloaderImpl(final String outputFolderSpec)
  {
    this(new PlayerToProfileUrlFunction(), new ProfileUrlToImageUrlFunction(), new PlayerToOutputFileFunction(
          outputFolderSpec), new DownloaderImpl());
  }

  PlayerPhotoDownloaderImpl(final Function<Player, URL> playerToProfileUrlFunction,
        final Function<URL, URL> profileUrlToImageUrlFunction,
        final Function<Player, File> playerToOutputFileFunction, final Downloader downloader)
  {
    this.playerToProfileUrlFunction = playerToProfileUrlFunction;
    this.profileUrlToImageUrlFunction = profileUrlToImageUrlFunction;
    this.playerToOutputFileFunction = playerToOutputFileFunction;
    this.downloader = downloader;
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
    return playerToOutputFileFunction.apply(player);
  }

  private void download(final URL url, final File outputFile)
  {
    downloader.download(url, outputFile);
  }
}
