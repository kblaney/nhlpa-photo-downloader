package com.kblaney.nhlpaphotodownloader;

import java.io.File;
import java.net.URL;

interface Downloader
{
  void download(URL url, File outputFile);
}
