# Nullable

Nullable uses Scala's [value classes][value-classes] to create an Option-like
type that allows for safe and performant use of null values. Unlike Option,
Nullable is represented as a simple reference at compile time, so there is no
creation of a wrapper object like there is for Some.

## License

Nullable is released under the [3-clause BSD license][bsd], which may be found in the
LICENSE file at the root of this distribution.

[value-classes]: (http://docs.scala-lang.org/overviews/core/value-classes.html)
[bsd]: (http://opensource.org/licenses/BSD-3-Clause)
