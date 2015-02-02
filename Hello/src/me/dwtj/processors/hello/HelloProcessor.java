package me.dwtj.processors.hello;

import java.io.IOException;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
//import javax.lang.model.element.Element;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic.Kind;
import javax.tools.JavaFileObject;


/**
 * This class was adapted from `SillyProcessor` described at
 * <http://kerebus.com/2011/02/using-java-6-processors-in-eclipse/>
 * 
 * @author dwtj
 */
@SupportedAnnotationTypes(value= {"me.dwtj.processors.hello.Hello"})
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class HelloProcessor extends AbstractProcessor {

	@Override
	public boolean process(Set<? extends TypeElement> annotations,
						   RoundEnvironment roundEnv)
	{
		processingEnv.getMessager().printMessage(Kind.NOTE, "Hello, world!");;
		String msg = "package me.dwtj.processors.world;\n" 
			       + "public class HelloWorld {\n"
			       + "   public String hello = \"hello world\";\n"
			       + "}";
	
        JavaFileObject file = null;
        try {
                file = processingEnv.getFiler().createSourceFile("me/dwtj/processors/world/HelloWorld");
                file.openWriter().append(msg).close();
        } catch (IOException e) {
                e.printStackTrace();
        }
		return false;
	}
}