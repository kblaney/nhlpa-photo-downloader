package com.kblaney.nhlpaphotodownloader;

import com.google.common.base.Function;
import java.net.MalformedURLException;
import java.net.URL;

final class PlayerToProfileUrlFunction implements Function<Player, URL>
{
  @Override
  public URL apply(final Player p)
  {
    final String urlSpec = "http://www.nhlpa.com/the-players/profile/" + convertNameToUrlComponent(p.getFirstName()) + "-" +
          convertNameToUrlComponent(p.getLastName());
    return getUrl(urlSpec);
  }

  private String convertNameToUrlComponent(final String name)
  {
    final String lowerCaseName = name.toLowerCase();
    return lowerCaseName.replace(' ', '-');
  }

  private URL getUrl(final String urlSpec)
  {
    try
    {
      return new URL(urlSpec);
    }
    catch (final MalformedURLException e)
    {
      throw new IllegalStateException("Invalid URL spec:" + urlSpec);
    }
  }
}
