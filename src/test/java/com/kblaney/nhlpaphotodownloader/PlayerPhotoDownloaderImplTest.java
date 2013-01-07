package com.kblaney.nhlpaphotodownloader;

import static org.mockito.Mockito.*;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import java.io.File;
import java.net.URL;
import org.junit.Before;
import org.junit.Test;

public final class PlayerPhotoDownloaderImplTest
{
  private Function<Player, URL> playerToProfileUrlFunction;
  private Function<URL, Optional<URL>> profileUrlToImageUrlFunction;
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
  public void apply_playerDoesNotHaveImage() throws Exception
  {
    final Player player = new Player("Wayne", "Gretzky");
    final URL profileUrl = new URL("http://www.nhlpa.com/wayne/gretzky/profile");
    when(playerToProfileUrlFunction.apply(player)).thenReturn(profileUrl);
    when(profileUrlToImageUrlFunction.apply(profileUrl)).thenReturn(Optional.<URL>absent());

    playerPhotoDownloader.download(player);

    verifyZeroInteractions(downloader);
  }

  @Test
  public void apply_playerHasImage() throws Exception
  {
    final Player player = new Player("Wayne", "Gretzky");
    final URL profileUrl = new URL("http://www.nhlpa.com/wayne/gretzky/profile");
    final URL imageUrl = new URL("http://www.nhlpaimages.com/12345.jpg");
    final File outputFile = new File("wayne-gretzky.jpg");
    when(playerToProfileUrlFunction.apply(player)).thenReturn(profileUrl);
    when(profileUrlToImageUrlFunction.apply(profileUrl)).thenReturn(Optional.of(imageUrl));
    when(playerToOutputFileFunction.apply(player)).thenReturn(outputFile);

    playerPhotoDownloader.download(player);

    verify(downloader).download(imageUrl, outputFile);
  }
}
