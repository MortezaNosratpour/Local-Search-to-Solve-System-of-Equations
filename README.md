# Local-Search-to-Solve-System-of-Equations
Implemented Hill Climbing and Simulated Annealing algorithms to solve system of equations in java


In this practical assignment, our goal is to explore the real-world applications of \textbf {Hill Climbing} and \textbf {Simulated Annealing}, which are local search techniques used in optimization problems. To illustrate their practical utility, we will tackle systems of equations that are challenging to solve using traditional mathematical methods. For instance, let's consider the following set of equations as an example:

       0.25a1+0.75a2+0.005a3+0.887a4+0.25a5+0.78a6+0.392a7+0.005a8+0.46a9+0.61a10=35.59
       0.23a1+0.07a2+0.35a3+0.75a4+0.2a5+0.68a6+0.89a7+0.15a8+0.27a9+0.64a10=49.25
       0.5828a1+0.2091a2+0.4154a3+0.2140a4+0.6833a5+0.4514a6+0.6085a9+0.72a10=−0.72
       0.76a1+0.059a2−0.7889a3−0.26a4+0.69a5−0.0928a6+0.63a7−0.72a8+0.23a9−0.17a10=93.225
       0.5155a1+0.7833a4+0.8744a5+0.32a6+0.8392a7+0.0272a8+0.0164a9=−54.9
       0.55a1+0.91a2+0.99a3+0.64a4+0.05a5+0.493a6+0.93a7+0.58a8+0.61a9+0.69a10=223.71
       0.01a1+0.006a2+0.7313a3+0.567a4+0.058a5+0.513a6+0.82a7+0.03a8+0.3527a9+0.41a10=−495.6

       
 Given the situation where we have 7 equations but 10 unknowns, solving the system of equations poses a challenging problem. This challenge arises from the fact that the system is underdetermined, meaning there are more unknowns than equations, which allows for a multitude of possible solutions.

To address this issue, one can explore various solutions within a specified range, such as [-1000, 1000], in an attempt to find an optimal or best-fit solution. However, it's important to note that this process may still yield multiple valid solutions, and without additional constraints or information, determining the "best" answer can be subjective or require further criteria to define what is considered optimal.
 
 Inputs:
 Your program will take a text file as input (please take a look at attached text
 file), which contains a system of equations. Each row in the file corresponds
 to one equation in the system. If you have a system with n unknowns, each
 row consists of n+1 numbers separated by commas. The first n numbers rep
resent the coefficients of the unknowns, and it’s important to note that these
 coefficients can be zero. The n+1th number is the constant on the right-hand
 side of the equation (review the attached file named ’coefficient.txt’).
 Additionally, your program must receive arguments specifying the start and
 end points of a range (e.g.,-1000 to 1000) and the step by which the un
knowns vary at each iteration.

 Outputs:
 The optimal solution for the system of equations (representing the most suit
able values for the unknowns resulting in the minimal error during the solving
 process), accompanied by the error value, and the overall time taken to solve
 the system
 
![Capture](https://github.com/MortezaNosratpour/Local-Search-to-Solve-System-of-Equations/assets/45389014/a5f92074-fc3a-4d86-be17-0a8dc91caf68)
