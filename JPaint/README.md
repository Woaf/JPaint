# JPaint
Logo: 
![JPaint logo](logo.jpg "JPaint")

## Project by: 
- Tünde Keller
- Bálint Fazekas
- Dávid Koszorú
- Kornél Konkolics

## Formatting: 
For this project, the default NetBeans Java code formatting is used.
(By default, pressing Alt+Shift+F should reformat the currently open source file.)
Here are these conventions:

### Naming: 
Camelcase naming for all variables and function names.
The names should reflect their purpose appropriately (as much as it is possible / necessary).
```
private int myInteger = 0;
private String myString = "text";
private double myDouble = 2.1;

public void writeMyText(String myText) {...}
private void setPageCenter() {...}
```

Capital letters with underscore separators for all constant values. 
```
public final static String WINDOW_TITLE = "Title";
public final static int WINDOW_WIDTH = 640;
public final static int WINDOW_HEIGHT = 420;
```

When naming constants, variables, and functions, try to give as detailed names as possible, without shortening words. 
For example:
 
Instead of `int myNum`, use `int myNumber`.

Instead of `void retMat2Vec(...) {...}`, use `void returnMatrixOfTwoVectors(...) {...}`.

### Spacing and indents:
The size of one tab indentation should be 4 spaces long. 

There should be one space after a comma. 
```
int myFunction(int x, int y) {...}
```
There should be one space after each function, right in front of the opening braces of the function. 
```
double myFunction(double x, double y) {...}
```
There should be one space before and after each value defining "=" mark. 
```
int x = 8;
int myNumber = 2;
```

### Braces: 
K&R style, such as: 
```
public void sample() {
    // code
}
```

### Classes:
Class naming follows the CapitalCamelCase convention. 
When defining a class, all public then private data members should come first. 
They are followed by the public then the private functions.
