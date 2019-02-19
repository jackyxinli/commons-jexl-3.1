commons-jexl-3.1
================
The original version does not support bit move operation. So I add bit move operator like "<<" and ">>" for bit move operation. Then parser can parse expression like "3 << 26", the result is 0x0C000000 in Hex format.

The files modified listed below:<br/>
org/apache/commons/jexl3/internal/Debugger.java<br/>
org/apache/commons/jexl3/internal/Interpreter.java<br/>
org/apache/commons/jexl3/parser/ParserVisitor.java<br/>
org/apache/commons/jexl3/parser/Parser.jjt<br/>
org/apache/commons/jexl3/JexlArithmetic.java<br/>
org/apache/commons/jexl3/JexlOperator.java<br/>

## Compile
1.  Install the latest version of [Maven](http://maven.apache.org/)
2.  Set environment in local operation system.
3.  Run commands in command line(Windows) or terminal(Unix/Linux).
3.1 mvn package<br/>
3.2 Copy jar file 'commons-jexl3-3.1.jar' generated by maven into project directory and add it to build path.

## Example code
````java
import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlContext;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;
import org.apache.commons.jexl3.MapContext;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		JexlBuilder builder = new JexlBuilder();
		JexlEngine engine = builder.create();
		JexlExpression expression = null;
		JexlContext context = null;

		context = new MapContext();
		context.set("l", 3);
		context.set("r", 26);

		expression = engine.createExpression("l << (r+1)");
		Object ret = expression.evaluate(context);

		if (ret instanceof Long) {
			Long val = (Long) ret;
			System.out.println(String.format("0x%08X", val.longValue()));
		}
	}
}
````
