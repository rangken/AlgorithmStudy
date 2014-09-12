#include <iostream>
#include "list.hpp"

using namespace std;
using namespace jy;

int main(int argc, char* argv[]){
    cout << "----- list -----" << endl;
    cout << "----- list<int> -----" << endl;
    list<int> t;
    int a = 1;
    int b = 2;
    int c = 3;

    t.push_back(a);
    t.push_back(b);
    t.push_back(c);

    for(list<int>::iterator iter = t.begin(); iter != t.end(); ++iter){
      cout << *iter << endl;
    }

    cout << "----- list<int*> -----" << endl;
    list<int*> t2;
    int *pa = new int();
    *pa = 4;
    int *pb = new int();
    *pb = 5;
    int *pc = new int();
    *pc = 6;

    t2.push_back(pa);
    t2.push_back(pb);
    t2.push_back(pc);
    for(list<int*>::iterator iter = t2.begin(); iter != t2.end(); ++iter){
      cout << **iter << endl;
    }
    return 1;
}

