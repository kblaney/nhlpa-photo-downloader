package com.kblaney.nhlpaphotodownloader;

import com.google.common.base.Function;

final class PlayerToProfileUrlFunction implements Function<Player, String>
{
  @Override
  public String apply(final Player p)
  {
    return "http://www.nhlpa.com/the-players/profile/" + convertNameToUrlComponent(p.getFirstName()) + "-" +
          convertNameToUrlComponent(p.getLastName());
  }

  private String convertNameToUrlComponent(final String name)
  {
    final String lowerCaseName = name.toLowerCase();
    return lowerCaseName.replace(' ', '-');
  }
}
