#include <stdio.h>
#include <stdlib.h>

int main(){
  FILE *input = stdin;
  FILE *output = stdout;
  int max = 100000000;
  int v = rand() % max;
  for(int i=0; i < max; i++){
    if( i != v){
      fprintf(output, "%d\n",  i);
    }
  }
  return 0;
}
