package com.kblaney.nhlpaphotodownloader;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import com.google.common.base.Function;
import java.util.regex.Pattern;

final class ProfileHtmlSourceToImageUrlFunction implements Function<String, URL>
{
  @Override
  public URL apply(final String profileHtmlSource)
  {
    final Pattern p = Pattern.compile("<img src=\"([^\"]+?\\.jpg)");
    final Matcher m = p.matcher(profileHtmlSource);
    if (m.find())
    {
      final int imageUrlGroupNum = 1;
      final String urlSpec = m.group(imageUrlGroupNum);
      return getUrl(urlSpec);
    }
    else
    {
      throw new IllegalArgumentException("Can't find image URL in profile HTML source");
    }
  }

  private URL getUrl(final String urlSpec)
  {
    try
    {
      return new URL(urlSpec);
    }
    catch (final MalformedURLException e)
    {
      throw new IllegalArgumentException("Malformed URL spec in profile HTML source:" + urlSpec, e);
    }
  }
}
