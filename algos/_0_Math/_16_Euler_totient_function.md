## Euler's totient function

Euler's totient function, also known as phi-function  
$\phi (n)$ , counts the number of integers between 1 and  
$n$  inclusive, which are coprime to  
$n$ . Two numbers are coprime if their greatest common divisor equals  
$1$  ( 
$1$  is considered to be coprime to any number).

Here are values of  
$\phi(n)$  for the first few positive integers:

<math xmlns="http://www.w3.org/1998/Math/MathML" display="block">
  <mtable columnalign="center center center center center center center center center center center center center center center center center center center center center center" columnspacing="1em" rowspacing="4pt" columnlines="solid solid solid solid solid solid solid solid solid solid solid solid solid solid solid solid solid solid solid solid solid" rowlines="none solid none" frame="solid">
    <mtr>
      <mtd>
        <mi>n</mi>
      </mtd>
      <mtd>
        <mn>1</mn>
      </mtd>
      <mtd>
        <mn>2</mn>
      </mtd>
      <mtd>
        <mn>3</mn>
      </mtd>
      <mtd>
        <mn>4</mn>
      </mtd>
      <mtd>
        <mn>5</mn>
      </mtd>
      <mtd>
        <mn>6</mn>
      </mtd>
      <mtd>
        <mn>7</mn>
      </mtd>
      <mtd>
        <mn>8</mn>
      </mtd>
      <mtd>
        <mn>9</mn>
      </mtd>
      <mtd>
        <mn>10</mn>
      </mtd>
      <mtd>
        <mn>11</mn>
      </mtd>
      <mtd>
        <mn>12</mn>
      </mtd>
      <mtd>
        <mn>13</mn>
      </mtd>
      <mtd>
        <mn>14</mn>
      </mtd>
      <mtd>
        <mn>15</mn>
      </mtd>
      <mtd>
        <mn>16</mn>
      </mtd>
      <mtd>
        <mn>17</mn>
      </mtd>
      <mtd>
        <mn>18</mn>
      </mtd>
      <mtd>
        <mn>19</mn>
      </mtd>
      <mtd>
        <mn>20</mn>
      </mtd>
      <mtd>
        <mn>21</mn>
      </mtd>
    </mtr>
    <mtr>
      <mtd></mtd>
    </mtr>
    <mtr>
      <mtd>
        <mi>&#x3D5;</mi>
        <mo stretchy="false">(</mo>
        <mi>n</mi>
        <mo stretchy="false">)</mo>
      </mtd>
      <mtd>
        <mn>1</mn>
      </mtd>
      <mtd>
        <mn>1</mn>
      </mtd>
      <mtd>
        <mn>2</mn>
      </mtd>
      <mtd>
        <mn>2</mn>
      </mtd>
      <mtd>
        <mn>4</mn>
      </mtd>
      <mtd>
        <mn>2</mn>
      </mtd>
      <mtd>
        <mn>6</mn>
      </mtd>
      <mtd>
        <mn>4</mn>
      </mtd>
      <mtd>
        <mn>6</mn>
      </mtd>
      <mtd>
        <mn>4</mn>
      </mtd>
      <mtd>
        <mn>10</mn>
      </mtd>
      <mtd>
        <mn>4</mn>
      </mtd>
      <mtd>
        <mn>12</mn>
      </mtd>
      <mtd>
        <mn>6</mn>
      </mtd>
      <mtd>
        <mn>8</mn>
      </mtd>
      <mtd>
        <mn>8</mn>
      </mtd>
      <mtd>
        <mn>16</mn>
      </mtd>
      <mtd>
        <mn>6</mn>
      </mtd>
      <mtd>
        <mn>18</mn>
      </mtd>
      <mtd>
        <mn>8</mn>
      </mtd>
      <mtd>
        <mn>12</mn>
      </mtd>
    </mtr>
    <mtr>
      <mtd></mtd>
    </mtr>
  </mtable>
</math>

Properties¶
The following properties of Euler totient function are sufficient to calculate it for any number:

- If  
$p$  is a prime number, then  
$\gcd(p, q) = 1$  for all  
$1 \le q < p$ . Therefore we have:

$$\phi (p) = p - 1.$$ 
- If  
$p$  is a prime number and  
$k \ge 1$ , then there are exactly  
$p^k / p$  numbers between  
$1$  and  
$p^k$  that are divisible by  
$p$ . Which gives us:

$$\phi(p^k) = p^k - p^{k-1}.$$ 
- If  
$a$  and  
$b$  are relatively prime, then:

$$\phi(a b) = \phi(a) \cdot \phi(b).$$ 
This relation is not trivial to see. It follows from the Chinese remainder theorem. The Chinese remainder theorem guarantees, that for each  
$0 \le x < a$  and each  
$0 \le y < b$ , there exists a unique  
$0 \le z < a b$  with  
$z \equiv x \pmod{a}$  and  
$z \equiv y \pmod{b}$ . It's not hard to show that  
$z$  is coprime to  
$a b$  if and only if  
$x$  is coprime to  
$a$  and  
$y$  is coprime to  
$b$ . Therefore the amount of integers coprime to  
$a b$  is equal to product of the amounts of  
$a$  and  
$b$ .

In general, for not coprime  
$a$  and  
$b$ , the equation



$$\phi(ab) = \phi(a) \cdot \phi(b) \cdot \dfrac{d}{\phi(d)}$$ 
with  
$d = \gcd(a, b)$  holds.

Thus, using the first three properties, we can compute  
$\phi(n)$  through the factorization of  
$n$  (decomposition of  
$n$  into a product of its prime factors). If  
$n = {p_1}^{a_1} \cdot {p_2}^{a_2} \cdots {p_k}^{a_k}$ , where  
$p_i$  are prime factors of  
$n$ 

<math xmlns="http://www.w3.org/1998/Math/MathML" display="block">
  <mtable displaystyle="true" columnalign="right left" columnspacing="0em" rowspacing="3pt">
    <mtr>
      <mtd>
        <mi>&#x3D5;</mi>
        <mo stretchy="false">(</mo>
        <mi>n</mi>
        <mo stretchy="false">)</mo>
      </mtd>
      <mtd>
        <mi></mi>
        <mo>=</mo>
        <mi>&#x3D5;</mi>
        <mo stretchy="false">(</mo>
        <msup>
          <mrow data-mjx-texclass="ORD">
            <msub>
              <mi>p</mi>
              <mn>1</mn>
            </msub>
          </mrow>
          <mrow data-mjx-texclass="ORD">
            <msub>
              <mi>a</mi>
              <mn>1</mn>
            </msub>
          </mrow>
        </msup>
        <mo stretchy="false">)</mo>
        <mo>&#x22C5;</mo>
        <mi>&#x3D5;</mi>
        <mo stretchy="false">(</mo>
        <msup>
          <mrow data-mjx-texclass="ORD">
            <msub>
              <mi>p</mi>
              <mn>2</mn>
            </msub>
          </mrow>
          <mrow data-mjx-texclass="ORD">
            <msub>
              <mi>a</mi>
              <mn>2</mn>
            </msub>
          </mrow>
        </msup>
        <mo stretchy="false">)</mo>
        <mo>&#x22EF;</mo>
        <mi>&#x3D5;</mi>
        <mo stretchy="false">(</mo>
        <msup>
          <mrow data-mjx-texclass="ORD">
            <msub>
              <mi>p</mi>
              <mi>k</mi>
            </msub>
          </mrow>
          <mrow data-mjx-texclass="ORD">
            <msub>
              <mi>a</mi>
              <mi>k</mi>
            </msub>
          </mrow>
        </msup>
        <mo stretchy="false">)</mo>
      </mtd>
    </mtr>
    <mtr>
      <mtd></mtd>
    </mtr>
    <mtr>
      <mtd></mtd>
      <mtd>
        <mi></mi>
        <mo>=</mo>
        <mrow data-mjx-texclass="INNER">
          <mo data-mjx-texclass="OPEN">(</mo>
          <msup>
            <mrow data-mjx-texclass="ORD">
              <msub>
                <mi>p</mi>
                <mn>1</mn>
              </msub>
            </mrow>
            <mrow data-mjx-texclass="ORD">
              <msub>
                <mi>a</mi>
                <mn>1</mn>
              </msub>
            </mrow>
          </msup>
          <mo>&#x2212;</mo>
          <msup>
            <mrow data-mjx-texclass="ORD">
              <msub>
                <mi>p</mi>
                <mn>1</mn>
              </msub>
            </mrow>
            <mrow data-mjx-texclass="ORD">
              <msub>
                <mi>a</mi>
                <mn>1</mn>
              </msub>
              <mo>&#x2212;</mo>
              <mn>1</mn>
            </mrow>
          </msup>
          <mo data-mjx-texclass="CLOSE">)</mo>
        </mrow>
        <mo>&#x22C5;</mo>
        <mrow data-mjx-texclass="INNER">
          <mo data-mjx-texclass="OPEN">(</mo>
          <msup>
            <mrow data-mjx-texclass="ORD">
              <msub>
                <mi>p</mi>
                <mn>2</mn>
              </msub>
            </mrow>
            <mrow data-mjx-texclass="ORD">
              <msub>
                <mi>a</mi>
                <mn>2</mn>
              </msub>
            </mrow>
          </msup>
          <mo>&#x2212;</mo>
          <msup>
            <mrow data-mjx-texclass="ORD">
              <msub>
                <mi>p</mi>
                <mn>2</mn>
              </msub>
            </mrow>
            <mrow data-mjx-texclass="ORD">
              <msub>
                <mi>a</mi>
                <mn>2</mn>
              </msub>
              <mo>&#x2212;</mo>
              <mn>1</mn>
            </mrow>
          </msup>
          <mo data-mjx-texclass="CLOSE">)</mo>
        </mrow>
        <mo>&#x22EF;</mo>
        <mrow data-mjx-texclass="INNER">
          <mo data-mjx-texclass="OPEN">(</mo>
          <msup>
            <mrow data-mjx-texclass="ORD">
              <msub>
                <mi>p</mi>
                <mi>k</mi>
              </msub>
            </mrow>
            <mrow data-mjx-texclass="ORD">
              <msub>
                <mi>a</mi>
                <mi>k</mi>
              </msub>
            </mrow>
          </msup>
          <mo>&#x2212;</mo>
          <msup>
            <mrow data-mjx-texclass="ORD">
              <msub>
                <mi>p</mi>
                <mi>k</mi>
              </msub>
            </mrow>
            <mrow data-mjx-texclass="ORD">
              <msub>
                <mi>a</mi>
                <mi>k</mi>
              </msub>
              <mo>&#x2212;</mo>
              <mn>1</mn>
            </mrow>
          </msup>
          <mo data-mjx-texclass="CLOSE">)</mo>
        </mrow>
      </mtd>
    </mtr>
    <mtr>
      <mtd></mtd>
    </mtr>
    <mtr>
      <mtd></mtd>
      <mtd>
        <mi></mi>
        <mo>=</mo>
        <msubsup>
          <mi>p</mi>
          <mn>1</mn>
          <mrow data-mjx-texclass="ORD">
            <msub>
              <mi>a</mi>
              <mn>1</mn>
            </msub>
          </mrow>
        </msubsup>
        <mo>&#x22C5;</mo>
        <mrow data-mjx-texclass="INNER">
          <mo data-mjx-texclass="OPEN">(</mo>
          <mn>1</mn>
          <mo>&#x2212;</mo>
          <mfrac>
            <mn>1</mn>
            <msub>
              <mi>p</mi>
              <mn>1</mn>
            </msub>
          </mfrac>
          <mo data-mjx-texclass="CLOSE">)</mo>
        </mrow>
        <mo>&#x22C5;</mo>
        <msubsup>
          <mi>p</mi>
          <mn>2</mn>
          <mrow data-mjx-texclass="ORD">
            <msub>
              <mi>a</mi>
              <mn>2</mn>
            </msub>
          </mrow>
        </msubsup>
        <mo>&#x22C5;</mo>
        <mrow data-mjx-texclass="INNER">
          <mo data-mjx-texclass="OPEN">(</mo>
          <mn>1</mn>
          <mo>&#x2212;</mo>
          <mfrac>
            <mn>1</mn>
            <msub>
              <mi>p</mi>
              <mn>2</mn>
            </msub>
          </mfrac>
          <mo data-mjx-texclass="CLOSE">)</mo>
        </mrow>
        <mo>&#x22EF;</mo>
        <msubsup>
          <mi>p</mi>
          <mi>k</mi>
          <mrow data-mjx-texclass="ORD">
            <msub>
              <mi>a</mi>
              <mi>k</mi>
            </msub>
          </mrow>
        </msubsup>
        <mo>&#x22C5;</mo>
        <mrow data-mjx-texclass="INNER">
          <mo data-mjx-texclass="OPEN">(</mo>
          <mn>1</mn>
          <mo>&#x2212;</mo>
          <mfrac>
            <mn>1</mn>
            <msub>
              <mi>p</mi>
              <mi>k</mi>
            </msub>
          </mfrac>
          <mo data-mjx-texclass="CLOSE">)</mo>
        </mrow>
      </mtd>
    </mtr>
    <mtr>
      <mtd></mtd>
    </mtr>
    <mtr>
      <mtd></mtd>
      <mtd>
        <mi></mi>
        <mo>=</mo>
        <mi>n</mi>
        <mo>&#x22C5;</mo>
        <mrow data-mjx-texclass="INNER">
          <mo data-mjx-texclass="OPEN">(</mo>
          <mn>1</mn>
          <mo>&#x2212;</mo>
          <mfrac>
            <mn>1</mn>
            <msub>
              <mi>p</mi>
              <mn>1</mn>
            </msub>
          </mfrac>
          <mo data-mjx-texclass="CLOSE">)</mo>
        </mrow>
        <mo>&#x22C5;</mo>
        <mrow data-mjx-texclass="INNER">
          <mo data-mjx-texclass="OPEN">(</mo>
          <mn>1</mn>
          <mo>&#x2212;</mo>
          <mfrac>
            <mn>1</mn>
            <msub>
              <mi>p</mi>
              <mn>2</mn>
            </msub>
          </mfrac>
          <mo data-mjx-texclass="CLOSE">)</mo>
        </mrow>
        <mo>&#x22EF;</mo>
        <mrow data-mjx-texclass="INNER">
          <mo data-mjx-texclass="OPEN">(</mo>
          <mn>1</mn>
          <mo>&#x2212;</mo>
          <mfrac>
            <mn>1</mn>
            <msub>
              <mi>p</mi>
              <mi>k</mi>
            </msub>
          </mfrac>
          <mo data-mjx-texclass="CLOSE">)</mo>
        </mrow>
      </mtd>
    </mtr>
  </mtable>
</math>


