# Method builder with Lombok @Builder

[[_TOC_]]

## Overview

In this tutorial we are going to explore the possibilities of generating 
method builders with [Lombok](https://projectlombok.org)'s 
[@Builder](https://projectlombok.org/features/Builder) annotation. 
The challenge is to provide a flexible way of calling a given method 
even if it has a lot of parameters.

## @Builder on simple methods

It is a general topic, how to provide a flexible usage for methods, 
which _might_ take multiple inputs. Take a look on the following example:
```
void method(@NotNull String firstParam, @NotNull String secondParam, 
            String thirdParam, String fourthParam, 
            Long fifthParam, @NotNull Object sixthParam) {
    ...            
}
```
This method could be called in many various way, a few examples:
```
method("A", "B", null, null, null, new Object());
method("A", "B", "C", null, 2L, "D");
method("A", "B", null, null, 3L, this);
```
This example already show some problematic points such as:
- the caller should know which parameter is which (e.g. in order to change 
  the first call to provide a Long too, the caller must know Long is expected 
  to be the fifth parameter)
- inputs must be set in a given order
- names of the input parameters are not transparent

In the meantime, from the provider's perspective, in order to provide methods 
with fewer parameters would mean a massive overloading of method name, such as:
```
void method(@NotNull String firstParam, @NotNull String secondParam, @NotNull Object sixthParam);
void method(@NotNull String firstParam, @NotNull String secondParam, String thirdParam, @NotNull Object sixthParam);
void method(@NotNull String firstParam, @NotNull String secondParam, String thirdParam, String fourthParam, @NotNull Object sixthParam);
void method(@NotNull String firstParam, @NotNull String secondParam, String thirdParam, String fourthParam, Long fifthParam, @NotNull Object sixthParam);
...
```

To achieve better usability and avoid boilerplate code method builders can be 
introduced. Project Lombok already provides an annotation in order to make usage 
of builders simple. The example method above could be annotated in the 
following way:
```
@Builder(builderMethodName = "methodBuilder", buildMethodName = "call")
void method(@NotNull String firstParam, @NotNull String secondParam, 
            String thirdParam, String fourthParam, 
            Long fifthParam, @NotNull Object sixthParam) {
    ...            
}
```
Thus, calling the method would look like:
```
methodBuilder()
        .firstParam("A")
        .secondParam("B")
        .sixthParam(new Object())
        .call();

methodBuilder()
        .firstParam("A")
        .secondParam("B")
        .thirdParam("C")
        .fifthParam(2L)
        .sixthParam("D")
        .call();

methodBuilder()
        .firstParam("A")
        .secondParam("B")
        .fifthParam(3L)
        .sixthParam(this)
        .call();
```

Thus the method call is much easier to understand and change later.
Some additional information:
- by default, a builder method (method to obtain a builder instance) on a static method, is going be itself also a static method.
- by default, the call() method will have the same throw signature as the original method

### Default values

In many cases it can be really helpful to define default values for the input parameters. Unlike some other languages (for example c++) JAVA does not have a language element to support this need. 
Therefore, in most of the cases this is reached via method overloading having structures like:
```
method() { method("Hello"); }
method(String a) { method(a, "builder"); }
method(String a, String b) { method(a, b, "world!"); }
method(String a, String b, String c) {
  ... acutal logic here ...
}
```
While using lombok builders a builder class is going to be created within the target class. This builder class:
- has as many properties as many arguments the method had
- has setters for its arguments

It is also possible to define the class manually, which also gives the possibility to define the default values for the parameters.
In this way the method above would look like:

```
@Builder(builderMethodName = "methodBuilder", buildMethodName = "call", builderClassName = "MethodBuilder")
method(String a, String b, String c) {
  ... acutal logic here ...
}

private class MethodBuilder {
    private String a = "Hello";
    private String b = "builder";
    private String c = "world!";
}
```
With this addition, if the caller does not specify one parameter, the default value defined in the builder class is going to be used.

Remark: in this case we do not have to declare all the input parameters of the method in the class. If an input parameter of the method
is not present in the class, Lombok will generate an additional property accordingly.

### Typed methods

It is a common need, to define the return type of a given method trough one of the inputs, such like:
```
public <T> T read(byte[] content, Class<T> type) {...}
```
In this case, the builder class will also be a typed class, but the builder method will create an instance without a bound type.
Take a look on the following example:
```
@Builder(builderMethodName = "methodBuilder", buildMethodName = "call", builderClassName = "MethodBuilder")
public <T> T read(byte[] content, Class<T> type) {...}
```
In this case, methodBuilder method is going to create an instance of MethodBuilder without any type parameters. 
This leads to the fact, that the following code will not compile (as required would be `Class<T>`, provided is `Class<String>`):
```
methodBuilder()
    .content(new byte[]{})
    .type(String.class)
    .call();
```
This can be resolved by casting the input of `type` and use it as:
```
methodBuilder()
    .content(new byte[]{})
    .type((Class)String.class)
    .call();
```
It will compile, but there is another aspect to mention: the return type of call method is not going to be String in this case, but still T. Therefore, the client have to cast the return type, like:
```
String result = (String)methodBuilder()
    .content(new byte[]{})
    .type((Class)String.class)
    .call();
```
This solution works, but it also requires the caller to cast both the input and the result. 
As the aim is to provide a caller-friendly way to invoke the methods, it is recommended to consider one of the two following options.

#### Override the builder method

As stated above, the root of the problem is, that the builder method creates an instance
of the builder class without specific type parameter. It is still possible to define the builder
method in the class and create an instance of the builder class with the desired type:

```
@Builder(builderMethodName = "methodBuilder", buildMethodName = "call", builderClassName = "MethodBuilder")
public <T> T read(final byte[] content, final Class<T> type) {...}

public <T extends Collection> MethodBuilder<T> methodBuilder(final Class<T> type) {
    return new MethodBuilder<T>().type(type);
}

public class MethodBuilder<T extends Collection> {
    private Class<T> type;
    public MethodBuilder<T> type(Class<T> type) { this.type = type; return this; }
    public T call() { return read(content, type); }
}
```
In this case the caller does not have to cast anytime and the call looks like:
```
String result = methodBuilder(String.class)
    .content(new byte[]{})
    .call();
```

#### Casting in the setter



## Conclusion

By using @Builder on methods can bring the following advantages:
- More flexibility on the callers side
- Default input values without method overloads
- Less boilerplate code for overrides
- Improved readability of the method calls
- Allowing fire similar calls via the same builder instance

In the meantime is also worth to mention that in some cases, using method builders can bring unnecessary complexity.