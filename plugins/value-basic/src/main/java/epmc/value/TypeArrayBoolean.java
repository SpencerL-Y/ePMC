/****************************************************************************

    ePMC - an extensible probabilistic model checker
    Copyright (C) 2017

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

 *****************************************************************************/

package epmc.value;

import epmc.value.ContextValue;
import epmc.value.TypeArray;
import epmc.value.ValueArray;

public final class TypeArrayBoolean implements TypeArray {
    private final static String ARRAY_INDICATOR = "[](boolean)";
    private final TypeBoolean entryType;

    TypeArrayBoolean(TypeBoolean entryType) {
        assert entryType != null;
        this.entryType = entryType;
    }

    @Override
    public ValueArray newValue() {
        return new ValueArrayBoolean(this);
    }

    @Override
    public TypeBoolean getEntryType() {
        return entryType;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TypeArrayBoolean)) {
            return false;
        }
        TypeArrayBoolean other = (TypeArrayBoolean) obj;
        return this.getEntryType().equals(other.getEntryType());
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash = getClass().hashCode() + (hash << 6) + (hash << 16) - hash;
        hash = getEntryType().hashCode() + (hash << 6) + (hash << 16) - hash;
        return hash;
    }    

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getEntryType());
        builder.append(ARRAY_INDICATOR);
        return builder.toString();
    }

    @Override
    public TypeArray getTypeArray() {
        return ContextValue.get().makeUnique(new TypeArrayGeneric(this));
    }
}
