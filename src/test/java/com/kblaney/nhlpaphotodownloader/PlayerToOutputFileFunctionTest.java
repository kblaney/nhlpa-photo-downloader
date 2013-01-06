package com.kblaney.nhlpaphotodownloader;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import com.google.common.base.Function;
import java.io.File;

public final class PlayerToOutputFileFunctionTest
{
  private String outputFolderSpec;
  private Function<Player, File> function;

  @Before
  public void setUp()
  {
    outputFolderSpec = "OUTPUT-FOLDER";
    function = new PlayerToOutputFileFunction(outputFolderSpec);
  }

  @Test
  public void apply() throws Exception
  {
    assertEquals(outputFolderSpec + System.getProperty("file.separator") + "Wayne_Gretzky.jpg",
          function.apply(new Player("Wayne", "Gretzky")).getPath());
  }
}
