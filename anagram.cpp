#include <iostream>
#include <string>
#include <cmath>

using namespace std;

int main(){
	int testNum;
	cin >> testNum;
	while(testNum--){
		int alphabet[26];
		int totalShifts = 0;
		string inputString;
		cin >> inputString;
		if(inputString.length() % 2 != 0){
			cout << "-1" << endl;
			continue;
		}
		for(int i = 0; i < 26;i++){
			alphabet[i] = 0;
		}
		for(int i = 0; i < (inputString.length()); i++){
			int insertNum = (inputString[i] - 'a');
			if(i < (inputString.length() / 2)){
				alphabet[insertNum]++;
			} else {
				alphabet[insertNum] = max((alphabet[insertNum] - 1), 0);
			}
			//cout << insertNum << endl;
		}
		for(int i = 0; i < 26;i++){
			totalShifts += abs(alphabet[i]);
		}

		cout << totalShifts << endl;
	}

	return 0;
}