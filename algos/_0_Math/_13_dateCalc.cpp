#include <iostream>
#include <cstdlib> // For exit()
using namespace std;

class date {
    int flag, dd, mm, yy;

public:
    date(int d, int m, int y) {
        dd = d;
        mm = m;
        yy = y;
        flag = (y % 4 == 0) ? 1 : 0;

        if ((m == 2 && d > 28 && flag == 0) || d > 31 || m > 12) {
            cout << "Error: Invalid date." << endl;
            exit(0);
        }
    }

    int return_date(date d);
    int operator-(date d2);
    date operator+(int n);
    friend ostream& operator<<(ostream& out, date d1);
};

int a[20] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
int b[20] = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

int date::return_date(date d) {
    int total_days = d.dd;
    for (int i = 0; i < d.mm; i++) {
        total_days += (d.flag == 1) ? b[i] : a[i];
    }
    return total_days;
}

int date::operator-(date d2) {
    date d1(dd, mm, yy);
    int days1 = return_date(d1);
    int days2 = return_date(d2);

    if (yy < d2.yy || (yy == d2.yy && mm < d2.mm) || (yy == d2.yy && mm == d2.mm && dd < d2.dd)) {
        cout << "Error: Second date is greater than the first date." << endl;
        exit(0);
    }

    int total_days = days1 - days2;
    for (int i = d2.yy; i < d1.yy; i++) {
        total_days += (i % 4 == 0) ? 366 : 365;
    }

    return total_days;
}

date date::operator+(int n) {
    date d(dd, mm, yy);
    while (n--) {
        d.dd++;
        if (d.flag == 1 && d.dd > b[d.mm]) {
            d.dd = 1;
            d.mm++;
        } else if (d.flag == 0 && d.dd > a[d.mm]) {
            d.dd = 1;
            d.mm++;
        }
        if (d.mm > 12) {
            d.mm = 1;
            d.yy++;
        }
    }
    return d;
}

ostream& operator<<(ostream& out, date d1) {
    out << d1.dd << "-" << d1.mm << "-" << d1.yy;
    return out;
}

int main() {
    int d, m, y, no_of_days;

    cout << "Enter a valid date (dd mm yyyy): ";
    cin >> d >> m >> y;
    date d1(d, m, y);

    cout << "Enter a valid date which is lesser than the first date: ";
    cin >> d >> m >> y;
    date d2(d, m, y);

    int difference = d1 - d2;
    cout << "Difference between two dates in days: " << difference << endl;

    cout << "Enter the number of days to be added to the first date: ";
    cin >> no_of_days;
    date new_date = d1 + no_of_days;
    cout << "The new date is: " << new_date << endl;

    return 0;
}
