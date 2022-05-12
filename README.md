# Hibernate CteInsertStrategy Exception

## Prerequisites

- OpenJDK 17
- Docker & Docker Compose

## Steps for reproducing the error

```
git clone git@github.com:haba713/hibernate-cte-insert-strategy-issue.git
cd hibernate-cte-insert-strategy-issue/
docker-compose up -d                                # Start DB.
./gradlew test                                      # Fails.
cat build/test-results/test/TEST-haba713.MyTest.xml # See the exception.
git checkout hibernate568
./gradlew test                                      # Works fine.
```

## Resolved!

The issue is resolved. Hibernate can't detect the correct PostgreSQL dialect
version because of this configuration property: 

```xml
<property name="hibernate.temp.use_jdbc_metadata_defaults">false</property>
```

Remove the line from [hibernate.cfg.xml](src/test/resources/hibernate.cfg.xml)
and run tests again:

```
git checkout hibernate6_resolved
docker-compose up -d
./gradlew test
```

## IllegalStateException, UnsupportedOperationException
```
java.lang.IllegalStateException: PostInitCallback queue could not be processed...
- PostInitCallbackEntry - Entity(haba713.MyEntity) `sqmMultiTableInsertStrategy` interpretation
at org.hibernate.metamodel.mapping.internal.MappingModelCreationProcess.executePostInitCallbacks(MappingModelCreationProcess.java:146)
at org.hibernate.metamodel.mapping.internal.MappingModelCreationProcess.execute(MappingModelCreationProcess.java:90)
at org.hibernate.metamodel.mapping.internal.MappingModelCreationProcess.process(MappingModelCreationProcess.java:39)
at org.hibernate.metamodel.model.domain.internal.MappingMetamodelImpl.finishInitialization(MappingMetamodelImpl.java:228)
at org.hibernate.metamodel.internal.RuntimeMetamodelsImpl.finishInitialization(RuntimeMetamodelsImpl.java:60)
at org.hibernate.internal.SessionFactoryImpl.<init>(SessionFactoryImpl.java:308)
at org.hibernate.boot.internal.SessionFactoryBuilderImpl.build(SessionFactoryBuilderImpl.java:415)
at org.hibernate.cfg.Configuration.buildSessionFactory(Configuration.java:746)
at org.hibernate.cfg.Configuration.buildSessionFactory(Configuration.java:765)
at haba713.MyTest.setUpBeforeClass(MyTest.java:23)
at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
at java.base/java.lang.reflect.Method.invoke(Method.java:568)
at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:59)
at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:56)
at org.junit.internal.runners.statements.RunBefores.invokeMethod(RunBefores.java:33)
at org.junit.internal.runners.statements.RunBefores.evaluate(RunBefores.java:24)
at org.junit.internal.runners.statements.RunAfters.evaluate(RunAfters.java:27)
at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
at org.junit.runners.ParentRunner.run(ParentRunner.java:413)
at org.gradle.api.internal.tasks.testing.junit.JUnitTestClassExecutor.runTestClass(JUnitTestClassExecutor.java:110)
at org.gradle.api.internal.tasks.testing.junit.JUnitTestClassExecutor.execute(JUnitTestClassExecutor.java:58)
at org.gradle.api.internal.tasks.testing.junit.JUnitTestClassExecutor.execute(JUnitTestClassExecutor.java:38)
at org.gradle.api.internal.tasks.testing.junit.AbstractJUnitTestClassProcessor.processTestClass(AbstractJUnitTestClassProcessor.java:62)
at org.gradle.api.internal.tasks.testing.SuiteTestClassProcessor.processTestClass(SuiteTestClassProcessor.java:51)
at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
at java.base/java.lang.reflect.Method.invoke(Method.java:568)
at org.gradle.internal.dispatch.ReflectionDispatch.dispatch(ReflectionDispatch.java:36)
at org.gradle.internal.dispatch.ReflectionDispatch.dispatch(ReflectionDispatch.java:24)
at org.gradle.internal.dispatch.ContextClassLoaderDispatch.dispatch(ContextClassLoaderDispatch.java:33)
at org.gradle.internal.dispatch.ProxyDispatchAdapter$DispatchingInvocationHandler.invoke(ProxyDispatchAdapter.java:94)
at jdk.proxy1/jdk.proxy1.$Proxy2.processTestClass(Unknown Source)
at org.gradle.api.internal.tasks.testing.worker.TestWorker$2.run(TestWorker.java:176)
at org.gradle.api.internal.tasks.testing.worker.TestWorker.executeAndMaintainThreadName(TestWorker.java:129)
at org.gradle.api.internal.tasks.testing.worker.TestWorker.execute(TestWorker.java:100)
at org.gradle.api.internal.tasks.testing.worker.TestWorker.execute(TestWorker.java:60)
at org.gradle.process.internal.worker.child.ActionExecutionWorker.execute(ActionExecutionWorker.java:56)
at org.gradle.process.internal.worker.child.SystemApplicationClassLoaderWorker.call(SystemApplicationClassLoaderWorker.java:133)
at org.gradle.process.internal.worker.child.SystemApplicationClassLoaderWorker.call(SystemApplicationClassLoaderWorker.java:71)
at worker.org.gradle.process.internal.worker.GradleWorkerMain.run(GradleWorkerMain.java:69)
at worker.org.gradle.process.internal.worker.GradleWorkerMain.main(GradleWorkerMain.java:74)
Suppressed: java.lang.UnsupportedOperationException: CteInsertStrategy can only be used with Dialects that support CTE that can take UPDATE or DELETE statements as well
	at org.hibernate.query.sqm.mutation.internal.cte.CteInsertStrategy.<init>(CteInsertStrategy.java:123)
	at org.hibernate.query.sqm.mutation.internal.cte.CteInsertStrategy.<init>(CteInsertStrategy.java:107)
	at org.hibernate.dialect.PostgreSQLDialect.getFallbackSqmInsertStrategy(PostgreSQLDialect.java:704)
	at org.hibernate.query.sqm.mutation.internal.SqmMutationStrategyHelper.resolveInsertStrategy(SqmMutationStrategyHelper.java:87)
	at org.hibernate.persister.entity.AbstractEntityPersister.interpretSqmMultiTableInsertStrategy(AbstractEntityPersister.java:5904)
	at org.hibernate.persister.entity.AbstractEntityPersister.lambda$prepareMappingModel$17(AbstractEntityPersister.java:5755)
	at org.hibernate.metamodel.mapping.internal.MappingModelCreationProcess$PostInitCallbackEntry.process(MappingModelCreationProcess.java:210)
	at org.hibernate.metamodel.mapping.internal.MappingModelCreationProcess.executePostInitCallbacks(MappingModelCreationProcess.java:108)
	... 44 more
```
