package com.kblaney.nhlpaphotodownloader;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import java.net.URL;

final class ProfileUrlToImageUrlFunction implements Function<URL, Optional<URL>>
{
  private final Function<URL, Optional<String>> urlToHtmlSourceFunction;
  private final Function<String, URL> profileHtmlSourceToImageUrlFunction;

  public ProfileUrlToImageUrlFunction()
  {
    this(new UrlToHtmlSourceFunction(), new ProfileHtmlSourceToImageUrlFunction());
  }

  ProfileUrlToImageUrlFunction(final Function<URL, Optional<String>> urlToHtmlSourceFunction,
        final Function<String, URL> profileHtmlSourceToImageUrlFunction)
  {
    this.urlToHtmlSourceFunction = urlToHtmlSourceFunction;
    this.profileHtmlSourceToImageUrlFunction = profileHtmlSourceToImageUrlFunction;
  }

  @Override
  public Optional<URL> apply(final URL playerProfileUrl)
  {
    final Optional<String> profileHtmlSource = getProfileHtmlSource(playerProfileUrl);
    if (profileHtmlSource.isPresent())
    {
      return Optional.of(getImageUrl(profileHtmlSource.get()));
    }
    else
    {
      return Optional.absent();
    }
  }

  private Optional<String> getProfileHtmlSource(final URL playerProfileUrl)
  {
    return urlToHtmlSourceFunction.apply(playerProfileUrl);
  }

  private URL getImageUrl(final String profileHtmlSource)
  {
    return profileHtmlSourceToImageUrlFunction.apply(profileHtmlSource);
  }
}
