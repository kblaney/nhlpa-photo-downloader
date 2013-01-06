package com.kblaney.nhlpaphotodownloader;

import com.kblaney.commons.lang.ArgAssert;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

final class Player
{
  private final String firstName;
  private final String lastName;

  public Player(final String firstName, final String lastName)
  {
    this.firstName = ArgAssert.notNull(firstName, "firstName");
    this.lastName = ArgAssert.notNull(lastName, "lastName");
  }

  public String getFirstName()
  {
    return firstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public String getFullName()
  {
    return firstName + " " + lastName;
  }

  /** {@inheritDoc} */
  @Override
  public boolean equals(final Object thatObject)
  {
    if (!(thatObject instanceof Player))
    {
      return false;
    }
    else
    {
      final Player that = (Player) thatObject;
      return new EqualsBuilder().append(firstName, that.firstName).append(lastName, that.lastName).isEquals();
    }
  }

  /** {@inheritDoc} */
  @Override
  public int hashCode()
  {
    return new HashCodeBuilder().append(firstName).append(lastName).toHashCode();
  }
}
