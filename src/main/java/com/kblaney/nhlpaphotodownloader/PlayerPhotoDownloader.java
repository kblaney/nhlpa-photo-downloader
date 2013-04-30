package com.kblaney.nhlpaphotodownloader;

interface PlayerPhotoDownloader
{
  /**
   * Downloads the photo for specified player.
   *
   * @param player the player for which to download a photo
   *
   * @throws NoProfileException if the player does not have an NHLPA profile
   * @throws NoPhotoException if the player does not have a photo on their NHLPA profile
   */
  void download(Player player);
}
