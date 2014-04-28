/**
 * This file is part of org.everit.commons.selection.
 *
 * org.everit.commons.selection is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * org.everit.commons.selection is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with org.everit.commons.selection.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.everit.commons.selection.range.time;

/**
 * {@code TimeInterval} is the interval of two timestamps represented in seconds using {@code Long} values.
 */
public class SecondInterval extends Interval<Long> {

    private static final long serialVersionUID = 1459251233031508309L;

    public SecondInterval(final Long lowerBound, final Long higherBound) {
        super(lowerBound, higherBound);
    }

    public SecondInterval(final Long lowerBound, final Long higherBound, final boolean lowerInclusive,
            final boolean higherInclusive) {
        super(lowerBound, higherBound, lowerInclusive, higherInclusive);
    }

    @Override
    protected long getHigherBoundInMillis() {
        return higherBound.longValue() * 1000;
    }

    @Override
    protected long getLowerBoundInMillis() {
        return lowerBound.longValue() * 1000;
    }

}
