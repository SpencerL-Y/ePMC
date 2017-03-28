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

package epmc.graph.explicit;

import epmc.error.EPMCException;
import epmc.value.Operator;
import epmc.value.Type;
import epmc.value.Value;

/**
 * Edge property obtaining values using the apply method of an operator.
 * The property is constructed with an {@link Operator} and a variable number of
 * {@link NodeProperty} objects, the number of which should fit the arity of the
 * operator of the edge property.
 * 
 * @author Ernst Moritz Hahn
 */
public final class EdgePropertyApply implements EdgeProperty {
    /** Graph to which this edge property belongs. */
    private final GraphExplicit graph;
    /** Operator computing the values obtained by {@link #get(int)}. */
    private final Operator operator;
    /** Node properties {@link #get(int)} of which {@link #operator} is applied. */
    private final EdgeProperty[] operands;
    /** Values used to perform {@link Operator#apply(Value, Value...)}. */
    private final Value[] callOperands;
    /** Value returned by {@link #get(int)}. */
    private final Value value;

    /**
     * Construct a new apply edge property.
     * None of the arguments may be {@code null} or contain {@code null}
     * entries.
     * 
     * @param graph graph to which this property belongs
     * @param operator operator used to generate values of {@link #get(int)}
     * @param operands node properties providing operands to the operator
     */
    public EdgePropertyApply(GraphExplicit graph, Operator operator, EdgeProperty... operands) {
        assert graph != null;
        assert operator != null;
        assert operands != null;
        for (EdgeProperty edgeProperty : operands) {
            assert edgeProperty != null;
            assert edgeProperty.getGraph() == graph;
        }
        this.graph = graph;
        this.operator = operator;
        this.operands = operands;
        this.callOperands = new Value[operands.length];
        Type[] types = new Type[operands.length];
        for (int operandNr = 0; operandNr < operands.length; operandNr++) {
            types[operandNr] = operands[operandNr].getType();
        }
        Type type = operator.resultType(operator.resultType(types));
        this.value = type.newValue();
    }
    
    /**
     * {@inheritDoc}
     * For this property type, the result of this function is computed as
     * follows. For each operand, a value is obtained using {@link #get(int)}.
     * Afterwards, the {@link Operator#apply(Value, Value...)} method of the
     * operator of this property is used to transform these values into the
     * resulting value.
     */
    @Override
    public Value get(int node, int successor) throws EPMCException {
        assert successor >= 0;
        assert successor < graph.getNumSuccessors();
        for (int operandNr = 0; operandNr < operands.length; operandNr++) {
            callOperands[operandNr] = operands[operandNr].get(node, successor);
        }
        operator.apply(value, callOperands);
        return value;
    }

    /**
     * {@inheritDoc}
     * As the values of this property type are computed using an operator, calls
     * to this function have no effect.
     */
    @Override
    public void set(int node, int succ, Value value) {
        assert value != null;
        assert succ >= 0;
        assert succ < graph.getNumNodes();
    }

    /**
     * {@inheritDoc}
     * The type of the node property will be computed by
     * {@link Operator#resultType(Type...)} call with the results of the
     * {@link NodeProperty#getType()} of the node properties used.
     */
    @Override
    public Type getType() {
        return value.getType();
    }

    @Override
    public GraphExplicit getGraph() {
        return graph;
    }
}
