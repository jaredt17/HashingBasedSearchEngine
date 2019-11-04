README File for CS3310 - HW3 by Jared Teller and Joseph Manto
_______________________________________________
Compile this code using Java version 1.8 sdk
Run in Java eclipse oxygen June 2018 environment

Assumptions: 
-urls.txt file will have the following format: 
line 1: url.com
line 2: keys for url
line 3: url2.com
line 4: keys for url2 
... continue until end of file

Limitations: 
Output file will be given in a readme file called output.txt

The following errors were reported when compiling in javac and all have to do
with Generics in Java (nothing serious). We went as far as to fix every
warning reported by IntelliJ and Eclipse but still receive these. 

C:\Users\jared\IdeaProjects\Hw3\src\edu\wmich\cs3310\JTellerJManto>javac -Xlint:all *.java
HashMapStats.java:23: warning: [fallthrough] possible fall-through into case
            default:
            ^
Map.java:10: warning: [rawtypes] found raw type: MapNode
        bucket = new MapNode[startSize];
                     ^
  missing type arguments for generic class MapNode<T>
  where T is a type-variable:
    T extends Object declared in class MapNode
Map.java:10: warning: [unchecked] unchecked conversion
        bucket = new MapNode[startSize];
                 ^
  required: MapNode<T>[]
  found:    MapNode[]
  where T is a type-variable:
    T extends Object declared in class Map
Map.java:70: warning: [rawtypes] found raw type: MapNode
        bucket = new MapNode[bucket.length*2];
                     ^
  missing type arguments for generic class MapNode<T>
  where T is a type-variable:
    T extends Object declared in class MapNode
Map.java:70: warning: [unchecked] unchecked conversion
        bucket = new MapNode[bucket.length*2];
                 ^
  required: MapNode<T>[]
  found:    MapNode[]
  where T is a type-variable:
    T extends Object declared in class Map
5 warnings