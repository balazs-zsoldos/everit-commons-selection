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
package org.everit.commons.selection.range.number;

import java.math.BigDecimal;

public class BigDecimalRange extends NumberRange<BigDecimal> {

    private static final long serialVersionUID = 6844568521124522782L;

    public BigDecimalRange(final BigDecimal lowerBound, final BigDecimal higherBound) {
        super(lowerBound, higherBound);
    }

    public BigDecimalRange(final BigDecimal lowerBound, final BigDecimal higherBound, final boolean lowerInclusive,
            final boolean higherInclusive) {
        super(lowerBound, higherBound, lowerInclusive, higherInclusive);
    }

    @Override
    public BigDecimal getLength() {
        return higherBound.subtract(lowerBound);
    }
}
