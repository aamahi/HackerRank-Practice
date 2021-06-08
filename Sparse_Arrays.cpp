#include<bits/stdc++.h>
using namespace std;
/*
    *
    * Prosen Ghosh
    * American International University - Bangladesh (AIUB)
    *
*/
int main(){

    map<string,int>m;
    int n,q;
    string str;
   
    cin >> n;
    for(int i = 0; i < n; i++){
        cin >> str;
        m[str]++;
    }
    cin >> q;
    for(int i = 0; i < q; i++){
        cin >> str;
        cout << m[str] << endl;
    }
    return 0;
}