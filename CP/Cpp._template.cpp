#include <bits/stdc++.h>

using namespace std;

#pragma GCC optimize("Ofast,unroll-loops") 
#pragma GCC target("avx,avx2,fma") 

template<typename A, typename B> ostream& operator<<(ostream &os, const pair<A, B> &p) { return os << '(' << p.first << ", " << p.second << ')'; }
template<typename T_container, typename T = typename enable_if<!is_same<T_container, string>::value, typename T_container::value_type>::type> ostream& operator<<(ostream &os, const T_container &v) {string sep; for (const T &x : v) os << sep << x, sep = " "; return os<<endl; }
void dbg_out() { cerr << endl; }
template<typename Head, typename... Tail> void dbg_out(Head H, Tail... T) { cerr << ' ' << H; dbg_out(T...); }
#ifdef LOCAL
#define dbg(...) cerr << "(" << #__VA_ARGS__ << "):", dbg_out(__VA_ARGS__)
#else
#define dbg(...)
#endif

#define ll long long
#define ld long double
#define all(a) (a).begin(), (a).end()
vector<int> scan_vector(int n) {vector<int> v(n);for (int i = 0; i < n; i++)cin >> v[i];return v;}




constexpr ld PI = 3.1415926535897932384626433832795l;
const int MAX_N = 1e5 + 5;
const ll MOD = 1e9 + 7;

const ll INF = 1e9;
const ld EPS = 1e-9;

// -------------------------<RNG>------------------------- 
// RANDOM NUMBER GENERATOR
mt19937 RNG(std::random_device{}());  
#define SHUF(v) shuffle(all(v), RNG); 

// ----------------------<BITWISE>-------------------------- 
/* a=target variable, b=bit number to act upon 0-n */
#define BIT_SET(a,b) ((a) |= (1ULL<<(b)))
#define BIT_CLEAR(a,b) ((a) &= ~(1ULL<<(b)))
#define BIT_FLIP(a,b) ((a) ^= (1ULL<<(b)))

// '!!' to make sure this returns 0 or 1
#define BIT_CHECK(a,b) (!!((a) & (1ULL<<(b))))

#define BITMASK_SET(x, mask) ((x) |= (mask))
#define BITMASK_CLEAR(x, mask) ((x) &= (~(mask)))
#define BITMASK_FLIP(x, mask) ((x) ^= (mask))
#define BITMASK_CHECK_ALL(x, mask) (!(~(x) & (mask)))
#define BITMASK_CHECK_ANY(x, mask) ((x) & (mask))
// ----------------------</BITWISE END>-------------------------- 

// ----------------------<MATH>--------------------------- 
 
template<typename T> T gcd(T a, T b) { return (b ? __gcd(a, b) : a); } 

template<typename T> T lcm(T a, T b) { return (a * (b / gcd(a, b))); } 

int add(int a, int b, int c = MOD) { int res = a + b;
    return (res >= c ? res - c : res); } 
int mod_neg(int a, int b, int c = MOD) { int res;
    if (abs(a - b) < c) res = a - b; else res = (a - b) % c;
    return (res < 0 ? res + c : res); } 
int mul(int a, int b, int c = MOD) { ll res = (ll)a * b;
    return (res >= c ? res % c : res); } 
int muln(int a, int b, int c = MOD) { ll res = (ll)a * b;
    return ((res % c) + c) % c; } 
ll mulmod(ll a, ll b, ll m = MOD) { ll q = (ll)(((ld)a * (ld)b) / (ld)m);
    ll r = a * b - q * m; if (r > m) r %= m; if (r < 0) r += m; return r; } 
template<typename T> T expo(T e, T n) { T x = 1, p = e; while (n)
    { if (n & 1) x = x * p; p = p * p; n >>= 1; } return x; } 
template<typename T> T power(T e, T n, T m = MOD) { T x = 1, p = e; while (n)
    { if (n & 1) x = mul(x, p, m); p = mul(p, p, m); n >>= 1; } return x; } 
template<typename T> T extended_euclid(T a, T b, T &x, T &y)
    { T xx = 0, yy = 1; y = 0; x = 1; while (b) { T q = a / b, t = b; b = a % b; a = t; \
    t = xx; xx = x - q * xx; x = t; t = yy; yy = y - q * yy; y = t; } return a; }
template<typename T> T mod_inverse(T a, T n = MOD) { T x, y, z = 0;
    T d = extended_euclid(a, n, x, y); return (d > 1 ? -1 : mod_neg(x, z, n)); } 
  
// Permutation and Combination

// Precompute factorials and modular inverses
const int MAX_FACT = 1e5 + 5;
vector<int> fact(MAX_FACT), ifact(MAX_FACT);

void precompute_factorials() {
    fact[0] = ifact[0] = 1;
    for (int i = 1; i < MAX_FACT; i++) {
        fact[i] = mul(fact[i - 1], i, MOD);
        ifact[i] = mod_inverse<int>(fact[i], MOD);
    }
}
int ncr(int n, int r, int c = MOD) { 
    return mul(mul(ifact[r], ifact[n - r], c), fact[n], c); 
}  

// ----------------------</MATH>-------------------------- 

/****************** Geometry *****************/ 
template <typename T> inline T Pyramid(T base, T height)
{ return (1.0 / 3) * base * height; } 
template <typename T> inline T Ellipsoid(T r1, T r2, T r3) 
{ return (4.0 / 3) * PI * r1 * r2 * r3; } 
template <typename T> inline T IrregularPrism(T base, T height)
{ return base * height; } 
template <typename T> inline T Sphere(T radius)
{ return (4.0 / 3) * PI * radius * radius * radius; } 
template <typename T> inline T Cone(T radius, T height) 
{ return (1.0 / 3) * PI * radius * radius * height;}
/****************** Geometry end *****************/ 

void solve() {
    
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0); cout.tie(0);
    // int tc ;
    // cin >> tc;
    // precompute_factorials();
    // for (int t = 1; t <= tc; t++) {
        // cout << "Case #" << t << ": ";
        solve();
    // }
}
