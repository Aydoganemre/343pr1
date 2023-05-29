# 343pr1
How the code works:
-The algorithm starts with a random solution and it generates neighboring solutions by giving random values to the item at every step.
-If the new solution is better than the current solution, then it is accepted as the current solution.
-If the new solution is worse tha the current solution, then it can still be accepted with according to the probability determined by the calculateAcceptanceProbability function and the current temperature.
-The temperature starts high and decreases at each iteration.
-The process continues until the temperature reaches the minimum threshold, resulting in the best solution found during the iterations.
Q1) How does the execution time change when cooling rate increased? Report it by running the
same algorithm for the given dataset.

Answer: I will run 10 tests to find a better guess. I will change cooling rate from 0.03 to 0.15 to see the difference clearly.

Initial cooling rate:   13,22,17,17,23,29,23,15,22,17 Avg: 19,8 ms
Increased cooling rate: 20,19,19,16,20,24,21,17,20,20
Avg: 19,6 ms

According to this test, run time drops when we increase the cooling rate

Q2)How does the execution time and solution quality change when the difference between
starting temperature and stopping temperature is increased? Report it by running the same
algorithm for the given datasets.
With this algorithm it runs 5 times with temperature differences getting
smaller(0.1,0.2,0.3,0.4,0.5) by multiplication and each time runs 5 to
get average solution quality and run time. If we look for the average
solution value in order it goes like: 1251.8, 1238.4, 1241.2,1282.6 .For
average run time:1.011800ms,0.497700ms,0.538400ms,0.427400ms,0.424000ms.
If we look at these numbers we can see that when difference decreases
value decreases and also the run time is decreases too.
