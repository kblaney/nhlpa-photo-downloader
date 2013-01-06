package com.kblaney.nhlpaphotodownloader;

import com.google.common.base.Function;
import java.io.File;

final class PlayerToOutputFileFunction implements Function<Player, File>
{
  private final String outputFolderSpec;

  public PlayerToOutputFileFunction(final String outputFolderSpec)
  {
    this.outputFolderSpec = outputFolderSpec;
  }

  @Override
  public File apply(final Player player)
  {
    final String fileName = player.getFirstName() + "_" + player.getLastName() + ".jpg";
    return new File(outputFolderSpec, fileName);
  }
}
