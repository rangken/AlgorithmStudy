test_list:
	g++ -O3 -std=c++11 test_list.cpp -o test_list && ./test_list

test_stack:
	g++ test_stack.cpp -o test_stack && ./test_stack

all: test_list test_stack

clean:
	rm -rf test_list test_stack

.PHONY: all test_list test_stack
