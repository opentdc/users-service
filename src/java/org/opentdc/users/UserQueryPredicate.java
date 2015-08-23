/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Arbalo AG
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.opentdc.users;

import org.opentdc.query.QueryPredicate;
import org.opentdc.service.exception.ValidationException;

public class UserQueryPredicate extends QueryPredicate {
	private QueryFeatureType featureType = null;

	/**
	 * Constructor.
	 */
	public UserQueryPredicate(String predicate) {	
		this.predicate = predicate;
	}
	
	/**
	 * Parse a stringified predicate from a query string into a QueryPredicate.
	 * @param predicate the stringified predicate  {quantor}{feature}().{operator}(values)
	 * @return the UserQueryPredicate
	 * @throws ValidationException if the syntax of the predicate is wrong
	 */
	public static UserQueryPredicate parsePredicate(
			String predicate) 
			throws ValidationException {
		UserQueryPredicate _queryPredicate = new UserQueryPredicate(predicate);
		_queryPredicate.setQuantor(parseQuantor(predicate));
		
		logger.info("parsePredicate(" + predicate + ")");
		String[] _tokens = predicate.split("\\.");
		if (_tokens.length != 2) {
			throw new ValidationException("invalid predicate found: <" + predicate + ">. Split by . does not result in 2 tokens. Correct syntax is {quantor}{feature}().{operator}(values).");
		}
		logger.info("_tokens[0]=<" + _tokens[0] + "> {quantor}{feature}()");
		logger.info("_tokens[1]=<" + _tokens[1] + "> {operator}(values)");
		_queryPredicate.setFeature(parseFeature(predicate, _tokens[0], getOffset(_queryPredicate.getQuantor())));
				
		String[] _tokens2 = _tokens[1].split("\\(");
		if (_tokens2.length != 2) {
			throw new ValidationException("invalid predicate found: <" + predicate + ">. Operator or values missing. Correct syntax is {quantor}{feature}().{operator}(values).");
		}
		String _values = _tokens2[1].substring(0, _tokens2[1].length()-1); 		// cut the trailing )
		logger.info("_tokens2[0]=<" + _tokens2[0] + "> {operator}");
		logger.info("_tokens2[1]=<" + _values + "> values)");
		_queryPredicate.setOperator(parseOperator(predicate, _tokens2[0]));
		_queryPredicate.setValues(parseValues(predicate, _values));	
		_queryPredicate.convertFeatureType();
		return _queryPredicate;
	}
	
	
	/**
	 * Converts all stringified features contained in the query into QueryFeatureTypes.
	 * Therefore, it validates the featureTypes as well.
	 * @throws ValidationException if it is an invalid feature
	 */
	public void convertFeatureType()
		throws ValidationException
	{
		try {
			featureType = QueryFeatureType.valueOf(feature.toUpperCase());
		}
		catch (Exception _ex) {
			throw new ValidationException("feature <" + feature + "> is not a valid FeatureType");
		}	
	}

	/**
	 * @return the FeatureType
	 */
	public QueryFeatureType getFeatureType() {
		return featureType;
	}

	/**
	 * Set the FeatureType
	 * @param featureType
	 */
	public void setFeatureType(QueryFeatureType featureType) {
		this.featureType = featureType;
	}	
}
