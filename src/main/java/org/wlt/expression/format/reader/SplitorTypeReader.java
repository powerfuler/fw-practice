/**
 * 
 */
package org.wlt.expression.format.reader;

import org.wlt.expression.format.Element;
import org.wlt.expression.format.ExpressionReader;
import org.wlt.expression.format.FormatException;

import java.io.IOException;

/**
 * 读取分割符类型
 * @author 林良益，卓诗垚
 * @version 2.0 
 * Sep 21, 2008
 */
public class SplitorTypeReader implements ElementReader {

	public static final String SPLITOR_CHAR ="(),";//所有分割符
	
	/**
	 * 从流中读取分割符类型的ExpressionToken
	 * @param sr
	 * @return
	 * @throws FormatException 不是合法的分割符类型时抛出
	 * @throws IOException
	 */
	public Element read(ExpressionReader sr) throws FormatException, IOException {
		int index = sr.getCruuentIndex();
		int b = sr.read();
		char c = (char)b;
		if (b == -1 || SPLITOR_CHAR.indexOf(c) == -1) {
			throw new FormatException("不是有效的分割字符");
		}
		return new Element(Character.toString(c), index,
				Element.ElementType.SPLITOR);
	}
}
