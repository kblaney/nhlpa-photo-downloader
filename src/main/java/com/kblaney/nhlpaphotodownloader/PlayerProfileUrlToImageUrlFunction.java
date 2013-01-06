package com.kblaney.nhlpaphotodownloader;

import com.google.common.base.Function;

final class PlayerProfileUrlToImageUrlFunction implements Function<String, String>
{
  private final Function<String, String> urlSpecToHtmlSourceFunction = null;
  private final Function<String, String> profileHtmlSourceToImageUrlFunction = null;

  @Override
  public String apply(final String playerProfileUrl)
  {
    final String profileHtmlSource = getProfileHtmlSource(playerProfileUrl);
    return getImageUrl(profileHtmlSource);
  }

  private String getProfileHtmlSource(final String playerProfileUrl)
  {
    return urlSpecToHtmlSourceFunction.apply(playerProfileUrl);
  }

  private String getImageUrl(final String profileHtmlSource)
  {
    return profileHtmlSourceToImageUrlFunction.apply(profileHtmlSource);
  }
}
