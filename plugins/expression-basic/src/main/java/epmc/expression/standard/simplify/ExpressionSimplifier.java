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

package epmc.expression.standard.simplify;

import java.util.Map;

import epmc.expression.Expression;
import epmc.expression.evaluatorexplicit.EvaluatorExplicit;
import epmc.expression.standard.evaluatorexplicit.UtilEvaluatorExplicit.EvaluatorCacheEntry;
import epmc.expressionevaluator.ExpressionToType;

/**
 * Expression simplifier interface.
 * 
 * @author Ernst Moritz Hahn
 */
public interface ExpressionSimplifier {
    interface Builder {
        Builder setExpressionToType(ExpressionToType expressionToType);
        Builder setEvaluatorCache(Map<EvaluatorCacheEntry,EvaluatorExplicit> cache);
        ExpressionSimplifier build();
    }
    
    /**
     * Simplifies the given expression.
     * If possible, the simplifier will return an expression which is equivalent
     * but shorter than its input expression.
     * If this simplifier cannot simplify this expression (further), it will
     * return {@code null}.
     * The expression parameter must not be {@code null}.
     * 
     * @param expression expression to simplify
     * @return simplified expression, or {@code null}
     */
    Expression simplify(Expression expression);
}
