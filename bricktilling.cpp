
#include <iostream>
#include <unordered_map>
#include <string>
#include <sstream>

using namespace std;

const int maxRows = 20;
const int maxCols = 8;

uint8_t setBit(uint8_t num, uint8_t count) {
	return num | (1 << count);
}

bool getBit(uint8_t num, uint8_t i) {
	return ((num & (1 << i)) != 0);
}

uint8_t clearBit(uint8_t num, uint8_t i) {
	uint8_t mask = ~(1 << i);
	return num & mask;
}

struct tile2 {
	int rows[4];
	int cols[4];

	tile2(int row1, int col1, int row2, int col2, int row3, int col3, int row4, int col4) {
		rows[0] = row1;
		cols[0] = col1;

		rows[1] = row2;
		cols[1] = col2;

		rows[2] = row3;
		cols[2] = col3;

		rows[3] = row4;
		cols[3] = col4;
	}

	bool canFit(uint8_t arr[maxRows], int row, int column) {
		bool fit = true;

		for (int i = 0; i < 4; ++i) {
			if (row + rows[i] < 0 || row + rows[i] >= maxRows || column + cols[i] < 0 || column + cols[i] >= maxCols) {
				return false;
			}
			if (getBit(arr[row + rows[i]], column + cols[i]) != false) {
				return false;
			}
		}

		arr[row + rows[0]] = setBit(arr[row + rows[0]], column + cols[0]);
		arr[row + rows[1]] = setBit(arr[row + rows[1]], column + cols[1]);
		arr[row + rows[2]] = setBit(arr[row + rows[2]], column + cols[2]);
		arr[row + rows[3]] = setBit(arr[row + rows[3]], column + cols[3]);
		return true;
	}

	/*void fitTile(uint8_t arr[], int row, int column) {
		arr[row + rows[0]] = setBit(arr[row + rows[0]], column + cols[0]);
		arr[row + rows[1]] = setBit(arr[row + rows[1]], column + cols[1]);
		arr[row + rows[2]] = setBit(arr[row + rows[2]], column + cols[2]);
		arr[row + rows[3]] = setBit(arr[row + rows[3]], column + cols[3]);
	}*/

	void removeTile(uint8_t arr[], int row, int column) {
		arr[row + rows[0]] = clearBit(arr[row + rows[0]], column + cols[0]);
		arr[row + rows[1]] = clearBit(arr[row + rows[1]], column + cols[1]);
		arr[row + rows[2]] = clearBit(arr[row + rows[2]], column + cols[2]);
		arr[row + rows[3]] = clearBit(arr[row + rows[3]], column + cols[3]);
	}
};
int N, M;
tile2 tiles[8] = {
	tile2(0, 0, 1, 0, 2, 0, 2, 1),
	tile2(0, 0, 1, 0, 1, -1, 1, -2),
	tile2(0, 0, 0, 1, 1, 1, 2, 1),
	tile2(0, 0, 1, 0, 0, 1, 0, 2),
	tile2(0, 0, 1, 0, 2, 0, 2, -1),
	tile2(0, 0, 0, 1, 0, 2, 1, 2),
	tile2(0, 0, 0, 1, 1, 0, 2, 0),
	tile2(0, 0, 1, 0, 1, 1, 1, 2)
};

const int tileCount = 8;

/*string getHash(uint8_t *arr) {
	string hash = "";
	for (int i = 0; i < N; ++i) {
		string s = to_string(arr[i]);
		while (s.length() != 3) {
			s = "0" + s;
		}
		hash += s;
	}
	return hash;
}
*/

string getHash(uint8_t *arr) {
	stringstream ss;

	//for (int i = 0; i < N; ++i) {
	ss << hex << arr[0] << arr[1] << arr[2] << arr[3] << arr[4] << arr[5] << arr[6] << arr[7] << arr[8] << arr[9] << arr[10] << arr[11] << arr[12] << arr[13] << arr[14] << arr[15] << arr[16] << arr[17] << arr[18] << arr[19];
	//}
	return ss.str();
}

unordered_map<string, int64_t> cache;

int64_t  getMyCount(uint8_t arr[maxRows], int emptyCount) {
	int64_t count = 0;
	bool empty = false;

	if (emptyCount % 4 != 0) {
		return 0;
	}

	for (int i = 0; i < maxRows; ++i) {
		for (int j = 0; j < maxCols; ++j) {
			if (getBit(arr[i], j) == false) {
				empty = true;
				for (int t = 0; t < 8; ++t) {
					if (tiles[t].canFit(arr, i, j)) {
						//tiles[t].fitTile(arr, i, j);
						int64_t combinations = 0;
						string hash = getHash(arr);
						if (cache.find(hash) != cache.end()) {
							combinations = cache.at(hash);
						}
						else {
							combinations = getMyCount(arr, emptyCount);
							cache[hash] = combinations;
						}

						count += combinations;
						tiles[t].removeTile(arr, i, j);
					}
				}
				return count;
			}
		}
	}
	if (count == 0 && empty == false) {
		return 1;
	}
	return count;
}

int main()
{
	int T;

	cin >> T;
	for (int test = 0; test < T; ++test) {
		uint8_t arr[maxRows]; // = {0};

		for (int i = 0; i < maxRows; ++i) {
			arr[i] = 0;
		}

		//memset(arr, 0, maxRows * sizeof(arr[0]));

		cin >> N;
		cin >> M;
		char  c;
		bool none = true;
		//cache.clear();
		int emptyCount = 0;
		for (int n = 0; n < N; ++n) {
			for (int m = 0; m < M; ++m) {
				cin >> c;
				if (c == '#') {
					arr[n] = setBit(arr[n], m);
				}
				else {
					emptyCount++;
				}
				/*else if (c != '.') {
				--m;
				}*/
			}

			for (int m = M; m < maxCols; ++m) {
				arr[n] = setBit(arr[n], m);
			}
		}

		for (int n = N; n < maxRows; ++n) {
			arr[n] = 255;
		}

		int64_t count = getMyCount(arr, emptyCount);

		cout << count % 1000000007 << "\n";
	}

	return 0;
}