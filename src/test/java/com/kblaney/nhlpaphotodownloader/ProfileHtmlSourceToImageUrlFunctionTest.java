package com.kblaney.nhlpaphotodownloader;

import static org.junit.Assert.*;
import com.google.common.base.Function;
import java.net.URL;
import org.junit.Before;
import org.junit.Test;

public final class ProfileHtmlSourceToImageUrlFunctionTest
{
  private Function<String, URL> function;

  @Before
  public void setUp()
  {
    function = new ProfileHtmlSourceToImageUrlFunction();
  }

  @Test
  public void apply_oneImageHtmlElement() throws Exception
  {
    assertEquals(new URL("http://origin.agilitycms.com/nhlpacom/Images/Player-Headshots/29088.jpg"),
          function.apply("<div class=\"thumb\">\n"
                + "<img src=\"http://origin.agilitycms.com/nhlpacom/Images/Player-Headshots/29088.jpg\" "
                + "alt=\"T.J. Oshie Headshot\" />"));
  }

  @Test
  public void apply_twoImageHtmlElements() throws Exception
  {
    assertEquals(new URL("http://origin.agilitycms.com/nhlpacom/Images/Player-Headshots/29088.jpg"),
          function.apply("<div class=\"thumb\">\n"
                + "<img src=\"http://origin.agilitycms.com/nhlpacom/Images/Player-Headshots/29088.jpg\" "
                + "alt=\"T.J. Oshie Headshot\" />\n" + "<div class=\"thumb\">\n"
                + "<img src=\"http://origin.agilitycms.com/nhlpacom/Images/Player-Headshots/12345.jpg\" "
                + "alt=\"David Perron Headshot\" />"));
  }
}
