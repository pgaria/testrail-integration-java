# Solution to Update the TestRail TestCase Reports with the Results using both DataProvider and Normal Tests.

### Tech Stack:
- Java
- Gradle
- TestNG
- TestRail API Client

### Problem statement:
Ii you use TestNG data provider for parameterized tests. I understand that the test method execute once for every instance in the data provider returned array.
Now we want to Update the Test Rail Results for both the kind of Tests Normal and Data provided Tests.

We understand also that each test method execution is reported in TestNG report separately, So we can utilize this behaviour for our reporting.

TestNG does not contain any out of the box way of doing this. But you can still get this done using TestNG listeners. Here's how to do it. I am making use of TestNG 7.1.0 version.

1. First create a custom annotation which captures the Test Rail ID of a particular test.
2. Now annotate your @Test methods using the custom annotation we created to tie it down to a particular Test Rail testcase.
3. Now build a custom listener which ensures that it is able to differentiate between ordinary tests and data driven tests and post the results accordingly.
4. For data driven tests, they would need to keep track of all the iterations that have run so far and then compute the overall results.
5. Use the Listener in the Test.