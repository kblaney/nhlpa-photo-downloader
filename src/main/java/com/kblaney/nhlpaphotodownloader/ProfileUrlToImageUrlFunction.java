package com.kblaney.nhlpaphotodownloader;

import com.google.common.base.Function;
import java.net.URL;

final class ProfileUrlToImageUrlFunction implements Function<URL, URL>
{
  private final Function<URL, String> urlToHtmlSourceFunction = new UrlToHtmlSourceFunction();
  private final Function<String, URL> profileHtmlSourceToImageUrlFunction = new ProfileHtmlSourceToImageUrlFunction();

  @Override
  public URL apply(final URL playerProfileUrl)
  {
    final String profileHtmlSource = getProfileHtmlSource(playerProfileUrl);
    return getImageUrl(profileHtmlSource);
  }

  private String getProfileHtmlSource(final URL playerProfileUrl)
  {
    return urlToHtmlSourceFunction.apply(playerProfileUrl);
  }

  private URL getImageUrl(final String profileHtmlSource)
  {
    return profileHtmlSourceToImageUrlFunction.apply(profileHtmlSource);
  }
}
