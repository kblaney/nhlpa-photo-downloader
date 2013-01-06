package com.kblaney.nhlpaphotodownloader;

import java.io.File;

interface Downloader
{
  void download(String url, File outputFile);
}
