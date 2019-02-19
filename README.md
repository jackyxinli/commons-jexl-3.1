commons-jexl-3.1
===========
I add bit move operator like "<<" and ">>" for bit move operation. Then parser can parse expression like "3 << 26", the result is 0x0C000000 in Hex format.

The files modified listed below:<br/>
org/apache/commons/jexl3/internal/Debugger.java<br/>
org/apache/commons/jexl3/internal/Interpreter.java<br/>
org/apache/commons/jexl3/parser/ParserVisitor.java<br/>
org/apache/commons/jexl3/parser/Parser.jjt<br/>
org/apache/commons/jexl3/JexlArithmetic.java<br/>
org/apache/commons/jexl3/JexlOperator.java<br/>
