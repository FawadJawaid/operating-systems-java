
 /*
  *
  *  REGISTER CLASS DEFINATION
  *
  */
package final2;

/**
 Coded By: M. Fawad Jawaid Malik (11k-2116) 
             Ali Manzar Jaffery (11k-2202)
 **/

public class register {
short general_purpose[] = new short [16]; //16 16-bits general purpose registers
short special_purpose[] = new short [15]; //15 16-bits special purpose registers
char flag_register[] = new char [16]; // flag register




register(){ //contructor
//   short value0=0;
//   for(int i=0; i<16; i++ )
//   general_purpose[i]=value0;  // defualt values of general purpose registers


 //  for(int i=0; i<15; i++ )
 //  special_purpose[i]=value0;  // default values of special purpose registers


//   for(int i=0; i<16; i++)
  //     flag_register[i]=0;


}


void set_spl_reg(short offset, short value){ //setter for special register
special_purpose[offset]=value;
}


void set_gen_reg(short offset, short value){ //setter for general register
general_purpose[offset]=value;
}


 short get_spl_reg(short offset){  //getter for special register
return special_purpose[offset];
}


short get_gen_reg(short offset){  //getter for general register
return general_purpose[offset];
}


boolean get_flag_val(short val){ //getter for flag register
if(flag_register[val]==0)
    return false;
else
    return true;
}


void MOV(short to, short from) //register to register move instruction for general register
         {
              general_purpose[to]=general_purpose[from];
         }


void MOVs(short to, short from) //register to register move instruction for special register
         {
              special_purpose[to]=special_purpose[from];
         }


void ADD(short to, short from) //register-register add instruction for general register
         {
              general_purpose[to]+=general_purpose[from];
         }


void ADDs(short to, short from) //register-register add instruction for special register
         {
              special_purpose[to]+=special_purpose[from];
         }


void SUB(short to, short from)//register-register sub instruction for general register
         {
              general_purpose[to]-=general_purpose[from];
         }


void SUBs(short to, short from)//register-register sub instruction for special register
         {
              special_purpose[to]-=special_purpose[from];
         }


void MUL(short to, short from)//register-register mul instruction for general register
         {
               general_purpose[to]-=general_purpose[from];
         }


void DIV(short to, short from)//register-register div instruction for general register
         {
              if(general_purpose[from] != 0)
              {
                  general_purpose[to] /= general_purpose[from];}
              else
              {
              System.out.println("Invalid Operation");
              }
         }


void AND (short to, short from)//register-register and instruction for general register
         {
              general_purpose[to]&=general_purpose[from];
         }


void ANDs (short to, short from)//register-register and instruction for special register
         {
              special_purpose[to]&=special_purpose[from];
         }


void OR (short to, short from)//register-register or instruction for general register
         {
               general_purpose[to]|=general_purpose[from];
         }


void ORs (short to, short from)//register-register or instruction for special register
         {
               special_purpose[to]|=special_purpose[from];
         }


void MOVI(short to,short number)//register to immidiate move instruction for general register
         {
              general_purpose[to] =(byte) number;
         }


void MOVIS(short to,short number)//register to immidiate move instruction for special register
         {
              special_purpose[to] = (byte) number;
         }




void ADDI(short to,short number)//register to immidiate add instruction for general register
         {
              general_purpose[to] += number;
         }


void ADDIS(short to,short number)//register to immidiate add instruction for special register
         {
              special_purpose[to] += number;
         }


void SUBI(short to,short number)//register to immidiate sub instruction for general register
         {
              general_purpose[to] -= number;
         }


void SUBIS(short to,short number)//register to immidiate sub instruction for special register
         {
              special_purpose[to] -= number;
         }


void MULI(short to,short number)//register to immidiate mul instruction for general register
         {
              general_purpose[to] *= number;
         }




void DIVI(short to,short number)//register to immidiate div instruction for general register
         {
              if (number!=0)
              general_purpose[to] /= number;
              else{
              System.out.println("Invalid Operation ");
              }
         }


void ANDI (short to,short number)//register to immidiate and instruction for general register
         {
              general_purpose[to] &= number;
         }


void ANDIS (short to,short number)//register to immidiate and instruction for special register
         {
              special_purpose[to] &= number;
         }


void ORI (short to,short number)//register to immidiate or  instruction for general register
         {
              general_purpose[to]|= number;
         }


void ORIS (short to,short number)//register to immidiate or  instruction for special register
         {
              special_purpose[to]|= number;
         }




void SHL(short to) //single oprand shifL ins for general register
         {
              general_purpose[to] <<= 1;
         }


void SHLS(short to) //single oprand shifL ins for special register
         {
              special_purpose[to] <<= 1;
         }


void SHR(short to)//single oprand shifR ins for general register
         {
              general_purpose[to] >>= 1;
         }


void SHRS(short to)//single oprand shifR ins for special register
         {
              special_purpose[to] >>= 1;
         }


void RTL(short to)//single oprand rotateL ins for general register
         {
          general_purpose[to] = (short)Integer.rotateLeft(general_purpose[to],1);
         }


void RTLS(short to)//single oprand rotateL ins for special register
         {
          special_purpose[to] = (short)Integer.rotateLeft(special_purpose[to], 1);
         }


void RTR(short to)//single oprand rotateR ins for general register
         {
          general_purpose[to] = (byte) Integer.rotateRight(general_purpose[to], 1);
         }


void RTRS(short to)//single oprand rotateR ins for special register
         {
          special_purpose[to] = (byte) Integer.rotateRight(special_purpose[to], 1);
         }


void INC(short to)//single oprand INCrement ins for general register
         {
             general_purpose[to]++;
         }


void INCS(short to)//single oprand INCrement ins for special register
         {
             special_purpose[to]++;
         }


void DEC(short to)//single oprand decrement ins for general register
         {
             general_purpose[to]--;
         }


void DECS(short to)//single oprand decrement ins for special register
         {
             special_purpose[to]--;
         }


};