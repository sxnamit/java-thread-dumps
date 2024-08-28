A deep dive on Java Thread Dumps, an invaluable tool to troubleshoot production issues.

#### Thread basics
- Basic unit of scheduling and execution.
- One (method) call stack per thread, or to think of it in reverse, one thread per (method) call stack; consequently each thread will have its own stacktrace.

Refer code examples,
com.sxnamit.jtd.basics.CallStack
com.sxnamit.jtd.basics.SleepyCallStack

#### What is thread dump
- Snapshot of all alive threads in the Java Virtual Machine (JVM) at a particular point in time.
- Captures state and other relevant details of each thread tracked by the JVM, along with its stacktrace (call stack), and bundles them all in a single dump file.

#### How to capture thread dump
1. SIGQUIT (kill -3 <pid>) on Linux, MacOS
    - Built-in, so preferrable
    - Prints thread dump in the stdout of the JVM process (<pid>)
    - Barebones format
    - Command:  
      ps aux| grep '[S]'leepyCallStack| awk '{print $2}'| args kill -3
2. jstack
    - Packaged with JDK; experimental, can be removed in future versions1
    - Prints thread dump in the terminal, so preferrable
    - Command  
    jstack [options] pid  
    jstack spec - https://docs.oracle.com/en/java/javase/21/docs/specs/man/jstack.html
3. jcmd
    - Packaged with JDK
    - Prints thread dump in the terminal, so preferrable
    - Command  
    jcmd <pid | main-class> Thread.print [options]  
    jcmd spec - https://docs.oracle.com/en/java/javase/21/docs/specs/man/jcmd.html
4. Visual VM
    - Provides a GUI
    - Not packaged with JDK since 9
    - Open source, to be downloaded separately
    - Remote JVM monitoring possible through JMX
5. jconsole
    - Packaged with JDK
    - Provides a GUI
    - Lists threads individually; not in a thread dump
    - Remote monitoring possible
6. Java Mission Control (JMC)
    - Not packaged with JDK since JDK 7 and JDK 8 (7u271 and 8u261)
    - Open source, to be downloaded separately
    - Provides a GUI

##### Note:
To get the pid, you can also use jps tool.  
jps spec - https://docs.oracle.com/en/java/javase/21/docs/specs/man/jps.html

#### When to capture thread dump
- Application hangs or freezes
- High CPU or memory usage
- Concurrency issues like stuck threads, deadlocks etc.
- Unexpected errors or exceptions
- Performance testing and tuning (interval captures)
- Integration testing or load testing

#### Thread call stack structure

```
"Garfield" #17 daemon prio=5 os_prio=31 cpu=0.15ms elapsed=3.02s tid=0x000000014e029e00 nid=0x6803 waiting on condition  [0x00000001709ee000]
   java.lang.Thread.State: TIMED_WAITING (sleeping)
    at java.lang.Thread.sleep(java.base@17.0.2/Native Method)
    at com.sxnamit.threaddumps.basics.SleepyCallStack.third(SleepyCallStack.java:36)
    at com.sxnamit.threaddumps.basics.SleepyCallStack.run(SleepyCallStack.java:44)
```
- Thread name ("Garfield")
- Thread ID (#17)
- Indication if the thread is a daemon thread (daemon)
- Thread priority inside JVM (prio=5); JVM platform dependent so not of much use
- Thread priority inside OS (os_prio=31)
- Time thread spent executing on the CPU (cpu=0.15ms)
- Wall clock time passed since the thread was started. (elapsed=3.02s )
- Thread ID, address of the thread structure in memory.(tid=0x000000014e029e00)
- ID of the native thread assigned to the JVM by OS (nid=0x6803)
- Condition part is optional
- Thread state, indicates what the thread was doing at the time thread dump was taken. JVM assigned state, not OS. Correlate with the last method call in the stack trace.
- Thread call stack or stack trace.

#####Note:
Note: Formats could vary between different JVM implementations ([e.g.](https://bugs.openjdk.org/browse/JDK-8200720)); we are using Open JDK here. Format also depends on the command used to generate the thread dump. For example, jstack strips the heap report; SIGQUIT doesn’t.

#### Thread states
TBD...






