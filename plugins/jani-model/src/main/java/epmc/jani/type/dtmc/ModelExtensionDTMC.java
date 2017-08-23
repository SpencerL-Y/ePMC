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

package epmc.jani.type.dtmc;

import static epmc.error.UtilError.ensure;

import javax.json.JsonObjectBuilder;

import epmc.error.EPMCException;
import epmc.graph.Semantics;
import epmc.graph.SemanticsDTMCStandard;
import epmc.jani.model.JANINode;
import epmc.jani.model.Location;
import epmc.jani.model.ModelExtensionSemantics;
import epmc.jani.model.ModelJANI;

public class ModelExtensionDTMC implements ModelExtensionSemantics {
	public final static String IDENTIFIER = "dtmc";

	private ModelJANI model;
	private JANINode node;
	
	@Override
	public String getIdentifier() {
		return IDENTIFIER;
	}

	@Override
	public void setModel(ModelJANI model) throws EPMCException {
		assert this.model == null;
		assert model != null;
		this.model = model;
	}

	@Override
	public void setNode(JANINode node) throws EPMCException {
		this.node = node;
	}

	@Override
	public void parseAfter() throws EPMCException {
//		if (node instanceof Edge) {
	//		Edge edge = (Edge) node;
		//	ensure(edge.getRate() == null, ProblemsJANIDTMC.JANI_DTMC_EDGE_FORBIDS_RATE);
	//	}
		if (node instanceof Location) {
			Location location = (Location) node;
			ensure(location.getTimeProgress() == null, ProblemsJANIDTMC.JANI_DTMC_DISALLOWED_TIME_PROGRESSES);
		}
	}

	@Override
	public void generate(JsonObjectBuilder generate) throws EPMCException {
		assert generate != null;
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public Semantics getSemantics() {
		return SemanticsDTMCStandard.DTMC;
	}

}
