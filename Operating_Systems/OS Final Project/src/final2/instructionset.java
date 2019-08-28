/*
 *
 * INSTRUCTION SET CLASS DEFINATION
 */

package final2;

/**
 Coded By: M. Fawad Jawaid Malik (11k-2116) 
             Ali Manzar Jaffery (11k-2202)
 **/

public class instructionset {
byte opcode, reg1,reg2;  // instruction set compoenents
short immediate;

instructionset(){ //constructor
opcode=reg1=reg2=0; //defult value of instruction set
immediate=0;
}

void setopcode(byte op){ //setter for opcode
    opcode=op;
}

void setreg1(byte regs){ //setter for reg1
    reg1=regs;
}

byte getreg1(){ //getter for reg1
   return reg1;
}

byte getreg2(){ //getter for reg2
  return reg2;
}
void setreg2(byte regs){ //setter for reg2
reg2= regs;
}

 void set_immediate(short imme) //sets the immidiate
      {
      immediate=imme;
      }

 void shift_immediate()//shifts the immidiate when it needs to be put on a 8 bbit reg
      {
      immediate=(short) (immediate << 8);
      }

short get_immediate() //getter for immediate value
    {
return immediate;
    }

int check_opcode()// checks opcode so that the appropriate func may be called or for appropriate memory instruction
      {
      if(opcode == 20 || opcode == 24 || opcode == 26 ||opcode == 28 || opcode == 30 || opcode == 32 || opcode == 34 )
      return 1;
      else if (opcode == 21 || opcode == 25 || opcode == 27 || opcode == 29 || opcode == 31 || opcode == 33 || opcode == 35 || opcode == 50 || opcode == 51 || opcode == 52 || opcode == 53 || opcode == 54 || opcode == 64 || opcode == 81)
      return 2;
      else if (opcode == 22 || opcode == 23 )
       return 3;
      else if( opcode == 36 || opcode == 37 || opcode == 38 || opcode == 39 || opcode == 40 || opcode == 41 || opcode == 42 || opcode == 43)
      return 4;
      else if (opcode == 65 || opcode == 47 || opcode == 255)
      return 5;
      else
      return 0;
      }

};
