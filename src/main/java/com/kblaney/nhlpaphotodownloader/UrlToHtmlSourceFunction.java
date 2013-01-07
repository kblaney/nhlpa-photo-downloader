package com.kblaney.nhlpaphotodownloader;

import java.io.IOException;
import com.google.common.base.Function;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import org.apache.commons.io.IOUtils;

final class UrlToHtmlSourceFunction implements Function<URL, String>
{
  @Override
  public String apply(final URL url)
  {
    InputStream inputStream = null;
    try
    {
      inputStream = url.openStream();
      return IOUtils.toString(inputStream, Charset.forName("UTF-8"));
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
