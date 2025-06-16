#include <iostream>
#include <queue>
#include <vector>
#include<algorithm>
using namespace std;

class Process {
public:
    int id;
    int arrival_time;
    int burst_time;
    int remaining_time;
    int completion_time;
    int turnaround_time;
    int waiting_time;

    Process(int i) {
        id = i;
    }

    void scanProcess() {
        cout << "Enter arrival time for process " << id << ": ";
        cin >> arrival_time;
        cout << "Enter burst time for process " << id << ": ";
        cin >> burst_time;
        remaining_time = burst_time;
    }
};

int main() {
    int n, time_quantum;
    cout << "Enter number of processes: ";
    cin >> n;

    vector<Process> processes;
    for (int i = 0; i < n; ++i) {
        processes.push_back(Process(i));
        processes[i].scanProcess();
    }

    cout << "Enter time quantum: ";
    cin >> time_quantum;

    queue<int> q;
    vector<bool> in_queue(n, false);
    int timer = 0, completed = 0;

    // Sort processes by arrival time
    sort(processes.begin(), processes.end(), [](Process &a, Process &b) {
        return a.arrival_time < b.arrival_time;
    });

    q.push(0);
    in_queue[0] = true;

    while (completed < n) {
        if (q.empty()) {
            // If queue is empty, move timer to next arrival
            for (int i = 0; i < n; i++) {
                if (processes[i].remaining_time > 0) {
                    timer = max(timer, processes[i].arrival_time);
                    q.push(i);
                    in_queue[i] = true;
                    break;
                }
            }
            continue;
        }

        int i = q.front(); q.pop();
        Process &p = processes[i];

        int exec_time = min(time_quantum, p.remaining_time);
        timer = max(timer, p.arrival_time);
        timer += exec_time;
        p.remaining_time -= exec_time;

        // Check for new arrivals during execution
        for (int j = 0; j < n; ++j) {
            if (j != i && !in_queue[j] && processes[j].arrival_time <= timer && processes[j].remaining_time > 0) {
                q.push(j);
                in_queue[j] = true;
            }
        }

        if (p.remaining_time > 0) {
            q.push(i);  // Not yet finished
        } else {
            p.completion_time = timer;
            p.turnaround_time = p.completion_time - p.arrival_time;
            p.waiting_time = p.turnaround_time - p.burst_time;
            completed++;
        }
    }

    // Output results
    cout << "\nProcess\tArrival\tBurst\tCompletion\tTurnaround\tWaiting\n";
    long avgWttime=0l,avgTuraround=0l;
    for (auto &p : processes) {
        cout << "P" << p.id << "\t" << p.arrival_time << "\t" << p.burst_time << "\t"
             << p.completion_time << "\t\t" << p.turnaround_time << "\t\t" << p.waiting_time << endl;
             avgWttime+=p.waiting_time;
             avgTuraround+=p.turnaround_time;
    }
    avgWttime/=n;
    avgTuraround/=n;
    cout<<"Avg waiting time: "<<avgWttime<<endl;
    cout<<"Avg turnaround time "<<avgTuraround<<endl;
    return 0;
}
