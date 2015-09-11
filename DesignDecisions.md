# Design Decisions

This is a place to track some design decisions that maybe of interest to those in the community and may not be readily apparent at first glance.

## Why Lombok

[Lombok](https://projectlombok.org/) makes your code much easier to read and maintain. It is really easy to get started and the resulting library does not have a dependency on Lombok.

An important question by those in the "know" wonder why Lombok instead of [AutoValue](https://github.com/google/auto). AutoValue is nice and they have a good (albeit biases) [comparison of alternative solutions](https://docs.google.com/presentation/d/14u_h-lMn7f1rXE1nDiLX0azS3IkgjGl5uxp5jGJ75RE/edit). Both Lombok and AutoValue accomplish similar goals. In my opinion AutoValues is non-starter because if you rearrange member variables of your class the interface is broken. Yes, this would get caught in unit testing but the fact your have to refactor all your usages of that class just because you want to rearrange member variable is bad. You want to encourage and reduce friction to refactoring code. The whole purpose for Lombok and AutoValue is to make the developers life much easier and reduce programming errors. AutoValue breaks at least one of those goals. Since this project is focused on a library breaking the interface just by rearranging member variable is a problem that we can not absorb into this project.