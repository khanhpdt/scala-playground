# Chapter 6

## Immutable vs. Mutable objects

Pros:
- Immutable objects are often easier to reason about than mutable ones, because they do not have complex state spaces that change over time. 
- You can pass immutable objects around quite freely, whereas you may need to make defensive copies of mutable objects before passing them to other code. 
- There is no way for two threads concurrently accessing an immutable to corrupt its state once it has been properly constructed, because no thread can change the state of an immutable. 
- Immutable objects make safe hash table keys. If a mutable object is mutated after it is placed into a HashSet, for example, that object may not be found the next time you look into the HashSet.

Cons:
- Immutable objects sometimes require a large object graph be copied, whereas an update could be done in its place.

## Naming convention

Use camelCase.

Identifiers in user programs should not contain `$' characters, even though it will compile; if they do, this might lead to name clashes with identifiers generated by the Scala compiler.

In Java, the convention is to give constants names that are all upper case, with underscores separating the words, such as MAX_VALUE or PI. 
In Scala, the convention is merely that the first character should be upper case. Thus, constants named in the Java style, such as X_OFFSET, will work as Scala constants, but the Scala convention is to use camel case for constants, such as XOffset.

Although underscores are legal in identifiers, they are not used that often in Scala programs, in part to be consistent with Java, but also because underscores have many other non-identifier uses in Scala code. As a result, it is best to avoid identifiers like to_string, \_\_init\_\_, or name_.
