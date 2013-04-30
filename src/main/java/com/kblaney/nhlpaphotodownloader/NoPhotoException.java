package com.kblaney.nhlpaphotodownloader;

@SuppressWarnings("serial")
public final class NoPhotoException extends RuntimeException
{
  public NoPhotoException(final Throwable cause)
  {
    super(cause);
  }
}
