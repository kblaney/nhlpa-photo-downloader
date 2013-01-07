package com.kblaney.nhlpaphotodownloader;

import static org.mockito.Mockito.*;
import org.junit.Test;
import org.junit.Before;
import com.google.common.base.Function;
import java.io.File;
import java.net.URL;

public final class PlayerPhotoDownloaderImplTest
{
  private Function<Player, URL> playerToProfileUrlFunction;
  private Function<URL, URL> profileUrlToImageUrlFunction;
  private Function<Player, File> playerToOutputFileFunction;
  private Downloader downloader;
  private PlayerPhotoDownloader playerPhotoDownloader;

  @Before
  @SuppressWarnings("unchecked")
  public void setUp()
  {
    playerToProfileUrlFunction = mock(Function.class);
    profileUrlToImageUrlFunction = mock(Function.class);
    playerToOutputFileFunction = mock(Function.class);
    downloader = mock(Downloader.class);
    playerPhotoDownloader = new PlayerPhotoDownloaderImpl(playerToProfileUrlFunction, profileUrlToImageUrlFunction,
          playerToOutputFileFunction, downloader);
  }

  @Test
  public void apply() throws Exception
  {
    final Player player = new Player("Wayne", "Gretzky");
    final URL profileUrl = new URL("http://www.nhlpa.com/wayne/gretzky/profile");
    final URL imageUrl = new URL("http://www.nhlpaimages.com/12345.jpg");
    final File outputFile = new File("wayne-gretzky.jpg");
    when(playerToProfileUrlFunction.apply(player)).thenReturn(profileUrl);
    when(profileUrlToImageUrlFunction.apply(profileUrl)).thenReturn(imageUrl);
    when(playerToOutputFileFunction.apply(player)).thenReturn(outputFile);

    playerPhotoDownloader.download(player);

    verify(downloader).download(imageUrl, outputFile);
  }
}
