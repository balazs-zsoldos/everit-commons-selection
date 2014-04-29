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

import java.text.Format;
import java.text.SimpleDateFormat;

import org.everit.commons.selection.range.Range;

/**
 * Represents a time interval.
 *
 * @param <T>
 */
public abstract class Interval<T extends Comparable<? super T>> extends Range<T> {

    protected static final Format DEFAULT_FORMAT = new SimpleDateFormat("y-M-d HH:mm:ss");

    protected static final ThreadLocal<Format> formatsPerThread = new ThreadLocal<Format>() {

        @Override
        protected Format initialValue() {
            return (Format) DEFAULT_FORMAT.clone();
        }

    };

    private static final long serialVersionUID = -6538272753451746418L;

    public Interval(final T lowerBound, final T higherBound) {
        super(lowerBound, higherBound);
    }

    public Interval(final T lowerBound, final T higherBound, final boolean lowerInclusive, final boolean higherInclusive) {
        super(lowerBound, higherBound, lowerInclusive, higherInclusive);
    }

    /**
     * @return the interval length in milliseconds
     */
    public long getDurationInMs() {
        return getHigherBoundInMillis() - getLowerBoundInMillis();
    }

    /**
     * Returns the interval duration described in human-readable format.
     *
     * The result will be in "((\d+)h)? ?((\d+)m)? ?((\d+)s)?" format.
     *
     * Examples:
     * <table style="width:400px">
     * <thead>
     * <tr>
     * <th>Interval</th>
     * <th>formatted duration</th>
     * </tr>
     * </thead> <tbody>
     * <tr>
     * <td>2010-10-10 08:00:00 - 2010-10-10 12:10:20</td>
     * <td>4h 10m 20s</td>
     * </tr>
     * <tr>
     * <td>2010-10-10 08:00:00 - 2010-10-10 08:10:20</td>
     * <td>10m 20s</td>
     * </tr>
     * <tr>
     * <td>2010-10-10 08:00:00 - 2010-10-10 12:00:20</td>
     * <td>4h 20s</td>
     * </tr>
     * <tr>
     * <td>2010-10-10 08:00:00 - 2010-10-10 12:10:00</td>
     * <td>4h 10m</td>
     * </tr>
     * <tr>
     * <td>2010-10-10 08:00:00 - 2010-10-10 12:00:00</td>
     * <td>4h</td>
     * </tr>
     * <tr>
     * <td>2010-10-10 08:00:00 - 2010-10-10 08:10:00</td>
     * <td>10m</td>
     * </tr>
     * <tr>
     * <td>2010-10-10 08:00:00 - 2010-10-10 08:00:20</td>
     * <td>20s</td>
     * </tr>
     * <tr>
     * <td>2010-10-10 08:00:00 - 2010-10-10 08:00:00</td>
     * <td>0s</td>
     * </tr>
     * </tbody>
     * </table>
     *
     * Useful for debugging and displaying purposes.
     */
    public String getFormattedDuration() {
        long duration = getDurationInMs();
        if (duration == 0L) {
            return "0s";
        }
        long remaining = duration / 1000;
        long seconds = remaining % 60;
        remaining /= 60;
        long minutes = remaining % 60;
        remaining /= 60;
        long hours = remaining % 60;
        StringBuilder sb = new StringBuilder();
        boolean leadingSpaceNeeded;
        if (hours != 0) {
            sb.append(Math.abs(hours)).append("h");
            leadingSpaceNeeded = true;
        } else {
            leadingSpaceNeeded = false;
        }
        if (minutes != 0) {
            if (leadingSpaceNeeded) {
                sb.append(' ');
            }
            sb.append(Math.abs(minutes)).append("m");
            leadingSpaceNeeded = true;
        }
        if ((seconds != 0) || ((minutes == 0L) && (hours == 0L))) {
            if (leadingSpaceNeeded) {
                sb.append(' ');
            }
            sb.append(Math.abs(seconds)).append("s");
        }
        return sb.toString();
    }

    protected abstract long getHigherBoundInMillis();

    protected abstract long getLowerBoundInMillis();

    /**
     * @return the interval with boundaries in "y-M-d HH:mm:ss" format.
     */
    @Override
    public String toString() {
        return toString(formatsPerThread.get());
    }

    public String toString(final Format fmt) {
        return new StringBuilder(41)
        .append(fmt.format(getLowerBoundInMillis()))
        .append(" - ")
        .append(fmt.format(getHigherBoundInMillis()))
        .toString();
    }

}
