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

package epmc.operator;

import static epmc.prism.exporter.util.UtilPRISMExporter.appendWithParenthesesIfNeeded;

import epmc.expression.Expression;
import epmc.expression.standard.ExpressionOperator;
import epmc.prism.exporter.operatorprocessor.PRISMExporter_OperatorProcessorStrict;

/**
 * @author Andrea Turrini
 *
 */
public class PRISMExporter_OperatorSubtractProcessor implements PRISMExporter_OperatorProcessorStrict {

    private ExpressionOperator expressionOperator = null;
    
    /* (non-Javadoc)
     * @see epmc.prism.exporter.processor.JANI2PRISMOperatorProcessorStrict#setExpressionOperator(epmc.expression.standard.ExpressionOperator)
     */
    @Override
    public PRISMExporter_OperatorProcessorStrict setExpressionOperator(ExpressionOperator expressionOperator) {
        assert expressionOperator != null;
        
        assert expressionOperator.getOperator().equals(OperatorSubtract.SUBTRACT);
    
        this.expressionOperator = expressionOperator;
        return this;
    }

    /* (non-Javadoc)
     * @see epmc.prism.exporter.operatorprocessor.JANI2PRISMOperatorProcessorStrict#toPRISM()
     */
    @Override
    public String toPRISM() {
        assert expressionOperator != null;

        StringBuilder prism = new StringBuilder();

        appendWithParenthesesIfNeeded(this, expressionOperator.getOperand1(), prism);
        prism.append(" - ");
        appendWithParenthesesIfNeeded(this, expressionOperator.getOperand2(), prism);

        return prism.toString();
    }
    /* (non-Javadoc)
     * @see epmc.prism.exporter.operatorprocessor.JANI2PRISMOperatorProcessorStrict#needsParentheses(epmc.expression.standard.Expression)
     */
    @Override
    public boolean needsParentheses(Expression childOperand) {
        if (childOperand instanceof ExpressionOperator) {
            Operator operator = ((ExpressionOperator) childOperand).getOperator();
            if (operator.equals(OperatorAdd.ADD))
                return false;
            if (operator.equals(OperatorSubtract.SUBTRACT))
                return false;
            if (operator.equals(OperatorMultiply.MULTIPLY))
                return false;
            if (operator.equals(OperatorMultiplyInverse.MULTIPLY_INVERSE))
                return false;
            if (operator.equals(OperatorDivide.DIVIDE))
                return false;
        }
        return PRISMExporter_OperatorProcessorStrict.super.needsParentheses(childOperand);
    }
}
