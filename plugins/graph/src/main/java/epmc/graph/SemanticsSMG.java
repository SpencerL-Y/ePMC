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

package epmc.graph;

import epmc.graph.Semantics;

/**
 * Semantics type for (discrete-time) stochastic Markov games (SMGs).
 */
public enum SemanticsSMG implements SemanticsDiscreteTime, SemanticsNonDet, SemanticsStochastic {
    SMG;

    /**
     * Checks whether this is a stochastic Markov game (SMG).
     * 
     * @return whether this is a stochastic Markov game (SMG)
     */
    public static boolean isSMG(Semantics semantics) {
        return semantics instanceof SemanticsSMG;
    }
}
