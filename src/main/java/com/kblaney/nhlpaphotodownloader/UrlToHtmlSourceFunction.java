package com.kblaney.nhlpaphotodownloader;

import com.kblaney.assertions.ArgAssert;
import java.io.FileNotFoundException;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import org.apache.commons.io.IOUtils;

final class UrlToHtmlSourceFunction implements Function<URL, Optional<String>>
{
  @Override
  public Optional<String> apply(final URL url)
  {
    ArgAssert.assertNotNull(url, "url");

    InputStream inputStream = null;
    try
    {
      inputStream = url.openStream();
      final String htmlSource = IOUtils.toString(inputStream, Charset.forName("UTF-8"));
      return Optional.of(htmlSource);
    }
    catch (final FileNotFoundException e)
    {
      return Optional.absent();
    }
    catch (final IOException e)
    {
      throw new IllegalStateException("Can't get HTML source for:" + url, e);
    }
    finally
    {
      IOUtils.closeQuietly(inputStream);
    }
  }
}
