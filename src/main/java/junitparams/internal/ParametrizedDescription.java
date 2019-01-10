package junitparams.internal;

import junitparams.naming.TestCaseNamingStrategy;
import org.junit.runner.Description;

import java.lang.annotation.Annotation;

class ParametrizedDescription {

    private TestCaseNamingStrategy namingStrategy;
    private String testClassName;
    private String methodName;

    ParametrizedDescription(TestCaseNamingStrategy namingStrategy, String testClassName, String methodName) {
        this.namingStrategy = namingStrategy;
        this.testClassName = testClassName;
        this.methodName = methodName;
    }

    Description parametrizedDescription(Object[] params, Annotation... annotations) {
        Description parametrised = Description.createSuiteDescription(methodName, annotations);
        for (int i = 0; i < params.length; i++) {
            Object paramSet = params[i];
            String name = namingStrategy.getTestCaseName(i, paramSet);
            parametrised.addChild(
                    Description.createTestDescription(testClassName, name, annotations)
            );
        }
        return parametrised;
    }
}
