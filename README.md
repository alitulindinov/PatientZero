# Assignment 4. Patient Zero

**Student ID:** 250925  
**Name:** Ali Tulindinov  
**Group:** SE-2511

## Program output

The 9 lines printed by my program on my data:

```text
Mutations: 85
Shared core: 1162
Marker hits: 4
First hit: 6588
Border length: 4
Shared words: 528
Tests needed: 44
Cheapest testing: 202
Longest chain: 31
```

My outbreak report: screenshot of `output/outbreak.html` saved as `output/outbreak.png`.

## Questions

**Mission 1. What does the cell d[i][j] of your table mean, and how is it computed from other cells?**

The cell `d[i][j]` represents the minimum number of operations needed to transform the first `i` characters of string A into the first `j` characters of string B. If the characters are equal, we take the diagonal value. Otherwise, we take the minimum of insertion, deletion, and substitution, and add 1.

**Mission 2. How is the longest common subsequence different from the longest common substring? My Shared core is bigger than the number of letters that are the same at the same place: why?**

A subsequence keeps the original order of characters but does not need to be continuous. A substring must contain consecutive characters. Therefore, LCS can find matching characters even when they are in different positions in the two strings.

**Mission 3. What does the prefix function tell you, and why does the pointer in the text never go back in KMP? What is the Border length of my marker and what does it mean?**

The prefix function shows the length of the longest proper prefix that is also a suffix for each prefix of the pattern. KMP uses this information to avoid checking characters again, so the text pointer never moves backwards. My marker has a Border length of 4, meaning its longest proper prefix that is also a suffix has 4 characters.

**Mission 4. Why must you compare the letters after the hashes are equal? What happens to the cost of Rabin-Karp if many windows have the same hash?**

Different strings can have the same hash because of hash collisions. Therefore, we must compare the actual characters to confirm a match. If many windows have the same hash, more character comparisons are needed and the algorithm can become slower.

**Mission 5. What are the two states of a person in your DP, and how do you get the answer for a person from its children? Why does a greedy "test the person with most links first" not always give the cheapest answer?**

The two states are whether a person is tested or not tested. If a person is not tested, all their children must be tested. If the person is tested, each child can be tested or not tested, depending on which option has the lower cost. A greedy approach does not always find the minimum cost because choosing one person can affect the best choices for their neighbours.

## AI use
I used ChatGPT occasionally to clarify some algorithm concepts and understand a few coding errors. Most of the implementation and testing were done by myself. I used the provided Check.java tool to verify my solutions.

## Timing (Mission 7)

Milliseconds, average of 3 runs, from `java -cp out Bench`.

| test | size | time |
|---|---|---|
| naive search | 20 000 | 70.5 ms |
| naive search | 40 000 | 142.7 ms |
| naive search | 80 000 | 298.1 ms |
| KMP | 20 000 | 2.4 ms |
| KMP | 40 000 | 3.2 ms |
| KMP | 80 000 | 0.6 ms |
| edit distance | 500 | 7.1 ms |
| edit distance | 1000 | 11.6 ms |
| edit distance | 2000 | 23.9 ms |
| edit distance | 4000 | 118.4 ms |

Naive search takes approximately twice as long when the text size doubles. KMP is much faster and has linear time complexity, although the measured times are very small and fluctuate between runs. Edit distance becomes significantly slower as the input strings grow because its time complexity is O(n × m).




## Complexity

| Algorithm | Time | Space | Assumptions |
|---|---|---|---|
| Edit distance | O(n × m) | O(n × m) | Two strings of lengths n and m |
| LCS | O(n × m) | O(n × m) | Two strings of lengths n and m |
| Prefix function and KMP | O(n + m) | O(m) | n = text length, m = pattern length |
| Rabin-Karp (shared words) | Expected O(n + m + k × r), worst-case O(n × m × k) | O(m) | r = number of hash candidates checked; assumes hash collisions are rare in the expected case |
| Tree DP | O(v) | O(v) | The input is a tree with v vertices |

