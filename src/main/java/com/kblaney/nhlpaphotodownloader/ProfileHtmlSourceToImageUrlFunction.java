package com.kblaney.nhlpaphotodownloader;

import java.util.regex.Matcher;
import com.google.common.base.Function;
import java.util.regex.Pattern;

final class ProfileHtmlSourceToImageUrlFunction implements Function<String, String>
{
  @Override
  public String apply(final String profileHtmlSource)
  {
    final Pattern p = Pattern.compile("<img src=\"([^\"]+?\\.jpg)");
    final Matcher m = p.matcher(profileHtmlSource);
    if (m.find())
    {
      final int imageUrlGroupNum = 1;
      return m.group(imageUrlGroupNum);
    }
    else
    {
      throw new IllegalArgumentException("Can't find image URL in profile HTML source:" + profileHtmlSource);
    }
  }
}
