# NATIVE CALL FROM JAVA CODE TO C CODE.

The keyword "native" on Java allow us to call native libraries, by using the JNI.

That document was based on [Baeldung](https://www.baeldung.com/jni) post and [Prof. Gustavo Leitão](https://www.youtube.com/watch?v=Ahcnr_qdCDQ) video about JNI.

## 1. Introduction
The JNI (_Java Native Interface_) allows Java to access codes on C and C++.

Commonly used for specifics performance cases, to handle hardwares or even use an existing library (not to need to rewrite).

## 2. The "native" key word.
When we are working with JNI, the _native_ key word need to be used.

That key word says for JVM: "That method has an implementation on other language".

Here is an example of a native method:

```
public native void myNativeMethod();
```

Native methods are like abstracts one, with the difference that its implementation will be done in a separated native code file instead of a _.java_ one.

## 3. The "System.loadLibrary()" method.

In order for the JVM to use the native code, it is necessary to use the static method _System.loadLibrary(String libName)_. That one will load the provided library from the file system into our memory.

Here is a call example:

```
System.loadLibrary(mylibrary);
```

It is very important to say that all library's name needs to start with the prefix "lib", for example: "libmylibrary". When loading through _System.loadLibrary_, the prefix can not be included on library's name.

Alternatively, we can use the command `System.load(D:\\my\\library\\path\\libmylibrary.dll`.

## 4. Step by step.
After a simple introduction, let's make an example.
### 4.1. Creating a Java program.
The first step is to create our Java class and declare its methods to be implemented on another language. As said before, we need to load our library, by using the method `System.loadLibrary(mylibrary)`.

![image](https://user-images.githubusercontent.com/71560285/209746001-9afd4095-5a4e-4b74-9d2e-3d0590b95e86.png)

Note that, after compile the file, there are no errors.

![MainClassCompileResult](https://user-images.githubusercontent.com/71560285/209746034-a3bcb62f-b7a9-46ae-8aa5-4adb55ca31bf.png)

### 4.2. Generating C header.
Java's compiler allows us to generete our C header automatically, using the command below:

```
javac Calculator.java -h ../c/
```

Where:<br>
`javac`: Executes the Java compiler.<br>
`Calculator.java`: Is the class that needs to be implemented in C.<br>
`-h`: Says for compiler where to save the native header file.<br>
`../c/`: Is the path where the header is going to be saved.<br>

After execute that command, a new _.h_ is generated. There, we can see all method's signature that we defined as native using the "native" key word.

![headerGenerated](https://user-images.githubusercontent.com/71560285/209746041-fb8eb3d8-4c65-4a26-b59a-eb45d14e73bf.png)

### 4.3. Implementing the methods with native code.
After all these steps, now we need to create the implementation of C code to the Java declared methods.

Let's create a new _.c_ file, there, we included the generated header file and implement a function for all methods, following its signature.

![CalculatorInC](https://user-images.githubusercontent.com/71560285/209746068-b96c4418-4209-421c-9020-38fe9b1c263e.png)

Notice that the methods signature was copy from the generated header, with the difference that all parameters have been changed to have a name that can be referenced in the code.

### 4.4. Compiling C class.
For compile the C class, I will use the _GCC_. If you are using Windows (like me), use that [step-by-step](https://www.youtube.com/watch?v=8Jc26cmTtKM) for stalling it. If you are using Linux, you must already have it installed.

To compile it, we can use the following command (you will have to change it for your case).

```
gcc -o libcalculator.dll -shared -I"your\java\home\include" -I"your\java\home\include\win32" -I"your\project\c" ./Calculator.c;
```

Where:<br>
`gcc`: Executes the GNU Compiler C.<br>
`-o`: Says for compiler where to save the generated file.<br>
`libcalculator.dll`: Is the name of the generated file.<br>
`-shared`: Says for compiler to create a shared library.<br>
`-I"your\java\home\include"`: Is the location of `jni.h`, that is used by `Calculator.h`.<br>
`-I"your\java\home\include\win32"`: Is the location of `jni_md.h` for Windows, that is also used by `Calculator.h`.<br>
`-I"your\project\c"`: Is the location of `Calculator.h`, that is used by `Calculator.c`.<br>
`./Calculator.c`: This is the file we want to compile<br>

All that code needs to be executed in one line. After it, a `libcalculator.dll` will be generated.

![LibGenerated](https://user-images.githubusercontent.com/71560285/209746080-76c71eaf-b61d-4b72-b322-eb2ab86fcc67.png)

### 4.5. Executing our program.
After all these steps, we are able to execute our program. Just do it by using the following command on `your/project/java`:

```
java -Djava.library.path=location/of/libcalculattor Calculator 2 2
```

**NOTE**: If you used the `System.load` instead of `System.loadLibrary`, the `-Djava.library.path=location/of/libcalculattor` argument does not need to be provided.

The following image shows the application working:

![Result](https://user-images.githubusercontent.com/71560285/209746013-b49383c7-f78d-4146-a208-9fd025811c92.png)
