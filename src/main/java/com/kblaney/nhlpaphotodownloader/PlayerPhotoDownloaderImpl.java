package com.kblaney.nhlpaphotodownloader;

import com.google.common.base.Function;
import java.io.File;

final class PlayerPhotoDownloaderImpl implements PlayerPhotoDownloader
{
  private final Function<Player, String> playerToProfileUrlFunction = new PlayerToProfileUrlFunction();
  private final Function<String, String> profileUrlToImageUrlFunction = null;
  private final Function<Player, File> playerToOutputFileSpecFunction;
  private final Downloader downloader = null;

  public PlayerPhotoDownloaderImpl(final String outputFolderSpec)
  {
    playerToOutputFileSpecFunction = new PlayerToOutputFileFunction(outputFolderSpec);
  }

  @Override
  public void download(final Player player)
  {
    final String profileUrl = getProfileUrl(player);
    final String imageUrl = getImageUrl(profileUrl);
    final File outputFileSpec = getOutputFile(player);
    download(imageUrl, outputFileSpec);
  }

  private String getProfileUrl(final Player player)
  {
    return playerToProfileUrlFunction.apply(player);
  }

  private String getImageUrl(final String profileUrl)
  {
    return profileUrlToImageUrlFunction.apply(profileUrl);
  }

  private File getOutputFile(final Player player)
  {
    return playerToOutputFileSpecFunction.apply(player);
  }

  private void download(final String url, final File outputFile)
  {
    downloader.download(url, outputFile);
  }
}
