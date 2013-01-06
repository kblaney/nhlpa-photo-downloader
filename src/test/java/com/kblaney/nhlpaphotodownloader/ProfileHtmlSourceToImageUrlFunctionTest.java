package com.kblaney.nhlpaphotodownloader;

import static org.junit.Assert.*;
import org.junit.Test;
import com.google.common.base.Function;
import org.junit.Before;

public final class ProfileHtmlSourceToImageUrlFunctionTest
{
  private Function<String, String> function;

  @Before
  public void setUp()
  {
    function = new ProfileHtmlSourceToImageUrlFunction();
  }

  @Test
  public void apply_oneImageHtmlElement()
  {
    assertEquals("http://origin.agilitycms.com/nhlpacom/Images/Player-Headshots/29088.jpg",
          function.apply("<div class=\"thumb\">\n"
                + "<img src=\"http://origin.agilitycms.com/nhlpacom/Images/Player-Headshots/29088.jpg\" "
                + "alt=\"T.J. Oshie Headshot\" />"));
  }

  @Test
  public void apply_twoImageHtmlElements()
  {
    assertEquals("http://origin.agilitycms.com/nhlpacom/Images/Player-Headshots/29088.jpg",
          function.apply("<div class=\"thumb\">\n"
                + "<img src=\"http://origin.agilitycms.com/nhlpacom/Images/Player-Headshots/29088.jpg\" "
                + "alt=\"T.J. Oshie Headshot\" />\n" + "<div class=\"thumb\">\n"
                + "<img src=\"http://origin.agilitycms.com/nhlpacom/Images/Player-Headshots/12345.jpg\" "
                + "alt=\"David Perron Headshot\" />"));
  }
}
