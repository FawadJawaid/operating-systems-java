 
/*
  *
  *  MEMORY CLASS DEFINATION
  *
  */
package final2;


/**
 * Coded By: M. Fawad Jawaid Malik (11k-2116) 
             Ali Manzar Jaffery (11k-2202)
 */


public class memory {    //memory class
 static byte mem2[]=new byte[65536]; //total memory of size 64K
 // byte stack[]=new byte[60];  //each stack is of 60 bytes
 static  pagetable memtable = new pagetable(64);
  int pagesize= 1024;
 //PCB pcb=new PCB();
 //int [][] segment = new int[2][2]; //segment table

  memory(){ //constructor
  //for(int i=0; i<65536; i++)//default memory allocation
  // mem2[i]=0;
//  for(int i=0; i<60; i++);
  //stack[i]=0;
  }

  void setmem(int offset,short value){ //setter for memory taking offset and value
      byte Fbyte = getFbyte(value);
      byte Sbyte = getSbyte(value);
      mem2[offset]= Fbyte;
      mem2[++offset]= Sbyte;
  }

  byte getmem(int offset)throws Exception{
  return mem2[offset];
  }

  short getmem4data(int offset){ //getter of memory
      if(offset>65534){
      System.out.println("memory out of bound");
      return 0;
      }
      else
      {
          short temp = Concatenate(mem2[offset],mem2[++offset]);
          System.out.println("concat" + temp);
     return temp;
      }
 }
  byte getmem4code(int offset){ //getter of memory
      if(offset>65536){
      System.out.println("memory out of bound");
      return 0;
      }
      else
      {
     return mem2[offset];

      }
 }
  /*******     Fawad And Ali     ******/

int cload(byte carray[], int csize ){ //load code into memory

    int i = search();

    if(i == -1) { System.out.println("Memory full");}
    else
    {
       // System.out.prinln
        memtable.setf(1, i);
        int j=0;
    for(j= 0; j<csize ; j++) {
       // System.out.println("i :" + i);
        int memind =  i*1024+j;
        mem2[ memind ] = carray[j];
       // System.out.println("mem2[" + memind + "] = " + carray[j]);
      }
    }
    return i;
}

int search() { //chk for empty frames in memory
    int i=0;
    for(i=0;(memtable.getf(i) == 1) && (i< memtable.size) ; i++);
     if(memtable.getf(i) == 0)
         return i;
     else return -1;
 }







byte getFbyte(short value) { //get first 8 bytes of short value
    byte temp = (byte)(value >> 8);
    return temp;
}

byte getSbyte(short value) { //get last 8 bytes of short value
    byte temp = (byte) value;
    return temp;
}

short Concatenate(byte Fbyte,byte Sbyte) {  //concat two bytes into short
    short temp = (short) (Fbyte * 256);
    temp = (short) (temp + Sbyte);
    return temp;
}
};
