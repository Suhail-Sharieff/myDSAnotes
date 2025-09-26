# Work 
- A can do total work in `x` days and B can `y` days then rate of A =`1/x` and rate of B=`1/y`, if they work together then total rate of work is `1/x + 1/y` work per day, ie they finish total work in `1/(1/x+1/y)` days
- A can do in `x` days and `B` can in `y` days, then find how mnay days it would take if A does for `m` days and B does for `n` days. Solution: just form 2 equations and solve
# Speed, Time, Distance
- These are mostly easy
- Uses relative velocicty, train platform problems etc
# Boat and river
- upstream net velocity = v(boat)+v(river)
- down stream net velocity = v(boat)-v(river)
- Man goes and comes back, means while going take upstreama nd while returning take downstream
- Man goes `x1`km upstream and `y1`km in `t1` hrs and `x2`km upstream and `y2` km downstream in `t2` hrs, then `x1/u+v  +  x2/u-v = t1` and similarly form for other case also, take `u+v` as p and `u-v` as q, solve both equations to get value of p and q , finally find p and q
# Ratio and proportions
- `a:b` and `b:c` is given, say `a:b`=`3:5` and `c:d`=`7:8` , ASKED `a:b:c`, to make it so in both eqns make some how b equal, in this case we can so by `a:b`=`21:35` and `b:c`=`35:8`, then `a:b:c`=`21:35:8`
- If `girls:boys` ratio given as `g:b`, and say `t` students in total, then `gx+bx=t` holds good
- If alloy `A` consists of `gold:silver` in `7:2`, whereas `B` has in `7:11`, when `C` is formed by melting A and B in equal ration, fing `g:c` in C. Imagine like `1g of A has 7/9 g gold and 2/9 g copper`, similar for B, then when mixed , ie `2g C has (7/9 + 7/18)g gold and (2/9 + 11/18)g Copper`
# Ages 
- These are also generally easy
# Areas
- `ar(traingle)=sqrt(s(s-a)(s-b)(s-c))` where `s=(a+b+c)>>1`
- Side of equilateral traingle inscribing a circle is `2root(3)r`, whereas the one excribing a circle is `root(3)r`
-  `ar(llgm)=b*h` also `(d1*d2)>>1`, same for rhombus
- `ar(trapezium)=h(a+b)>>1`
- `ar(sector)=(pi*pow(r,2)*thetha)/360`
- `length(segment)`=`2*pi*r*theta/180`
# Combinatorics
- `Permuatation`: ways to select and dearrange as well, `Combination`:ways to select only. ex: for string "abc" `3C2={ab,bc,ca}` whereas `3P2={ab,ba,bc,cb,ca,ac}`
- So `nPr=nCr*(r!)`
- Also `nCr=nC(n-r)`, for ex `100 C 98` = `100 C 2`, where RHS is much easier to calc
- No of ways to form new word using letters in `ENGINEERING`, `ans=(11!)/(3!*3!*2!*2!)` ie dont forget to divide by no of repetitions too
- `and` case `multiply`, `or`case `add`, when `atleast` given cosnider further cases too
# Simple Interest and Compound  Interest
- in case of simple interest the rate on initial money remains fixed, for ex 8k borrowed at 10% interest would mean that every year 10% of 8k needs to be paid irrespective of the current status of balance, in compund interest the money rate is with respect to current balance and not just the initial one.
- `SI=ptr/100` where t is always in years, so be careful since in majority cases time is given in days or months, p is the principal amt ie initial amt, r is rate
- `CI=P (1+(R1/100))^t1 * (1+(R2/100))^t2.....`
- Suppose say rate given for half yearly for 2 years then eqn transforms as `P(1+(R/200))^(2*2)`,observe both dr and power changes
- Now suppose given for 3 yr 3 months, eqn transforms as `P((1+(R/100))^3 * (1+(3R/1200))^1)`,  observe the split


# Clocks
- Minute hand covers `6 deg` per minute
- Hour hand covers `30 deg` per hr ie `1/2 deg` per min
- `MCQ: `min hand gains `55` minutes over hour hand
- Hour and minute hand coincides `once` per hour
- One tricy part is, question: `how many times will hr and min hand conincide say between 2 am and 2pm `, answer is NOT 12, its 11(think about case at 12 am), so the thing is `if x<12, then hr and min hand coincides x times in x hr interval`, if more then `x-(x/12) times`, same formula for number of times the hr and minute hand are in straight line or 180deg to each other
- Similary, for evrry 1 hr minute and hr hand becomes perpendicular `twice`, in `12 hrs 22 times`,`24 hrs 44 times`
- The mirror time can just be calculating by subtracting with 12, for ex the mirror timing for `5:17` is `6:43`

# Divisibility
- If sum of digits in a number is divisible by 3 then the whole number is also divisible by 3, same for 9 too
- If last 2 digits of a number is divisible by 4, then entire number is also divisible by 4
- If last 3 digits are divisible by 8, then entire number is also divisible by 8
- If `abs(sumOfDigitsAtEvenPlaces-sumOfDigitsAtOddPlaces)%11==0`, then that number is divisible by 11. For ex `1331`, abs((1+3)-(3+1)) ie 0%11==0, hence divisble by 11. `ExQ:` What is least value of `*` such that `78*3975` is divisible by 11. `Soln:`((8+*+9+5)-(8+3+7)) ie `(6+x)%11=0` ie `*=5`
- Any number divisible by 12 must be divisible by both 3 and 4

# Primes
- We just need to chk till `sqrt(n)` to check if `n` is a prime number

# Series
- Sum of 1st n nums `n(n+1)>>1`
- Sum of 1st n^2 nums `n(n+1)(2n+1)/6`
- Sum of 1st n^3 nums `(n(n+1)>>1)^2`
- `(a^3 + b^3)=(a+b)(a^2-ab+b^2)`
- `(a^3 - b^3)=(a-b)(a^2+ab+b^2)`
- If (a+b+c)=0 then (a^3+b^3+c^3)=3abc
 # Unit digits
 - Refer [.md](../algos/_0_Math/_04_UnitDigitOfK.md)

# Geometry
- In a polygon of `n` sides there are exactly `(nC2)-n` diagonals(line connecting 2 differenct verticies). `nC2` for the total lines,n for adj sides line connector
- Sum of all interiror angles of a `n` sided polygon is `(n-2)*180 deg`
- Any `n` sided polygon can be divided into `n-2` triangles
-  Sum of all exteriror angles is always `360 deg` in any polygon
- Sum of opp interiror angles = exterorr angle
- Also remeber parallel line interoror angle rule
- Sum of opp sides of a quadri is 180
- All angles subtended by chord are equal, all angles subtended by diameter is 90
- Angle subteneded by chord not toaching cirlce is twicw the angle subtended by chord toching cirlce
- When two chords are extended the `OA* Ob=OC * OD`
- 

# Calendar
- If a non century year has last two digits divisible by 4 then its a leap year , to check if a century year is leap check if its divisible by 400
- If there are `n` days , then there are `n%7` odd days
- There are `24(NOT 25)` leap years in 100 years and 76 normal years
- `IMP` In an ordinary year there are `365%7` ie 1 odd day and `2` in case of leap year. Ex: A man celebrated his birthday on 12 Aug 2023 on Monday, at what day will he celebrate birthday next year. Soln: Since 2024 is leap e 2 odd days/ extra days ie Monday+2 ie Wednesday
- 100 years has 76 normal + 24 leap, ie `((76%7)+(24*2)%7)%7` ie `5` odd days, ie after 100 years from today it will be thatDay + 5, similarly in 200 years there will be `(5*2)%7` ie 3 odd days, in 300 years `(5*3)%7` ie 1 odd day, but in 400 years there will be `((5*4)+1)%7` ie 0 odd days(+1 becoz 400th year willl be a leap year), similarly in case of 600 years calc(400+200) ie (0+3) odd days, also 1200,1600.. have 0 odd days, meaning year exactly divisible by 400 has 0 odd days 
- {0 odd day:Sunday,1 odd day :Mon.......6 odd days:Saturday}
- `Ex: What was the day of the week on 16th July 1776`. `Soln:`First 1776 we can write as (1600+100+75)yrs+(nDaysTill_16_July) ie {0 odd days in 1600 + 5 odd days in 100 yrs + {(75/4)leap*2 + remaining ie (18 *2)%7 + (75-18)%7} ie 2 odd days in 75 years}, so total (0+5+2)%7 ie 0 odd days till end of 1775. Now in 1776 we need to calc till 16 July,ie sumOfOddDaysInEachMonth ie {jan:31%7 + Feb:29%7....+ july:16%7}%7 ie 2 odd days, in nutshell (0+2) ie 2  odd days till 16 July 1776 ie it will be `Tuesday`
- Calencdar for some year `x` is as same as for the year `y`
- Last day of century cannot be  `Tuesday`   
# Directions
- Easy
 # Blood Relations
 - Just form family tree
 - `Niece`: my brother/sister's male child, female is `Nephew`
 - `Uncle/Aunt`: father/mother's sis or brother
 - `Brother in law`: my wife's brother, `Sister in law`:my husband's sister 
 # Water and mirror images
 - Mirror img flip towards left
 - Water img flip towards down
# Sitting Arrangements
- Generaly doable
# Venn Diagrams
- Doable
# Syllogism
- Statement based questions like `if all pencils are pen...like that`
- Solve my making Venn diagrams it will be easy to solve, for each statement read draw Venn diagram
 # Letter series
 - Just write `A to M` then `N to Z` in reverse with numbers
 - Remeber they can give cyclic orer too ir after Z, A is taken
 - Keep an eye by veryng with options too
 # Number Series
 - Practise
 
 # Coding Decoding
 - Letter Series+ Number Series Combo





