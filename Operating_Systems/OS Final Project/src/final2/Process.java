
/*
 * PROCESS CLASS IMPLEMENTATION
 * 
 */

package final2;
import java.io.*;

/**
 *
 * Coded By: M. Fawad Jawaid Malik (11k-2116) 
             Ali Manzar Jaffery (11k-2202)
 */
public class Process {
   memory mem1; // SHARED MEMORY BY STATIC MEMORY DATA MEMBERS
   int i=0;             // Every time MEMORY new object is declared so it will use share Memory
    PCB pcb;
    static int p=0;
    //

Process() { //constructor
   pcb = new PCB();
   mem1 = new memory();
}

    void LoadProcess(String filename) throws Exception { //process file is loaded
    
    pcb.setpfilename(filename);
     byte code[];
     byte data[]; 

     File file = new File(filename);
     FileInputStream fis = new FileInputStream(file);
     System.out.println("reading process file");
     p++;
     pcb.setpid(p);
     pcb.ppriority =(byte) fis.read(); //priority is read
     byte Fbyte = (byte) fis.read();  // 1st byte of data size is read
     byte Sbyte = (byte) fis.read(); // 2nd byte of code size is read
     /*pcb.reg.set_spl_reg((short)8,Concatenate(Fbyte,Sbyte) );//concat the two bytes and intitalize pcb
     System.out.println("datasize" + pcb.reg.get_spl_reg((byte)8));*/
     pcb.setpsized(Concatenate(Fbyte,Sbyte));
      Fbyte = (byte) fis.read(); // 1st byte of code size is read
     Sbyte = (byte) fis.read(); //2nd byte of code size is read
    /* pcb.reg.set_spl_reg((short)2,  Concatenate(Fbyte,Sbyte));//concat two bytes and initialize pcb
       System.out.println("codesize" + pcb.reg.get_spl_reg((byte)2));*/
       pcb.setpsizec(Concatenate(Fbyte,Sbyte));
     code = new byte[pcb.getcodesize()]; //array for code bytes
     data = new byte[pcb.getdatasize()]; //array for data bytes


     for(int i = 0;i< pcb.getdatasize() ; i++) { //data bytes are read
         data[i+1] = (byte) fis.read();
         data[i] = (byte) fis.read();
         i++;
        }
     for(int i = 0;i< pcb.getcodesize() ; i++) { //code bytes are read
         code[i] = (byte) fis.read();
        }
     // Paged Segmentation is done Here
     int no_of_code_pages = pcb.getcodesize() / 1024;
     int no_of_data_pages = pcb.getdatasize() / 1024;
     int temp = no_of_code_pages;
     byte pagechunk[] = new byte[1024];
     int i;
     int curpageno=0;

     // Entrieng Code
     while(temp > 0) {
        for(i=0; ((1024 * curpageno) + i) < pcb.getcodesize() && i<1024 ; i++){
            pagechunk[i] = code[i +(1024 * curpageno)];

         }
      int frame = mem1.cload(pagechunk, 1024);
      pcb.codept.setf(frame, curpageno);
      ++curpageno;
      temp--;
      }

     //Entering Data
     temp= no_of_data_pages;

       i=0;
       curpageno=0;
     while(temp > 0) {
        for(i=0;(i +(1024 * curpageno)) < pcb.getdatasize() && i<1024 ; i++){
            pagechunk[i] = data[i +(1024 * curpageno)];
         }
      int frame = mem1.cload(pagechunk, 1024);
      pcb.datapt.setf(frame, curpageno);
      curpageno++;
      temp--;
      }
       code = null;
       data = null;
 System.out.println("readed");
        //pcb.set_cpt(curpageno, curpageno);

}

   /**** Ali ****/

    byte getCode(short pc) { //getting code from physical memory by logical address
    short p = (short) (pc / 1024);
    short d = (short) (pc % 1024);
    int frame = pcb.codept.getf(p);
    //System.out.println("p:" + p + " d :" + d + "frame :" + frame);
   return  mem1.mem2[(frame * 1024) + d];
  }

    short getData(short offset) {  //getting data from physical memory by logical address
    short p = (short) (offset / 1024);
    short d = (short) (offset % 1024);
    int frame = pcb.datapt.getf(p);
    byte Fbyte = mem1.mem2[(frame * 1024) + d];
    byte Sbyte = mem1.mem2[(frame * 1024) + d+1];
    return mem1.Concatenate(Fbyte, Sbyte);
  }
  void setData(short offset,short value){ //setting data in phyical memory with the help of logical address
      short p = (short) (offset / 1024);
      short d = (short) (offset % 1024);
      int frame = pcb.datapt.getf(p);
      byte Fbyte = mem1.getFbyte(value);
      byte Sbyte = mem1.getSbyte(value);
      mem1.mem2[(frame * 1024) + d] = Fbyte;
      mem1.mem2[(frame * 1024) + d+1] = Sbyte;
  }

  /**** Fawad ******/

  byte getFbyte(short value) { //get first 8 bytes of short value
    byte temp = (byte)(value >> 8);
    return temp;
}

byte getSbyte(short value) { //get last 8 bytes of short value
    byte temp = (byte) value;
    return temp;
}

short Concatenate(byte Fbyte,byte Sbyte) { //concat two bytes into short
    short temp = (short) (Fbyte * 256);
    temp = (short) (temp + Sbyte);
    return temp;
}
}


