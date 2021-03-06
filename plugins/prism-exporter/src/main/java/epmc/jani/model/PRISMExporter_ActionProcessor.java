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

package epmc.jani.model;

import epmc.prism.exporter.JANIComponentRegistrar;
import epmc.prism.exporter.processor.PRISMExporter_ProcessorStrict;

public class PRISMExporter_ActionProcessor implements PRISMExporter_ProcessorStrict {

    private Action action = null;

    @Override
    public PRISMExporter_ProcessorStrict setElement(Object obj) {
        assert obj != null;
        assert obj instanceof Action; 

        action = (Action) obj;
        return this;
    }

    @Override
    public String toPRISM() {
        assert action != null;

        StringBuilder prism = new StringBuilder();

        prism.append("[")
            .append(JANIComponentRegistrar.getActionName(action))
            .append("]");

        return prism.toString();
    }

    @Override
    public void validateTransientVariables() {
        assert action != null;
    }


    @Override
    public boolean usesTransientVariables() {
        assert action != null;

        return false;
    }	
}
