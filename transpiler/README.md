libldt3-transpiler
==================

The libldt3-transpiler is used to convert the Java sources to other programming
languages. You can find usage information for the different types in the
sections below.

The basic approach is to read the code using [Spoon](https://spoon.gforge.inria.fr/)
library. It offers a Java model, which is transpiled using
[FreeMarker](https://freemarker.apache.org/) templates.

C#
--

To create the C# code run the TranspileCsharp#main() method. This will read
produce updated code in the *cs* directory. Please note that the previous code
will be deleted.

It is strongly recommend to run "dotnet format" before making use of the code.