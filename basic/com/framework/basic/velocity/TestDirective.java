package com.framework.basic.velocity;

import java.io.IOException;
import java.io.Writer;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;
import org.apache.velocity.runtime.parser.node.SimpleNode;

/**
 * 
 * 自定义测试标签.
 * 
 * @Project FrameWork
 * 
 * @Version 1.0.0
 * 
 * @JDK version used 6.0
 * 
 * @Modification history none
 * 
 */
public class TestDirective extends Directive{

	@Override
	public String getName() {
		return "testInfo";
	}

	@Override
	public int getType() {
		return LINE;
	}

	@Override
	public boolean render(InternalContextAdapter context, Writer writer,
			Node node) throws IOException, ResourceNotFoundException,
			ParseErrorException, MethodInvocationException 
	{
		int argsNum = node.jjtGetNumChildren();
		for(int step = 0 ; step < argsNum; step++)
		{
			SimpleNode simpleNode = (SimpleNode) node.jjtGetChild(step);

			Object argObject = simpleNode.value(context);

			//传入参数
			if(argObject instanceof String)
			{
				System.out.println((String)argObject);
				writer.write((String)argObject);
				writer.flush();
			}
		}
		return true;
	}
}
