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

import static epmc.error.UtilError.fail;

import epmc.util.BitStream;
import epmc.value.Value;

public final class ValueBoolean implements ValueEnumerable, ValueBitStoreable, ValueSetString {
    public static boolean isBoolean(Value value) {
        return value instanceof ValueBoolean;
    }

    public static ValueBoolean asBoolean(Value value) {
        if (isBoolean(value)) {
            return (ValueBoolean) value;
        } else {
            return null;
        }
    }

    public static boolean isTrue(Value value) {
        ValueBoolean valueBoolean = asBoolean(value);
        if (valueBoolean == null) {
            return false;
        }
        if (valueBoolean.getBoolean()) {
            return true;
        }
        return false;
    }

    public static boolean isFalse(Value value) {
        ValueBoolean valueBoolean = asBoolean(value);
        if (valueBoolean == null) {
            return false;
        }
        if (!valueBoolean.getBoolean()) {
            return true;
        }
        return false;
    }

    private final static String TRUE = "true";
    private final static String FALSE = "false";
    private final static String SPACE = " ";

    private boolean value;
    private final TypeBoolean type;
    private boolean immutable;

    ValueBoolean(TypeBoolean type, boolean value) {
        assert type != null;
        this.type = type;
        this.value = value;
    }

    ValueBoolean(TypeBoolean type) {
        this(type, false);
    }

    public boolean getBoolean() {
        return value;
    }

    public void set(boolean value) {
        assert !isImmutable();
        this.value = value;
    }

    @Override
    public TypeBoolean getType() {
        return type;
    }

    @Override
    public ValueBoolean clone() {
        return new ValueBoolean(getType(), value);
    }

    @Override
    public boolean equals(Object obj) {
        assert obj != null;
        if (!(obj instanceof ValueBoolean)) {
            return false;
        }
        ValueBoolean other = (ValueBoolean) obj;
        return this.value == other.value;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash = getClass().hashCode() + (hash << 6) + (hash << 16) - hash;
        hash = value ? 1 : 0 + (hash << 6) + (hash << 16) - hash;
        return hash;
    }

    @Override
    public String toString() {
        return Boolean.toString(value);
    }

    @Override
    public void set(Value operand) {
        assert !isImmutable();
        assert operand != null;
        assert ValueBoolean.isBoolean(operand)
        : operand + SPACE + operand.getType();
        set(ValueBoolean.asBoolean(operand).getBoolean());
    }

    @Override
    public void read(BitStream reader) {
        assert !isImmutable();
        assert reader != null;
        set(reader.read());
    }

    @Override
    public void write(BitStream writer) {
        assert writer != null;
        writer.write(getBoolean());
    }

    @Override
    public void set(String string) {
        assert string != null;
        string = string.toLowerCase();
        string = string.trim();
        if (string.equals(TRUE)) {
            value = true;
        } else if (string.equals(FALSE)) {
            value = false;
        } else {
            fail(ProblemsValueBasic.VALUES_STRING_INVALID_VALUE, value, type);
        }
    }

    void setImmutable() {
        this.immutable = true;
    }

    boolean isImmutable() {
        return immutable;
    }

    @Override
    public int getValueNumber() {
        return value ? 1 : 0;
    }

    @Override
    public void setValueNumber(int number) {
        assert getType().canImport(getType()) : value;
        assert number >= 0 : number;
        assert number < 2 : number;
        set(number == 1);
    }
}
