package com.kblaney.nhlpaphotodownloader;

import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import org.apache.commons.io.IOUtils;

public final class DownloaderImpl implements Downloader
{
  @Override
  public void download(final URL url, final File outputFile)
  {
    InputStream inputStream = null;
    OutputStream outputStream = null;
    try
    {
      inputStream = url.openStream();
      outputStream = new FileOutputStream(outputFile);
      IOUtils.copy(inputStream, outputStream);
    }
    catch (final FileNotFoundException e)
    {
      throw new NoPhotoException(e);
    }
    catch (final IOException e)
    {
      throw new IllegalStateException("Can't download from:" + url, e);
    }
    finally
    {
      IOUtils.closeQuietly(inputStream);
      IOUtils.closeQuietly(outputStream);
    }
  }
}
