1. fixedThreadPool
2. CachedThreadPool
3.ScheduledPool

service.schedule(new Task, 10, SECONDS)
service.scheduleAtFixedDelay()
service.scheduleWithFixedDelay(new Task, 10,10,TImeUnit.SECONDS)


4.Single ThreadedExecutor

recreates thread if killed because of task

task 1 is always run before task 2 and task 2 aslways runs before task 3

sequentially