package com.kblaney.nhlpaphotodownloader;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import java.net.URL;
import org.junit.Before;
import org.junit.Test;

public final class ProfileUrlToImageUrlFunctionTest
{
  private Function<URL, Optional<String>> urlToHtmlSourceFunction;
  private Function<String, URL> profileHtmlSourceToImageUrlFunction;
  private Function<URL, Optional<URL>> profileUrlToImageUrlFunction;

  @Before
  @SuppressWarnings("unchecked")
  public void setUp()
  {
    urlToHtmlSourceFunction = mock(Function.class);
    profileHtmlSourceToImageUrlFunction = mock(Function.class);
    profileUrlToImageUrlFunction = new ProfileUrlToImageUrlFunction(urlToHtmlSourceFunction,
          profileHtmlSourceToImageUrlFunction);
  }

  @Test
  public void apply_playerDoesNotHaveProfile() throws Exception
  {
    final URL profileUrl = new URL("http://profile.url");
    when(urlToHtmlSourceFunction.apply(profileUrl)).thenReturn(Optional.<String>absent());

    assertEquals(Optional.<URL>absent(), profileUrlToImageUrlFunction.apply(profileUrl));
  }

  @Test
  public void apply_playerHasProfile() throws Exception
  {
    final URL profileUrl = new URL("http://profile.url");
    final String profileHtmlSource = "PROFILE-HTML-SOURCE";
    final URL imageUrl = new URL("http://profile.url");
    when(urlToHtmlSourceFunction.apply(profileUrl)).thenReturn(Optional.of(profileHtmlSource));
    when(profileHtmlSourceToImageUrlFunction.apply(profileHtmlSource)).thenReturn(imageUrl);

    assertEquals(Optional.of(imageUrl), profileUrlToImageUrlFunction.apply(profileUrl));
  }
}
