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

package epmc.jani.model.type;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

import epmc.jani.exporter.processor.JANIProcessor;
import epmc.qmc.model.JANITypeArray;
import epmc.util.UtilJSON;

public final class QMCExporter_JANITypeArray2JANIProcessor implements JANIProcessor {
    /** Identifier for array type. */
    private static final String VECTOR = "vector";

    private JANITypeArray array = null;

    @Override
    public JANIProcessor setElement(Object obj) {
        assert obj instanceof JANITypeArray;

        array = (JANITypeArray) obj;
        return this;
    }

    
    @Override
    public JsonValue toJSON() {
        assert array != null;

        return UtilJSON.toStringValue(VECTOR);
    }
}
