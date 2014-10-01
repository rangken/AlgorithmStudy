#include <stdio.h>
#include <stdlib.h>

//#define STD_INPUT
int main(void){
#ifdef STD_INPUT
  FILE *input = stdin;
#else
  FILE *input = fopen("./sample.txt","r");
#endif
  if(!input){
    perror("fopen fail");
  }
  FILE *output = stdout;

  int max = 100000000;
  int sum = 0;
  for(int i=0; i < max; i++){
    sum ^= i;
  }
  char line[20];
  while ( fgets ( line, sizeof line, input ) != NULL )
  {
    sum ^= atoi(line);
  }

  fprintf(output,"%d\n",sum);
  fclose(input);
  return 0;
}
